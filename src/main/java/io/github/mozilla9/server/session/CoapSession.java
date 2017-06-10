/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.server.session;

import java.util.Date;
import java.util.HashMap;

public class CoapSession {

    private Integer token = -1;

    private Integer serialNumber = -1;

    private Date createTs = new Date();

    private Date updateTs = new Date();

    private HashMap<CoapSessionKey, Object> store = new HashMap<CoapSessionKey, Object>();

    CoapSession(Integer token, Integer serialNumber) {
        this.token = token;
        this.serialNumber = serialNumber;
    }

    public Integer getToken() {
        return token;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public void putProperty(CoapSessionKey key, Object value) {
        store.put(key, value);
    }

    public Object getProperty(CoapSessionKey key) {
        return store.get(key);
    }
}
