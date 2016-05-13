/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionFactory;

import hesselintensifies.HesselIntensifies;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author visinius
 */
public class TipoITest {
    
    public TipoITest() {
    }

    @Before
    public void populateLabelAddresses(){
        HesselIntensifies.labelAddresses.put("saoIguais", 14*4);
    }
    
//    @Test
//    public void testAlphaNumericalToHexa() {
//        String toHexa="addi $t1,$a2,15";
//        assertEquals(InstructionFactory.TipoI.alphaNumericalToHexa(toHexa),"0x20c9000f");
//    }

    @Test   
    public void testHexaToAlphaNumerical() {
        String toAN = "0x8d090000";
        assertEquals(InstructionFactory.TipoI.hexaToAlphaNumerical(toAN),"lw $t1,0($t0)");
    }
    
}
