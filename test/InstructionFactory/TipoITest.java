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
        String toHexa="addi $t0,$v0,";
        assertEquals(InstructionFactory.TipoI.alphaNumericalToHexa(toHexa),"0x20481900");
    }

    @Test
    public void testHexaToAlphaNumerical() {
    }
    
}
