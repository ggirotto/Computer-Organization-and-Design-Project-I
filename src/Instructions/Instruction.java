
package Instructions;

/**
 *
 * @author 15105207
 */
public abstract class Instruction {
    
    protected final String opcode;
    
    public Instruction(String newOpcode)
    {
        opcode=newOpcode;
    }
    
}
