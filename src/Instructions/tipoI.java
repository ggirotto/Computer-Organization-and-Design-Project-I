package Instructions;

public class tipoI extends Instruction {
    private final String opcode;
    private final String rs;
    private final String rt;
    private final String immediate;
    
    public tipoI(String newOpcode, String newRs, String newRt, String newImmediate){
        super(newOpcode, newRs, newRt);
        immediate = newImmediate;
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
    
    public String twoComplement(String imediate){
        int bin = Integer.parseInt(imediate);
        String binario = Integer.toBinaryString(bin);
        String newString = "";
        for(int i=0; i<=binario.length(); i++){
            if(binario.charAt(i)=='0') newString += "1";
            if(binario.charAt(i)=='1') newString += "0";
        }
        String finalString = "";
        int aux = 0;
        for(int i=newString.length(); i>=0; i--){
            if(newString.charAt(i) == 1 && aux == 1){
                finalString += "0";
                aux = 1;
                continue;
            }
            if(newString.charAt(i) == 0 && aux == 1){
                finalString += "1";
                aux = 0;
                continue;
            }
            finalString += "0";
        }
        return finalString;
    }
    
    public String toHexa(){
 
        String retorno = "0x";
        String total = "";
        total += toBinary(opcode, 6);
        total += toBinary(rs, 5);
        total += toBinary(rt, 5);
        if(immediate.matches("-[0-9]+")) immediate = twoComplement(toBinary(immediate, 16));
        total += toBinary(immediate, 16);
        
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
