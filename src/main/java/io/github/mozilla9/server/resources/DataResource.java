/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.server.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import io.github.mozilla9.dao.DeviceDataDAO;
import io.github.mozilla9.dao.IotDeviceDAO;
import io.github.mozilla9.entities.DeviceData;
import io.github.mozilla9.entities.IotDevice;
import io.github.mozilla9.server.client.ClientDataPayloadParser;
import io.github.mozilla9.server.client.ParserHelper;
import io.github.mozilla9.server.session.CoapSession;
import io.github.mozilla9.server.session.SessionHolder;


public class DataResource extends CoapResource {

    private SessionHolder sessionHolder;

    public DataResource(SessionHolder sessionHolder) {

        super("data");
        getAttributes().setTitle("Data Resource");
        this.sessionHolder = sessionHolder;
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(CoAP.ResponseCode.METHOD_NOT_ALLOWED);
    }

    public void handlePOST(CoapExchange exchange) {
        ClientDataPayloadParser parser = new ClientDataPayloadParser(exchange.getRequestPayload());
        DeviceData data = parser.assembleDeviceData();

        if (exchange.getRequestOptions().getETagCount() > 0) {
            Integer token = ParserHelper.decodeETag(exchange.getRequestOptions().getETags().get(0));
            CoapSession session = sessionHolder.getSession(token);

            if (session != null) {
                storeData(data, session);
                exchange.respond(CoAP.ResponseCode.CREATED);

                return;
            }
        }

        exchange.respond(CoAP.ResponseCode.UNAUTHORIZED);
    }

    private void storeData(DeviceData data, CoapSession session) {
        IotDeviceDAO iotDeviceDAO = new IotDeviceDAO();
        DeviceDataDAO dataDAO = new DeviceDataDAO();

        try {
            IotDevice device = iotDeviceDAO.select(session.getSerialNumber());

            data.setOwner(device);
            dataDAO.insert(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}