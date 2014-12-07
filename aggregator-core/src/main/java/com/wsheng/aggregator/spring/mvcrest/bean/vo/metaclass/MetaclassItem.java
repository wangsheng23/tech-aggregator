package com.wsheng.aggregator.spring.mvcrest.bean.vo.metaclass;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The MetaclassItem class is consisted of two properties, a creator of a certain
 * metaclass, and its corresponding count.
 * @author chengzhu
 */
@XmlType(propOrder={"creator", "count"})
@XmlRootElement(name="metaclassItem")
public class MetaclassItem implements Comparable<MetaclassItem> {
	
	private String creator;
	private int count;
	
	public MetaclassItem() {}
	public MetaclassItem(String creator, int count) {
		super();
		this.creator = creator;
		this.count = count;
	}
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * We override it, just to compare the creator to identify a MetaclassItem class, 
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
		MetaclassItem other = (MetaclassItem) obj;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(MetaclassItem o) {
		return this.creator.compareTo(o.getCreator());
	}
	
	@Override
	public String toString() {
		return "{\"creator\": \"" + creator + "\",\"count\": \"" + count + "\"}";
	}
}
