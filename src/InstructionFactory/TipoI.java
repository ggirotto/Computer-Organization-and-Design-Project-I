/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionFactory;

import BaseConversions.FromBinary;
import Enumerations.EnumInstrucao;
import Enumerations.EnumRegistradores;
import Instructions.InstructConversion;
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
        }else if(regs[2].matches("[0-9]+")){
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
        String binRt = BaseConversions.FromDecimal.toBinaryUnsigned(rt,6);
        String binImmediate = BaseConversions.FromDecimal.toBinary(immediate);
        
        String hexaInstruction=FromBinary.toHexa(binOpcode+binRs+binRt+binImmediate).substring(2);
        
        while(hexaInstruction.length()<8) hexaInstruction = "0"+hexaInstruction;
        return "0x"+hexaInstruction;
    }
    
    public static String hexaToAlphaNumerical(String toAlphaNumerical){return "";}
}
