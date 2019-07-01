    
package br.edu.ifsul.controle;

import br.edu.ifsul.DAO.LocatarioDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.util.Util;

/**
 *
 * @author vinirafaelsch
 */

@Named(value = "ControleLocatario")
@SessionScoped
public class ControleLocatario implements Serializable{
    
    @EJB
    private LocatarioDAO<Locatario> dao;
    private Locatario objeto;
    private Boolean editando;

    public ControleLocatario() {
        editando = false;
    }

    public String listar() {
        return "/privado/locatario/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Locatario();
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
    
    public LocatarioDAO<Locatario> getDao() {
        return dao;
    }

    public void setDao(LocatarioDAO<Locatario> dao) {
        this.dao = dao;
    }

    public Locatario getObjeto() {
        return objeto;
    }

    public void setObjeto(Locatario objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    
    
}