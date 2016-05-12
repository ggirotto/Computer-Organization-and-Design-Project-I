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
public class TipoJTest {
    
    public TipoJTest() {}
    
    @Before
    public void populateAddresses(){
        for(int i=0;i<100;i++) HesselIntensifies.labelAddresses.put("vinicius"+i, i*4);
    }

//    @Test
//    public void testAlphaNumericalToHexa() {   
//        String toHexa="j vinicius20";
//        assertEquals(InstructionFactory.TipoJ.alphaNumericalToHexa(toHexa),"0x08100014");
//    }

    @Test
    public void testHexaToAlphaNumerical() {
        String toAlphaN="0x0810000d";
        assertEquals(InstructionFactory.TipoJ.hexaToAlphaNumerical(toAlphaN),"j vinicius13");
    }
    
}
