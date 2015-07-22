/**
 * 
 */
package com.wsheng.aggregator.annotation;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
@Description(value="这是一个有用的工具类") // value可以省略
public class Utility {

	@Author(name="wangsheng", group="developer team")
	public String work() {
		return "work over!";
	}
}
