/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.server.client;

import io.github.mozilla9.entities.DeviceData;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;


public class ClientDataPayloadParser {

    private ByteBuffer byteBuffer;

    public ClientDataPayloadParser(byte[] payload) {
        this.byteBuffer = ByteBuffer.wrap(payload);
        this.byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public DeviceData assembleDeviceData() {
        DeviceData data = new DeviceData();

        byteBuffer.position(0);

        Date dateStamp = new Date(byteBuffer.getInt());
        data.setDatestamp(dateStamp);

        data.setAlarmMask(0);

        data.setRawData(ParserHelper.serializeRawDataToHexString(byteBuffer.array()));

        return data;
    }
}
