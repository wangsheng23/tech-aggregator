package com.wsheng.aggregator.spring.mvcrest.bean.vo.metaclass;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The MetaclassGroup class consists of a list that contains all metaclasses
 * in it.
 */
@XmlType(propOrder={"metaclass", "count"})
@XmlRootElement(name="metaclassGroup")
public class MetaclassGroup {
	
	private int count;
	private List<Metaclass> metaclassList;

	public MetaclassGroup() {
		super();
		this.metaclassList = new ArrayList<Metaclass>();
	}
	public MetaclassGroup(List<Metaclass> metaclassList, int count) {
		super();
		this.count = count;
		if (metaclassList != null)
			this.metaclassList = metaclassList;
		else
			this.metaclassList = new ArrayList<Metaclass>();
	}

	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	@XmlElement(name="metaclass")
	public List<Metaclass> getMetaclass() {
		return metaclassList;
	}

	public void setMetaclass(List<Metaclass> metaclassList) {
		this.metaclassList = metaclassList;
	}
	
	public Metaclass getItem(String metadata_type) {
		for (Metaclass tempMetaclass: this.metaclassList)
			if (tempMetaclass.getName().equals(metadata_type))
				return tempMetaclass;
		return null;
	}
	
	public void addItem(Metaclass metaclass) {
		if (metaclass == null || this.metaclassList == null) return;
		if (!this.metaclassList.contains(metaclass))
			this.metaclassList.add(metaclass);
	}
	
	public void updateItem(Metaclass metaclass) {
		if (metaclass == null || this.metaclassList == null) return;
		int index = this.metaclassList.indexOf(metaclass);
		if (index < 0)
			this.metaclassList.add(metaclass);
		else
			this.metaclassList.set(index, metaclass);
	}
	
	public void removeItem(String name) {
		Metaclass metaclass = new Metaclass(name, -1);
		if (this.metaclassList.contains(metaclass))
			this.metaclassList.remove(metaclass);
	}
	
	@Override
	public String toString() {
		String retString = "";
		for (Metaclass tempMetaclass: this.metaclassList)
			retString += tempMetaclass.toString();
		return retString;
	}
}
