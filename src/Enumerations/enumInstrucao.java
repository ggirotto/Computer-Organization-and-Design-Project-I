package Enumerations;

public enum enumInstrucao {
    add("R"),
    addi("I"),
    addu("R"),
    addiu("I"),
    sll("R"),
    srl("R"),
    and("R"),
    beq("I"),
    bne("I"),
    slt("I"),
    andi("I"),
    j("J"),
    lw("I"),
    sw("I"),
    sltiu("I");
    
    private String valorInstrucao;
    
    enumInstrucao(String newData){
        valorInstrucao = newData;
    }
    
    public String getValue(){
        return valorInstrucao;
    }
}
