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

    //PELO AMOR DE DEUS SO USAR ESSE METODO PARA VALORES NEGATIVOS
    
    @Test
    public void testTwoComplement() {                                     
        String toTwoComplement = TwoComplement.twoComplement("-7",4);
        assertEquals(toTwoComplement,                        "1001"); 
        toTwoComplement=TwoComplement.twoComplement("-1",2);
        assertEquals(toTwoComplement,               "11");
        toTwoComplement=TwoComplement.twoComplement("-35",9);
        assertEquals(toTwoComplement,               "111011101");
        toTwoComplement=TwoComplement.twoComplement("63",6);
        assertEquals(toTwoComplement,FromDecimal.toBinarySigned("63", 6));
    }

    @Test
    public void testUnTwoComplement() {
        String toUnTwoComplement = TwoComplement.unTwoComplement("010001",6);
        assertEquals(toUnTwoComplement,                          "010001");
        toUnTwoComplement = TwoComplement.unTwoComplement("111111",6);
        assertEquals(toUnTwoComplement,                   "000001");
        toUnTwoComplement = TwoComplement.unTwoComplement("1010111",7);
        assertEquals(toUnTwoComplement,                   "0101001");
    }

    /*se o primeiro dígito for 1 1 -> corta fora o 1, aplica o complemento de 2,
    corta fora o 1 que aparece e devolve o resto com um 0 na frente*/
    
}
