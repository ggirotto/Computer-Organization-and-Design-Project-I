package hesselintensifies;

import java.util.HashMap;
import java.util.Map;
import Enumerations.enumInstrucao;
import hesselintensifies.calculateInstruction;
import java.util.Scanner;

public class HesselIntensifies {
    
    // Hash Map que salva as labels e sua distancia do inicio do programa
    protected static final Map<String, Integer> distanceLabels = new HashMap<>();
    // Hash Map que salva as instruções e sua distancia do inicio do programa
    protected static final Map<String, Integer> distanceInstructions = new HashMap<>();
    // Variavel para adicionar a distancia nos dois hashMaps
    private static int iteratorDistance = 0;
    
     public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        //Perguntar se quer passar de codigo para hexa ou de hexa para codigo
        System.out.println("1. Código para Hexa");
        System.out.println("2. Hexa para código");
        // Salva todos os comandos em uma string grandona legal

        String line = "bne $t0,$t1,teste";
        
        // Chamar método apropriado
        instructionsToHexa(line);
    }

    public static void instructionsToHexa(String line) {

        // Separa as informações da linha por espaço
        String[] parts = line.split(" ");

        // Pega o opcode e tipo da função
        String operacao = parts[0];
        
        // Verifica o tipo da instrução e chama o método apropriado
        if (enumInstrucao.valueOf(operacao).getValue().equals("R")) {
            calculateInstruction.opTipoR(line);
        } else if (enumInstrucao.valueOf(operacao).getValue().equals("I")) {
            calculateInstruction.opTipoI(line);
        } else {
            calculateInstruction.opTipoJ(line);
        }

    }
    
    // Salva a distancia das labels e das operações que utilizam labels nos hashmaps
    public static void saveDistance(String line){
        switch(line){
            case "beq":
                distanceInstructions.put("beq",iteratorDistance);
                iteratorDistance++;
            case "bne":
                distanceInstructions.put("bne",iteratorDistance);
                iteratorDistance++;
            case "j":
                distanceInstructions.put("j",iteratorDistance);
                iteratorDistance++;
            case "jal":
                distanceInstructions.put("jal",iteratorDistance);
                iteratorDistance++;
            case ":":
                distanceLabels.put("jal",iteratorDistance);
            default:
                iteratorDistance++;
        }
    }
    
    // Retira espaços em branco e textos desnecessários do arquivo (.text, .globl, ...)
    public static String limpaCodigo(String linha){
        if(linha.equals("\n")) return "";
        if(linha.matches("\\.*")) return "";
        return linha;
    }
}