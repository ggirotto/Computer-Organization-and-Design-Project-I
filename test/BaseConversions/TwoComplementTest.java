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
public class TwoComplementTest {
    
    public TwoComplementTest() {
    }

    @Test
    public void testTwoComplement() {
        String toTwoComplement = TwoComplement.twoComplement("10001");
        assertEquals(toTwoComplement,                        "101111");
        toTwoComplement = TwoComplement.twoComplement("01111" );
        assertEquals(toTwoComplement,                 "110001");
        toTwoComplement = TwoComplement.twoComplement("000000");
        assertEquals(toTwoComplement,                 "1000000");
        toTwoComplement = TwoComplement.twoComplement("111111");
        assertEquals(toTwoComplement,                "1000001");
    }

    @Test
    public void testUnTwoComplement() {
    }
    
}
