package in.co.Junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestJunit {
	
	@Test
	public void testAdd() {
		
		System.out.println("this is testcase in this class");
		
		String str1 = "this is testcase in this class";
		
		assertEquals("this is testcase in this class",str1);
	}

}
