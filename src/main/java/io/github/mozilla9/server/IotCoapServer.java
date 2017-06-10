/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */

package io.github.mozilla9.server;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.network.interceptors.MessageTracer;
import org.eclipse.californium.core.network.interceptors.OriginTracer;
import org.eclipse.californium.elements.Connector;
import org.eclipse.californium.elements.tcp.TcpServerConnector;
import io.github.mozilla9.server.resources.*;
import io.github.mozilla9.server.session.SessionHolder;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;


/**
 * Tutorial
 * https://docs.google.com/presentation/d/1dDZ7VTdjBZxnqcIt6qoX742d6dHbzap-D_H8Frf3LRE/edit#slide=id.g38a4dd9e2_0139
 *
 *
 */
public class IotCoapServer extends CoapServer {

    private SessionHolder sessionHolder = new SessionHolder(300L * 1000L);
    private static final int COAP_PORT = NetworkConfig.getStandard().getInt(NetworkConfig.Keys.COAP_PORT);

    public IotCoapServer() throws SocketException {

        add(new DataResource(sessionHolder));
        add(new LoginResource(sessionHolder));
        add(new ConfigurationResource(sessionHolder));

        addEndpoints();
    }

    private static Connector createTcpConnector(final InetSocketAddress address, final NetworkConfig config) {
        return new TcpServerConnector(address,
                config.getInt(NetworkConfig.Keys.TCP_CONNECTION_IDLE_TIMEOUT),
                config.getInt(NetworkConfig.Keys.TCP_WORKER_THREADS));
    }

    private void addEndpoints() {
        NetworkConfig.getStandard();

        for (InetAddress addr : EndpointManager.getEndpointManager().getNetworkInterfaces()) {
            // only binds to IPv4 addresses and localhost
            if (addr instanceof Inet4Address || addr.isLoopbackAddress()) {

                InetSocketAddress bindToAddress = new InetSocketAddress(addr, COAP_PORT);
                CoapEndpoint ep = new CoapEndpoint(bindToAddress);
                //CoapEndpoint ep = new CoapEndpoint(createTcpConnector(bindToAddress, NetworkConfig.getStandard()), NetworkConfig.getStandard());

                ep.addInterceptor(new MessageTracer());
                ep.addInterceptor(new OriginTracer());

                addEndpoint(ep);
            }
        }
    }
}
