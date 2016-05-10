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
public class FromDecimalTest {
    
    public FromDecimalTest() {
    }

    @Test
    public void testToDecimal() {
    }

    @Test
    public void testToHexa() {
        String decimal = "64";
        assertEquals("0x40",FromDecimal.toHexa(decimal));
        decimal = "63";
        assertEquals("0x3F",FromDecimal.toHexa(decimal));
        decimal = "-63"; 
        assertEquals("0x1",FromDecimal.toHexa(decimal));
        decimal = "-64";
        assertEquals("0x40",FromDecimal.toHexa(decimal));
        decimal = "-683792";
        assertEquals("0x590F0",FromDecimal.toHexa(decimal));    
    }
    
    @Test
    public void testToBinary()
    {
        String decimal = "-63";
        assertEquals("1000001",FromDecimal.toBinary(decimal));
        decimal = "64";
        assertEquals("01000000",FromDecimal.toBinary(decimal));
    }
    
    public class FromDecimalImpl extends FromDecimal {
    }
    
}
