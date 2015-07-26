/**
 * 
 */
package com.wsheng.aggregator.mock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
@RunWith(PowerMockRunner.class)
public class PowerMockSystemPrivateFinalTest {
	
	// 6. mock the private and final methods of System class
	@Test
	@PrepareForTest(PowerMockTarget.class)
	public void callSystemStaticMethod() {
		// ============== Note : Its required that to add @RunWith and @PrepareForTest(both for Type and Method)
		// when mock System final / static method, its just like mock the normal object's final / static method
		// The difference is the Class of @PrepareForTest is the class which calling the System classs methods
				
		PowerMockTarget target = new PowerMockTarget();
		
		mockStatic(System.class);
		when(System.getProperty("joshwang")).thenReturn("swang6");
		Assert.assertEquals("swang6", target.callSystemStaticMethod("joshwang"));
	}

}
