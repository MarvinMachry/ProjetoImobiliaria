
package br.edu.ifsul.controle;

import br.edu.ifsul.DAO.RecursoDAO;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.modelo.Recurso;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "ControleRecurso")
@SessionScoped
public class ControleRecurso implements Serializable{
    
    @EJB
    private RecursoDAO<Recurso> dao;
    private Recurso objeto;
    private Boolean editando;

    public ControleRecurso() {
        editando = false;
    }

    public String listar() {
        return "/privado/recurso/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Recurso();
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
            if(objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public RecursoDAO<Recurso> getDao() {
        return dao;
    }

    public void setDao(RecursoDAO<Recurso> dao) {
        this.dao = dao;
    }

    public Recurso getObjeto() {
        return objeto;
    }

    public void setObjeto(Recurso objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
     
}