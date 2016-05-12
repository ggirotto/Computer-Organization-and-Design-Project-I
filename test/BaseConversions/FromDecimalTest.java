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
    public void testToBinarySigned()
    {
        assertEquals(FromDecimal.toBinarySigned("128", 9),"010000000");
        assertEquals(FromDecimal.toBinarySigned("129", 9),"010000001");
        assertEquals(FromDecimal.toBinarySigned("127", 8),"01111111");
        assertEquals(FromDecimal.toBinarySigned("-128", 8),"10000000");
        assertEquals(FromDecimal.toBinarySigned("-129", 9),"101111111");
        assertEquals(FromDecimal.toBinarySigned("-127", 8),"10000001");
    }
    
    @Test
    
    public void testToBinaryUnsigned()
    {
        assertEquals(FromDecimal.toBinaryUnsigned("128",8),"10000000");
        assertEquals(FromDecimal.toBinaryUnsigned("127",8),"01111111");
        assertEquals(FromDecimal.toBinaryUnsigned("129",8),"10000001");
        assertEquals(FromDecimal.toBinaryUnsigned("63",6),"111111");
    }
    
    
}
