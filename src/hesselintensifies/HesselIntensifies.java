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
    
    public static int linhaDoPrograma=0;
    public static PrintWriter writer;
    // Hash Map que salva as labels e sua linha*4
    public static Map<String,Integer> labelAddresses = new HashMap<>();
    
     public static void main(String[] args) throws IOException{
        writer  = new PrintWriter("saida.txt", "UTF-8");
        Scanner in = new Scanner(System.in);
        //Perguntar se quer passar de codigo para hexa ou de hexa para codigo
        System.out.println("1. Código para Hexa");
        System.out.println("2. Hexa para código");
        int choose = 1;
        FileReader fileRead = new FileReader("teste.txt");
        
        BufferedReader lerArq = new BufferedReader(fileRead);
        String lineBeingRead = lerArq.readLine(); // lê a primeira linha
        
        // Primeira passada que recolhe as informações das labels
        while (lineBeingRead != null){
            PrimeiraPassada(lineBeingRead);
            lineBeingRead = lerArq.readLine();
        }
        
        linhaDoPrograma = 0;
        
        // Volta para o inicio do arquivo
        fileRead = new FileReader("teste.txt");
        lerArq = new BufferedReader(fileRead);
        lineBeingRead = lerArq.readLine(); // lê a primeira linha
        
        
        //Segunda Passada
        while (lineBeingRead != null) {
            if(!(limpaCodigo(lineBeingRead))){
                writer.println(lineBeingRead);
                lineBeingRead = lerArq.readLine();
                continue;
            }
            
            //Detecta instruções em hexa com a opção instruction to hexa ligada
            if(lineBeingRead.substring(0, 2).equals("0x") && choose==1){
                    lineBeingRead = lerArq.readLine();
                    linhaDoPrograma++;
                    continue;
            }

            //Detecta instruções com a opção hexa to instruction ligada
            if(!(lineBeingRead.substring(0, 2).equals("0x")) && choose==2){
                    lineBeingRead = lerArq.readLine();
                    linhaDoPrograma++;
                    continue;
            }

            // Detecta qual a manipulação a ser usada (instruction to hexa ou hexa to instruction)
            // Se for de instruction para hexa
            if(choose==1){
                String result = instructionToHexa(lineBeingRead);
                linhaDoPrograma++;
                writer.println(result);
            }else{
                String result = hexaToInstruction(lineBeingRead);
                linhaDoPrograma++;
                writer.println(result);
            }
            
            lineBeingRead = lerArq.readLine();
        }
        writer.close();
    }
         
    public static String instructionToHexa(String line) {
        
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
            return InstructionFactory.TipoI.alphaNumericalToHexa(line);
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
            return InstructionFactory.TipoR.hexaToAlphaNumerical(line.split("x")[1]);
        } else if (EnumInstrucao.valueOf(opcode).getTipo().equals("I")) {
            return InstructionFactory.TipoI.hexaToAlphaNumerical(line.split("x")[1]);
        } else {
            return InstructionFactory.TipoJ.hexaToAlphaNumerical(line.split("x")[1]);
        }
        
    }
                 
    // Retira espaços em branco e textos desnecessários do arquivo (.text, .globl, ...)
    public static boolean limpaCodigo(String linha){
        if(linha.equals("")) return false;
        if(linha.contains(".")) return false;
        if(linha.contains(":")) return false;
        return true;
    }
    
    public static void PrimeiraPassada(String lineBeingRead)
    {
        if(lineBeingRead.contains(":"))
            labelAddresses.put(lineBeingRead.split(":")[0],linhaDoPrograma*4);
        else if(!(lineBeingRead.contains(".")) && !(lineBeingRead.equals("")))
            linhaDoPrograma++;          
    }
}
