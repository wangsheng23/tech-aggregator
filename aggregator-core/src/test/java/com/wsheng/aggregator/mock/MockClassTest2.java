/**
 * 
 */
package com.wsheng.aggregator.mock;

import static org.powermock.api.mockito.PowerMockito.*;


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
@PrepareForTest( {ClassMocked2.class })
public class MockClassTest2 {
	
	
	@Test
	public void mockStatickMethod() {
		mockStatic(ClassMocked2.class);
		when(ClassMocked2.getDouble(1)).thenReturn(3);
		
		int actual = ClassMocked2.getDouble(1);
		Assert.assertEquals(3, actual);
		
		verifyStatic();
		ClassMocked2.getDouble(1);
	}
	
	/**
	 * PowerMockit的局域模拟使用方式和mockito类似(毕竟是扩展mockito),
	 * 但强大之处在于可以模拟private方法，普通方法和final方法。
	 * 模拟普通方法和final方法的方式与模拟private方法一模一样，现以模拟private方法为例。
	 * 
	 * 模拟私有方法(doCallRealMethod方式)
	 * @throws Exception 
	 */
	@Test
	public void mockPrivateMethod() throws Exception {
		ClassMocked2 mocked2 = mock(ClassMocked2.class);
		when(mocked2, "multiply3", 1).thenReturn(4);
		doCallRealMethod().when(mocked2).getTripleString(1); // will call private method
		// doCallRealMethod().when(mocked2.getTripleString(1));
		String actual = mocked2.getTripleString(1);
		Assert.assertEquals("4", actual);
		
		verifyPrivate(mocked2).invoke("multiply3", 1);
	}
	
	/**
	 * 模拟私有方法(spy方式)
	 * @throws Exception 
	 */
	@Test
	public void mockPrivateMethod2() throws Exception {
		ClassMocked2 mocked2 = spy(new ClassMocked2());
		when(mocked2, "multiply3", 1).thenReturn(4);
		
		String actual = mocked2.getTripleString(1);
		Assert.assertEquals("4", actual);
		
		verifyPrivate(mocked2).invoke("multiply3", 1);
	}
	
	/**
	 * 模拟构造方法
	 * @throws Exception
	 */
	@Test
	public void mockConstructWhenPathNotExist() throws Exception {
	     final String directoryPath = "mocked path"; 
		
	     // 定义行为
		 File directoryMock = mock(File.class);  
		 whenNew(File.class).withArguments(directoryPath).thenReturn(directoryMock); 
		 when(directoryMock.exists()).thenReturn(true);
		 
		 // 开始做事情
		 File file = new File(directoryPath);
		 Assert.assertTrue(file.exists()); 
		 
		 verifyNew(File.class).withArguments(directoryPath);
		 verifyPrivate(directoryMock).invoke("exists");
		 
	}

}
