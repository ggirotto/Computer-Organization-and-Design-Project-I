/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseConversions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author visinius
 */
public class FromHexaTest {
    
    public FromHexaTest() {
    }

    @Test   
    public void testToBinary()
    {
        String toBinary="0xF";
        assertEquals(FromHexa.toBinary(toBinary),"1111");
        toBinary="0xff";
        assertEquals(FromHexa.toBinary(toBinary),"11111111");
        toBinary="0xf01";
        assertEquals(FromHexa.toBinary(toBinary),"111100000001");
        toBinary="0x4f35ea";
        assertEquals(FromHexa.toBinary(toBinary),"010011110011010111101010");
        
    }
    
    @Test
    public void testToDecimalSigned()
    {
        String toDecimal="0x7";
        assertEquals(FromHexa.toDecimalSigned(toDecimal),"7");
        toDecimal="0xFF";
        assertEquals(FromHexa.toDecimalSigned(toDecimal),"-1");
        toDecimal="0x8F2E4D";
        assertEquals(FromHexa.toDecimalSigned(toDecimal),"-7393715");
    }    
    
}
