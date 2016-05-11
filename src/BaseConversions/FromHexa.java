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
    

    public static String toHexa(String toHexa)
    {
        return toHexa;
    }
    
    public static String toBinary(String toBinary)
    {
        // Retira o '0x' da instrução em hexa
        String newLine = toBinary.substring(2,toBinary.length());
        
        String total = "";
        String j;
        
        // Faz a conversão de cada elemento para binário.
        for(int i=0; i<= newLine.length()-1; i++){
            j = newLine.charAt(i)+"";
            if(j.matches("[0-9]+")) total += FromDecimal.toBinary(j,4);
            else {
                switch(j.toLowerCase()){
                    case "a":
                        total += FromDecimal.toBinary("10",4);
                        break;
                    case "b":
                        total += FromDecimal.toBinary("11",4);
                        break;
                    case "c":
                        total += FromDecimal.toBinary("12",4);
                        break;
                    case "d":
                        total += FromDecimal.toBinary("13",4);
                        break;
                    case "e":
                        total += FromDecimal.toBinary("14",4);
                        break;
                    case "f":
                        total += FromDecimal.toBinary("15",4);
                        break;
                    
                }
            }
        }
        return total;
    }
    
    public static String toDecimal(String toDecimal)
    {
        String fromHexaToDecimal="";
        //Passa para binário primeiro, verificando se está em complemento de 2
        if(toBinary(toDecimal).charAt(0)=='1')
            fromHexaToDecimal="1"+FromBinary.toDecimal(TwoComplement.unTwoComplement(toDecimal));
        else fromHexaToDecimal="0"+ FromBinary.toDecimal(toBinary(toDecimal));       
        return fromHexaToDecimal;
    }
    
}
