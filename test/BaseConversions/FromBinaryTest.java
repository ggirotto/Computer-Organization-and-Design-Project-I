/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseConversions;

import BaseConversions.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author visinius
 */
public class FromBinaryTest {
    
    public FromBinaryTest() {}
    

    @Test
    public void toBinaryTest()
    {
        String binary="101010";
        assertEquals(binary,FromBinary.toBinary(binary));
    }
    
    @Test
    public void toDecimalTest()
    {
        String binary ="0";
        assertEquals("0",FromBinary.toDecimal(binary));
        binary = "01";
        assertEquals("1",FromBinary.toDecimal(binary));
        binary = "11";
        assertEquals("-1",FromBinary.toDecimal(binary));     
        binary = "111000111";
        assertEquals("-57",FromBinary.toDecimal(binary));
    }
    
    @Test
    public void toHexaTest()
    {
        String binary = "1111";
        assertEquals("0xF",FromBinary.toHexa(binary));
        binary = "0111";
        assertEquals("0x7",FromBinary.toHexa(binary));
        binary = "11110000101011100010";
        assertEquals("0xF0AE2",FromBinary.toHexa(binary));
    }

}
