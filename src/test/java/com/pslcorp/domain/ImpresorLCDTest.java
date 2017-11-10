package com.pslcorp.domain;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pslcorp.utils.Constants;
import com.pslcorp.utils.Utilities;

/**
 * Class that contains the tests.
 * 
 * @author German Perez
 */
public class ImpresorLCDTest {
	
	private ImpresorLCD impresorLCD = null;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		impresorLCD = new ImpresorLCD();
		System.setOut(new PrintStream(outContent));
	}

	/**
	 * Validate that a chain is a number.
	 */
	@Test
	public void testIsNumeric() {
		String chain = "8";
		boolean isNumeric = Utilities.isNumeric(chain);
		
		assertTrue(isNumeric);
	}
	
	/**
	 * Validate that a chain is not a valid number.
	 */
	@Test
	public void testIsNotNumeric() {
		String chain = "87t";
		boolean isNumeric = Utilities.isNumeric(chain);
		
		assertFalse(isNumeric);
	}
	
	/**
	 * Validate that the segment list corresponds to the digit.
	 */
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
	
	/**
	 * Validate that the segment list does not correspond to the digit.
	 */
	@Test
	public void testIncorrectSegmentList() {
		List<Integer> segListTest = new ArrayList<>();
		segListTest.add(5);
		segListTest.add(3);
		segListTest.add(6);
   		segListTest.add(2);
   		segListTest.add(7);
   		
   		List<Integer> segList = Utilities.getSegmentList(3);
		
		assertNotSame(segListTest, segList);
	}
	
	/**
	 * Validate that the input does not contain a comma.
	 */
	@Test
	public void testProcessInputWithoutComma() {
		String comand = "23456";
		String outputMessage = Constants.ERROR_MESSAGE 
				+ Constants.COMMA_MISSING_CHARACTER_EXCEPTION + comand;
				
		impresorLCD.processInput(comand, Constants.DIGIT_SPACE);
		
		assertEquals(outputMessage, outContent.toString().trim());
	}
	
	/**
	 * Validate that the input has more than two parameters.
	 */
	@Test
	public void testProcessInputMoreThanTwoParameters() {
		String comand = "2,34,56";
		String outputMessage = Constants.ERROR_MESSAGE 
				+ Constants.MORE_THAN_TWO_PARAMETERS_EXCEPTION + comand;
				
		impresorLCD.processInput(comand, Constants.DIGIT_SPACE);
		
		assertEquals(outputMessage, outContent.toString().trim());
	}
	
	/**
	 * Validate that the input has less than two parameters.
	 */
	@Test
	public void testProcessInputLessThanTwoParameters() {
		String comand = "2,";
		String outputMessage = Constants.ERROR_MESSAGE 
				+ Constants.LESS_THAN_TWO_PARAMETERS_EXCEPTION + comand;
				
		impresorLCD.processInput(comand, Constants.DIGIT_SPACE);
		
		assertEquals(outputMessage, outContent.toString().trim());
	}
	
	/**
	 * Validate that the size value of an input is not a number.
	 */
	@Test
	public void testProcessInputNotNumberSize() {
		String comand = "two,3456";
		String size = "two";
		String outputMessage = Constants.ERROR_MESSAGE 
				+ Constants.NOT_NUMBER_SIZE_EXCEPTION + size;
				
		impresorLCD.processInput(comand, Constants.DIGIT_SPACE);
		
		assertEquals(outputMessage, outContent.toString().trim());
	}
	
	/**
	 * Validate that the size value of an input is not between 1 and 10.
	 */
	@Test
	public void testProcessInputNotValidSizeValue() {
		String comand = "12,3456";
		String size = "12";
		String outputMessage = Constants.ERROR_MESSAGE 
				+ Constants.NOT_VALID_SIZE_VALUE_EXCEPTION + size;
				
		impresorLCD.processInput(comand, Constants.DIGIT_SPACE);
		
		assertEquals(outputMessage, outContent.toString().trim());
	}
	
	/**
	 * Validate that the number value of an input contains a character
	 * that is not a digit.
	 */
	@Test
	public void testProcessInputCharacterNotDigit() {
		String comand = "2,34t6";
		String digit = "t";
		String outputMessage = Constants.ERROR_MESSAGE 
				+ Constants.CHARACTER_NOT_DIGIT_EXCEPTION + digit;
				
		impresorLCD.processInput(comand, Constants.DIGIT_SPACE);
		
		assertEquals(outputMessage, outContent.toString().trim());
	}

}
