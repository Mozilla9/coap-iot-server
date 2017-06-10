/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9;

import org.apache.ibatis.io.Resources;
import io.github.mozilla9.server.IotCoapServer;

import java.io.*;
import java.net.SocketException;
import java.util.Map;
import java.util.Properties;


public class MainApp {

    private static void loadSystemProperties() {

        try {
            String derbyProperties = "mybatis/derby/derby.properties";
            InputStream inputStream = Resources.getResourceAsStream(derbyProperties);

            Properties props = new Properties();

            props.load(inputStream);

            for (Map.Entry<Object, Object> p : props.entrySet()) {
                String k = p.getKey().toString();
                String v = p.getValue().toString();

                System.setProperty(k, v);
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {

            MainApp.loadSystemProperties();

            // create server
            IotCoapServer server = new IotCoapServer();
            server.start();

        } catch (SocketException e) {
            System.err.println("Failed to initialize server: " + e.getMessage());
        }
    }
}
