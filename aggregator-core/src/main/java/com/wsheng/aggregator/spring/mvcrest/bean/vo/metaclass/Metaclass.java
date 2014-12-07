package com.wsheng.aggregator.spring.mvcrest.bean.vo.metaclass;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"name", "count", "items"})
@XmlRootElement(name="metaclass")
public class Metaclass implements AbstractMetaclass<MetaclassItem>, Comparable<Metaclass> {
	
	private String name;
	private int count;
	private List<MetaclassItem> metaclassItemList;
	
	public Metaclass() {
		super();
		this.metaclassItemList = new ArrayList<MetaclassItem>();
	}
	public Metaclass(String name, int count) {
		super();
		this.name = name;
		this.count = count;
		this.metaclassItemList = new ArrayList<MetaclassItem>();
	}
	public Metaclass(String name, int count, List<MetaclassItem> metaclassItemList) {
		super();
		this.name = name;
		this.count = count;
		if (metaclassItemList != null)
			this.metaclassItemList = metaclassItemList;
		else
			this.metaclassItemList = new ArrayList<MetaclassItem>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@XmlElement(name="metaclassItem")
	public List<MetaclassItem> getItems() {
		return metaclassItemList;
	}
	public void setItems(List<MetaclassItem> itemList) {
		this.metaclassItemList = itemList;
	}
	
	public MetaclassItem getItem(String creator) {
		for (MetaclassItem tempMetaclassItem: this.metaclassItemList)
			if (tempMetaclassItem.getCreator().equals(creator))
				return tempMetaclassItem;
		return null;
	}
	
	public void addItem(MetaclassItem metaclassItem) {
		if (metaclassItem == null || this.metaclassItemList == null) return;
		if (!this.metaclassItemList.contains(metaclassItem))
			this.metaclassItemList.add(metaclassItem);
	}

	public void updateItem(MetaclassItem metaclassItem) {
		if (metaclassItem == null || this.metaclassItemList == null) return;
		int index = this.metaclassItemList.indexOf(metaclassItem);
		if (index < 0)
			this.metaclassItemList.add(metaclassItem);
		else
			this.metaclassItemList.set(index, metaclassItem);
	}
	
	public void removeItem(String creator) {
		MetaclassItem metaclassItem = new MetaclassItem(creator, -1);
		if (this.metaclassItemList.contains(metaclassItem))
			this.metaclassItemList.remove(metaclassItem);
	}
	
	/**
	 * We override it, just to compare the name to identify a Metaclass class, 
	 * in order to be conveniently used in list.contains() and list.indexOf() methods.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metaclass other = (Metaclass) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Metaclass o) {
		return this.name.compareTo(o.getName());
	}
	
	@Override
	public String toString() {
		return  "{ \"name\": " + "\"" + name + "\"" + 
			    ", \"count\": " + "\"" + count + "\"" + 
				", items: " + metaclassItemList.toString() + "}";
	}
}
