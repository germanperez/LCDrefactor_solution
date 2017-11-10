package com.pslcorp.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pslcorp.utils.Utilities;

/**
 * Class that contains the tests.
 * 
 * @author German Perez
 */
public class ImpresorLCDTest {
	
	private ImpresorLCD impresorLCD = null;

	@Before
	public void beforeEachTest() {
		impresorLCD = new ImpresorLCD();
	}

	@Test
	public void testIsNumeric() {
		String chain = "8";
		boolean isNumeric = Utilities.isNumeric(chain);
		
		assertTrue(isNumeric);
	}
	
	@Test
	public void testFailedIsNumeric() {
		String chain = "test";
		boolean isNumeric = Utilities.isNumeric(chain);
		
		assertFalse(isNumeric);
	}
	
	@Test
	public void testSegmentList() {
		List<Integer> segListTest = new ArrayList<>();
		segListTest.add(5);
		segListTest.add(3);
		segListTest.add(6);
   		segListTest.add(2);
   		segListTest.add(7);
   		
   		List<Integer> segList = Utilities.getSegmentList(2);
		
		assertEquals(segListTest, segList);
	}
	
	@Test
	public void testFailedSegmentList() {
		List<Integer> segListTest = new ArrayList<>();
		segListTest.add(5);
		segListTest.add(3);
		segListTest.add(6);
   		segListTest.add(2);
   		segListTest.add(7);
   		
   		List<Integer> segList = Utilities.getSegmentList(3);
		
		assertNotSame(segListTest, segList);
	}

}
