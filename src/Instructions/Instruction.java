package Instructions;

public abstract class Instruction {
    
    protected final String opcode;
    
    public Instruction(String newOpcode)
    {
        opcode=newOpcode;
    }
    
}
