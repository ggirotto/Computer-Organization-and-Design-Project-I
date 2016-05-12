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

        String nomeDaInstrucao = parts[0];

        String opcode = EnumInstrucao.valueOf(nomeDaInstrucao).getOpcode();       
        
        //Separa os registradores/valores
        String [] regs = parts[1].split(",");

        // Resgata o valor em decimal dos registradores da operação
        // verifica se é sw/lw, dado que a informação está disposta de forma diferente
        String rt = EnumRegistradores.valueOf(regs[0]).ordinal()+"";
        String rs;
        if(regs[1].contains("(")){ //se tem parêntese, com certeza fecha depois e com certeza é lw/sw
            rs = EnumRegistradores.valueOf
        (regs[1].substring(regs[1].indexOf('(')+1, regs[1].indexOf(')'))).ordinal()+"";     
        }else rs = EnumRegistradores.valueOf(regs[1]).ordinal()+""; //se não for sw/lw, o rs tá do lado já
        //rt, rs, immediate ou p/ sw e lw -> rt, immediate(rs)

        String immediate="";

        /* Verifica se é um valor immediate, uma label ou um offset
         Se for uma label, usa as informações dos hashmaps para calcular a distancia
         desta instrução para a label
         No final, seta a string immediate para o valor imediato ou o valor calculado*/
        
        /*if -> verifica se é um lw/sw -> É COM SINAL SEMPRE
        else if -> não é um lw/sw, mas pode ter um immediate em forma numérica
        -> addi por exemplo. esse immediate é COM SINAL SEMPRE
        else -> não tem um immediate "direto", então é uma label (beq/bne)
        essa label é COM SINAL SEMRPE (na hora de passar p hexa por ex pq bne/beq
        podem ir para cima
        
        qualquer que seja o immediate, ele LEVA SINAL. então, deve ser tratado
        signed. bom lembrar que é só beq/bne que chega até aqui
        */
        
        if(regs[1].contains("(")){
            immediate=regs[1].substring(0,regs[1].indexOf('('));
            if(immediate.equals("")) immediate="0";
        }
        else if(regs[2].matches("-[0-9]+|[0-9]+")) immediate = regs[2];
        //chegou aqui é uma label isolada e tem que botar a distância DA LABEL
        //para a instrução de agora no immediate
        //instrução é na forma instrucao rs,rt,label
        else immediate=""+(((HesselIntensifies.labelAddresses.get(regs[2])-((HesselIntensifies.linhaDoPrograma)*4))/4)-1);

        
        String binOpcode= BaseConversions.FromDecimal.toBinaryUnsigned(opcode,6);
        String binRt = BaseConversions.FromDecimal.toBinaryUnsigned(rt,5);
        String binRs = BaseConversions.FromDecimal.toBinaryUnsigned(rs,5);
        String binImmediate = BaseConversions.FromDecimal.toBinarySigned(immediate,16);
        
        String hexaInstruction=FromBinary.toHexa(binOpcode+binRt+binRs+binImmediate,8);
        
        return hexaInstruction;
    }
    
    public static String hexaToAlphaNumerical(String toAlphaNumerical){
        
        //Passa para binário, já que certos dígitos hexa têm informação sobre mais de
        //um campo da instrução.
        String instructionAsBinary = BaseConversions.FromHexa.toBinary(toAlphaNumerical);
        String opcode = instructionAsBinary.substring(0,6);
        String rs = instructionAsBinary.substring(6,11);
        String rt = instructionAsBinary.substring(11,16);
        String immediate = instructionAsBinary.substring(16);
        
        for(EnumInstrucao instruction : EnumInstrucao.values())
            if(instruction.getOpcode().equals(BaseConversions.FromBinary.toDecimalUnsigned(opcode)))
            {
                opcode = instruction.toString();
                break;
            }
        
        rs = EnumRegistradores.values()[Integer.parseInt(rs,2)].toString();
        rt = EnumRegistradores.values()[Integer.parseInt(rt,2)].toString();
        immediate = BaseConversions.FromBinary.toDecimalSigned(immediate);
        
        /*verifica agora se é uma instrução I "comum", na estrutura "instrucao rt,rs,immediate"
        (tipo addi), se é sw/lw (instrucao rt,immediate(rs)) ou se é bne/beq, que daí precisa
        processar o offset tb
        */
        
        //Vê primeiro se é sw/lw
        if(opcode.equals("sw")||opcode.equals("lw")) 
            return opcode+" "+rt+","+immediate+"("+rs+")";
        //Depois, vê se é uma "comum" (mais fácil de ler o código)
        if(!(opcode.equals("bne")||opcode.equals("beq")))
            return opcode+" "+rt+","+rs+","+immediate;
        String targetAddress=""+(HesselIntensifies.linhaDoPrograma+1+Long.parseLong(immediate))*4;
        
        for(String label : HesselIntensifies.labelAddresses.keySet())
            if((""+(HesselIntensifies.labelAddresses.get(label))).equals(targetAddress)){
                immediate = label;
                break;
            }
        return opcode+" "+rs+","+rt+","+immediate;       
    }
}
