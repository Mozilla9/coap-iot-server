/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.data.mappers;

import org.apache.ibatis.annotations.Param;
import io.github.mozilla9.entities.DeviceData;
import io.github.mozilla9.entities.IotDevice;

import java.util.List;

public interface DeviceDataMapper {

    void insert(@Param("data") DeviceData data);

    List<DeviceData> selectAll(@Param("device") IotDevice device);

    void update(@Param("data") DeviceData data);

    void delete(@Param("data") DeviceData data);
}
