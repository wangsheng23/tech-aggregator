/**
 *
 */
package com.wsheng.aggregator.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wsheng.aggregator.bean.DynamicQueryParam;
import com.wsheng.aggregator.bean.QueryParam;

/**
 * This interface used for MyBatis CRUD operations
 *
 * @author Josh Wang(Sheng)
 */
public interface IMBaseDao {

    /**
     * Load the result when there is no parameter
     *
     * @param <T>
     * @param queryId
     * @param queryParam
     * @return
     */
    public <T> T loadByQuery(String queryId);

    /**
     * Load the result when there are more than 1 query parameter
     *
     * @param <T>
     * @param queryId
     * @param queryParam
     * @return
     */
    public <T> T loadByQuery(String queryId, QueryParam queryParam);

    /**
     * Load the result when there is only 1 query parameter
     *
     * @param <T>
     * @param queryId
     * @param queryParam
     * @return
     */
    public <T> T loadByQuery(String queryId, String queryParam);

    /**
     * List the results when there is no any query parameters
     *
     * @param <T>
     * @param queryId
     * @return
     */
    public <T> List<T> listByQuery(String queryId);

    /**
     * List the results when there is only 1 parameter
     *
     * @param <T>
     * @param queryId
     * @param queryParam
     * @return
     */
    public <T> List<T> listByQuery(String queryId, String queryParam);

    /**
     * List the results when there are more than 1 query parameter
     *
     * @param <T>
     * @param queryId
     * @param queryParam
     * @return
     */
    public <T> List<T> listByQuery(String queryId, QueryParam queryParam);

    /**
     * List the results according to the fetch size
     * <p>we have not use the interface now, because seems there is no any way to define the "fetch size" dynamically</p>
     *
     * @param <T>
     * @param queryName
     * @param queryParam
     * @param fetchSize
     * @return
     */
    public <T> List<T> listByQuery(String queryName, QueryParam queryParam, PageBounds pageBounds);

    /**
     * Delete the Object by the statement
     *
     * @param statement
     */
    public void delete(String statement);

    /**
     * Delete the Object by the statement and the parameter
     *
     * @param statement
     * @param parameter
     */
    public void delete(String statement, QueryParam parameter);

    /**
     * Save the Object by the statement and parameter
     *
     * @param statement
     * @param parameter
     */
    public void save(String statement, Object parameter);

    /**
     * Update the Object by the statement and parameter
     *
     * @param statement
     * @param parameter
     */
    public void update(String statement, Object parameter);

    /**
     * Update the Object by the statement and several parameters
     *
     * @param statement
     * @param parameter
     */
    public void update(String statement, QueryParam parameter);


    /**
     * Interface for pagination query object list
     */
    public <T> List<T> selectList(String statement, Object parameter,
                                  RowBounds rowBounds);


    /**
     * Interface for pagination query object list with no parameter
     * RowBounds(int offset, int limit)
     */
    public <T> List<T> selectList(String statement, RowBounds rowBounds);

    /**
     * Interface for pagination query object Map
     */
    public Map<?, ?> selectMap(String statement, Object parameter,
                               String mapKey, RowBounds rowBounds);

    /**
     * Used to dynamic query in Mybatis
     *
     * @param <T>
     * @param queryId
     * @param queryParam
     * @return
     */
    public <T extends Object> List<T> listByQuery(String queryId, DynamicQueryParam queryParam);

}
