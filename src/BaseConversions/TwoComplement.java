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
public abstract class TwoComplement {
    public static String twoComplement(String toTwoComplement){
        String oneComplement = "";
        
        for(char bit : toTwoComplement.toCharArray())
            oneComplement = oneComplement + (bit=='0' ? "1" : "0");
        
        int twoComplementedDecimal = Integer.parseInt(oneComplement,2)+1;
        String twoComplementedString = Integer.toBinaryString(twoComplementedDecimal);
        
        if(twoComplementedString.length()>toTwoComplement.length())
            return twoComplementedString.substring(1);
        
        while(twoComplementedString.length()<toTwoComplement.length())
            twoComplementedString = "0"+twoComplementedString;
        
        return twoComplementedString;
    }
    
    public static String unTwoComplement(String twoComplemented)
    {
        return twoComplement(twoComplemented);
    }
    
    
}
