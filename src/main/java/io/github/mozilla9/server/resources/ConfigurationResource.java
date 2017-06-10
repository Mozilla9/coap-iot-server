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
import io.github.mozilla9.dao.IotDeviceDAO;
import io.github.mozilla9.entities.IotDevice;
import io.github.mozilla9.server.client.ParserHelper;
import io.github.mozilla9.server.data.ExchangeParameters;
import io.github.mozilla9.server.session.CoapSession;
import io.github.mozilla9.server.session.CoapSessionKey;
import io.github.mozilla9.server.session.SessionHolder;

public class ConfigurationResource extends CoapResource {

    private SessionHolder sessionHolder;

    public ConfigurationResource(SessionHolder sessionHolder) {

        super("config");
        getAttributes().setTitle("Configuration Resource");
        this.sessionHolder = sessionHolder;
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        if (exchange.getRequestOptions().getETagCount() == 1) {

            Integer token = ParserHelper.decodeETag(exchange.getRequestOptions().getETags().get(0));
            CoapSession session = sessionHolder.getSession(token);

            if (session != null) {
                ExchangeParameters params = (ExchangeParameters) session.getProperty(CoapSessionKey.EXCHANGE_PARAMS);

                if (params.isNewConfiguration()) {

                    IotDeviceDAO iotDeviceDAO = new IotDeviceDAO();

                    try {
                        IotDevice device = iotDeviceDAO.select(session.getSerialNumber());

                        // respond to the request
                        exchange.setETag(ParserHelper.encodeETag(token));
                        exchange.respond(CoAP.ResponseCode.CONTENT, device.getConfig().toArray(),
                                MediaTypeRegistry.APPLICATION_OCTET_STREAM);

                        return;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // request doesn't contain etag
        exchange.respond(CoAP.ResponseCode.UNAUTHORIZED);
    }

    public void handlePOST(CoapExchange exchange) {
        exchange.respond(CoAP.ResponseCode.METHOD_NOT_ALLOWED);
    }
}
