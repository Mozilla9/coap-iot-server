/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.data.mappers;

import org.apache.ibatis.annotations.Param;
import io.github.mozilla9.entities.FirmwareData;

public interface FirmwareDataMapper {

    void insert(@Param("fw") FirmwareData fw);

    FirmwareData select(@Param("id") Integer id);

    FirmwareData selectByToken(@Param("token") Integer token);

    void update(@Param("fw") FirmwareData fw);

    void delete(@Param("fw") FirmwareData fw);
}
