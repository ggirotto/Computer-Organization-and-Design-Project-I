package Instructions;

public abstract class InstructConversion{
    
  
       
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
    
    public static String RtoHexa(TipoR tipoR){
        
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
    
    public static String ItoHexa(TipoI tipoI){
 
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
    
    public static String JtoHexa(TipoJ tipoJ){
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