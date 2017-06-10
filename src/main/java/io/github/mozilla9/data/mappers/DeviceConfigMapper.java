/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.data.mappers;


import org.apache.ibatis.annotations.Param;
import io.github.mozilla9.entities.DeviceConfig;


public interface DeviceConfigMapper {

    void insert(@Param("config") DeviceConfig config);

    DeviceConfig select(@Param("id") Integer id);

    DeviceConfig selectByToken(@Param("token") Integer token);

    void update(@Param("config") DeviceConfig config);

    void delete(@Param("config") DeviceConfig config);

}
