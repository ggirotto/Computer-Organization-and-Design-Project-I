
package Instructions;

/**
 *
 * @author 15105207
 */
public abstract class Instruction {
    
    protected final String opcode;
    protected final String rs;
    protected final String rt;
    
    public Instruction(String newOpcode, String newRs, String newRt)
    {
        opcode=newOpcode;
        rs=newRs;
        rt=newRt;
    }
}
