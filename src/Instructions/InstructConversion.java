package Instructions;

public abstract class InstructConversion{
    
    public static String integerToBinary(String valor, int nroCaracteres){
        // Converte o valor para inteiro
        int bin = Integer.parseInt(valor);
        
        // Passa para string novamente em valor binário
        String binario;
        if(valor.matches("-[0-9]+")) bin = Math.abs(-bin);
        
        binario = Integer.toBinaryString(bin);
        
        String retorno = "";
        
        /* Calcula o número de 0's necessários para se adicionar no início
        da string para completar o número de bits da operação
         Exemplo: opcode - 6bits
        */
        int novoNro = nroCaracteres - binario.length();
        
        // Adiciona os 0's
        for(int i=1; i<= novoNro; i++) retorno += "0";
        
        // Adiciona o código cnovertido para binário aos 0's já adicionados
        retorno += binario;
        if(valor.matches("-[0-9]+")) return twoComplement(retorno);
        return retorno;
    }
    
    public static String twoComplement(String immediate){
        
        // Salva o valor na variável binario
        String binario = immediate;
        
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
    
    public static String integerToHexa(String value){
        String retorno = "004";
        
        // Converte a string value para hexa
        String hexa = Integer.toHexString(Integer.parseInt(value));
        
        // Adiciona os 0's necessários para completar uma operação em hexa (8 byts)
        for(int i=0; i<=(4-hexa.length()); i++) retorno += "0";
        
        // Concatena a string com o valor em hexa
        retorno += hexa;
        return retorno;
    }
    
    public static String hexaToBinary(String line){
        // Retira o '0x' da instrução em hexa
        String newLine = line.substring(2,line.length());
        
        String total = "";
        String j;
        
        // Faz a conversão de cada elemento para binário.
        for(int i=0; i<= newLine.length()-1; i++){
            j = newLine.charAt(i)+"";
            if(j.matches("[0-9]+")) total += integerToBinary(j,4);
            else {
                switch(j.toLowerCase()){
                    case "a":
                        total += integerToBinary("10",4);
                        break;
                    case "b":
                        total += integerToBinary("11",4);
                        break;
                    case "c":
                        total += integerToBinary("12",4);
                        break;
                    case "d":
                        total += integerToBinary("13",4);
                        break;
                    case "e":
                        total += integerToBinary("14",4);
                        break;
                    case "f":
                        total += integerToBinary("15",4);
                        break;
                    
                }
            }
        }
        return total;
    }
    
    public static String RtoHexa(tipoR tipoR){
        
        // Cria a string que os valores serão concatenados formando o hexa final
        String retorno = "0x";
        
        // Armazena os valores em binario na string total
        String total = "";
        total += integerToBinary(tipoR.opcode, 6);
        total += integerToBinary(tipoR.rs, 5);
        total += integerToBinary(tipoR.rt, 5);
        total += integerToBinary(tipoR.rd, 5);
        total += integerToBinary(tipoR.shamt, 5);
        total += integerToBinary(tipoR.funct, 6);
        
        // Faz a conversão de binario para hexadecimal
        return binaryToHexa(total);
    }
    
    public static String ItoHexa(tipoI tipoI){
 
        // Cria a string que os valores serão concatenados formando o hexa final
        String retorno = "0x";
        
        // Armazena os valores em binario na string total
        String total = "";
        total += integerToBinary(tipoI.opcode, 6);
        total += integerToBinary(tipoI.rs, 5);
        total += integerToBinary(tipoI.rt, 5);
        total += integerToBinary(tipoI.immediate, 16);
        
        // Faz a conversão de binario para hexadecimal
        return binaryToHexa(total);
    }
    
    public static String JtoHexa(tipoJ tipoJ){
        // Cria a string que os valores serão concatenados formando o hexa final
        String retorno = "0x";
        
        // Armazena os valores em binario na string total
        String total = "";
        total += integerToBinary(tipoJ.opcode, 6);
        
        // Passa o valor imediato para hexadecimal:
        String immediate = integerToHexa(tipoJ.immediate);
        
        // Passa o valor em hexa para binário:
        String newImmediate = hexaToBinary("0x" + immediate);
        
        // Retira os 4 mais significativos e os dois menos
        String finalImmediate = newImmediate.substring(4,newImmediate.length()-2);
        
        // Concatena junto com o opcode na string total
        total += finalImmediate;
        
        // Faz a conversão de binario para hexadecimal
        return binaryToHexa(total);
    }
    
    public static int binaryToInt(String valueAsBinary)
    {
        if(valueAsBinary.length()==1) return Integer.parseInt(valueAsBinary,2);
        if(valueAsBinary.charAt(0)=='1') return binaryToInt(twoComplement(valueAsBinary));
        return Integer.parseInt(valueAsBinary,2);
    }
    
    public static String binaryToHexa(String total){
        String retorno = "";
        /*  Separa a string total em 7 partes de 4 caracteres.
            Para cada parte, faz a conversão para hexadecimal
            Aplica este valor final na string retorno
        */
        
        int i = 0;
        int base = 0;
        int calc = 0;
        
        while(i<=28){
            String sub = total.substring(i, i+4);
            for(int j=3; j>=0; j--){
                int val = Character.getNumericValue(sub.charAt(j));
                calc += val * Math.pow(2,base);
                base++;
            }
            switch(calc){
                case 10:
                    retorno += "a";
                    break;
                case 11:
                    retorno += "b";
                    break;
                case 12:
                    retorno += "c";
                    break;
                case 13:
                    retorno += "d";
                    break;
                case 14:
                    retorno += "e";
                    break;
                case 15:
                    retorno += "f";
                    break;
                default:
                    retorno += calc;
            }
            calc = 0;
            i+=4;
            base=0;
        }
        return retorno;
    }
    
}