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
    public void testToHexa() {
    }

    @Test
    public void testToBinary() {
        String hexa = "0xFFF";
        assertEquals("111111111111",FromHexa.toBinary(hexa));
        hexa = "0x7EA9";
        assertEquals("0111111010101001",FromHexa.toBinary(hexa));
    }
    
    @Test
    public void testToDecimal(){
        String decimal = "0xE";
        assertEquals("14",FromHexa.toDecimal(decimal));
    }
    
}
