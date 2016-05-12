/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseConversions;

/**
 *
 * @author visinius
 */
public abstract class FromBinary{
    
    //FUNCIONANDO (tem como dar errado?)
    public static String toDecimalUnsigned (String toDecimal)
    {
        return ""+Integer.parseInt(toDecimal,2);
    }
    
    /*Recebe um valor binário representado em complemento de 2 (com sinal)
        e devolve um vlaor decimal com o sinal ajustado
    */
    
    //FUNCIONANDO, NÃO MEXER MAIS;
    public static String toDecimalSigned(String toDecimal)
    {
        if(toDecimal.charAt(0)=='1')
            return "-"+Integer.parseInt(TwoComplement.unTwoComplement(toDecimal,32),2);         
        
        return ""+Integer.parseInt(toDecimal,2);
    }
    
    //FUNCIONANDO, NÃO MEXER MAIS
    public static String toHexa(String toHexa)
    {
        return "0x"+Integer.toString(Integer.parseInt(toHexa,2),16);
    }
    
}
