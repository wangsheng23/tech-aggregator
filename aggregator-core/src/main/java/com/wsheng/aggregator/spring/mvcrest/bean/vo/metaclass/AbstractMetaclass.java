package com.wsheng.aggregator.spring.mvcrest.bean.vo.metaclass;

/**
 * An interface of the metaclass, in order to deal with future extensibility.
 *
 * @param <T>
 */
public interface AbstractMetaclass<T> {
	
	/**
	 * Because the list is a global variable in the child class, 
	 * the child class should implement it to get an item from the list
	 * with id.
	 * @param id
	 * @return
	 */
	public T getItem(String id);
	
	/**
	 * To add an item to the list defined in the child class.
	 * @param item
	 */
	public void addItem(T item);
	
	/**
	 * To update an item in the list defined in the child class.
	 * @param item
	 */
	public void updateItem(T item);
	
	/**
	 * To remove an item from a list defined in the child class.
	 * @param id
	 */
	public void removeItem(String id);
}