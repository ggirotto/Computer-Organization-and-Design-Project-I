/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author visinius
 */
public class InstructConversionTest {
    
    public InstructConversionTest() {}
     
    @Test
    public void testBinaryToInt()
    {
        String binary = "0";
        int correctBinary = Integer.parseInt(binary,2);
        int possiblyCorrectBinary = InstructConversion.binaryToInt(binary);
        assertEquals(correctBinary,possiblyCorrectBinary);
        binary = "1";
        correctBinary = Integer.parseInt(binary,2);
        possiblyCorrectBinary = InstructConversion.binaryToInt(binary);
        assertEquals(correctBinary,possiblyCorrectBinary);
        binary = "11011";
        correctBinary = Integer.parseInt(binary,2);
        possiblyCorrectBinary = InstructConversion.binaryToInt(binary);
        assertEquals(correctBinary,possiblyCorrectBinary);
        
    }
}
