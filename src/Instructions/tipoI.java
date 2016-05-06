package Instructions;

public class tipoI extends Instruction {
    protected final String immediate;
    protected final String rs;
    protected final String rt;
    
    public tipoI(String newOpcode, String newRs, String newRt, String newImmediate){
        super(newOpcode);
        rs = newRs;
        rt = newRt;
        immediate = newImmediate;
    }
    
}
