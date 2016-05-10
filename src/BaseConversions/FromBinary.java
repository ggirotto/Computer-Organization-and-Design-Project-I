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
    
    public static String toBinary(String toBinary)
    {
        return toBinary;
    }
    
    public static String toDecimal(String toDecimal)
    {
        String toDecimaled = "";
        if(toDecimal.charAt(0)=='1')
        {
            toDecimal = TwoComplement.unTwoComplement(toDecimal);
            toDecimaled += "-";         
        }
        
        toDecimaled+=Integer.parseInt(toDecimal,2);
        
        return toDecimaled;        
    }
    
    public static String toHexa(String toHexa)
    {
        int toHexaAsDecimal = Integer.parseInt(toHexa,2);
        String toHexaed = Integer.toString(toHexaAsDecimal,16).toUpperCase();        
        return "0x"+toHexaed;
    }
    
}
