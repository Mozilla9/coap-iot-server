/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.entities;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class DeviceConfig extends BaseEntity {

    private static final long serialVersionUID = 3262360032187609169L;

    private Integer token;

    private Integer freqOfSendingData;

    private Integer durationOfSession;

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public Integer getFreqOfSendingData() {
        return freqOfSendingData;
    }

    public void setFreqOfSendingData(Integer freqOfSendingData) {
        this.freqOfSendingData = freqOfSendingData;
    }

    public Integer getDurationOfSession() {
        return durationOfSession;
    }

    public void setDurationOfSession(Integer durationOfSession) {
        this.durationOfSession = durationOfSession;
    }

    public byte[] toArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(3 * Integer.BYTES);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

        byteBuffer.putInt(token);
        byteBuffer.putInt(freqOfSendingData);
        byteBuffer.putInt(durationOfSession);

        return byteBuffer.array();
    }
}
