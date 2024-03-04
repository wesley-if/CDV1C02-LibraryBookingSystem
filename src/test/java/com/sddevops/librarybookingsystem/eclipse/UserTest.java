package com.sddevops.librarybookingsystem.eclipse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	private User user1;

	@BeforeEach
	void setUp() throws Exception {
		user1 = new User("John Doe", "password123", "john@doe.com", "en");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetName() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "John Doe";
		
		// assert
		assertEquals(bVal, user1.getName());
	}

	@Test
	void testSetName() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "Jane Doe";
		
		user1.setName(bVal);
		
		// assert
		assertEquals(bVal, user1.getName());
	}

	@Test
	void testGetPassword() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "password123";
		
		// assert
		assertEquals(bVal, user1.getPassword());
	}

	@Test
	void testSetPassword() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "StrongPassword123";
		
		user1.setPassword(bVal);
		
		// assert
		assertEquals(bVal, user1.getPassword());
	}

	@Test
	void testGetEmail() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "john@doe.com";
		
		// assert
		assertEquals(bVal, user1.getEmail());
	}

	@Test
	void testSetEmail() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "jane@doe.com";
		
		user1.setEmail(bVal);
		
		// assert
		assertEquals(bVal, user1.getEmail());
	}

	@Test
	void testGetLanguage() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "en";
		
		// assert
		assertEquals(bVal, user1.getLanguage());
	}

	@Test
	void testSetLanguage() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "zh";
		
		user1.setLanguage(bVal);
		
		// assert
		assertEquals(bVal, user1.getLanguage());
	}

}
