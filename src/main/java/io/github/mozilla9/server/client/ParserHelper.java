/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.server.client;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ParserHelper {

    public static String serializeRawDataToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);

        for(byte b: data) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static Integer decodeETag(byte[] data) {

        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);

        return byteBuffer.getInt();
    }

    public static byte[] encodeETag(Integer etag) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(etag);

        return byteBuffer.array();
    }

    public double extractBatteryVoltage(short rawValue) {
        /*
            value = raw / 100
         */

        return (1.0 * rawValue) / 100.0;
    }

    public double extractSignalQuality(short rawValue) {
        /*
            CSQ         RSSI (dBm)
            0           -113 or less
            1           -111
            2 thru 30   -109 to -53
            31          -51 or greater
            99          not present or not measurable

            dBm = -113 + N * 2 (where N is the returned value).
         */

        if (rawValue < 32) {
            return -113.0 + 2.0 * rawValue;
        } else {
            return -113.0;
        }
    }
}
