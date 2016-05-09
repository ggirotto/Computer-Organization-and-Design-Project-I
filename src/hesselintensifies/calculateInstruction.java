package hesselintensifies;

import Enumerations.*;
import Instructions.*;

public class calculateInstruction {
    // Método para operações do tipo R (instruct to hexa)
    public static void opTipoR(String line) {
  
        String[] parts = line.split(" "); 
        String operacao = parts[0];
        String opcode = enumInstrucao.valueOf(operacao).getOpcode();        
        String funct = enumInstrucao.valueOf(operacao).getFunct();
        
        //Separa os registradores/valores
        String[] regs = parts[1].split(",");

        // Resgata o valor em decimal dos registradores da operação
        String rd = enumRegistradores.valueOf(regs[0]).ordinal()+"";
        String rs = enumRegistradores.valueOf(regs[1]).ordinal()+"";

        // Inicializa variáveis que serão ajustadas no próximo IF
        String rt;
        String shamt = "0";

        /* Se o 3 elemento é um registrador, não é uma operação que usa o shamt,
         então mantenha ela em 0 e resgata o valor do registrador rd
         Caso contrário, seta o registrador rd em 0 e armazena o valor do shamt*/

        if (regs[2].contains("$")) {
            rt = enumRegistradores.valueOf(regs[2]).ordinal()+"";
        } else {
            rt = rs;
            rs = "0";
            shamt = regs[2];
        }

        tipoR objInstrucao = new tipoR(opcode, rs, rt, rd, shamt, funct);

        // Utiliza as informações armazenadas no objeto para converter a instrução em hexa
        String resultado = InstructConversion.RtoHexa(objInstrucao);
        System.out.println(resultado);
    }

    // Método para operações do tipo R (hexa to instruct)
    public static void opTipoRH(String binary, String opcode) {

        // Retira o opcode do código binário
        binary = binary.substring(6,binary.length());

        // Separa os valores de cada registrador, shamt e funct do número binário total
        String rs = binary.substring(0,5);
        String rt = binary.substring(5,10);
        String rd = binary.substring(10,15);
        String shamt = binary.substring(15,20);
        String funct = binary.substring(20,26);

        // Passa os valores de binário para decimal
        rs = Integer.parseInt(rs,2)+"";
        rt = Integer.parseInt(rt,2)+"";
        rd = Integer.parseInt(rd,2)+"";
        shamt = Integer.parseInt(shamt,2)+"";

        // Faz a busca nos enumeradores pelos registradores
        for (enumRegistradores opc : enumRegistradores.values()) {
            if(opc.ordinal()==Integer.parseInt(rs)){
                rs = opc+"";
            }
            else if(opc.ordinal()==Integer.parseInt(rt)){
                rt = opc+"";
            }
            else if(opc.ordinal()==Integer.parseInt(rd)){
                rd = opc+"";
            }
        }

        /*
            Faz a montagem da instrução. Se for uma instrução de deslocamento de bits (sll ou slt)
            faz a montagem com o shamt, caso contrario, monta com os 3 registradores.
        */
        if(opcode.equals("sll") || opcode.equals("slt")){
            opcode += " "+rd+","+rt+","+shamt;
        }else{
            opcode += " "+rd+","+rs+","+rt;
        }

        System.out.println(opcode);

    }

    // Método para operações do tipo I (instruct to hexa)
    // Método para operações do tipo I (instruct to hexa)
    public static void opTipoI(String line){
         // Separa as informações da linha por espaço
        String [] parts = line.split(" ");

        // Pega a operação (exemplo: bne)
        String operacao = parts[0];

        // Pega o opcode da operação acima

        String opcode = enumInstrucao.valueOf(operacao).getOpcode();
        
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
            if(distancia.matches("[0-9]+")) distancia = Integer.parseInt(distancia)-1+"";
            else distancia = Integer.parseInt(distancia)-1+"";
            String aux = rs;
            rs = rt;
            rt = aux;
            immediate = distancia;
        }

        // Cria o objeto com todas as informações necessárias
        tipoI objInstrucao = new tipoI(opcode, rs, rt, immediate);

        // Utiliza as informações armazenadas no objeto para converter a instrução em hexa
        String resultado = InstructConversion.ItoHexa(objInstrucao);

        // Imprime o resultado
        System.out.println(resultado);

    }
    
    public static void opTipoIH(String IInstructionAsHexa)
    {
        /*Converte o hexa para binário (facilita a manipulação)
        e captura as partes da instrução:
        > opcode (6 bits)
        > rs (5 bits)
        > rd (5 bits)
        > immediate (16 bits)
        */
        String IInstructionAsBinary = InstructConversion.hexaToBinary(IInstructionAsHexa);
        String IInstructionAsString = "";
        
        String opcode = IInstructionAsBinary.substring(0,6);
        String rs = IInstructionAsBinary.substring(6,11);
        String rt = IInstructionAsBinary.substring(11,16);
        String immediate = IInstructionAsBinary.substring(16,32);
        
        for(enumInstrucao instruction : enumInstrucao.values())
            //Procura por uma instrução no enum com o mesmo opcode
            if(Integer.parseInt(instruction.getOpcode())==Integer.parseInt(opcode,2)) IInstructionAsString+=instruction.toString()+" ";
        
        IInstructionAsString+= enumRegistradores.values()[InstructConversion.binaryToInt(rs)].toString()+",";
        IInstructionAsString+= enumRegistradores.values()[InstructConversion.binaryToInt(rt)].toString()+",";
        IInstructionAsString+= InstructConversion.binaryToInt(immediate);
        System.out.println(IInstructionAsString);
    }

    public static void opTipoJ(String line){
        // Separa as informações da linha por espaço
        String [] parts = line.split(" ");

        // Pega a operação (exemplo: jal)
        String operacao = parts[0];

        // Pega o opcode da operação acima
        
        String opcode = enumInstrucao.valueOf(operacao).getOpcode();
        
        // Pega a label
        String label = parts[1];

        String immediate = ((HesselIntensifies.distanceLabels.get(label)-1) * 4)+"";
        // Cria o objeto com todas as informações necessárias
        tipoJ objInstrucao = new tipoJ(opcode, immediate);

        // Utiliza as informações armazenadas no objeto para converter a instrução em hexa
        String resultado = InstructConversion.JtoHexa(objInstrucao);
        // Imprime o resultado
        System.out.println(resultado);

    }
}
