/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.server.client;

import io.github.mozilla9.entities.DeviceLogin;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class ClientLoginPayloadParser {

    private ByteBuffer byteBuffer;

    public ClientLoginPayloadParser(byte[] payload) {
        this.byteBuffer = ByteBuffer.wrap(payload);
        this.byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public Integer getDeviceSerialNumber() {
        return byteBuffer.getInt(0);
    }

    public DeviceLogin assembleDeviceLogin() {
        ParserHelper helper = new ParserHelper();
        DeviceLogin login = new DeviceLogin();

        byteBuffer.position(4);

        login.setFirmwareToken(byteBuffer.getInt());

        login.setConfigToken(byteBuffer.getInt());

        login.setBatteryVoltageMin(helper.extractBatteryVoltage(byteBuffer.getShort()));

        login.setBatteryVoltage(helper.extractBatteryVoltage(byteBuffer.getShort()));

        login.setSignalQuality(helper.extractSignalQuality(byteBuffer.getShort()));

        login.setRawData(ParserHelper.serializeRawDataToHexString(byteBuffer.array()));

        return login;
    }
}
