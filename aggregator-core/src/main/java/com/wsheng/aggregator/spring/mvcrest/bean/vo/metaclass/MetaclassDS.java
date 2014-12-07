package com.wsheng.aggregator.spring.mvcrest.bean.vo.metaclass;

public class MetaclassDS {
	
	private static MetaclassGroup metaclassList;
	
	static {
		metaclassList = new MetaclassGroup();
		Metaclass m1 = new Metaclass("NodeServers", 661);
		Metaclass m2 = new Metaclass("FQDN", 661);
		m1.addItem(new MetaclassItem("JoshWang", 91));
		m1.addItem(new MetaclassItem("Jack", 55));
		m1.addItem(new MetaclassItem("Michell", 13));
		m1.addItem(new MetaclassItem("Mad", 46));
		m1.addItem(new MetaclassItem("Dunk", 456));
		m2.addItem(new MetaclassItem("JoshWang", 191));
		m2.addItem(new MetaclassItem("Jack", 55));
		m2.addItem(new MetaclassItem("Michell", 53));
		m2.addItem(new MetaclassItem("Mad", 6));
		m2.addItem(new MetaclassItem("Jordan", 356));
		metaclassList.addItem(m1);
		metaclassList.addItem(m2);
		metaclassList.setCount(223);
	}
	
	public MetaclassGroup getMetaclassList() {
		return metaclassList;
	}
}
