package hesselintensifies;

import Enumerations.*;
import Instructions.*;
import java.util.Map;

public class InstructionFactory {

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
        for (EnumRegistradores opc : EnumRegistradores.values()) {
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
    public static void opTipoI(String line){
       
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
        String IInstructionAsBinary = BaseConversions.FromHexa.toBinary(IInstructionAsHexa);
        String IInstructionAsString = "";
        
        String opcode = IInstructionAsBinary.substring(0,6);
        String rs = IInstructionAsBinary.substring(6,11);
        String rt = IInstructionAsBinary.substring(11,16);
        String immediate = IInstructionAsBinary.substring(16,32);
        
        for(EnumInstrucao instruction : EnumInstrucao.values())
            //Procura por uma instrução no enum com o mesmo opcode
            if(Integer.parseInt(instruction.getOpcode())==Integer.parseInt(opcode,2)) IInstructionAsString+=instruction.toString()+" ";
        
        IInstructionAsString+= EnumRegistradores.values()[InstructConversion.binaryToInt(rs)].toString()+",";
        IInstructionAsString+= EnumRegistradores.values()[InstructConversion.binaryToInt(rt)].toString()+",";
        IInstructionAsString+= InstructConversion.binaryToInt(immediate);
        System.out.println(IInstructionAsString);
    }

    public static void opTipoJ(String line){
        // Separa as informações da linha por espaço
        String [] parts = line.split(" ");

        // Pega a operação (exemplo: jal)
        String operacao = parts[0];

        // Pega o opcode da operação acima
        String opcode = EnumInstrucao.valueOf(operacao).getOpcode();
        
        // Pega a label
        String label = parts[1];

        String immediate = ((HesselIntensifies.distanceLabels.get(label)-1) * 4)+"";
        
        // Cria o objeto com todas as informações necessárias
        TipoJ objInstrucao = new TipoJ(opcode, immediate);

        // Utiliza as informações armazenadas no objeto para converter a instrução em hexa
        String resultado = InstructConversion.JtoHexa(objInstrucao);
        // Imprime o resultado
        System.out.println(resultado);

    }
    
    // Método para operações do tipo J (hexa to instruct)
    public static void opTipoJH(String binary, String opcode) {

        // Retira o opcode do código binário
        binary = binary.substring(6,binary.length());

        // Resgata o valor imediato
        String immediate = binary.substring(0,26);
        
        // Adiciona quatro 0 no inicio e dois 0 no final
        String newImmediate = "0000" + immediate;
        immediate = newImmediate + "00";
        
        // Passa o valor para decimal e depois para binario
        immediate = InstructConversion.binaryToHexa(immediate);
        
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
        String instruction = opcode+ " " +immediate;

        System.out.println(instruction);

    }
}
