package Instructions;

public abstract class InstructConversion{
          
    
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
        return "004"+binaryToHexa(total);
    }
}