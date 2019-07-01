/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converter;

import br.edu.ifsul.DAO.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "ConverterPessoa") 
@RequestScoped
public class ConverterPessoa implements Converter, Serializable {

    @PersistenceContext(unitName = "ProjetoImobiliariaWebPU")
    private EntityManager em;

    public ConverterPessoa() {

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("Selecione uma opção")) {
            return null;
        }
        return em.find(Pessoa.class, Integer.parseInt(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Pessoa obj = (Pessoa) value;
        return (value != null) ? ((Pessoa) value).getId().toString() : null;
    }

}
