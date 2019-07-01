/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.DAO.AluguelDAO;
import br.edu.ifsul.DAO.MensalidadeDAO;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.Mensalidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "ControleAluguel")
@SessionScoped
public class ControleAluguel implements Serializable {

    @EJB
    private AluguelDAO<Aluguel> dao;
    private MensalidadeDAO<Mensalidade> daoMensalidade;
    private Mensalidade mensalidade;
    private Aluguel objeto;
    private Boolean novaMensalidade;

    public ControleAluguel() {
        dao = new AluguelDAO<>();
        daoMensalidade = new MensalidadeDAO<>();
    }

    public void novaMensalidade() {
        mensalidade = new Mensalidade();
        novaMensalidade = true;
    }

    public void alterarMensalidade(int index) {
        mensalidade = objeto.getMensalidades().get(index);
        novaMensalidade = false;
    }

    public void salvarMensalidade() {
        if (novaMensalidade) {
            objeto.adicionarMensalidade(mensalidade);
        }
        Util.mensagemInformacao("Mensalidade persistida com sucesso");
    }

    public void removerMensalidade(int index) {
        objeto.removerMensalidade(index);
        Util.mensagemInformacao("Mensalidade removida com sucesso");
    }

    public String listar() {
        return "/privado/aluguel/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Aluguel();
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

    public MensalidadeDAO<Mensalidade> getDaoMensalidade() {
        return daoMensalidade;
    }

    public void setDaoMensalidade(MensalidadeDAO<Mensalidade> daoMensalidade) {
        this.daoMensalidade = daoMensalidade;
    }

    public AluguelDAO<Aluguel> getDao() {
        return dao;
    }

    public void setDao(AluguelDAO<Aluguel> dao) {
        this.dao = dao;
    }

    public Aluguel getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluguel objeto) {
        this.objeto = objeto;
    }

    public Mensalidade getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
    }

    public Boolean getNovaMensalidade() {
        return novaMensalidade;
    }

    public void setNovaMensalidade(Boolean novaMensalidade) {
        this.novaMensalidade = novaMensalidade;
    }

}
