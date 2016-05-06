package Instructions;

public class tipoR extends Instruction{
    protected final String rs;
    protected final String rt;
    protected final String rd;
    protected final String shamt;
    protected final String funct;
    
    public tipoR(String newOpcode, String newRs, String newRt, String newRd, String newShamt, String newFunct){
        super(newOpcode);
        rs = newRs;
        rt = newRt;
        rd = newRd;
        shamt = newShamt;
        funct = newFunct;
    }
    
}
