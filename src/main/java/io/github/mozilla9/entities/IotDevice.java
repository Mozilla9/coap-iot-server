/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */

package io.github.mozilla9.entities;

import java.util.List;

public class IotDevice extends BaseEntity {

    private static final long serialVersionUID = -2771327209421141516L;

    private String address;

    private Double latitude;

    private Double longitude;

    private DeviceConfig config;

    private FirmwareData firmware;

    private List<DeviceData> data;

    public DeviceConfig getConfig() {
        return config;
    }

    public void setConfig(DeviceConfig config) {
        this.config = config;
    }

    public List<DeviceData> getData() {
        return data;
    }

    public void setData(List<DeviceData> data) {
        this.data = data;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public FirmwareData getFirmware() {
        return firmware;
    }

    public void setFirmware(FirmwareData firmware) {
        this.firmware = firmware;
    }
}
