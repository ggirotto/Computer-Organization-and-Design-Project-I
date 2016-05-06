package Instructions;

public class tipoR extends Instruction{
    private final String opcode;
    private final String rs;
    private final String rt;
    private final String rd;
    private final String shamt;
    private final String funct;
    
    public tipoR(String newOpcode, String newRs, String newRt, String newRd, String newShamt, String newFunct){
        opcode = newOpcode;
        rs = newRs;
        rt = newRt;
        rd = newRd;
        shamt = newShamt;
        funct = newFunct;
    }
    
    public String toBinary(String valor, int nroCaracteres){
        int bin = Integer.parseInt(valor);
        String binario = Integer.toBinaryString(bin);
        String retorno = "";
        int novoNro = nroCaracteres - binario.length();
        for(int i=1; i<= novoNro; i++) retorno += "0";
        retorno += binario;
        return retorno;
    }
    
    public String toHexa(){
        String retorno = "0x";
        String total = "";
        total += toBinary(opcode, 6);
        total += toBinary(rs, 5);
        total += toBinary(rt, 5);
        total += toBinary(opcode, 5);
        total += toBinary(opcode, 5);
        total += toBinary(opcode, 6);
        
        int i = 0;
        int base = 0;
        int calc = 0;
        while(i<=28){
            String sub = total.substring(i, i+4);
            for(int j=3; j>=0; j--){
                int val = Character.getNumericValue(sub.charAt(j));
                calc += val * Math.pow(2,base);
                base++;
            }
            switch(calc){
                case 10:
                    retorno += "A";
                case 11:
                    retorno += "B";
                case 12:
                    retorno += "C";
                case 13:
                    retorno += "D";
                case 14:
                    retorno += "E";
                case 15:
                    retorno += "F";
                default:
                    retorno += calc;
            }
            calc = 0;
            i+=4;
            base=0;
        }
        
        
        return retorno;
    }
    
}
