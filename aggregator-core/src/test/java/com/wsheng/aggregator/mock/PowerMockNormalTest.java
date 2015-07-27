/**
 * 
 */
package com.wsheng.aggregator.mock;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
/**
 * Note: these PowerMock**Test.java can't be exist in a single class, or
 * one is could be executed success, but others can't be success
 * 
 * TODO: research the root cause
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */

public class PowerMockNormalTest {
	
	// 1. normal mock, mock the passed parameter: Object
	@Test
	public void callArgumentInstance() {
		// ============== Note : The normal mock doesn't need @RunWith and @PrepareForTest
		File file = mock(File.class); // construct File object
		
		PowerMockTarget target = new PowerMockTarget();
		when(file.exists()).thenReturn(true);
		Assert.assertTrue(target.callArgumentInstance(file));
	}
	
}
