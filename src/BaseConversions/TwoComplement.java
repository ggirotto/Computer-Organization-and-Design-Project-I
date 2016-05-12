package BaseConversions;

/**
 *
 * @author visinius
 *
*/
public abstract class TwoComplement {
    
    /*ISSO AQUI TUDO FUNCIONA
    ALTERAR QUALQUER UM DESSES MÉTODOS CORRESPONDE A 1000 ANOS
    NO FOGO DO INFERNO DAS ESCOVAS DE BITS
    
    o twocomplement recebe uma string EM DECIMAL a ser twocomplementada e devolve
    uma string EM BINÁRIO adequadamente twcomplementada
    
    o untwocomplement recebe um valor em formato complemento de 2 (ou seja, só
    realmente faz alguma coisa se o valor passado começar com um 1 pq daí é
    negativo) e devolve um valor binário no tamanho desejado com o complemento de
    2 removido, que no caso é o módulo do número.
    */
    public static String twoComplement(String toTwoComplement,int desiredSize)
    {
        if(!(toTwoComplement.charAt(0)=='-')) return FromDecimal.toBinaryUnsigned(toTwoComplement,desiredSize);
        String toOneComplement = Integer.toBinaryString(Integer.parseInt(toTwoComplement.substring(1)));
        while(toOneComplement.length() < desiredSize) toOneComplement = "0"+toOneComplement;
        String oneComplemented="";
        for(char bit : toOneComplement.toCharArray())
            oneComplemented=oneComplemented + (bit == '0' ? '1' : '0');
        return Integer.toBinaryString(Integer.parseInt(oneComplemented,2)+1);       
    }
    
    /*
    NÃO ALTERA ___NADA___ AQUI PELO AMOR DE DEUS
    Só aplica o complemento de 2 de novo. Recebe um valor signed representado
    em complemento de 2 e devolve um valor unsigned representado só em módulo.
    */
    
    public static String unTwoComplement(String twoComplemented, int desiredSize)
    {
        if(twoComplemented.charAt(0)=='0') return twoComplemented;
        String oneComplemented="";
        for(char bit : twoComplemented.toCharArray())
            oneComplemented=oneComplemented + (bit == '0' ? '1' : '0');
        while(oneComplemented.length()<desiredSize) oneComplemented = "0"+oneComplemented;
        String unTwoComplemented = Integer.toBinaryString(Integer.parseInt(oneComplemented,2)+1);
        while(unTwoComplemented.length()<desiredSize) unTwoComplemented = "0"+unTwoComplemented;
        return unTwoComplemented;
    }
    
    
}
