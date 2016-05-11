/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionFactory;

import InstructionFactory.TipoR;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author visinius
 */
public class TipoRTest {
    
    public TipoRTest() {
    }

    @Test
    public void testAlphaNumericalToHexa() {
        String testInstruction = "add $t0,$v0,$a3";
        assertEquals("0x00474020",InstructionFactory.TipoR.alphaNumericalToHexa(testInstruction));
        testInstruction = "sll $sp,$at,8";
        assertEquals("0x0001ea00",InstructionFactory.TipoR.alphaNumericalToHexa(testInstruction));
        testInstruction = "srl $gp,$zero,31";
        assertEquals("0x0000e7c2",InstructionFactory.TipoR.alphaNumericalToHexa(testInstruction));
    }
    

    @Test
    public void testHexaToAlphaNumerical(){
       String testInstruction= "0x00474020";
       assertEquals("add $t0,$v0,$a3",InstructionFactory.TipoR.hexaToAlphaNumerical(testInstruction, "add"));
    }

    
}
