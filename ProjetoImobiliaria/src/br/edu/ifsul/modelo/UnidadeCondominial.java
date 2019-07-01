

package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "unidade_condominial")
public class UnidadeCondominial implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_unCondominial", sequenceName = "seq_unCondominial_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_unCondominial", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message = "O número não pode ser nulo")
    @NotBlank(message = "O número não pode estar em branco")
    @Length(max = 8, message = "O número não pode ter mais de {max} caracteres")
    @Column(name = "numero", length = 40, nullable = false)
    private String numero;
    
    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descrição não pode estar em branco")
    @Length(max = 100, message = "A descrição não pode ter mais de {max} caracteres")
    @Column(name = "descricao", length = 40, nullable = false)
    private String descricao;
    
    @Min(message = "A area não pode ser negativa", value = 0)
    @NotNull(message = "A area deve ser informada")
    @Column(name = "area", nullable = false, columnDefinition = "numeric(12,2)")
    private Double area;
    
    @NotNull(message = "O número do quarto deve ser informado")
    @Min(message = "O número do quarto não pode ser negativo", value = 0)
    @Column(name = "numero_quarto", nullable = false)
    private Integer numeroQuarto;
    
    @NotNull(message = "A Pessoa deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pessoa_id"))
    private Pessoa pessoa;//pessoa associação
    
    @NotNull(message = "O condominio deve ser informado")
    @ManyToOne
    @JoinColumn(name = "condominio", referencedColumnName = "id", nullable = false, 
            foreignKey = @ForeignKey(name = "fk_condominio_id"))
    private Condominio condominio;//bidirecional

    public UnidadeCondominial() {
    
    }

    public UnidadeCondominial(Integer id, String numero, String descricao, Double area, Integer numeroQuarto, Pessoa pessoa, Condominio condominio) {
        this.id = id;
        this.numero = numero;
        this.descricao = descricao;
        this.area = area;
        this.numeroQuarto = numeroQuarto;
        this.pessoa = pessoa;
        this.condominio = condominio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(Integer numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnidadeCondominial other = (UnidadeCondominial) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
     
}