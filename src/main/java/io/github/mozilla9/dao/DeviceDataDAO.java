/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.dao;

import org.apache.ibatis.session.SqlSession;
import io.github.mozilla9.data.DBConnectorSingleton;
import io.github.mozilla9.data.mappers.DeviceDataMapper;
import io.github.mozilla9.entities.DeviceData;
import io.github.mozilla9.entities.IotDevice;

import java.util.List;

public class DeviceDataDAO implements ICommonDAO<DeviceData, Integer> {

    public void insert(DeviceData object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DeviceDataMapper mapper = sqlSession.getMapper(DeviceDataMapper.class);

            mapper.insert(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public DeviceData select(Integer id) throws Exception {
        throw new Exception("Method not implemented");
    }

    public List<DeviceData> selectAll() throws Exception {
        throw new Exception("Method not implemented");
    }

    public List<DeviceData> selectAllByDevice(IotDevice device) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DeviceDataMapper mapper = sqlSession.getMapper(DeviceDataMapper.class);

            return mapper.selectAll(device);
        } finally {
            sqlSession.close();
        }
    }

    public void update(DeviceData object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DeviceDataMapper mapper = sqlSession.getMapper(DeviceDataMapper.class);

            mapper.update(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public void delete(DeviceData object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DeviceDataMapper mapper = sqlSession.getMapper(DeviceDataMapper.class);

            mapper.delete(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
