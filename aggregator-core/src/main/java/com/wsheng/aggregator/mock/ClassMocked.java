/**
 * 
 */
package com.wsheng.aggregator.mock;

/**
 * 
 * ① 使用 mockito 生成 Mock 对象；

   ② 定义(并非录制) Mock 对象的行为和输出(expectations部分)；

   ③ 调用 Mock 对象方法进行单元测试；

   ④ 对 Mock 对象的行为进行验证。
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class ClassMocked {

	public String hello (String name) {
		System.out.println(" in hello : " + name);
		return "hello : " + name;
	}
	
	public void show() {
		System.out.println("ClassMocked.show()");
	}
	
}
