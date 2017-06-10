/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.entities;


public class DeviceLogin extends BaseEntity {

    private static final long serialVersionUID = 736149005601380392L;

    private Integer deviceId;

    private Integer configToken;

    private Integer firmwareToken;

    private Double signalQuality;

    private Double batteryVoltage;

    private Double batteryVoltageMin;

    private String rawData;

    private IotDevice owner;

    public Integer getConfigToken() {
        return configToken;
    }

    public void setConfigToken(Integer configToken) {
        this.configToken = configToken;
    }

    public Integer getFirmwareToken() {
        return firmwareToken;
    }

    public void setFirmwareToken(Integer firmwareToken) {
        this.firmwareToken = firmwareToken;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Double getSignalQuality() {
        return signalQuality;
    }

    public void setSignalQuality(Double signalQuality) {
        this.signalQuality = signalQuality;
    }

    public Double getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(Double batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public Double getBatteryVoltageMin() {
        return batteryVoltageMin;
    }

    public void setBatteryVoltageMin(Double batteryVoltageMin) {
        this.batteryVoltageMin = batteryVoltageMin;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public IotDevice getOwner() {
        return owner;
    }

    public void setOwner(IotDevice owner) {
        this.owner = owner;
    }
}
