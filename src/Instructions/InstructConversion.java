package Instructions;

public abstract class InstructConversion{
    
    public static String toBinary(String valor, int nroCaracteres){
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
        String binario = immediate;
        String newString = "";
        for(int i=0; i<=binario.length()-1; i++){
            if(binario.charAt(i)=='0') newString += "1";
            if(binario.charAt(i)=='1') newString += "0";
        }
        String finalString = "";
        int base = 0;
        if(newString.charAt(newString.length()-1) == '0'){
            finalString += "1";
        }else{
            finalString += "0";
            base = 1;
        }
        for(int i=newString.length()-2; i>=0; i--){
            if(newString.charAt(i) == 1 && base == 1){
                finalString += "0";
                base = 1;
            }else if(newString.charAt(i) == 1 && base == 0){
                finalString += "1";
            }else if(newString.charAt(i) == 0 && base == 1){
                finalString += "1";
                base = 0;
            }else finalString += newString.charAt(i);
        }
        String reverse = new StringBuffer(finalString).reverse().toString();
        return reverse;
    }
    
    public static String toHexa(String value){
        String retorno = "004";
        
        // Converte a string value para hexa
        String hexa = Integer.toHexString(Integer.parseInt(value));
        
        // Adiciona os 0's necessários para completar uma operação em hexa (8 bits)
        for(int i=0; i<=(4-hexa.length()); i++) retorno += "0";
        
        // Concatena a string com o valor em hexa
        retorno += hexa;
        return retorno;
    }
    
    public static String hexaToBinary(String line){
        String newLine = line.substring(2,line.length());
        String total = "";
        String j;
        for(int i=0; i<= newLine.length()-1; i++){
            j = newLine.charAt(i)+"";
            if(j.matches("[0-9]+")) total += toBinary(j,4);
            else {
                switch(j){
                    case "a":
                        total += toBinary("10",4);
                        break;
                    case "b":
                        total += toBinary("11",4);
                        break;
                    case "c":
                        total += toBinary("12",4);
                        break;
                    case "d":
                        total += toBinary("13",4);
                        break;
                    case "e":
                        total += toBinary("14",4);
                        break;
                    case "f":
                        total += toBinary("15",4);
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
        total += toBinary(tipoR.opcode, 6);
        total += toBinary(tipoR.rs, 5);
        total += toBinary(tipoR.rt, 5);
        total += toBinary(tipoR.rd, 5);
        total += toBinary(tipoR.shamt, 5);
        total += toBinary(tipoR.funct, 6);
        
        
        int i = 0;
        int base = 0;
        int calc = 0;
        
        /*  Separa a string total em 7 partes de 4 caracteres.
            Para cada parte, faz a conversão para hexadecimal
            Aplica este valor final na string retorno
        */
        
        while(i<=28){
            String sub = total.substring(i, i+4);
            for(int j=3; j>=0; j--){
                int val = Character.getNumericValue(sub.charAt(j));
                calc += val * Math.pow(2,base);
                base++;
            }
            switch(calc){
                case 10:
                    retorno += "A";
                    break;
                case 11:
                    retorno += "B";
                    break;
                case 12:
                    retorno += "C";
                    break;
                case 13:
                    retorno += "D";
                    break;
                case 14:
                    retorno += "E";
                    break;
                case 15:
                    retorno += "F";
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
    
    public static String ItoHexa(tipoI tipoI){
 
        // Cria a string que os valores serão concatenados formando o hexa final
        String retorno = "0x";
        
        // Armazena os valores em binario na string total
        String total = "";
        total += toBinary(tipoI.opcode, 6);
        total += toBinary(tipoI.rt, 5);
        total += toBinary(tipoI.rs, 5);
        total += toBinary(tipoI.immediate, 16);
        
        int i = 0;
        int base = 0;
        int calc = 0;
        
        /*  Separa a string total em 7 partes de 4 caracteres.
            Para cada parte, faz a conversão para hexadecimal
            Aplica este valor final na string retorno
        */
        
        while(i<=28){
            String sub = total.substring(i, i+4);
            for(int j=3; j>=0; j--){
                int val = Character.getNumericValue(sub.charAt(j));
                calc += val * Math.pow(2,base);
                base++;
            }
            switch(calc){
                case 10:
                    retorno += "A";
                    break;
                case 11:
                    retorno += "B";
                    break;
                case 12:
                    retorno += "C";
                    break;
                case 13:
                    retorno += "D";
                    break;
                case 14:
                    retorno += "E";
                    break;
                case 15:
                    retorno += "F";
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
    
    public static String JtoHexa(tipoJ tipoJ){
        // Cria a string que os valores serão concatenados formando o hexa final
        String retorno = "0x";
        
        // Armazena os valores em binario na string total
        String total = "";
        total += toBinary(tipoJ.opcode, 6);
        
        // Passa o valor imediato para hexadecimal:
        String immediate = toHexa(tipoJ.immediate);
        
        String newImmediate = "";
        // Passa o valor em hexa para binário:
        for(int i=0; i<= immediate.length()-1; i++){
            switch(immediate.charAt(i)){
                case 'a':
                    newImmediate += toBinary("10",4);
                    break;
                case 'b':
                    newImmediate += toBinary("11",4);
                    break;
                case 'c':
                    newImmediate += toBinary("12",4);
                    break;
                case 'd':
                    newImmediate += toBinary("13",4);
                    break;
                case 'e':
                    newImmediate += toBinary("14",4);
                    break;
                case 'f':
                    newImmediate += toBinary("15",4);
                    break;
                default:
                    newImmediate += toBinary(immediate.charAt(i)+"", 4);
                
            }
        }
        
        // Retira os 4 mais significativos e os dois menos
        String finalImmediate = newImmediate.substring(4,newImmediate.length()-2);
        
        // Concatena junto com o opcode na string total
        total += finalImmediate;

        int i = 0;
        int base = 0;
        int calc = 0;
        
        /*  Separa a string total em 7 partes de 4 caracteres.
            Para cada parte, faz a conversão para hexadecimal
            Aplica este valor final na string retorno
        */
        
        while(i<=28){
            String sub = total.substring(i, i+4);
            for(int j=3; j>=0; j--){
                int val = Character.getNumericValue(sub.charAt(j));
                calc += val * Math.pow(2,base);
                base++;
            }
            switch(calc){
                case 10:
                    retorno += "A";
                    break;
                case 11:
                    retorno += "B";
                    break;
                case 12:
                    retorno += "C";
                    break;
                case 13:
                    retorno += "D";
                    break;
                case 14:
                    retorno += "E";
                    break;
                case 15:
                    retorno += "F";
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
    
    public static int binaryToInt(String valueAsBinary)
    {
        if(valueAsBinary.length()==1) return Integer.parseInt(valueAsBinary,2);
        if(valueAsBinary.charAt(0)=='1') return binaryToInt(twoComplement(valueAsBinary));
        return Integer.parseInt(valueAsBinary,2);
    }
    
}