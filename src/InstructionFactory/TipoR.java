/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionFactory;

import BaseConversions.*;
import Enumerations.*;

/**
 *
 * @author visinius
 */
public abstract class TipoR{
 
    public static String alphaNumericalToHexa(String toHexa){
        
        String[] parts = toHexa.split(" "); 
        String operacao = parts[0];
        
        EnumInstrucao instruction = EnumInstrucao.valueOf(operacao);
        
        String opcode = instruction.getOpcode();        
        String funct = instruction.getFunct();
                
        //Separa os registradores/valores
        String[] regs = parts[1].split(",");

        // Resgata o valor em decimal dos registradores da operação
        String rd = EnumRegistradores.valueOf(regs[0]).ordinal()+"";
        String rs = EnumRegistradores.valueOf(regs[1]).ordinal()+"";

        // Inicializa variáveis que serão ajustadas no próximo IF
        String rt;
        String shamt = "0";

        /* Se o 3 elemento é um registrador, não é uma operação que usa o shamt,
         então mantenha ela em 0 e resgata o valor do registrador rd
         Caso contrário, seta o registrador rd em 0 e armazena o valor do shamt
        */
            
        //opcode rs rt rd shamt funct
        
        if (regs[2].contains("$")) {
            rt = EnumRegistradores.valueOf(regs[2]).ordinal()+"";
        } else {
            rt = rs;
            rs = "0";
            shamt = regs[2];
        }
        
        // Passa os valroes para binario
        
        String binOpcode = BaseConversions.FromDecimal.toBinaryUnsigned(opcode,6);
        String binRs = BaseConversions.FromDecimal.toBinaryUnsigned(rs,5);
        String binRt = BaseConversions.FromDecimal.toBinaryUnsigned(rt,5);
        String binRd = BaseConversions.FromDecimal.toBinaryUnsigned(rd,5);
        String binShamt = BaseConversions.FromDecimal.toBinaryUnsigned(shamt,5);
        String binFunct = BaseConversions.FromDecimal.toBinaryUnsigned(funct,6);
        
        //Passa para hexa os binarios concatenados
        String hexaInstruction=FromBinary.toHexa(binOpcode+binRs+binRt+binRd+binShamt+binFunct,8);
                
        return hexaInstruction;
    }
    
    //supondo que eu já to recebendo uma tipo R
    public static String hexaToAlphaNumerical(String toAlphaNumerical){
        //pega com 0x
        //passa tudo para binário e localiza a operação
        String rInstructionAsBinary = BaseConversions.FromHexa.toBinary(toAlphaNumerical);
        
        String opcode = BaseConversions.FromBinary.toDecimalUnsigned(rInstructionAsBinary.substring(0,6)); 
        String rs = EnumRegistradores.values()[Integer.parseInt
        (BaseConversions.FromBinary.toDecimalUnsigned(rInstructionAsBinary.substring(6,11)))].toString();
        String rt = EnumRegistradores.values()[Integer.parseInt
        (BaseConversions.FromBinary.toDecimalUnsigned(rInstructionAsBinary.substring(11,16)))].toString();
        String rd = EnumRegistradores.values()[Integer.parseInt
        (BaseConversions.FromBinary.toDecimalUnsigned(rInstructionAsBinary.substring(16,21)))].toString();
        String shamt = BaseConversions.FromBinary.toDecimalUnsigned(rInstructionAsBinary.substring(21,26));
        String funct = BaseConversions.FromBinary.toDecimalUnsigned(rInstructionAsBinary.substring(26,32));
        
        String operation = "";
        
        for(EnumInstrucao instruction : EnumInstrucao.values())
            if(instruction.getOpcode().equals(opcode))
                if(instruction.getFunct().equals(funct))
                {
                    operation = instruction.toString();
                    break;
                }
        //verifica se é srl/sll para ver oq deve ser retornado
        if(operation.equals("srl")||operation.equals("sll"))
            return operation+" "+rd+","+rt+","+shamt;    
        //caso contrário...
     
        return operation+" "+rd+","+rs+","+rt;        
    }
}
