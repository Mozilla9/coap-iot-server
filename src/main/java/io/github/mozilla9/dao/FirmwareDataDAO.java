/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.dao;

import org.apache.ibatis.session.SqlSession;
import io.github.mozilla9.data.DBConnectorSingleton;
import io.github.mozilla9.data.mappers.FirmwareDataMapper;
import io.github.mozilla9.entities.FirmwareData;

import java.util.List;


public class FirmwareDataDAO implements ICommonDAO<FirmwareData, Integer> {

    public void insert(FirmwareData object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            FirmwareDataMapper mapper = sqlSession.getMapper(FirmwareDataMapper.class);

            mapper.insert(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public FirmwareData select(Integer id) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            FirmwareDataMapper mapper = sqlSession.getMapper(FirmwareDataMapper.class);

            return mapper.select(id);
        } finally {
            sqlSession.close();
        }
    }

    public FirmwareData selectByToken(Integer token) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            FirmwareDataMapper mapper = sqlSession.getMapper(FirmwareDataMapper.class);

            return mapper.selectByToken(token);
        } finally {
            sqlSession.close();
        }
    }

    public List<FirmwareData> selectAll() throws Exception {
        throw new Exception("Method not implemented");
    }

    public void update(FirmwareData object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            FirmwareDataMapper mapper = sqlSession.getMapper(FirmwareDataMapper.class);

            mapper.update(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public void delete(FirmwareData object) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            FirmwareDataMapper mapper = sqlSession.getMapper(FirmwareDataMapper.class);

            mapper.delete(object);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}
