/**
 * 
 */
package com.wsheng.aggregator.mock;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.File;

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
public class PowerMockMethodInternalTest {

		// 2. mock the new Object in the method internal
		@Test
		@PrepareForTest(PowerMockTarget.class)
		public void callInternalInstance() throws Exception {
			// ============== Note : Its required that to add @RunWith and @PrepareForTest(both for Type and Method)
			// when calling PowerMockito.whenNew(), The Class of @PrepareForTest is the class which
			// used to new the Object
			File file = mock(File.class);
			
			PowerMockTarget target = new PowerMockTarget();
			whenNew(File.class).withArguments("joshwang").thenReturn(file); // want to create a new File
			when(file.exists()).thenReturn(true);
			
			Assert.assertTrue(target.callInternalInstance("joshwang"));
			
		}
		
}
