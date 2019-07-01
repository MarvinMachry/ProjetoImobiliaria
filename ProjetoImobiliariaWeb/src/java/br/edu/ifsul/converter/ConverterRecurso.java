
package br.edu.ifsul.converter;

import br.edu.ifsul.DAO.RecursoDAO;
import br.edu.ifsul.modelo.Recurso;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "ConverterRecurso") 
@RequestScoped
public class ConverterRecurso implements Converter, Serializable {

    @PersistenceContext(unitName = "ProjetoImobiliariaWebPU")
    private EntityManager em;

    public ConverterRecurso() {

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("Selecione uma opção")) {
            return null;
        }
        return em.find(Recurso.class, Integer.parseInt(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Recurso obj = (Recurso) value;
        return (value != null) ? ((Recurso) value).getId().toString() : null;
    }

}

