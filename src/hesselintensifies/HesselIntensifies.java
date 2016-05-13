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
        Scanner in = new Scanner(System.in);
        //Perguntar se quer passar de codigo para hexa ou de hexa para codigo
        System.out.println("1. Código para Hexa");
        System.out.println("2. Hexa para código");
        int choose = Integer.parseInt(in.nextLine());
        FileReader fileRead=new FileReader("dummy.txt");
        if(choose==1){
            fileRead = new FileReader("teste.txt");
            writer  = new PrintWriter("saida.txt", "UTF-8");
        }
        else if(choose==2) {
            fileRead = new FileReader("teste2.txt");
            writer  = new PrintWriter("saida2.txt", "UTF-8");
        }
        BufferedReader lerArq = new BufferedReader(fileRead);
        String lineBeingRead = lerArq.readLine(); // lê a primeira linha
        
        // Primeira passada que recolhe as informações das labels
        while (lineBeingRead != null){
            PrimeiraPassada(lineBeingRead);
            lineBeingRead = lerArq.readLine();
        }
        
        linhaDoPrograma = 0;
        
        // Volta para o inicio do arquivo
        if(choose==1) fileRead = new FileReader("teste.txt");
        else fileRead = new FileReader("teste2.txt");
        lerArq = new BufferedReader(fileRead);
        lineBeingRead = lerArq.readLine(); // lê a primeira linha
          
        //Segunda Passada
        while (lineBeingRead != null) {
            if(!(limpaCodigo(lineBeingRead))){
                writer.println(lineBeingRead);
                lineBeingRead = lerArq.readLine();
                continue;
            }
            
            //Se o programa encontrar instruções em hexa no modo an->hex
            //ou em an no modo hex->a, pula aquela linha
            
            if(lineBeingRead.substring(0, 2).equals("0x") ==(choose==1)){
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
    
    public static String hexaToInstruction(String hexaValue){
        if(!hexaValue.contains("0x")) return "Esse código em hexa não corresponde a nenhuma instrução.";
        String binario = BaseConversions.FromHexa.toBinary(hexaValue);
        
        //Lê o opcode e o que *pode* ser o funct e passa para decimal.
        String decimalOpcode = Integer.parseInt(binario.substring(0,6),2)+"";
        String decimalPossibleFunct = Integer.parseInt(binario.substring(26,32), 2)+"";        
        
        /* Verifica se é uma instrução tipo j (como só tem uma, vê se o opcode
        é 2 de uma vez - se houvesse mais, era só fazer como é feito com as tipo
        I). Se não for, verifica se é uma R pelo funct. Se não for, verifica se 
        é uma tipo I.*/
        for(EnumInstrucao instruction : EnumInstrucao.values())
        {
            if(instruction.getOpcode().equals(decimalOpcode)&&
               instruction.getTipo().equals("J"))
                return InstructionFactory.TipoJ.hexaToAlphaNumerical(hexaValue);
            if(instruction.getOpcode().equals(decimalOpcode)&&
               instruction.getFunct().equals(decimalPossibleFunct))
                return InstructionFactory.TipoR.hexaToAlphaNumerical(hexaValue);
            if(instruction.getOpcode().equals(decimalOpcode)&&
               instruction.getTipo().equals("I"))
                return InstructionFactory.TipoI.hexaToAlphaNumerical(hexaValue);
        }
        return "Esse código em hexa não corresponde a nenhuma instrução.";
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
