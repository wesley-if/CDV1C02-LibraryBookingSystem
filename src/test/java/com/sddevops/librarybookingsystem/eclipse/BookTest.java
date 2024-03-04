package com.sddevops.librarybookingsystem.eclipse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {
	private Book book1;

	@BeforeEach
	void setUp() throws Exception {
		book1 = new Book(1, "Alices adventures in wonderland", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "9781786751041", 1, "2024-01-01 10:00:00", "2024-02-15 15:00:00");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetId() {
//		fail("Not yet implemented");
		
		// act
		Integer bVal = 1;
		
		// assert
		assertEquals(bVal, book1.getId());
	}

	@Test
	void testSetId() {
		//fail("Not yet implemented");

		// act
		Integer bVal = 123;
		
		book1.setId(bVal);
		
		// assert
		assertEquals(bVal, book1.getId());
	}

	@Test
	void testGetTitle() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "Alices adventures in wonderland";
		
		// assert
		assertEquals(bVal, book1.getTitle());
	}

	@Test
	void testSetTitle() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "Harry Potter";
		
		book1.setTitle(bVal);
		
		// assert
		assertEquals(bVal, book1.getTitle());
	}

	@Test
	void testGetDescription() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
		
		// assert
		assertEquals(bVal, book1.getDescription());
	}

	@Test
	void testSetDescription() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "Donec posuere iaculis rhoncus.";
		
		book1.setDescription(bVal);
		
		// assert
		assertEquals(bVal, book1.getDescription());
	}

	@Test
	void testGetIsbn() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "9781786751041";
		
		// assert
		assertEquals(bVal, book1.getIsbn());
	}

	@Test
	void testSetIsbn() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "0000000000002";
		
		book1.setIsbn(bVal);
		
		// assert
		assertEquals(bVal, book1.getIsbn());
	}

	@Test
	void testGetPublished() {
//		fail("Not yet implemented");
		
		// act
		Integer bVal = 1;
				
		// assert
		assertEquals(bVal, book1.getPublished());
	}

	@Test
	void testSetPublished() {
//		fail("Not yet implemented");
		
		// act
		Integer bVal = 0;
		
		book1.setPublished(bVal);
		
		// assert
		assertEquals(bVal, book1.getPublished());
	}

	@Test
	void testGetCreated() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "2024-01-01 10:00:00";
		
		// assert
		assertEquals(bVal, book1.getCreated());
	}

	@Test
	void testSetCreated() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "2024-02-29 12:34:56";
		
		book1.setCreated(bVal);
		
		// assert
		assertEquals(bVal, book1.getCreated());
	}

	@Test
	void testGetModified() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "2024-02-15 15:00:00";
		
		// assert
		assertEquals(bVal, book1.getModified());
	}

	@Test
	void testSetModified() {
//		fail("Not yet implemented");
		
		// act
		String bVal = "2024-02-29 12:34:56";
		
		book1.setModified(bVal);
		
		// assert
		assertEquals(bVal, book1.getModified());
	}

}
