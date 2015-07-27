/**
 * 
 */
package com.wsheng.aggregator.mock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
@RunWith(PowerMockRunner.class)
public class PowerMockPrivateMethodTest {
	
	// 5. Mock private method
	@Test
	@PrepareForTest(PowerMockTarget.class)
	public void callPrivateMethod() throws Exception {
		// ============== Note : Its required that to add @RunWith and @PrepareForTest(both for Type and Method)
		// when mock static method, The Class of @PrepareForTest is the class which contain private method.

		PowerMockTarget target = mock(PowerMockTarget.class);
		when(target.callPrivateMethod()).thenCallRealMethod();
		when(target, "isExists").thenReturn(true);
		Assert.assertTrue(target.callPrivateMethod());
	}

}
