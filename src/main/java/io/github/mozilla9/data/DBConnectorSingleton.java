/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.data;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class DBConnectorSingleton {

    private static volatile DBConnectorSingleton instance;

    private SqlSessionFactory sessionFactory;

    public static DBConnectorSingleton getInstance() {

        DBConnectorSingleton localInstance = instance;

        if (localInstance == null) {

            synchronized (DBConnectorSingleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    try {
                        instance = localInstance = new DBConnectorSingleton();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    private DBConnectorSingleton() throws IOException {

        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
