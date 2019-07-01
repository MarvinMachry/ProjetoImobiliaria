package br.edu.ifsul.controle;

import br.edu.ifsul.DAO.UnCondominialDAO;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value = "ControleUnidadeCondominial")
@SessionScoped
public class ControleUnCondominial implements Serializable {

    @EJB
    private UnCondominialDAO<UnidadeCondominial> dao;
    private UnidadeCondominial objeto;
    private Boolean editando;

    public ControleUnCondominial() {
        dao = new UnCondominialDAO<>();
        editando = false;
    }

    public String listar() {
        return "/privado/condominio/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new UnidadeCondominial();
        editando = true;
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;

        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            editando = false;

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
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public UnCondominialDAO<UnidadeCondominial> getDao() {
        return dao;
    }

    public void setDao(UnCondominialDAO<UnidadeCondominial> dao) {
        this.dao = dao;
    }

    public UnidadeCondominial getObjeto() {
        return objeto;
    }

    public void setObjeto(UnidadeCondominial objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

}
