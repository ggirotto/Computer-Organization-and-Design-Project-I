/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionFactory;

import BaseConversions.*;
import Enumerations.*;
import java.util.Map;
import hesselintensifies.HesselIntensifies;

/**
 *
 * @author visinius
 */
public abstract class TipoI{
    
    public static String alphaNumericalToHexa(String toHexa){
    
        String [] parts = toHexa.split(" ");

        // Pega a operação (exemplo: bne)
        String operacao = parts[0];

        EnumInstrucao instruction = EnumInstrucao.valueOf(operacao);       
        String opcode = instruction.getOpcode();
        
        //Separa os registradores/valores
        String [] regs = parts[1].split(",");

        // Resgata o valor em decimal dos registradores da operação
        String rs = EnumRegistradores.valueOf(regs[0]).ordinal()+"";
        String rt;
        if(regs[1].contains("(")){
            rt = regs[1];
        }else rt = EnumRegistradores.valueOf(regs[1]).ordinal()+"";

        String immediate;

        /* Verifica se é um valor imediate, uma label ou um offset
         Se for uma label, usa as informações dos hashmaps para calcular a distancia
         desta instrução para a label
         No final, seta a string immediate para o valor imediato ou o valor calculado*/
        if(rt.contains("(")){
            String aux = rt;
            rt = rs;
            rs = aux.substring(aux.indexOf('(')+1, aux.indexOf(')'));
            rs = EnumRegistradores.valueOf(rs).ordinal()+"";
            immediate = aux.substring(0, aux.indexOf('('));
        }else if(regs[2].matches("[0-9]+") || regs[2].matches("-[0-9]+")){
            immediate = regs[2];
        }
        else{
            String distancia = ""+(HesselIntensifies.distanceLabels.get(regs[2]) - HesselIntensifies.distanceInstructions.get(operacao));
            if(distancia.matches("[0-9]+")) distancia = Integer.parseInt(distancia)-1+"";
            else distancia = Integer.parseInt(distancia)-1+"";
            immediate = distancia;
        }

        String binOpcode= BaseConversions.FromDecimal.toBinaryUnsigned(opcode,6);
        String binRs = BaseConversions.FromDecimal.toBinaryUnsigned(rs,5);
        String binRt = BaseConversions.FromDecimal.toBinaryUnsigned(rt,5);
        String binImmediate = BaseConversions.FromDecimal.toBinarySigned(immediate,16);
        
        String hexaInstruction=FromBinary.toHexa(binOpcode+binRs+binRt+binImmediate);
        
        return "0x"+hexaInstruction;
    }
    
    public static String hexaToAlphaNumerical(String toAlphaNumerical){
        /*Converte o hexa para binário (facilita a manipulação)
        e captura as partes da instrução:
        > opcode (6 bits)
        > rs (5 bits)
        > rd (5 bits)
        > immediate (16 bits)
        */
        String IInstructionAsBinary = BaseConversions.FromHexa.toBinary(toAlphaNumerical);
        String IInstructionAsString = "";
        
        String opcode = IInstructionAsBinary.substring(0,6);
        String rs = IInstructionAsBinary.substring(6,11);
        String rt = IInstructionAsBinary.substring(11,16);
        String immediate = IInstructionAsBinary.substring(16,32);

        for(EnumInstrucao instruction : EnumInstrucao.values())
            //Procura por uma instrução no enum com o mesmo opcode
            if(Integer.parseInt(instruction.getOpcode())==Integer.parseInt(opcode,2)) IInstructionAsString+=instruction.toString()+" ";
        
        if(IInstructionAsString.contains("beq") || IInstructionAsString.contains("bne")){
            String instrucao = IInstructionAsString.substring(0, IInstructionAsString.length()-1);
            int distanciaInstrucao = 0;
            int distanciaFinal = 0;
            IInstructionAsString+= EnumRegistradores.values()[Integer.parseInt(BaseConversions.FromBinary.toDecimalUnsigned(rs))].toString()+",";
            IInstructionAsString+= EnumRegistradores.values()[Integer.parseInt(BaseConversions.FromBinary.toDecimalUnsigned(rt))].toString()+",";
            
            // Faz a pesquisa nos HashMaps pela label que condiz com o valor imediato
            Map<String,Integer> distanceInstructions = HesselIntensifies.distanceInstructions;
            for (Map.Entry<String, Integer> entry : distanceInstructions.entrySet()) {
                if(entry.getKey().contains(instrucao)){
                    distanciaInstrucao = entry.getValue();
                }else continue;
                
                if(immediate.charAt(0) == '1') distanciaFinal = (distanciaInstrucao - Integer.parseInt(BaseConversions.FromBinary.toDecimalSigned(immediate)))-1;
                else distanciaFinal = (distanciaInstrucao + Integer.parseInt(BaseConversions.FromBinary.toDecimalSigned(immediate)))+1;

                // Pega a label a partir do valor encontrado
                Map<String,Integer> distanceLabels = HesselIntensifies.distanceLabels;
                String label = "";
                for (Map.Entry<String, Integer> entryLabels : distanceLabels.entrySet()) {
                    if(entryLabels.getValue() == distanciaFinal){
                        label = entryLabels.getKey();
                        IInstructionAsString+=entryLabels.getKey();
                        break;
                    }
                }
                if(label.length() > 1) break;
            }
           return IInstructionAsString;
        }else if(IInstructionAsString.contains("lw") || IInstructionAsString.contains("sw")){
            String instrucao = IInstructionAsString.substring(0, IInstructionAsString.length()-1);
            IInstructionAsString+= EnumRegistradores.values()[Integer.parseInt(BaseConversions.FromBinary.toDecimalUnsigned(rt))].toString()+",";
            IInstructionAsString+= BaseConversions.FromBinary.toDecimalUnsigned(immediate);
            IInstructionAsString+= "(" + EnumRegistradores.values()[Integer.parseInt(BaseConversions.FromBinary.toDecimalUnsigned(rs))].toString()+")";
            return IInstructionAsString;
            
        }else{
            IInstructionAsString+= EnumRegistradores.values()[Integer.parseInt(BaseConversions.FromBinary.toDecimalUnsigned(rs))].toString()+",";
            IInstructionAsString+= EnumRegistradores.values()[Integer.parseInt(BaseConversions.FromBinary.toDecimalUnsigned(rt))].toString()+",";
            if(immediate.charAt(0)=='1') immediate = "-"+TwoComplement.twoComplement(immediate,16);
            IInstructionAsString+= BaseConversions.FromBinary.toDecimalUnsigned(immediate);

            return IInstructionAsString;
        }
    }
}
