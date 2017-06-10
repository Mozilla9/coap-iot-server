/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.entities;

import java.util.Date;

public class DeviceData extends BaseEntity {

    private static final long serialVersionUID = -5377101123916614863L;

    private Integer deviceId;

    private Date datestamp;

    private Integer alarmMask;

    private String rawData;

    private IotDevice owner;

    public Integer getAlarmMask() {
        return alarmMask;
    }

    public void setAlarmMask(Integer alarmMask) {
        this.alarmMask = alarmMask;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public Date getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Date datestamp) {
        this.datestamp = datestamp;
    }

    public IotDevice getOwner() {
        return owner;
    }

    public void setOwner(IotDevice owner) {
        this.owner = owner;
    }
}
