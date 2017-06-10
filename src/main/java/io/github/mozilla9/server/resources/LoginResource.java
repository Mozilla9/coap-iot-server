/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.server.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;
import io.github.mozilla9.dao.DeviceConfigDAO;
import io.github.mozilla9.dao.DeviceLoginDAO;
import io.github.mozilla9.dao.FirmwareDataDAO;
import io.github.mozilla9.dao.IotDeviceDAO;
import io.github.mozilla9.entities.DeviceConfig;
import io.github.mozilla9.entities.DeviceLogin;
import io.github.mozilla9.entities.FirmwareData;
import io.github.mozilla9.entities.IotDevice;
import io.github.mozilla9.server.client.ClientLoginPayloadParser;
import io.github.mozilla9.server.client.ParserHelper;
import io.github.mozilla9.server.data.ExchangeParameters;
import io.github.mozilla9.server.session.CoapSession;
import io.github.mozilla9.server.session.CoapSessionKey;
import io.github.mozilla9.server.session.SessionHolder;

public class LoginResource extends CoapResource {

    private SessionHolder sessionHolder;

    public LoginResource(SessionHolder sessionHolder) {

        super("login");
        getAttributes().setTitle("Login Resource");
        this.sessionHolder = sessionHolder;
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        DeviceLoginDAO loginDAO = new DeviceLoginDAO();
        ClientLoginPayloadParser parser = new ClientLoginPayloadParser(exchange.getRequestPayload());

        DeviceLogin login = parser.assembleDeviceLogin();
        IotDevice device = loadDeviceOrCreateNewIfNeeded(parser.getDeviceSerialNumber(), login);

        try {
            login.setOwner(device);
            loginDAO.insert(login);

            ExchangeParameters params = formParameters(login);

            // create session
            CoapSession session = sessionHolder.createSession(parser.getDeviceSerialNumber());
            session.putProperty(CoapSessionKey.EXCHANGE_PARAMS, params);

            // respond to the request
            exchange.setETag(ParserHelper.encodeETag(session.getToken()));
            exchange.respond(CoAP.ResponseCode.VALID, params.toArray(), MediaTypeRegistry.APPLICATION_OCTET_STREAM);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleGET(CoapExchange exchange) {
        exchange.respond(CoAP.ResponseCode.METHOD_NOT_ALLOWED);
    }

    private ExchangeParameters formParameters(DeviceLogin login) {

        IotDeviceDAO iotDeviceDAO = new IotDeviceDAO();
        ExchangeParameters params = new ExchangeParameters();

        try {
            IotDevice device = iotDeviceDAO.select(login.getOwner().getId());

            if (!login.getConfigToken().equals(device.getConfig().getToken())) {
                params.setNewConfiguration(true);
            }

            // firmware
            if (!login.getFirmwareToken().equals(device.getFirmware().getToken())) {
                params.setNewFirmware(true);
            }

            params.setCurrentData(true);
            params.setArchiveData(true);
            params.setLogData(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return params;
    }

    private IotDevice loadDeviceOrCreateNewIfNeeded(Integer serialNumber, DeviceLogin login) {
        IotDeviceDAO iotDeviceDAO = new IotDeviceDAO();
        FirmwareDataDAO firmwareDAO = new FirmwareDataDAO();
        DeviceConfigDAO configDAO = new DeviceConfigDAO();

        IotDevice device = null;

        try {
            device = iotDeviceDAO.select(serialNumber);

            if (device == null) {
                DeviceConfig config = configDAO.selectByToken(login.getConfigToken());
                FirmwareData firmwareData = firmwareDAO.selectByToken(login.getFirmwareToken());

                device = new IotDevice();

                device.setId(serialNumber);
                device.setConfig(config);
                device.setFirmware(firmwareData);

                iotDeviceDAO.insert(device);
                device = iotDeviceDAO.select(serialNumber);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return device;
    }
}
