/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

/**
 *
 * @author 15105207
 */
public class tipoJ extends Instruction {
    
    protected final String address;
    
    public tipoJ(String newOpcode, String newAddress)
    {
        super(newOpcode);
        address=newAddress;
    }
    
}
