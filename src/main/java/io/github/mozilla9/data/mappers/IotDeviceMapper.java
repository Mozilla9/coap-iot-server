/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.data.mappers;

import org.apache.ibatis.annotations.Param;
import io.github.mozilla9.entities.IotDevice;

import java.util.List;

public interface IotDeviceMapper {

    void insert(@Param("device") IotDevice device);

    IotDevice select(@Param("id") Integer id);

    List<IotDevice> selectAll();

    void update(@Param("device") IotDevice device);

    void delete(@Param("device") IotDevice device);
}
