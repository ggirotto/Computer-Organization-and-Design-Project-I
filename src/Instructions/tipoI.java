package Instructions;

public class tipoI extends Instruction {
    protected final String immediate;
    
    public tipoI(String newOpcode, String newRs, String newRt, String newImmediate){
        super(newOpcode, newRs, newRt);
        immediate = newImmediate;
    }
    
}
