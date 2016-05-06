package Enumerations;

public enum enumOpcodes {
    add("0"),
    addi("8"),
    addu("0"),
    addiu("9"),
    sll("0"),
    srl("0"),
    and("0"),
    beq("4"),
    bne("5"),
    slt("0"),
    andi("12"),
    j("2"),
    lw("36"),
    sw("43"),
    sltiu("11");

    public String valorOpcode;
    
    enumOpcodes(String newData){
        valorOpcode = newData;
    }
   
    public String getValue(){
        return valorOpcode;
    }
}
