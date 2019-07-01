
package br.edu.ifsul.converter;

import br.edu.ifsul.DAO.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.UnidadeCondominial;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "ConverterUnidadeCondominial")
@RequestScoped
public class ConverterUnCondominial implements Converter, Serializable {

    @PersistenceContext(unitName = "ProjetoImobiliariaWebPU")
    private EntityManager em;

    public ConverterUnCondominial() {

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("Selecione uma opção")) {
            return null;
        }
        return em.find(UnidadeCondominial.class, Integer.parseInt(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        UnidadeCondominial obj = (UnidadeCondominial) value;
        return (value != null) ? ((UnidadeCondominial) value).getId().toString() : null;
    }

}
