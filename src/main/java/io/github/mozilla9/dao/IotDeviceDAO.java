/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.dao;

import org.apache.ibatis.session.SqlSession;
import io.github.mozilla9.data.DBConnectorSingleton;
import io.github.mozilla9.data.mappers.IotDeviceMapper;
import io.github.mozilla9.entities.IotDevice;

import java.util.List;

public class IotDeviceDAO implements ICommonDAO<IotDevice, Integer> {

    public void insert(IotDevice object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IotDeviceMapper mapper = sqlSession.getMapper(IotDeviceMapper.class);

            mapper.insert(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public IotDevice select(Integer id) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IotDeviceMapper mapper = sqlSession.getMapper(IotDeviceMapper.class);

            return mapper.select(id);
        } finally {
            sqlSession.close();
        }
    }

    public List<IotDevice> selectAll() throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IotDeviceMapper mapper = sqlSession.getMapper(IotDeviceMapper.class);

            return mapper.selectAll();
        } finally {
            sqlSession.close();
        }
    }

    public void update(IotDevice object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IotDeviceMapper mapper = sqlSession.getMapper(IotDeviceMapper.class);

            mapper.update(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public void delete(IotDevice object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IotDeviceMapper mapper = sqlSession.getMapper(IotDeviceMapper.class);

            mapper.delete(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
