package Enumerations;

public enum enumFuncts {
    add("32"),addu("33"),sll("0"),srl("2"),and("36"),slt("42");
    
    public String valorFunct;
    
    enumFuncts(String newData){
        valorFunct = newData;
    }
    
    public String getValue(){
        return valorFunct;
    }
}
