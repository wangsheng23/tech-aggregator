/**
 *
 */
package com.wsheng.aggregator.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Id;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

import com.wsheng.aggregator.util.AssertUtils;


/**
 * @author Josh Wang(Sheng)
 * 
 * @email josh_wang23@hotmail.com
 */
public class HBaseDaoImpl<M extends Serializable, PK extends Serializable> implements IHBaseDao<M, PK> {

    protected static final Log logger = LogFactory.getLog(HBaseDaoImpl.class);
    /**
     * The exact Entity Class
     */
    private final Class<M> entityClass;
    /**
     * List all of the Objects of the entityClass
     */
    private final String HQL_LIST_ALL;
    /**
     * Count the Objects of the entityClass
     */
    private final String HQL_COUNT_ALL;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    /**
     * the pk name of the entityClass
     */
    private String pkName = null;


    @SuppressWarnings("unchecked")
    public HBaseDaoImpl() {
        this.entityClass = (Class<M>) ((ParameterizedType) (getClass().getGenericSuperclass())).getActualTypeArguments()[0];

        // _get the PK Name of the entityClass
        Field[] fields = this.entityClass.getDeclaredFields(); // _get the properties of the entityClass
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                this.pkName = field.getName();
                break;
            }
        }
        // for the case of extends class
        //TODO: should loop all the superclass
        for (Field field : this.entityClass.getSuperclass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                this.pkName = field.getName();
                break;
            }
        }

        AssertUtils.notNull(pkName);

        //TODO: @Entity name not null
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " asc";
        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
    }

    public Session getCurrentSession() {
        // The transaction must be open(Required), or there will not _get the session
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void _update(final Class<?> clazz, final String setHQL, final String whereHQL, final Object... parameters) {
        String q = String.format("update %s set %s where %s", clazz.getSimpleName(), setHQL, whereHQL);
        Query query = getCurrentSession().createQuery(q);
        setParameters(query, parameters);

        query.executeUpdate();
    }


    @SuppressWarnings("unchecked")
    @Override
    public PK _save(M model) {
        return (PK) getCurrentSession().save(model);
    }

    @Override
    public void _saveOrUpdate(M model) {
        getCurrentSession().saveOrUpdate(model);
    }

    @Override
    public void _update(M model) {
        getCurrentSession().update(model);
    }

    @Override
    public void _merge(M model) {
        getCurrentSession().merge(model);
    }

    @Override
    public void _delete(PK id) {
        getCurrentSession().delete(_get(id));
    }

    @Override
    public void _delete(final Class<?> clazz, final String hql, final Object... parameters) {
        String q = String.format("delete %s where %s", clazz.getSimpleName(), hql);
        Query query = getCurrentSession().createQuery(q);
        setParameters(query, parameters);

        query.executeUpdate();
    }

    @Override
    public void _deleteObject(M model) {
        getCurrentSession().delete(model);
    }

    @SuppressWarnings("unchecked")
    @Override
    public M _get(PK id) {
        return (M) getCurrentSession().get(this.entityClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public M _get(final String hql, final Object... parameters) {
        Query query = getCurrentSession().createQuery(hql);
        setParameters(query, parameters);

        List<M> results = query.list();
        if (results != null && results.size() > 0)
            return results.get(0);
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<M> _filter(final String hql, final Object... parameters) {
        Query query = getCurrentSession().createQuery(hql);
        setParameters(query, parameters);

        List<M> results = query.list();
        return results;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<M> _filter2(final Class<?> clazz, Object... parameters) {
        Criteria c = getCurrentSession().createCriteria(clazz);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Criterion) {
                    c.add((Criterion) parameters[i]);
                }
            }
        }
        List<M> results = c.list();
        return results;
    }

    @Override
    public int _countAll() {
        Long total = aggregate(HQL_COUNT_ALL);
        return total.intValue();
    }

    @Override
    public List<M> _listAll() {
        return list(HQL_LIST_ALL, -1, -1);
    }

    @Override
    public List<M> _list(int pageNumber, int pageSize) {
        return list(HQL_LIST_ALL, pageNumber, pageSize);
    }

    @Override
    public boolean _isExists(PK id) {
        return _get(id) != null;
    }

    @Override
    public void _flush() {
        getCurrentSession().flush();

    }

    @Override
    public void _clear() {
        getCurrentSession().clear();
    }

    @SuppressWarnings("unchecked")
    protected <T> T aggregate(final String hql, final Object... parameters) {
        Query query = getCurrentSession().createQuery(hql);
        setParameters(query, parameters);

        return (T) query.uniqueResult();
    }

    protected void setParameters(Query query, Object[] parameters) {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Date) {
                    query.setTimestamp(i, (Date) parameters[i]);
                } else {
                    query.setParameter(i, parameters[i]);
                }
            }
        }
    }


    @SuppressWarnings("unchecked")
    protected <T> List<T> list(final String hql, final int pageNumber, final int pageSize, final Object... parameters) {
        Query query = getCurrentSession().createQuery(hql);
        setParameters(query, parameters);
        if (pageNumber > -1 && pageSize > -1) {
            query.setMaxResults(pageSize);

            int start = (pageNumber - 1) * pageSize;
            if (start != 0) {
                query.setFirstResult(start);
            }
        }

        if (pageNumber < 0) {
            query.setFirstResult(0);
        }

        List<T> results = query.list();
        return results;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
