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
    
    public static String toHexaWAddres(String toHexa)
    {
        String toHexaFromDecimalFinal = "004";
        
        String toHexaFromDecimal=Integer.toHexString(Integer.parseInt(toHexa));
        
        // Adiciona os 0's necessários para completar uma operação em hexa (8 byts)
        for(int i=0; i<=(4-toHexaFromDecimal.length()); i++) toHexaFromDecimalFinal += "0";
        toHexaFromDecimalFinal+=toHexaFromDecimal;
        return toHexaFromDecimalFinal;
    }
    
    public static String toBinary(String toBinary,int desiredSize)
    {
        // Converte o valor para inteiro
        int bin = Integer.parseInt(toBinary);
        
        // Passa para string novamente em valor binário
        String binario;
        if(toBinary.matches("-[0-9]+")) bin = Math.abs(-bin);
        
        binario = Integer.toBinaryString(bin);
        
        String retorno = "";
        
        /* Calcula o número de 0's necessários para se adicionar no início
        da string para completar o número de bits da operação
         Exemplo: opcode - 6bits
        */
        int novoNro = desiredSize - binario.length();
        
        // Adiciona os 0's
        for(int i=1; i<= novoNro; i++) retorno += "0";
        
        // Adiciona o código cnovertido para binário aos 0's já adicionados
        retorno += binario;
        if(toBinary.matches("-[0-9]+")) return TwoComplement.twoComplement(retorno);
        return retorno;
    }
    
    
}
