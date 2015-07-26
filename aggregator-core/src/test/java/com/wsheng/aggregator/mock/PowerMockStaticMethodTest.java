/**
 * 
 */
package com.wsheng.aggregator.mock;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
@RunWith(PowerMockRunner.class)
public class PowerMockStaticMethodTest {
	
	// 4. mock static method
	@Test
	@PrepareForTest(PowerMockDependency.class)
	public void callStaticMethod() {
		// ============== Note : Its required that to add @RunWith and @PrepareForTest(both for Type and Method)
		// when mock static method, The Class of @PrepareForTest is the class which contain static method

		PowerMockTarget target = new PowerMockTarget();
		mockStatic(PowerMockDependency.class);
		when(PowerMockDependency.isExist()).thenReturn(true);
		Assert.assertTrue(target.callStaticMethod());
	}

}
