package com.ers.testing;
import org.junit.Test;

import com.ers.models.User;

import io.jsonwebtoken.lang.Assert;

public class UserTest {
	
	User user = new User();
	
	//this is a positive test case
	@Test
	public void testUserWithName() {
//		Assert.assertEquals("User Bob is added successfully", user.addUser("Bob"));
	}
	
	//here are my negative test cases
	@Test
	public void testUserWithNull() {
//		Assert.assertEquals("Please provide a username", user.addUser(null));
	}
	
	@Test
	public void testUserWithEmptyName() {
//		Assert.assertEquals("Please provide a username", user.addUser(""));
	}
	
	@Test
	public void testUserWithAdminName() {
//		Assert.assertEquals("Sorry Manager. This portal is for regular users only", user.addUser("Admin"));
	}
}