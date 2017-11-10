package com.pslcorp.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.pslcorp.domain.ImpresorLCD;
import com.pslcorp.utils.Constants;

/**
 * Class that tests the ImpresorLCD class.
 * 
 * @author German Perez
 */
public class LCDTester {
    
    public static void main(String[] args) {
        
        List<String> comandList = new ArrayList<>();
        String comand;
        
        try {
            try (Scanner lector = new Scanner(System.in)) {
            	
                System.out.print("Entrada: ");
                
                /*
                 * Read each parameter of the input and if it is not the final
                 * chain (0,0) add it to the list.
                 */
                do {
                    comand = lector.next();
                    
                    if (!Constants.FINAL_CHAIN.equalsIgnoreCase(comand)) {
                    	comandList.add(comand);
                    }
                } while (!Constants.FINAL_CHAIN.equalsIgnoreCase(comand));
            }

            ImpresorLCD impresorLCD = new ImpresorLCD();

            Iterator<String> iterator = comandList.iterator();
            
            while (iterator.hasNext()) {
            	impresorLCD.processInput(iterator.next(), Constants.DIGIT_SPACE);                    
            }
            
        } catch (Exception ex) {
            System.out.println(Constants.UNEXPECTED_ERROR);
        }
    }
}