/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.server.session;

public enum CoapSessionKey {

    EXCHANGE_PARAMS {
        public String getDescription() {
            return "EXCHANGE_PARAMS";
        }
    }
}
