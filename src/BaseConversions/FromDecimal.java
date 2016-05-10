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
public abstract class FromDecimal{
    
    public static String toDecimal(String toDecimal)
    {
        return toDecimal;
    }
    
    public static String toHexa(String toHexa)
    {
        String toHexaFromDecimal = "";
        
        if(toHexa.contains("-")) toHexaFromDecimal = FromBinary.toHexa(TwoComplement.twoComplement
            (Integer.toString(Integer.parseInt(toHexa.substring(1)),2)));
        
        else toHexaFromDecimal=FromBinary.toHexa(Integer.toString(Integer.parseInt(toHexa),2));
        
        return toHexaFromDecimal;
    }
    
    public static String toBinary(String toBinary)
    {
        String toBinaryFromDecimal="";
        if(toBinary.contains("-"))       
            toBinaryFromDecimal = "1"+TwoComplement.twoComplement(
            Integer.toString(Integer.parseInt(toBinary.substring(1)),2));
                   
       else toBinaryFromDecimal = "0"+Integer.toString(Integer.parseInt(toBinary),2);
        
       return toBinaryFromDecimal;
    }
    
    
}
