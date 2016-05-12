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
   
    //aqui a única sintaxe possível é o opcode e um valor "imediato" de 26 bits
    //o valor imediato sempre é POSITIVO, então pode ser tratado como unsigned
    //desprezar os 4 MSB e os 2 LSB é o de menos.
    public static String alphaNumericalToHexa(String toHexa){
                   
        //Pega o opcode e a label (ambos como strings)
        String opcode = EnumInstrucao.valueOf(toHexa.split(" ")[0]).getOpcode();
        String label = toHexa.split(" ")[1];
        
        //Pega o endereço associado à label como um inteiro 
        //(contado a partir de 0 em decimal indo de 4 em 4)
        int jumpAddress = HesselIntensifies.labelAddresses.get(label);
        
        
        String binOpcode = BaseConversions.FromDecimal.toBinaryUnsigned(opcode,6);
        String binTargetAddress = Integer.toBinaryString(Integer.parseInt("00400000", 16)+jumpAddress);
        while(binTargetAddress.length()<32) binTargetAddress="0"+binTargetAddress;
        binTargetAddress = binTargetAddress.substring(4,binTargetAddress.length()-2);
       
        String toHexaTarget = binOpcode+binTargetAddress;
        
        return BaseConversions.FromBinary.toHexa(toHexaTarget,8);
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
        immediate = BaseConversions.FromBinary.toHexa(immediate,4);
        
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
