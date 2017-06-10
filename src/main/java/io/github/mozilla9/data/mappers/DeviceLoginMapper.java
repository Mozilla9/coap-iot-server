/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.data.mappers;

import org.apache.ibatis.annotations.Param;
import io.github.mozilla9.entities.DeviceLogin;
import io.github.mozilla9.entities.IotDevice;

import java.util.List;

public interface DeviceLoginMapper {

    void insert(@Param("login") DeviceLogin login);

    List<DeviceLogin> selectAll(@Param("device") IotDevice device);

    void update(@Param("login") DeviceLogin login);

    void delete(@Param("login") DeviceLogin login);
}
