package InstructionFactory;

import Enumerations.*;
import hesselintensifies.*;
import java.util.Map;

public abstract class TipoJ{
   
    //aqui a única sintaxe possível é o opcode e um valor "imediato" de 26 bits
    //o valor imediato sempre é POSITIVO, então pode ser tratado como unsigned
    //desprezar os 4 MSB e os 2 LSB é o de menos.
    public static String alphaNumericalToHexa(String toHexa){
                   
        //Pega o opcode e a label (ambos como strings)
        String opcode = EnumInstrucao.valueOf(toHexa.split(" ")[0]).getOpcode();
        String label = toHexa.split(" ")[1];
        
        //Pega o endereço associado à label como um inteiro 
        //(contado a partir de 0 em decimal indo de 4 em 4)
        int jumpAddress = HesselIntensifies.labelAddresses.get(label)+8;
        
        String binOpcode = BaseConversions.FromDecimal.toBinaryUnsigned(opcode,6);
        String binTargetAddress = Integer.toBinaryString(Integer.parseInt("00400000", 16)+jumpAddress);
        while(binTargetAddress.length()<32) binTargetAddress="0"+binTargetAddress;
        String cutBinTargetAddress = binTargetAddress.substring(4,binTargetAddress.length()-2);
       
        String toHexaTarget = binOpcode+cutBinTargetAddress;
        
        return BaseConversions.FromBinary.toHexa(toHexaTarget,8);
    }
    
    public static String hexaToAlphaNumerical(String toAlphaNumerical){
        //pega com 0x
        String instructionAsBinary = BaseConversions.FromHexa.toBinary(toAlphaNumerical);
        String opcode=instructionAsBinary.substring(0,6);
        String address="0000"+instructionAsBinary.substring(6)+"00";
        address=BaseConversions.FromBinary.toHexa(address, 8);
        //address= 0x00400000 + número de instruções
        address= ""+(Integer.parseInt(address.substring(2),16)-Integer.parseInt("00400000",16)-8);
        
        opcode = BaseConversions.FromBinary.toDecimalUnsigned(opcode);
        for(EnumInstrucao instruction : EnumInstrucao.values())
            if(instruction.getOpcode().equals(opcode)) 
            {
                opcode = instruction.toString();
                break;
            }
        
        int addressAsInt = Integer.parseInt(address);
        for(String label : HesselIntensifies.labelAddresses.keySet())
            if(HesselIntensifies.labelAddresses.get(label)==addressAsInt)
                address = label;
        return opcode+" "+address;
    }
}
