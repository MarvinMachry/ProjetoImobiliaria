package br.edu.ifsul.controle;

import br.edu.ifsul.DAO.CondominioDAO;
import br.edu.ifsul.DAO.PessoaDAO;
import br.edu.ifsul.DAO.RecursoDAO;
import br.edu.ifsul.DAO.UnCondominialDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;


@Named(value = "ControleCondominio")
@SessionScoped
public class ControleCondominio implements Serializable {

    @EJB
    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    private RecursoDAO<Recurso> daoRecurso;
    private Recurso recurso;
    private UnCondominialDAO<UnidadeCondominial> daoUnCondominial;
    private UnidadeCondominial unidadeCondominial;
    private PessoaDAO<Pessoa> daoPessoa;
    private Boolean novaUnidadeCondominial;

    public ControleCondominio() {
        dao = new CondominioDAO<>();
//        daoRecurso = new RecursoDAO<>();
//        daoUnCondominial = new UnCondominialDAO<>();
        daoPessoa = new PessoaDAO<>();
    }

    public String listar() {
        return "/privado/condominio/listar?faces-redirect=true";
    }

    public void imprimeCondominio() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCondominio", parametros, dao.getListaTodos());
    }
    
    public void imprimeCondominioCompleto (Integer id) throws Exception{
        objeto = dao.getObjectById(id);
        List<Condominio> lista = new ArrayList<>();
        lista.add(objeto);
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCondominioCompleto", parametros, lista);
    }
    
    public void adicionarRecurso() {
        if (recurso != null) {
            if (!objeto.getRecursos().contains(recurso)) {
                objeto.getRecursos().add(recurso);
                System.out.println(objeto.getRecursos());
                Util.mensagemInformacao("Recurso persistido com sucesso!");
            } else {
                Util.mensagemErro("Este desejo já existe na sua lista.");
            }
        }
    }

    public void removerRecurso(Recurso obj) {
        objeto.getRecursos().remove(obj);
        Util.mensagemInformacao("Desejo removido com sucesso!");
    }

    public void novaUnidadeCondominial() {
        setUnidadeCondominial(new UnidadeCondominial());
        novaUnidadeCondominial = true;
        System.out.println("Unidade Condominial: " + getUnidadeCondominial());
    }

    public void alterarUnidadeCondominial(int index) {
        setUnidadeCondominial(objeto.getUnidade_condominial().get(index));
        novaUnidadeCondominial = false;
    }

    public void salvarUnidadeCondominial() {
        if (novaUnidadeCondominial) {
            objeto.adicionarUnidadeCondominial(unidadeCondominial);
        }
        Util.mensagemInformacao("Unidade Condominial persistida com sucesso");
    }

    public void removerUnidadeCondominial(int index) {
        objeto.removerUnidadeCondominial(index);
        Util.mensagemInformacao("Unidade Condominial removida com sucesso");
    }

    public void novo() {
        objeto = new Condominio();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);

        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);

        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public CondominioDAO<Condominio> getDao() {
        return dao;
    }

    public void setDao(CondominioDAO<Condominio> dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

    public RecursoDAO<Recurso> getDaoEstado() {
        return daoRecurso;
    }

    public void setDaoEstado(RecursoDAO<Recurso> daoRecurso) {
        this.daoRecurso = daoRecurso;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public UnCondominialDAO<UnidadeCondominial> getDaoUnCondominial() {
        return daoUnCondominial;
    }

    public void setDaoUnCondominial(UnCondominialDAO<UnidadeCondominial> daoUnCondominial) {
        this.daoUnCondominial = daoUnCondominial;
    }

    public UnidadeCondominial getUnidadeCondominial() {
        return unidadeCondominial;
    }

    public void setUnidadeCondominial(UnidadeCondominial unidadeCondominial) {
        this.unidadeCondominial = unidadeCondominial;
    }

    public Boolean getNovaUnidadeCondominial() {
        return novaUnidadeCondominial;
    }

    public void setNovaUnidadeCondominial(Boolean novaUnidadeCondominial) {
        this.novaUnidadeCondominial = novaUnidadeCondominial;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public RecursoDAO<Recurso> getDaoRecurso() {
        return daoRecurso;
    }

    public void setDaoRecurso(RecursoDAO<Recurso> daoRecurso) {
        this.daoRecurso = daoRecurso;
    }

}
