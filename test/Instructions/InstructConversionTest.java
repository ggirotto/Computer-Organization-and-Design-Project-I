/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import BaseConversions.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author visinius
 */
public class InstructConversionTest {
    
    public InstructConversionTest() {}
     
//    @Test
//    public void testBinaryToInt()
//    {
//        String binary = "0";
//        int correctBinary = Integer.parseInt(binary,2);
//        int possiblyCorrectBinary = InstructConversion.binaryToInt(binary);
//        assertEquals(correctBinary,possiblyCorrectBinary);
//        binary = "1";
//        correctBinary = Integer.parseInt(binary,2);
//        possiblyCorrectBinary = InstructConversion.binaryToInt(binary);
//        assertEquals(correctBinary,possiblyCorrectBinary);
//        binary = "11011";
//        correctBinary = Integer.parseInt(binary,2);
//        possiblyCorrectBinary = InstructConversion.binaryToInt(binary);
//        assertEquals(correctBinary,possiblyCorrectBinary);
//        
//    }
    
    @Test
    public void testTwoComplement()
    {
        String binary = "010101"; //um número qualquer - 21
                                  //complemento de 2 é 101011
        String twoComplement=TwoComplement.twoComplement(binary);
        assertEquals(twoComplement, "101011");
        binary = "1111111111111111"; 
        twoComplement=TwoComplement.twoComplement(binary);
        assertEquals(twoComplement,"0000000000000001");
        binary = "0000000000000000";
        twoComplement=TwoComplement.twoComplement(binary);
        assertEquals(twoComplement,"0000000000000000");
    }
    
    @Test
    public void unTwoComplement()
    {
        String binary = "010101";
        assertEquals(binary,TwoComplement.twoComplement(TwoComplement.twoComplement(binary)));
    }
    
}
