package hesselintensifies;

import Enumerations.enumFuncts;
import Enumerations.enumOpcodes;
import Enumerations.enumRegistradores;
import Instructions.InstructConversion;
import Instructions.tipoI;
import Instructions.tipoJ;
import Instructions.tipoR;


public class calculateInstruction {
    // Método para operações do tipo R (instruct to hexa)
    public static void opTipoR(String line) {
        // Separa as informações da linha por espaço
        String[] parts = line.split(" ");
        
        // Pega a operação (exemplo: add)
        String operacao = parts[0];
        
        // Pega o opcode da operação acima
        String opcode = enumOpcodes.valueOf(operacao).getValue();

        // Pega o funct da operação acima
        String funct = enumFuncts.valueOf(operacao).getValue();
        
        //Separa os registradores/valores
        String[] regs = parts[1].split(",");
        
        // Resgata o valor em decimal dos registradores da operação
        String rs = enumRegistradores.valueOf(regs[0]).ordinal()+"";
        String rt = enumRegistradores.valueOf(regs[1]).ordinal()+"";
        
        // Inicializa variáveis que serão ajustadas no próximo IF
        String rd;
        String shamt = "0";
        
        /* Se o 3 elemento é um registrador, não é uma operação que usa o shamt,
         então mantenha ela em 0 e resgata o valor do registrador rd
         Caso contrário, seta o registrador rd em 0 e armazena o valor do shamt*/
        
        if (regs[2].contains("$")) {
            rd = enumRegistradores.valueOf(regs[2]).ordinal()+"";
        } else {
            rd = "0";
            shamt = regs[2];
        }
        
        // Cria o objeto com todas as informações necessárias
        tipoR objInstrucao = new tipoR(opcode, rs, rt, rd, shamt, funct);
        
        // Utiliza as informações armazenadas no objeto para converter a instrução em hexa
        String resultado = InstructConversion.RtoHexa(objInstrucao);
        
        // Imprime o resultado
        System.out.println(resultado);

    }
    
    // Método para operações do tipo I (instruct to hexa)
    public static void opTipoI(String line){
         // Separa as informações da linha por espaço
        String [] parts = line.split(" ");
        
        // Pega a operação (exemplo: bne)
        String operacao = parts[0];
        
        // Pega o opcode da operação acima
        String opcode = enumOpcodes.valueOf(operacao).getValue();
        
        //Separa os registradores/valores
        String [] regs = parts[1].split(",");
        
        // Resgata o valor em decimal dos registradores da operação
        String rs = enumRegistradores.valueOf(regs[0]).ordinal()+"";
        String rt = enumRegistradores.valueOf(regs[1]).ordinal()+"";
        
        String immediate;
        
        /* Verifica se é um valor imediate ou uma label
         Se for uma label, usa as informações dos hashmaps para calcular a distancia
         desta instrução para a label
         No final, seta a string immediate para o valor imediato ou o valor calculado*/
        
        if(regs[2].matches("[0-9]+")){
            immediate = regs[2];
        }
        else{
            String distancia = ""+(HesselIntensifies.distanceLabels.get(regs[2]) - HesselIntensifies.distanceInstructions.get(operacao));
            immediate = distancia;
        }
        
        // Cria o objeto com todas as informações necessárias
        tipoI objInstrucao = new tipoI(opcode, rs, rt, immediate);
        
        // Utiliza as informações armazenadas no objeto para converter a instrução em hexa
        String resultado = InstructConversion.ItoHexa(objInstrucao);
        
        // Imprime o resultado
        System.out.println(resultado);
        
    }
    
    public static void opTipoJ(String line){
        // Separa as informações da linha por espaço
        String [] parts = line.split(" ");
        
        // Pega a operação (exemplo: jal)
        String operacao = parts[0];
        
        // Pega o opcode da operação acima
        String opcode = enumOpcodes.valueOf(operacao).getValue();
        
        // Pega a label
        String label = parts[1];
        
        String immediate = (HesselIntensifies.distanceLabels.get(label) * 4)+"";
        
        // Cria o objeto com todas as informações necessárias
        tipoJ objInstrucao = new tipoJ(opcode, immediate);
        
        // Utiliza as informações armazenadas no objeto para converter a instrução em hexa
        String resultado = InstructConversion.JtoHexa(objInstrucao);
        
        // Imprime o resultado
        System.out.println(resultado);
        
    }
}
