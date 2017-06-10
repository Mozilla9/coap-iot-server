/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.dao;

import org.apache.ibatis.session.SqlSession;
import io.github.mozilla9.data.DBConnectorSingleton;
import io.github.mozilla9.data.mappers.DeviceConfigMapper;
import io.github.mozilla9.entities.DeviceConfig;

import java.util.List;

public class DeviceConfigDAO implements ICommonDAO<DeviceConfig, Integer> {

    public void insert(DeviceConfig object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DeviceConfigMapper mapper = sqlSession.getMapper(DeviceConfigMapper.class);

            mapper.insert(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public DeviceConfig select(Integer id) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DeviceConfigMapper mapper = sqlSession.getMapper(DeviceConfigMapper.class);

            return mapper.select(id);
        } finally {
            sqlSession.close();
        }
    }

    public DeviceConfig selectByToken(Integer token) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DeviceConfigMapper mapper = sqlSession.getMapper(DeviceConfigMapper.class);

            return mapper.selectByToken(token);
        } finally {
            sqlSession.close();
        }
    }

    public List<DeviceConfig> selectAll() throws Exception {
        throw new Exception("Method not implemented");
    }

    public void update(DeviceConfig object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DeviceConfigMapper mapper = sqlSession.getMapper(DeviceConfigMapper.class);

            mapper.update(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public void delete(DeviceConfig object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DeviceConfigMapper mapper = sqlSession.getMapper(DeviceConfigMapper.class);

            mapper.delete(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
