package hesselintensifies;

import java.util.HashMap;
import java.util.Map;
import Enumerations.*;
import java.io.*;
import java.util.Scanner;

/*
  todo onde quer que a gente ponha o PrimeiraPassada() e o SegundaPassada(),
  tem que resetar o linhaDoPrograma entre eles e reinicializar o inputstream
  while(inputStream.hasNextLine() PrimeiraPassada(inputStream.nextLine());
  linhaDoPrograma=0;
  inputStream = REINICIALIZA INPUT STREAM
  while(inputStream.hasNextLine() SegundaPassada(inputStream.nextLine());
  
*/

public class HesselIntensifies {
    
    int linhaDoPrograma=0;
    public static PrintWriter writer;
    public static Map<String,Integer> labelAddresses = new HashMap<>();
    // Hash Map que salva as labels e sua distancia do inicio do programa
    public static Map<String, Integer> distanceLabels = new HashMap<>();
    // Hash Map que salva as instruções e sua distancia do inicio do programa
    public static Map<String, Integer> distanceInstructions = new HashMap<>();
    // Variavel para adicionar a distancia nos dois hashMaps
    
     public static void main(String[] args) throws IOException{
        writer  = new PrintWriter("saida.txt", "UTF-8");
        Scanner in = new Scanner(System.in);
        //Perguntar se quer passar de codigo para hexa ou de hexa para codigo
        System.out.println("1. Código para Hexa");
        System.out.println("2. Hexa para código");
        int choose = 1;
        FileReader fileRead = new FileReader("teste.txt");
        
        BufferedReader lerArq = new BufferedReader(fileRead);
        String line = lerArq.readLine(); // lê a primeira linha
        while (line != null){
            //saveDistance(line);
            line = lerArq.readLine();
        }
        fileRead = new FileReader("teste.txt");
        lerArq = new BufferedReader(fileRead);
        line = lerArq.readLine(); // lê a primeira linha
        while (line != null) {
            boolean validLine = limpaCodigo(line);
            if(validLine == false){
                if(line.equals("")){
                    line = lerArq.readLine();
                    continue;
                }
                writer.println(line);
                line = lerArq.readLine();
                continue;
            }
            if(line.substring(0, 2).equals("0x") && choose==1){
                line = lerArq.readLine();
                continue;
            }
            else if(!(line.substring(0, 2).equals("0x")) && choose==2){
                line = lerArq.readLine();
                continue;
            }
            else{
                if(choose==1){
                    String result = instructionToHexa(line,0); //dar um jeito nisso
                    writer.println(result);
                }else{
                    String result = hexaToInstruction(line);
                    writer.println(result);
                }
            }
            
            line = lerArq.readLine();
        }
        
        writer.close();
    }

    public static String instructionToHexa(String line, int lineNumber) {
        
        // Separa as informações da linha por espaço
        String[] parts = line.split(" ");

        // Pega o opcode e tipo da função
        String operacao = parts[0];
        
        // Verifica se a instrução é valida
        boolean existe = false;
        for (EnumInstrucao c : EnumInstrucao.values()) {
            if (c.name().equals(operacao)) {
                existe = true;
            }
        }
        
        // Se não for, retorna uma mensagem
        if(!existe) return "Esta instrução não consta no nosso banco de dados";
        
        // Verifica o tipo da instrução e chama o método apropriado
        if (EnumInstrucao.valueOf(operacao).getTipo().equals("R")) {
            return InstructionFactory.TipoR.alphaNumericalToHexa(line);
        } else if (EnumInstrucao.valueOf(operacao).getTipo().equals("I")) {
            return InstructionFactory.TipoI.alphaNumericalToHexa(line,lineNumber);
        } else if (EnumInstrucao.valueOf(operacao).getTipo().equals("J")){
            return InstructionFactory.TipoJ.alphaNumericalToHexa(line);
        }
        
        return "Esta instrução não consta no nosso banco de dados";

    }
    
    public static String hexaToInstruction(String line){
        String binario = BaseConversions.FromHexa.toBinary(line);
        
        String opcode = binario.substring(0,6);
        String funct = binario.substring(26,32);
        
        String decimalOpcode = Integer.parseInt(opcode,2)+"";
        String decimalFunct = Integer.parseInt(funct, 2)+"";
        
        // Verifica se só possui uma instrução com o opcode
        int cont = 0;
        for (EnumInstrucao opc : EnumInstrucao.values()) {
            if(opc.getOpcode().equals(decimalOpcode)){
                opcode = opc+"";
                cont++;
            }
        }
        
        // Se tiver mais de uma instrução com o mesmo opcode, usa o funct para pegar a correta
        if(cont > 1){
            for (EnumInstrucao func : EnumInstrucao.values()) {
                if(func.getFunct().equals(decimalFunct)){
                    opcode = func+"";
                    break;
                }
            }
        }
        
        // Nenhuma instrução encontrada
        if(cont==0) return "Este código hexa não representa nenhuma instrução no nosso banco de dados";
        
        // Verifica o tipo da instrução e chama o método apropriado
        if (EnumInstrucao.valueOf(opcode).getTipo().equals("R")) {
            return InstructionFactory.TipoR.hexaToAlphaNumerical(binario, opcode);
        } else if (EnumInstrucao.valueOf(opcode).getTipo().equals("I")) {
            return InstructionFactory.TipoI.hexaToAlphaNumerical(line);
        } else {
            return InstructionFactory.TipoJ.hexaToAlphaNumerical(binario, opcode);
        }
        
    }
                 
    // Retira espaços em branco e textos desnecessários do arquivo (.text, .globl, ...)
    public static boolean limpaCodigo(String linha){
        if(linha.equals("")) return false;
        if(linha.contains(".")) return false;
        if(linha.contains(":")) return false;
        return true;
    }
    
    public void PrimeiraPassada(String lineBeingRead)
    {
        if(lineBeingRead.contains(":"))
            labelAddresses.put(lineBeingRead.split(":")[0],linhaDoPrograma*4);
        linhaDoPrograma++;          
    }
    
//    public void SegundaPassada(String lineBeingRead, int choose)
//    {
//        if(!(lineBeingRead.contains(":")||lineBeingRead.contains(".")))
//            //todo adicionar a parte que processa o tipo de instrução (só ctrlc ctrlv)
//            if(lineBeingRead.contains("bne")||lineBeingRead.contains("beq"))
//                InstructionFactory.TipoI.alphaNumericalToHexa(
//     /*opcode*/     lineBeingRead.split(" ")[0]+
//     /*  rs  */     lineBeingRead.split(" ")[1].split(",")[0]+
//     /*  rt  */     lineBeingRead.split(" ")[1].split(",")[1]+
//     /*immediate*/  
//             /*TODO dar um jeito de botar aqui o immediate
//             no InstructionFactory, ele lê a label e não
//             processa as distâncias direito. O que tem que fazer é
//             alterar o InstructionFactory.tipoI para que ele use o hashMap
//             labelAddresses que a gente criou agora.
//             */
//                    );
//    }
}
