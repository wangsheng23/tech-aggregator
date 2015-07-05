/**
 * 
 */
package com.wsheng.aggregator.mock;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class MockClassTest {
	
	// =============================================== Mockito ======================================
	@Test
	public void normailMock() {
		// 1.  使用 mockito 生成 Mock 对象；
		ClassMocked mocked = Mockito.mock(ClassMocked.class);
		
		// 2.  定义Mock对象的行为和输出(Expectation)
		Mockito.when(mocked.hello("wangsheng")).thenReturn("WangSheng");
		
		// 3. 调用 Mock 对象方法进行单元测试
		String actual = mocked.hello("wangsheng");
		Assert.assertEquals("WangSheng", actual);
		
		// 4. 对Mock对象的行为进行验证
		Mockito.verify(mocked).hello("wangsheng");
	}
	
	@Test
	public void mockMethodInOrder() {
		ClassMocked mock1 = Mockito.mock(ClassMocked.class);
		ClassMocked mock2 = Mockito.mock(ClassMocked.class);
		
		Mockito.when(mock1.hello("wangsheng")).thenReturn("WangSheng");
		Mockito.when(mock2.hello("wangsheng")).thenReturn("WangSheng");
		
		String input1 = mock1.hello("wangsheng");
		Assert.assertEquals("WangSheng", input1);
		
		String input2 = mock2.hello("wangsheng");
		Assert.assertEquals("WangSheng", input2);
		
		InOrder inOrder = Mockito.inOrder(mock1, mock2);
		inOrder.verify(mock1).hello("wangsheng");
		inOrder.verify(mock2).hello("wangsheng");
	}
	
	/**
	 * 非局部模拟
	 * 
	 * 模拟一整个类或者对象，对于没有使用 When().thenReturn()方法指定的函数，
	 * 系统会返回各种类型的默认值（具体值可参考官方文档）
	 */
	@Test
	public void skipExpectation() {
		ClassMocked mock1 = Mockito.mock(ClassMocked.class);
		
		// 上面的代码省略了expectations部分(即定义代码行为和输出)，运行该测试可以看到hello方法默认返回null(show方法本来就是无返回值的)，
		// 而且在控制台中两个方法都没有输出任何语句。
		Assert.assertEquals(null, mock1.hello("wangsheng")); // String返回了null
		mock1.show();
		
		Mockito.verify(mock1).hello("wangsheng");
		Mockito.verify(mock1).show();
	}
	
	/**
	 * mockito的局部模拟有两种方式，一种是doCallRealMethod()方式，另一种是spy()方式。
	 * 
	 * 局部模拟创建出来的模拟对象依然是原系统对象，虽然可以使用方法When().thenReturn()来指定某些具体方法的返回值，
	 * 但是没有被用此函数修改过的函数依然按照系统原始类的方式来执行
	 * 
	 * WS：局部模拟按类定义的原有方式执行
	 */
	@Test
	public void callRealMethod() {
		ClassMocked mock1 = Mockito.mock(ClassMocked.class);
		
		Mockito.doCallRealMethod().when(mock1).hello("wangsheng");
		
		// 运行这个测试会发现在执行hello("wangsheng")时会执行原有的代码，
		// 而执行hello("wangsheng23")时则是返回默认值null且没有输出打印，执行show()同样没有输出打印。
		Assert.assertEquals("hello : wangsheng", mock1.hello("wangsheng"));
		Assert.assertEquals(null, mock1.hello("wangsheng23"));
		mock1.show();
		
		Mockito.verify(mock1).hello("wangsheng");
		Mockito.verify(mock1).hello("wangsheng23");
		Mockito.verify(mock1).show();
	}
	
	/**
	 * 运行这个测试会发现在执行hello("wangsheng")时会执行原有的代码，
	 * 但是执行show()时在控制台中没有打印语句。

		但值得注意的是在mockito的psy()方式模拟中expectations部分使用的语法不同，
		执行起来存在微妙的不同，如spy2()：
	 */
	@Test
	public void spy1() {
		ClassMocked mocked = Mockito.spy(new ClassMocked());
		Mockito.doNothing().when(mocked).show();
		
		Assert.assertEquals("hello : wangsheng", mocked.hello("wangsheng"));
		mocked.show();
		
		Mockito.verify(mocked).hello("wangsheng");
		Mockito.verify(mocked).show();
	}
	
	@Test
	public void spy2() {
		ClassMocked mocked = Mockito.spy(new ClassMocked());
		Mockito.when(mocked.hello("wangsheng")).thenReturn("hello : wangsheng23");
		Assert.assertEquals("hello : wangsheng23", mocked.hello("wangsheng"));
		
		Mockito.verify(mocked).hello("wangsheng");
	}
	
	/**
	 * 上面代码虽然能顺利运行，但在控制台中输出了hello wangsheng，说明实际的代码仍然执行了，
	 * 只是mockito在最后替换了返回值。 但下面的代码就不会执行实际的代码：
	 * 
	 * WS:用spy的时候应该使用doReturn而不是thenReturn的方式
	 */
	@Test
	public void spy3() {
		ClassMocked mocked = Mockito.spy(new ClassMocked());
		Mockito.doReturn("hello : wangsheng23").when(mocked).hello("wangsheng");
		
		Assert.assertEquals("hello : wangsheng23", mocked.hello("wangsheng"));
		Mockito.verify(mocked).hello("wangsheng");
	}

}
