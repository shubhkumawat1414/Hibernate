package com.rays.junit;

import org.junit.Test;

import junit.framework.TestCase;

public class TestUserModel extends TestCase{

	@Test
	public void testAdd() throws Exception {

		UserBean bean = new UserBean();
		bean.setId(19);
		bean.setName("aman");
		bean.setSalary(5000);

		UserModel model = new UserModel();

		model.add(bean);

		bean = model.findByPk(20);

		/*
		 * if (bean == null) {
		 * 
		 * fail("Record is not added...!!!");
		 * 
		 * } else {
		 * 
		 * System.out.println("Record is  added...!!!");
		 * 
		 * }
		 */
		
		assertNotNull("Record is added...!!!" , bean);
		
		assertNull("Record is not added...!!!" , bean);
	}
}