/**
 *
 */
package com.wsheng.aggregator.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wsheng.aggregator.bean.DynamicQueryParam;
import com.wsheng.aggregator.bean.QueryParam;


/**
 * @author Josh Wang(Sheng)
 * @email josh_wang23@hotmail.com
 */
@Repository("mBaseDao") // myBatis Base Dao
public class MBaseDaoImpl implements IMBaseDao {
    private
    @Resource
    SqlSessionTemplate sqlSessionTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T loadByQuery(String queryId) {
        return (T) sqlSessionTemplate.selectOne(queryId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T loadByQuery(String queryId, QueryParam queryParam) {
        return (T) sqlSessionTemplate.selectOne(queryId, queryParam.getParams());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T loadByQuery(String queryId, String queryParam) {
        return (T) sqlSessionTemplate.selectOne(queryId, queryParam);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> listByQuery(String queryId) {
        return (List<T>) sqlSessionTemplate.selectList(queryId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> listByQuery(String queryId, String queryParam) {
        return (List<T>) sqlSessionTemplate.selectList(queryId, queryParam);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> listByQuery(String queryId, QueryParam queryParam) {
        return (List<T>) sqlSessionTemplate.selectList(queryId, queryParam.getParams());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> listByQuery(String queryName, QueryParam queryParam,
                                   PageBounds pageBounds) {
        return (List<T>) sqlSessionTemplate.selectList(queryName, queryParam.getParams(), pageBounds);

    }

    @Override
    public void delete(String statement) {
        sqlSessionTemplate.delete(statement);
    }

    @Override
    public void delete(String statement, QueryParam parameter) {
        sqlSessionTemplate.delete(statement, parameter.getParams());
    }

    @Override
    public void save(String statement, Object parameter) {
        sqlSessionTemplate.insert(statement, parameter);
    }

    @Override
    public void update(String statement, Object parameter) {
        sqlSessionTemplate.update(statement, parameter);
    }


    @Override
    public void update(String statement, QueryParam parameter) {
        sqlSessionTemplate.update(statement, parameter.getParams());

    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> selectList(String statement, Object parameter,
                                  RowBounds rowBounds) {
        return (List<T>) sqlSessionTemplate.selectList(statement, parameter, rowBounds);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> selectList(String statement, RowBounds rowBounds) {
        return (List<T>) sqlSessionTemplate.selectList(statement, null, rowBounds);
    }

    @Override
    public Map<?, ?> selectMap(String statement, Object parameter,
                               String mapKey, RowBounds rowBounds) {
        return sqlSessionTemplate.selectMap(statement, parameter, mapKey, rowBounds);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> listByQuery(String queryId, DynamicQueryParam queryParam) {
        return (List<T>) sqlSessionTemplate.selectList(queryId, queryParam);
    }


}
