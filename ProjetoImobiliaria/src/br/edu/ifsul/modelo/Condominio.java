
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "condominio")
public class Condominio implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_condominio", sequenceName = "seq_condominio_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_condominio", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode estar em branco")
    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull(message = "O endereço não pode ser nulo")
    @NotBlank(message = "O endereço não pode estar em branco")
    @Length(max = 60, message = "O endereço não pode ter mais de {max} caracteres")
    @Column(name = "endereco", length = 60, nullable = false)
    private String endereco;

    @NotNull(message = "O numero não pode ser nulo")
    @NotBlank(message = "O numero não pode estar em branco")
    @Length(max = 8, message = "O numero não pode ter mais de {max} caracteres")
    @Column(name = "numero", length = 8, nullable = false)
    private String numero;

    @NotNull(message = "O cep não pode ser nulo")
    @NotBlank(message = "O cep não pode estar em branco")
    @Length(max = 10, message = "O cep não pode ter mais de {max} caracteres")
    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    @OneToMany(mappedBy = "condominio", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UnidadeCondominial> unidade_condominial;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recursos",
            joinColumns
            = @JoinColumn(name = "condominio", referencedColumnName = "id", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "recurso", referencedColumnName = "id", nullable = false))
    private Set<Recurso> recursos; 

    public void adicionarUnidadeCondominial(UnidadeCondominial obj) {
        obj.setCondominio(this);
        this.getUnidade_condominial().add(obj);
    }

    public void removerUnidadeCondominial(int index) {
        UnidadeCondominial obj = this.getUnidade_condominial().get(index);
        this.getUnidade_condominial().remove(index);
    }

    public Condominio() {
        unidade_condominial = new ArrayList<>();
        recursos = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<UnidadeCondominial> getUnidade_condominial() {
        return unidade_condominial;
    }

    public void setUnidade_condominial(List<UnidadeCondominial> unidade_condominial) {
        this.unidade_condominial = unidade_condominial;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Condominio other = (Condominio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Set<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(Set<Recurso> recursos) {
        this.recursos = recursos;
    }

}
