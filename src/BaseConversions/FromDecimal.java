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
    
    
    public static String toHexaWAddres(String toHexa)
    {
        String toHexaFromDecimalFinal = "004";
        
        String toHexaFromDecimal=Integer.toHexString(Integer.parseInt(toHexa));
        
        // Adiciona os 0's necessários para completar uma operação em hexa (8 bytes)
        for(int i=0; i<=(4-toHexaFromDecimal.length()); i++) toHexaFromDecimalFinal += "0";
        toHexaFromDecimalFinal+=toHexaFromDecimal;
        return toHexaFromDecimalFinal;
    }
    
    //NÃO MEXER NISSO AQUI DE NOVO
    public static String toBinaryUnsigned(String toBinary,int desiredSize)
    {
        String unsignedBinary = Integer.toBinaryString(Integer.parseInt(toBinary));
        while (unsignedBinary.length() < desiredSize) unsignedBinary = "0"+unsignedBinary;
        return unsignedBinary;
    }
    
    //Recebe um decimal!!
    //NÃO CORTA O SINAL DE MENOS AQUI
    //NÃO VOLTAR A MEXER NESSE MÉTODO
    public static String toBinarySigned(String toBinary,int desiredSize)
    {
        if(toBinary.contains("-")) return TwoComplement.twoComplement(toBinary,desiredSize);          
        String toBinaried=Integer.toBinaryString(Integer.parseInt(toBinary));
        while(toBinaried.length()<desiredSize) toBinaried="0"+toBinaried;
        return toBinaried;
    }
    
    
}
