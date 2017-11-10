package com.pslcorp.domain;

import java.util.Iterator;
import java.util.List;

import com.pslcorp.utils.Constants;
import com.pslcorp.utils.Utilities;

/**
 * Class that contains the variables and methods related to LCD printer.
 * 
 * @author German Perez
 */
public class ImpresorLCD {
		
	/** Fixed points */
    private final int[] fixedPoint1;
    private final int[] fixedPoint2;
    private final int[] fixedPoint3;
    private final int[] fixedPoint4;
    private final int[] fixedPoint5;
    
    /** Matrix variables for size, rows and columns */
    private int size;
    private int digitRows;
    private int digitColumns;
    private int totalRows;
    private int totalColumns;
    
    private String[][] impressionMatrix;
    
    /**
     * Class constructor.
     */
    public ImpresorLCD() {
        this.fixedPoint1 = new int[2];
        this.fixedPoint2 = new int[2];
        this.fixedPoint3 = new int[2];
        this.fixedPoint4 = new int[2];
        this.fixedPoint5 = new int[2];
    }
    
    
    /**
     * This method set the initial values for the matrix variables.
     * 
     * @param size        the size of the digit segment
     * @param printNumber the number to print
     * @param space       the space between digits
     */
    private void setMatrixValues(int size, String printNumber, int space) {
    	// Set the size
    	this.size = size;

        // Calculate the number of rows of each digit
        this.digitRows = (2 * this.size) + 3;

        // Calculate the number of columns of each digit
        this.digitColumns = this.size + 2;

        // Calculate the matrix total rows in which the digits will be stored
        this.totalRows = this.digitRows;

        // Calculate the matrix total columns in which the digits will be stored
        this.totalColumns = (this.digitColumns * printNumber.length()) 
        		+ (space * printNumber.length());

        // Create the matrix that will store the numbers to print
        this.impressionMatrix = new String[this.totalRows][this.totalColumns];
        
        // Initialize the matrix
        for (int i = 0; i < this.totalRows; i++) {
        	for (int j = 0; j < this.totalColumns; j++) {
        		this.impressionMatrix[i][j] = " ";
        	}
        }
    }
    
    
    /**
     * This method set the initial values for the fixed points.
     * 
     * @param pivotX       the pivot X value
     * @param digitRows    the digit rows of the matrix
     * @param digitColumns the digit columns of the matrix
     */
    private void setFixedPoints(int pivotX, int digitRows, int digitColumns) {
    	this.fixedPoint1[0] = 0;
    	this.fixedPoint1[1] = 0 + pivotX;

    	this.fixedPoint2[0] = (digitRows / 2);
    	this.fixedPoint2[1] = 0 + pivotX;

    	this.fixedPoint3[0] = (digitRows - 1);
    	this.fixedPoint3[1] = 0 + pivotX;

    	this.fixedPoint4[0] = (digitColumns - 1);
    	this.fixedPoint4[1] = (digitRows / 2) + pivotX;

    	this.fixedPoint5[0] = 0;
    	this.fixedPoint5[1] = (digitColumns - 1) + pivotX;
    }
    
    
    /**
     * This method adds a line to the impression matrix.
     * 
     * @param matrix        the impression matrix
     * @param point         a pivot point
     * @param fixedPosition the fixed position
     * @param size          the segment size
     * @param character     the segment character
     */
    private void addLineToMatrix(String[][] matrix, int[] point, String fixedPosition, 
    		int size, String character) {

    	try {
	    	if (Constants.X_POSITION.equalsIgnoreCase(fixedPosition)) {
	    		for (int i = 1; i <= size; i++) {
	    			int value = point[1] + i;
	    			matrix[point[0]][value] = character;
	    		}
	    	} else {
	    		for (int i = 1; i <= size; i++) {
	    			int value = point[0] + i;
	    			matrix[value][point[1]] = character;
	    		}
	    	}
	    	
    	} catch (ArrayIndexOutOfBoundsException e) {
    		System.out.println(Constants.ERROR_MESSAGE + e.getMessage());
    	}
    }
    
    
    /**
     * This method adds a segment to the impression matrix.
     *
     * @param segment the segment to add
     */
    private void addSegmentToMatrix(int segment) {
    	
    	switch (segment) {
        	case 1:
        		addLineToMatrix(this.impressionMatrix, this.fixedPoint1, Constants.Y_POSITION,
        				this.size, Constants.VERTICAL_CHARACTER);
                break;
        	case 2:
        		addLineToMatrix(this.impressionMatrix, this.fixedPoint2, Constants.Y_POSITION,
        				this.size, Constants.VERTICAL_CHARACTER);
        		break;
        	case 3:
        		addLineToMatrix(this.impressionMatrix, this.fixedPoint5, Constants.Y_POSITION,
        				this.size, Constants.VERTICAL_CHARACTER);
        		break;
        	case 4:
        		addLineToMatrix(this.impressionMatrix, this.fixedPoint4, Constants.Y_POSITION,
        				this.size, Constants.VERTICAL_CHARACTER);
        		break;
        	case 5:
        		addLineToMatrix(this.impressionMatrix, this.fixedPoint1, Constants.X_POSITION,
        				this.size, Constants.HORIZONTAL_CHARACTER);
        		break;
        	case 6:
        		addLineToMatrix(this.impressionMatrix, this.fixedPoint2, Constants.X_POSITION,
        				this.size, Constants.HORIZONTAL_CHARACTER);
        		break;
        	case 7:
        		addLineToMatrix(this.impressionMatrix, this.fixedPoint3, Constants.X_POSITION,
        				this.size, Constants.HORIZONTAL_CHARACTER);
        		break;
        	default:
        		break;
    	}
    }
    
    
    /**
     * This method defines the segments that make up a digit and from
     * those segments adds the representation of the digit to the matrix.
     *
     * @param digit the digit to add
     */
    private void addDigitToMatrix(int digit) {
    	
    	// List that contains the segments of a digit
    	List<Integer> segList = Utilities.getSegmentList(digit);    	

    	// Set the segments of the digit
    	Iterator<Integer> iterator = segList.iterator();

    	while (iterator.hasNext()) {
    		addSegmentToMatrix(iterator.next());
    	}
    }
    
    
    /**
     * This method prints a number.
     *
     * @param size        the size of the digit segment
     * @param printNumber the number to print
     * @param space       the space between digits
     * 
     * @throws IllegalArgumentException
     */
    private void printNumber(int size, String printNumber, int space)
    		throws IllegalArgumentException {
    	
    	// Initialize the values
    	setMatrixValues(size, printNumber, space);

        // Create the digit array
        char[] digits = printNumber.toCharArray();
        int pivotX = 0;

        for (char digit : digits) {
        	
        	// Validate that the character is a digit
        	if (!Character.isDigit(digit)) {
        		throw new IllegalArgumentException(
        				Constants.CHARACTER_NOT_DIGIT_EXCEPTION + digit);
        	}

        	int number = Integer.parseInt(String.valueOf(digit));

        	// Calculate fixed points
        	setFixedPoints(pivotX, this.digitRows, this.digitColumns);

        	// Calculate the new pivot X value
        	pivotX = pivotX + this.digitColumns + space;

        	addDigitToMatrix(number);
        }

        // Print the matrix
        Utilities.printMatrix(impressionMatrix);
    }
    
    /**
     * This method processes the input that contains the segment size
     * of the digits and the digits to be printed.
     *
     * @param comand     the segment size of the digits and the number to print
     * @param digitSpace the space between digits
     */
    public void processInput(String comand, int digitSpace) {
       
        String[] parameters;
        int size;
        
        try {
	        // Validates that chain contains a comma
	        if (comand != null && !comand.contains(Constants.COMMA_CHARACTER)) {
	        	throw new IllegalArgumentException(
	        			Constants.COMMA_MISSING_CHARACTER_EXCEPTION + comand);
	        }
	       
	        // The chain split is made
	        parameters = comand.split(",");
	       
	        // Validate the maximum amount of parameters
	        if (parameters.length > Constants.TOTAL_PARAMETERS) {
	        	throw new IllegalArgumentException(
	        			Constants.MORE_THAN_TWO_PARAMETERS_EXCEPTION + comand); 
	        }
	       
	        // Validate the minimum amount of parameters
	        if (parameters.length < Constants.TOTAL_PARAMETERS) {
	        	throw new IllegalArgumentException(
	        			Constants.LESS_THAN_TWO_PARAMETERS_EXCEPTION + comand); 
	        }
	       
	        // Validates that the SIZE parameter is of numeric type
	        if (Utilities.isNumeric(parameters[0])) {
	        	size = Integer.parseInt(parameters[0]);
	           
	        	// Validates that the SIZE parameter is between 1 and 10
	        	if (size < Constants.MINIMUM_SIZE || size > Constants.MAXIMUM_SIZE) {
	        		throw new IllegalArgumentException(
	        				Constants.NOT_VALID_SIZE_VALUE_EXCEPTION + size);
	           }
	        } else {
	        	throw new IllegalArgumentException(
	        			Constants.NOT_NUMBER_SIZE_EXCEPTION + parameters[0]);
	        }

	        // Prints the number
	        printNumber(size, parameters[1], digitSpace);
	        
        } catch (IllegalArgumentException e) {
            System.out.println(Constants.ERROR_MESSAGE + e.getMessage());
        }
    }
}
