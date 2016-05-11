package BaseConversions;

/**
 *
 * @author visinius
 *
*/
public abstract class TwoComplement {
    
    /*Inverte todos os dígitos da string a ser convertida, passa para decimal,
    soma 1, inverte de volta para binário e, se necessário, completa com os zeros.
    Captura o caso especial do zero, em que da um overflow e tem que cortar fora
    um digito.
    Lembrando que recebe um valor binário unsigned e devolve um valor signed
    em complemento de 2.
    */
    public static String twoComplement(String toTwoComplement){
       
       String twoComplement = "";      
       for(char bit : toTwoComplement.toCharArray())
           twoComplement = twoComplement + (bit=='0'? '1': '0');
        
       twoComplement = ""+Integer.toBinaryString(Integer.parseInt(twoComplement,2)+1);
       if(twoComplement.length()>toTwoComplement.length())
           twoComplement=twoComplement.substring(1);
       
       while (twoComplement.length()<toTwoComplement.length())
           twoComplement = "0"+twoComplement;
       
       return twoComplement;

    }
    
    /*
    Só aplica o complemento de 2 de novo. Recebe um valor signed representado
    em complemento de 2 e devolve um valor unsigned representado só em módulo.
    */ 
    public static String unTwoComplement(String twoComplemented)
    {
        return twoComplement(twoComplemented);
    }
    
    
}
