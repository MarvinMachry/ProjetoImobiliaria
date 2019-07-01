
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "aluguel")
public class Aluguel implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_aluguel", sequenceName = "seq_aluguel_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_aluguel", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Min(message = "O valor não pode ser negativo", value = 0)
    @NotNull(message = "O valor deve ser informado")
    @Column(name = "valor", nullable = false, columnDefinition = "numeric(12,2)")
    private Double valor;
    
    @NotNull(message = "A data de inicio do contrato não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "inicio_contrato", nullable = false)
    private Calendar inicioContrato;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fim_contrato", nullable = true)
    private Calendar fimContrato;
    
    @NotNull(message = "O dia de vencimento deve ser informado")
    @Min(message = "O dia de vencimento não pode ser negativo", value = 0)
    @Column(name = "dia_vencimento", nullable = false)
    private Integer diaVencimento;

    @NotNull(message = "O locatario deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false,
        foreignKey = @ForeignKey(name = "fk_locatario_id"))
    private Locatario locatario;//locatario associação

    @NotNull(message = "A unidade condominial deve ser informada")
    @ManyToOne
    @JoinColumn(name = "unidade_condominial", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_unCondominial_id"))
    private UnidadeCondominial unidadeCondominial;//Unidade Condiminial associação
    
    @OneToMany(mappedBy = "aluguel", cascade = CascadeType.ALL,
        orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Mensalidade> mensalidades;
    
    public Aluguel() {
        mensalidades = new ArrayList<>();
    }

    public Aluguel(Integer id, Double valor, Calendar inicioContrato, Calendar fimContrato, Integer diaVencimento, Locatario locatario, UnidadeCondominial unidadeCondominial, List<Mensalidade> mensalidades) {
        this.id = id;
        this.valor = valor;
        this.inicioContrato = inicioContrato;
        this.fimContrato = fimContrato;
        this.diaVencimento = diaVencimento;
        this.locatario = locatario;
        this.unidadeCondominial = unidadeCondominial;
        this.mensalidades = mensalidades;
    }
    
    public void adicionarMensalidade(Mensalidade obj) {
        obj.setAluguel(this);
        this.getMensalidades().add(obj);
    }

    public void removerMensalidade(int index) {
        Mensalidade obj = this.getMensalidades().get(index);
        this.getMensalidades().remove(index);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Calendar getInicioContrato() {
        return inicioContrato;
    }

    public void setInicioContrato(Calendar inicioContrato) {
        this.inicioContrato = inicioContrato;
    }

    public Calendar getFimContrato() {
        return fimContrato;
    }

    public void setFimContrato(Calendar fimContrato) {
        this.fimContrato = fimContrato;
    }

    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(Integer diaVenciamento) {
        this.diaVencimento = diaVenciamento;
    }

    public Locatario getLocatario() {
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    public UnidadeCondominial getUnidadeCondominial() {
        return unidadeCondominial;
    }

    public void setUnidadeCondominial(UnidadeCondominial unidadeCondominial) {
        this.unidadeCondominial = unidadeCondominial;
    }

    public List<Mensalidade> getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(List<Mensalidade> mensalidades) {
        this.mensalidades = mensalidades;
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
        final Aluguel other = (Aluguel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}