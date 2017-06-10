/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.dao;

import java.util.List;

public interface ICommonDAO <T, K> {

    /**
     * Insert an object into DB
     * @param object instance of type T
     * @throws Exception
     */
    public void insert(T object) throws Exception;

    /**
     * Select an object of type T by primary key
     * @param id primary key
     * @return fetched object of type T
     * @throws Exception
     */
    public T select(K id) throws Exception;

    /**
     * Select all objects of type T
     * @return list of fetched objects
     * @throws Exception
     */
    public List<T> selectAll() throws Exception;

    /**
     * Update an object of type T
     * @param object instance of type T
     * @throws Exception
     */
    public void update(T object) throws Exception;

    /**
     * Delete an object type of T
     * @param object instance of type T
     * @throws Exception
     */
    public void delete(T object) throws Exception;
}
