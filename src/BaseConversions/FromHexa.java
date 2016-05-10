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
        //Lê cada caractere da string toBinary e o expande corretamente nos 4 dígitos correspondentes
        //O substring(2) esta la porque os 2 primeiros digitos da string hexa sao 0x
        String toBinaried="";
        //Orgulho disso aqui
        for(char hexaDigit : toBinary.substring(2).toCharArray())
            if((String.valueOf(hexaDigit)).matches("[0-9]"))
            {
                //Aquele while ali é para preencher o dígito com os zeros necessários
                //a função toBinaryString não adiciona mais nada além do suficiente
                String toBinariedDigit = "";
                toBinariedDigit+=Integer.toBinaryString(Integer.parseInt(String.valueOf(hexaDigit),2));
                while(toBinariedDigit.length()<4) toBinariedDigit="0"+toBinariedDigit;
                toBinaried=toBinaried+toBinariedDigit;
            }
            else switch (hexaDigit)
            {
                //Nem tanto orgulho dessa parte
                case 'A': case 'a':
                    toBinaried+="1010";
                    break;
                case 'B': case 'b':
                    toBinaried+="1011";
                    break;
                case 'C': case 'c':
                    toBinaried+="1100";
                    break;
                case 'D': case 'd':
                    toBinaried+="1101";
                    break;
                case 'E': case 'e':
                    toBinaried+="1110";
                    break;
                case 'F': case 'f':
                    toBinaried+="1111";
                    break;
            }    
        return toBinaried;
    }
    
    public static String toDecimal(String toDecimal)
    {
        //Passa para binário primeiro, verificando se está em complemento de 2
        if(toBinary(toDecimal).charAt(0)=='1') toDecimal=TwoComplement.unTwoComplement(toDecimal);
        else toDecimal=toBinary(toDecimal);
        
       
    }
    
}
