package com.pslcorp.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains generic methods.
 * 
 * @author German Perez
 */
public class Utilities {
	
	/**
     * This method validates if a string chain is numeric.
     *
     * @param chain the string chain to validate
     */  
    public static boolean isNumeric(String chain) {
    	try {
    		Integer.parseInt(chain);
    		return true;
    	} catch (NumberFormatException ex) {
    		return false;
    	}
    }
    
    
    /**
     * This method prints the whole matrix.
     *
     * @param rows    the digits segment size
     * @param columns the number to print
     * @param matrix  the matrix to print
     */    
    public static void printMatrix(String[][] matrix) {
    	for (int i = 0; i < matrix.length; i++) {
        	for (int j = 0; j < matrix[i].length; j++) {
        		System.out.print(matrix[i][j]);
        	}
        	System.out.println();
        }
    }
    
    
    /**
     * This method defines the segments that make up a digit.
     * 
     * @param digit the digit to add
     * 
     * @return the list with the segments that make up a digit.
     */
    public static List<Integer> getSegmentList(int digit) {
    	
    	// List that contains the segments of a digit
    	List<Integer> segList = new ArrayList<>();

    	switch (digit) {
           	case 1:
           		segList.add(3);
           		segList.add(4);
           		break;
           	case 2:
           		segList.add(5);
           		segList.add(3);
           		segList.add(6);
           		segList.add(2);
           		segList.add(7);
           		break;
           	case 3:
           		segList.add(5);
           		segList.add(3);
           		segList.add(6);
           		segList.add(4);
           		segList.add(7);
           		break;
           	case 4:
           		segList.add(1);
           		segList.add(6);
           		segList.add(3);
           		segList.add(4);
           		break;
           	case 5:
           		segList.add(5);
           		segList.add(1);
           		segList.add(6);
           		segList.add(4);
           		segList.add(7);
           		break;
           	case 6:
           		segList.add(5);
           		segList.add(1);
           		segList.add(6);
               	segList.add(2);
               	segList.add(7);
               	segList.add(4);
               	break;
           	case 7:
           		segList.add(5);
           		segList.add(3);
           		segList.add(4);
           		break;
           	case 8:
           		segList.add(1);
           		segList.add(2);
           		segList.add(3);
           		segList.add(4);
           		segList.add(5);
           		segList.add(6);
           		segList.add(7);
           		break;
           	case 9:
           		segList.add(1);
           		segList.add(3);
           		segList.add(4);
           		segList.add(5);
           		segList.add(6);
           		segList.add(7);
           		break;
           	case 0:
           		segList.add(1);
           		segList.add(2);
           		segList.add(3);
           		segList.add(4);
           		segList.add(5);
           		segList.add(7);
           		break;
           	default:
           		break;
    	}
    	
    	return segList;
    }
}
