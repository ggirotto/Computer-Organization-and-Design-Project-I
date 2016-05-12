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
public abstract class FromHexa{
    
    //FUNCIONANDO, NÃO MEXER MAIS
    public static String toBinary(String toBinary)
    {
        // Retira o '0x' da instrução em hexa
        
        String binaried="";
        
        for(char hexaDigit : toBinary.substring(2).toCharArray())
        {   
            String addToBinary=Integer.toBinaryString(Integer.parseInt(""+hexaDigit,16));
            while(addToBinary.length()<4) addToBinary="0"+addToBinary;
            binaried=binaried+addToBinary;
        }
        return binaried;
    }
    
    //FUNCIONANDO, NÃO MEXER MAIS
    public static String toDecimalSigned(String toDecimal)
    {
        String binaryToCheck = toBinary(toDecimal);
        if(binaryToCheck.charAt(0)=='0') return ""+Integer.parseInt(toDecimal.substring(2),16);               
        
        return FromBinary.toDecimalSigned(binaryToCheck);
    }
    
    //corta fora o 0x
    //FUNCIONANDO, NÃO MEXER MAIS
    public static String toDecimalUnsigned(String toDecimal)
    {
        return Integer.toString(Integer.parseInt(toDecimal.substring(2),16));        
    }
    
}
