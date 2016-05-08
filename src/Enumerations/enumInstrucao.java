package Enumerations;

public enum enumInstrucao {
    add("R","0","32"),
    addi("I","8","0"),
    addu("R","0","33"),
    addiu("I","9","0"),
    sll("R","0","0"),
    srl("R","0","2"),
    and("R","0","36"),
    beq("I","4","0"),
    bne("I","5","0"),
    slt("I","0","42"),
    andi("I","12","0"),
    j("J","2","0"),
    lw("I","36","0"),
    sw("I","43","0"),
    sltiu("I","11","0");
    
    private final String tipoInstrucao,valorOpcode,valorFunct;
    
    enumInstrucao(String newTipo, String newOpcode, String newFunct){
        tipoInstrucao = newTipo;
        valorOpcode = newOpcode;
        valorFunct = newFunct;
    }
     
    public String getTipo(){return tipoInstrucao;}
    
    public String getOpcode(){return valorOpcode;}
    
    public String getFunct(){return valorFunct;}
}
