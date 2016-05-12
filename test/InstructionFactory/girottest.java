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
public class girottest {
    
    public girottest() {
    }

    @Before
    public void populateLabelAddresses(){
        HesselIntensifies.labelAddresses.put("saoIguais", 14*4);
    }
    
    @Test
    public void testAlphaNumericalToHexa7() {
        String toHexa="beq $a0,$a1,saoIguais";
        assertEquals(InstructionFactory.TipoI.alphaNumericalToHexa(toHexa),"0x1085000d");
    }

    @Test
    public void testHexaToAlphaNumerical() {
    }
    
}