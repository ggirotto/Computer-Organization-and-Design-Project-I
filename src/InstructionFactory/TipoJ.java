/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionFactory;

import Enumerations.*;
import hesselintensifies.*;
import java.util.Map;

/**
 *
 * @author visinius
 */
public abstract class TipoJ{
   
    public static String alphaNumericalToHexa(String toHexa){
        // Separa as informações da linha por espaço
        String [] parts = toHexa.split(" ");

        // Pega a operação (exemplo: jal)
        String operacao = parts[0];

        // Pega o opcode da operação acima
        EnumInstrucao instruction = EnumInstrucao.valueOf(operacao);
        
        String opcode = instruction.getOpcode();
        
        // Pega a label
        String label = parts[1];
        
        String immediate = ((HesselIntensifies.distanceLabels.get(label)) * 4)+"";
        
        String BinOpcode = BaseConversions.FromDecimal.toBinary(opcode,6);
        
        // Passa o valor imediato para hexadecimal:
        String hexaImmediate = BaseConversions.FromDecimal.toHexaWAddres(immediate);
        
        // Passa o valor em hexa para binário:
        String BinImmediate = BaseConversions.FromHexa.toBinary("0x" + hexaImmediate);
        
        // Retira os 4 mais significativos e os dois menos
        String finalImmediate = BinImmediate.substring(4,BinImmediate.length()-2);
        String teste = BinOpcode+finalImmediate;
        String hexaInstruction = BaseConversions.FromBinary.toHexa(teste);
        
        return "0x"+hexaInstruction;

    }
    
    public static String hexaToAlphaNumerical(String toAlphaNumerical, String opcode){
        
        // Retira o opcode do código binário
        String IInstructionAsBinary = toAlphaNumerical.substring(6,toAlphaNumerical.length());

        // Resgata o valor imediato
        String immediate = IInstructionAsBinary.substring(0,26);
        
        // Adiciona quatro 0 no inicio e dois 0 no final
        String newImmediate = "0000" + immediate;
        immediate = newImmediate + "00";
        
        // Passa o valor para hexa
        immediate = BaseConversions.FromBinary.toHexa(immediate);
        
        // Pega a diferença entre o endereço da label e o endereço de inicio
        String distance = (Integer.parseInt(immediate) - 400000)+"";
        
        // Converte o valor para decimal (o valor será a soma do peso das instruções até a label)
        distance = Integer.parseInt(distance.trim(), 16 )+"";
        
        // Divide este valor por 4 para pegar a linha da label
        int line = Integer.parseInt(distance)/4;
        
        // Soma 1
        line = line+1;
        
        // Busca no HashMap pelo nome da label
        Map<String,Integer> distanceLabels = HesselIntensifies.distanceLabels;
        for (Map.Entry<String, Integer> entry : distanceLabels.entrySet()) {
            if(entry.getValue() == line){
                immediate = entry.getKey();
                break;
            }
        }
        
        // Concatena o opcode da instrução com a label descoberta
        String IInstructionAsString = opcode+ " " +immediate;

        return IInstructionAsString;
    }
}
