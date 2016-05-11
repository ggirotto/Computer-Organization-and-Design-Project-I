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
    
    public static String toDecimalUnsigned (String toDecimal)
    {
        return ""+Integer.parseInt(toDecimal,2);
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
        String result = "";
        /*  Separa a string total em 7 partes de 4 caracteres.
            Para cada parte, faz a conversão para hexadecimal
            Aplica este valor final na string retorno
        */
        
        int i = 0;
        int base = 0;
        int calc = 0;
        
        while(i<=28){
            String sub = toHexa.substring(i, i+4);
            for(int j=3; j>=0; j--){
                int val = Character.getNumericValue(sub.charAt(j));
                calc += val * Math.pow(2,base);
                base++;
            }
            switch(calc){
                case 10:
                    result += "a";
                    break;
                case 11:
                    result += "b";
                    break;
                case 12:
                    result += "c";
                    break;
                case 13:
                    result += "d";
                    break;
                case 14:
                    result += "e";
                    break;
                case 15:
                    result += "f";
                    break;
                default:
                    result += calc;
            }
            calc = 0;
            i+=4;
            base=0;
        }
        
               
        return result;
    }
    
}
