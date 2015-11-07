package com.wsheng.aggregator.dao;

import java.io.Serializable;
import java.util.List;


/**
 * <p>IBaseDao is an interface to operate database by Hibernate</p>
 *
 * @author Josh Wang(Sheng)
 * @email josh_wang23@hotmail.com
 */
public interface IHBaseDao<M extends Serializable, PK extends Serializable> {

    public void _update(final Class<?> clazz, String setHQL, String whereHQL, Object... parameters);

    /**
     * Persist the model to database and return the id of the model
     *
     * @param model
     * @return
     */
    public PK _save(M model);


    /**
     * Save the model when its not exist in database, Update it if it already exists
     *
     * @param model
     */
    public void _saveOrUpdate(M model);

    /**
     * Update the model
     *
     * @param model
     */
    public void _update(M model);

    /**
     * Merge the model
     *
     * @param model
     */
    public void _merge(M model);

    /**
     * Delete the model from database by id
     *
     * @param id
     */
    public void _delete(PK id);

    /**
     * Delete by hql
     *
     * @param hql        "id = ?"
     * @param parameters id = 1
     */
    public void _delete(final Class<?> clazz, final String hql, final Object... parameters);

    /**
     * Delete the model directly
     *
     * @param model
     */
    public void _deleteObject(M model);

    /**
     * Get the Model by id
     *
     * @param id
     * @return
     */
    public M _get(PK id);

    /**
     * Get the object by specified parameters
     *
     * @param hql
     * @param parameters
     * @return
     */
    public M _get(String hql, Object... parameters);


    public List<M> _filter(String hql, Object... parameters);

    public List<M> _filter2(final Class<?> clazz, Object... parameters);


    /**
     * Count all of the specified Entity Class
     *
     * @return
     */
    public int _countAll();

    /**
     * List all of the Model, no pagination
     *
     * @return
     */
    public List<M> _listAll();

    /**
     * List all of the Model in the specified page number
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<M> _list(int pageNumber, int pageSize);

    /**
     * Verify the Model exists or not by id
     *
     * @param id
     * @return
     */
    public boolean _isExists(PK id);

    /**
     * Flush the cache
     */
    public void _flush();

    /**
     * Clear the cache
     */
    public void _clear();
}
