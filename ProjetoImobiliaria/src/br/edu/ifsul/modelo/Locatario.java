
package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table
@DiscriminatorValue(value = "LOC")
public class Locatario extends Pessoa implements Serializable{
    
    @Min(message = "A renda não pode ser negativa", value = 0)
    @NotNull(message = "A renda deve ser informada")
    @Column(name = "renda", nullable = false, columnDefinition = "numeric(12,2)")
    private Double renda;
    
    @NotNull(message = "O local de trabalho não pode ser nulo")
    @NotBlank(message = "O local de trabalho não pode estar em branco")
    @Length(max = 40, message = "O local de trabalho não pode ter mais de {max} caracteres")
    @Column(name = "local_trabalho", length = 40, nullable = false)
    private String localTrabalho;
    
    @NotNull(message = "O telefone de trabalho não pode ser nulo")
    @NotBlank(message = "O telefone de trabalho não pode estar em branco")
    @Length(max = 20, message = "O telefone de trabalho não pode ter mais de {max} caracteres")
    @Column(name = "telefone_trabalho", length = 20, nullable = false)
    private String telefoneTrabalho;

    public Locatario() {
    
    }

    public Locatario(Double renda, String localTrabalho, String telefoneTrabalho) {
        this.renda = renda;
        this.localTrabalho = localTrabalho;
        this.telefoneTrabalho = telefoneTrabalho;
    }

    public String getTelefoneTrabalho() {
        return telefoneTrabalho;
    }

    public void setTelefoneTrabalho(String telefoneTrabalho) {
        this.telefoneTrabalho = telefoneTrabalho;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }
    
}