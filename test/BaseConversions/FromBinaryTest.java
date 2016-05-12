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
    public void toDecimalSignedTest()
    {
        String toDecimalSigned="0111";
        assertEquals(FromBinary.toDecimalSigned(toDecimalSigned),"7");
        toDecimalSigned="1111";
        assertEquals(FromBinary.toDecimalSigned(toDecimalSigned),"-1");
        toDecimalSigned="1101101";
        assertEquals(FromBinary.toDecimalSigned(toDecimalSigned),"-19");
        toDecimalSigned="111110";
        assertEquals(FromBinary.toDecimalSigned(toDecimalSigned),"-2");
    }
    
    @Test
    public void toDecimalUnsigned()
    {
        String toDecimalUnsigned="0111";
        assertEquals(FromBinary.toDecimalUnsigned(toDecimalUnsigned),"7");
        toDecimalUnsigned="1111";
        assertEquals(FromBinary.toDecimalUnsigned(toDecimalUnsigned),"15");
        toDecimalUnsigned="1101101";
        assertEquals(FromBinary.toDecimalUnsigned(toDecimalUnsigned),"109");
        toDecimalUnsigned="111110";
        assertEquals(FromBinary.toDecimalUnsigned(toDecimalUnsigned),"62");
    }
    
    @Test
    public void toHexaTest()
    {
        String binary = "11111111111111111111111111111111";
        assertEquals("0xffffffff",FromBinary.toHexa(binary,8));
    }

}
