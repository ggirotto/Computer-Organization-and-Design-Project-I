package Instructions;

public class InstructConversion<InstructionType extends Instruction> {
    
    public static String toBinary(String valor, int nroCaracteres){
        // Converte o valor para inteiro
        int bin = Integer.parseInt(valor);
        
        // Passa para string novamente em valor binário
        String binario = Integer.toBinaryString(bin);
        
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
        return retorno;
    }
    
    public static String twoComplement(String imediate){
        int bin = Integer.parseInt(imediate);
        String binario = Integer.toBinaryString(bin);
        String newString = "";
        for(int i=0; i<=binario.length(); i++){
            if(binario.charAt(i)=='0') newString += "1";
            if(binario.charAt(i)=='1') newString += "0";
        }
        String finalString = "";
        int aux = 0;
        for(int i=newString.length(); i>=0; i--){
            if(newString.charAt(i) == 1 && aux == 1){
                finalString += "0";
                aux = 1;
                continue;
            }
            if(newString.charAt(i) == 0 && aux == 1){
                finalString += "1";
                aux = 0;
                continue;
            }
            finalString += "0";
        }
        return finalString;
    }
    
    public static String RtoHexa(tipoR tipoR){
        
        // Cria a string que os valores serão concatenados formando o hexa final
        String retorno = "0x";
        
        // Armazena os valores em binario na string total
        String total = "";
        total += toBinary(tipoR.opcode, 6);
        total += toBinary(tipoR.rs, 5);
        total += toBinary(tipoR.rt, 5);
        total += toBinary(tipoR.opcode, 5);
        total += toBinary(tipoR.opcode, 5);
        total += toBinary(tipoR.opcode, 6);
        
        
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
                case 11:
                    retorno += "B";
                case 12:
                    retorno += "C";
                case 13:
                    retorno += "D";
                case 14:
                    retorno += "E";
                case 15:
                    retorno += "F";
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
        total += toBinary(tipoI.rs, 5);
        total += toBinary(tipoI.rt, 5);
        /*
            Se o valor é negativo, faz o complemento de dois.
        */
        String newImmediate = "";
        if(tipoI.immediate.matches("-[0-9]+")) newImmediate = twoComplement(toBinary(tipoI.immediate, 16));
        total += toBinary(newImmediate, 16);
        
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
                case 11:
                    retorno += "B";
                case 12:
                    retorno += "C";
                case 13:
                    retorno += "D";
                case 14:
                    retorno += "E";
                case 15:
                    retorno += "F";
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
        total += toBinary(tipoJ.immediate, 26);
        
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
                case 11:
                    retorno += "B";
                case 12:
                    retorno += "C";
                case 13:
                    retorno += "D";
                case 14:
                    retorno += "E";
                case 15:
                    retorno += "F";
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