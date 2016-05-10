package Instructions;

public class TipoI extends Instruction {
    protected final String immediate;
    protected final String rs;
    protected final String rt;
    
    public TipoI(String newOpcode, String newRs, String newRt, String newImmediate){
        super(newOpcode);
        rs = newRs;
        rt = newRt;
        immediate = newImmediate;
    }
    
}
