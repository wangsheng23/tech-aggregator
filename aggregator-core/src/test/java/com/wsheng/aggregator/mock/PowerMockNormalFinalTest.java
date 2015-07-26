/**
 * 
 */
package com.wsheng.aggregator.mock;

import static org.powermock.api.mockito.PowerMockito.mock;
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
public class PowerMockNormalFinalTest {
	
	// 3. mock the normal object's final method
	@Test
	@PrepareForTest(PowerMockDependency.class)
	public void callFinalMethod() {
		// ============== Note : Its required that to add @RunWith and @PrepareForTest(both for Type and Method)
		// when mock final method, The Class of @PrepareForTest is the class which contain final method
		PowerMockDependency dependency = mock(PowerMockDependency.class);
		PowerMockTarget target = new PowerMockTarget();
		when(dependency.isAlive()).thenReturn(true);
		Assert.assertTrue(target.callFinalMethod(dependency));
	}
	
}
