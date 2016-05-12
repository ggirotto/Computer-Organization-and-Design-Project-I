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
     
        String binOpcode = BaseConversions.FromDecimal.toBinaryUnsigned(opcode,6);
        String binRs = BaseConversions.FromDecimal.toBinaryUnsigned(rs,5);
        String binRt = BaseConversions.FromDecimal.toBinaryUnsigned(rt,5);
        String binRd = BaseConversions.FromDecimal.toBinaryUnsigned(rd,5);
        String binShamt = BaseConversions.FromDecimal.toBinaryUnsigned(shamt,5);
        String binFunct = BaseConversions.FromDecimal.toBinaryUnsigned(funct,6);
        
        String hexaInstruction=FromBinary.toHexa(binOpcode+binRs+binRt+binRd+binShamt+binFunct,8);
                
        return "0x"+hexaInstruction;
    }
    
    public static String hexaToAlphaNumerical(String toAlphaNumerical, String opcode){
        // Retira o opcode do código binário
        String toAlphaNumericalAsBinary = BaseConversions.FromHexa.toBinary(toAlphaNumerical);
        toAlphaNumericalAsBinary = toAlphaNumericalAsBinary.substring(6,toAlphaNumericalAsBinary.length());

        // Separa os valores de cada registrador, shamt e funct do número binário total
        String rs = toAlphaNumericalAsBinary.substring(0,5);
        String rt = toAlphaNumericalAsBinary.substring(5,10);
        String rd = toAlphaNumericalAsBinary.substring(10,15);
        String shamt = toAlphaNumericalAsBinary.substring(15,20);
        String funct = toAlphaNumericalAsBinary.substring(20,26);

        // Passa os valores de binário para decimal
        rs = BaseConversions.FromBinary.toDecimalUnsigned(rs);
        rt = BaseConversions.FromBinary.toDecimalUnsigned(rt);
        rd = BaseConversions.FromBinary.toDecimalUnsigned(rd);
        shamt = BaseConversions.FromBinary.toDecimalUnsigned(shamt);

        // Faz a busca nos enumeradores pelos registradores
        rs=EnumRegistradores.values()[Integer.parseInt(rs)]+"";
        rt=EnumRegistradores.values()[Integer.parseInt(rt)]+"";
        rd=EnumRegistradores.values()[Integer.parseInt(rd)]+"";
   
        /*
            Faz a montagem da instrução. Se for uma instrução de deslocamento de bits (sll ou slt)
            faz a montagem com o shamt, caso contrario, monta com os 3 registradores.
        */
        String instructionAsAlphaNumerical="";
        if(opcode.equals("sll") || opcode.equals("slt"))
            instructionAsAlphaNumerical = opcode+" "+rd+","+rt+","+shamt;
        else instructionAsAlphaNumerical = opcode+" "+rd+","+rs+","+rt;
       
        return instructionAsAlphaNumerical;
    }
}
