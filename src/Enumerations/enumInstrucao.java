package Enumerations;

public enum enumInstrucao {
    R("R"),I("I"),J("J");
    
    private String valorInstrucao;
    
    enumInstrucao(String newData){
        valorInstrucao = newData;
    }
    
    public String getValue(){
        return valorInstrucao;
    }
}
