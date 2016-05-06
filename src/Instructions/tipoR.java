package Instructions;

public class tipoR extends Instruction{
    protected final String rd;
    protected final String shamt;
    protected final String funct;
    
    public tipoR(String newOpcode, String newRs, String newRt, String newRd, String newShamt, String newFunct){
        super(newOpcode, newRs, newRt);
        rd = newRd;
        shamt = newShamt;
        funct = newFunct;
    }
    
}
