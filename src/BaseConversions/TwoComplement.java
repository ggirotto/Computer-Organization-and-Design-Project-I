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
       
       // Salva o valor na variável binario
        String binario = toTwoComplement;
        
        // Cria uma nova string para concatenar o resultado
        String newString = "";
        
        // Inverte os valroes do número binário. 1 vira 0 e 0 vira 1
        for(int i=0; i<=binario.length()-1; i++){
            if(binario.charAt(i)=='0') newString += "1";
            if(binario.charAt(i)=='1') newString += "0";
        }
        
        // Cria uma nova string para concatenar o resultado.
        String finalString = "";
        
        int base = 0;
        
        // Caso para o último número da string
        if(newString.charAt(newString.length()-1) == '0'){
            finalString += "1";
        }else{
            finalString += "0";
            base = 1;
        }
        
        // Faz a soma do elemento na posição i com 1 + valor da base
        for(int i=newString.length()-2; i>=0; i--){
            if(newString.charAt(i) == '1' && base == 1){
                finalString += "0";
                base = 1;
            }else if(newString.charAt(i) == '1' && base == 0){
                finalString += "1";
            }else if(newString.charAt(i) == '0' && base == 1){
                finalString += "1";
                base = 0;
            }else finalString += newString.charAt(i);
        }
        
        // Inverte a string
        String reverse = new StringBuffer(finalString).reverse().toString();
        
        return reverse;

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
