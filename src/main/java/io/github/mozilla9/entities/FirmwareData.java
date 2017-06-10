/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.entities;

public class FirmwareData extends BaseEntity {

    private static final long serialVersionUID = -8517295775985444231L;

    private Integer token;

    private String version;

    private String description;

    private String fileUrl;

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
