/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionFactory;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author visinius
 */
public class TipoITest {
    
    public TipoITest() {
    }

    @Test
    public void testAlphaNumericalToHexa() {
        String toHexa="addi $t0,$k0,12";
        assertEquals(InstructionFactory.TipoI.alphaNumericalToHexa(toHexa),"0x2348000C");
    }

    @Test
    public void testHexaToAlphaNumerical() {
    }
    
}
