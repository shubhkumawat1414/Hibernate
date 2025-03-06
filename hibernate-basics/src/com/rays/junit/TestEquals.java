package com.rays.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestEquals {

	@Test
	public void testAdd() throws Exception {
		
		UserBean bean = new UserBean();
		
		bean.setId(100000);
		bean.setName("suman");
		bean.setSalary(30000);
		
		int i = UserModel.add1(bean);
		
		assertEquals(1, i,"asdfghjk");
		
		//System.out.println("asdfghyjk");
	}
}
