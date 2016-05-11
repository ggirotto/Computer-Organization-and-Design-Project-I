package BaseConversions;

/**
 *
 * @author visinius
 * Resumão do funcionamento:
 * twoComplement: pega uma string de tamanho N, faz o complemento de 2 dela
 * (não verifica NADA), completa com todos os zeros necessários à esquerda 
 * (o motivo disso é explicado no método) e adiciona um "1" no final, devolvendo
 * uma String de tamanho N+1 - o que faz sentido
 * 
 * unTwoComplement: pega uma string de tamanho N que É uma representação em
 * complemento de 2 e, se for positivo (primeiro dígito 0), devolve como está.
 * Caso contrário (primeiro dígito 1 - é negativo), o método corta fora o 1,
 * aplica o complemento de 2 (método acima) sobre essa string, corta fora o 1
 * adicionado pelo método no final e adiciona um 0 (para representar que está 
 * sem sinal). Devolve uma string de tamanho N.
*/
public abstract class TwoComplement {
    public static String twoComplement(String toTwoComplement){
        /*
            Sempre bom lembrar que a representação em complemento de 2 exige um bit
            para o sinal, logo este método deve receber apenas o "módulo", que
            aparece como uma string de tamanho máximo 31 (para instruções de 32
            bits) e adiciona um "1" no começo para indicar que é negativo com 
            representação de complemento de 2. 
            Qualquer uso deste método que adicione um "1" depois está errado.
        
            O problema é que é meio feio de enxergar porque o toTwoComplement 
            (argumento do método) é um valor unsigned, e tanto o resultado
            retornado quanto o argumento e o resultado do unTwoComplement são
            valores n+1 bits - no caso, o argumento é signed também e acho que
            a resposta deve ser retornada completando os zeros p ficar com o
            mesmo tamanho. Meio tosco, mas enfim né.
        */
        
        String oneComplement = "";
        //Troca todos os dígitos, passa para decimal, soma 1, converte de volta para binário
        //e completa com todos os zeros à esquerda necessários e com o "1".
        for(char bit : toTwoComplement.toCharArray())
            oneComplement = oneComplement + (bit=='0' ? "1" : "0");
        
        int twoComplementedDecimal = Integer.parseInt(oneComplement,2)+1;
        String twoComplementedString = Integer.toBinaryString(twoComplementedDecimal);
        
        /*Esse caso aqui embaixo é só para o 0, aparentemente.
            Mais interessante do que botar um if(... == 0).
        */
        if(twoComplementedString.length()>toTwoComplement.length())
            twoComplementedString = twoComplementedString.substring(1);
        /*Com esse Integer.toBinaryString é possível que a string representando
        o complemento de 2 tenha perdido uns dígitos. Então, a adição deve ser
        feita AGORA.
        */
//        if(twoComplementedString.length()>toTwoComplement.length())
//            return twoComplementedString.substring(1);

        while(twoComplementedString.length()<toTwoComplement.length())
            twoComplementedString = "0"+twoComplementedString;
        twoComplementedString="1"+twoComplementedString;
        return twoComplementedString;
    }
    
    public static String unTwoComplement(String twoComplemented)
    {
        //Sempre bom fazer essas verificações né
        if(twoComplemented.charAt(0)=='0') return twoComplemented;
        /*Faz o complemento de 2 do valor que já está representado em complemento
        de 2, corta fora o primeiro dígito (que é um "1" representando o negativo)
        e adiciona um "0" no lugar dele
        */
        return "0"+twoComplement(twoComplemented.substring(1)).substring(1);
    }
    
    
}
