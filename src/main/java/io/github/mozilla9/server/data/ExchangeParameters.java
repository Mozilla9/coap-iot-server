/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.server.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ExchangeParameters {

    private static short defaultDataDotsPerSession = 100;
    private static short defaultLogDotsPerSession = 100;

    private boolean currentData = true;

    private boolean archiveData = true;

    private boolean logData = true;

    private boolean newConfiguration = false;

    private boolean newFirmware = false;

    private short dataDotsPerSession = defaultDataDotsPerSession;

    private short logDotsPerSession = defaultLogDotsPerSession;

    private enum BitMask {

        SEND_CURRENT_DATA(1),
        GET_NEW_CONFIGURATION(2),
        SEND_ARCHIVE_DATA(4),
        SEND_LOG_DATA(8),
        GET_NEW_FIRMWARE(16);

        private final int number;

        BitMask(int number) {
            this.number = number;
        }
    }

    public byte[] toArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

        byteBuffer.putInt(getBitMaskOfOptions());
        byteBuffer.putShort(dataDotsPerSession);
        byteBuffer.putShort(logDotsPerSession);

        return byteBuffer.array();
    }

    private int getBitMaskOfOptions() {
        int bitMask = 0;

        if (currentData) {
            bitMask |= BitMask.SEND_CURRENT_DATA.number;
        }

        if (newConfiguration) {
            bitMask |= BitMask.GET_NEW_CONFIGURATION.number;
        }

        if (archiveData) {
            bitMask |= BitMask.SEND_ARCHIVE_DATA.number;
        }

        if (logData) {
            bitMask |= BitMask.SEND_LOG_DATA.number;
        }

        if (newFirmware) {
            bitMask |= BitMask.GET_NEW_FIRMWARE.number;
        }

        return bitMask;
    }

    public boolean isCurrentData() {
        return currentData;
    }

    public void setCurrentData(boolean currentData) {
        this.currentData = currentData;
    }

    public boolean isArchiveData() {
        return archiveData;
    }

    public void setArchiveData(boolean archiveData) {
        this.archiveData = archiveData;
    }

    public boolean isLogData() {
        return logData;
    }

    public void setLogData(boolean logData) {
        this.logData = logData;
    }

    public boolean isNewConfiguration() {
        return newConfiguration;
    }

    public void setNewConfiguration(boolean newConfiguration) {
        this.newConfiguration = newConfiguration;
    }

    public boolean isNewFirmware() {
        return newFirmware;
    }

    public void setNewFirmware(boolean newFirmware) {
        this.newFirmware = newFirmware;
    }

    public short getDataDotsPerSession() {
        return dataDotsPerSession;
    }

    public void setDataDotsPerSession(short dataDotsPerSession) {
        this.dataDotsPerSession = dataDotsPerSession;
    }

    public short getLogDotsPerSession() {
        return logDotsPerSession;
    }

    public void setLogDotsPerSession(short logDotsPerSession) {
        this.logDotsPerSession = logDotsPerSession;
    }
}
