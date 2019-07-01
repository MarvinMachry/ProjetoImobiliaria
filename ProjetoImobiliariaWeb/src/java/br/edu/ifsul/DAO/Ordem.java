
package br.edu.ifsul.DAO;

import java.io.Serializable;

public class Ordem implements Serializable{
    
    private String atributo;
    private String label;
    private String operador;

    
    public Ordem(String atributo, String label, String operador){        
        this.atributo = atributo;
        this.label = label;
        this.operador = operador;
    }

    public String getAtributo() {
        return atributo;
    }

    public String getLabel() {
        return label;
    }

    public String getOperador() {
        return operador;
    }


    
    
    
}
