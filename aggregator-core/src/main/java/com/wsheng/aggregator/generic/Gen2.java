package com.wsheng.aggregator.generic;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class Gen2 {

	// 定义一个通用类型成员
	private Object obj;
	
	public Gen2(Object obj) {
        this.obj = obj;
    }
 
    public Object getObj() {
        return obj;
    }
 
    public void setObj(Object obj) {
        this.obj = obj;
    }
 
    public void showType() {
        System.out.println("T的实际类型是: " + obj.getClass().getName());
    }
	
    public static void main(String[] args) {
    	// 定义类Gen2的一个Integer版本
		Gen2 intObj = new Gen2(2);
		intObj.showType();
		
		int i = (Integer) intObj.getObj();
		System.out.println(" value = " + i);
		System.out.println(" ====================== ");
		
		// 定义类Gen2的一个String版本
        Gen2 strOb = new Gen2("Hello Gen!");
        strOb.showType();
        String s = (String) strOb.getObj();
        System.out.println(" value= " + s);
	}
}
