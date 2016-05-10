package Instructions;

public class TipoR extends Instruction{
    protected final String rs;
    protected final String rt;
    protected final String rd;
    protected final String shamt;
    protected final String funct;
    
    public TipoR(String newOpcode, String newRs, String newRt, String newRd, String newShamt, String newFunct){
        super(newOpcode);
        rs = newRs;
        rt = newRt;
        rd = newRd;
        shamt = newShamt;
        funct = newFunct;
    }
    
}
