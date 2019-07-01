package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.Mensalidade;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Named(value = "ConverterMensalidade")
@RequestScoped
public class ConverterMensalidade implements Converter, Serializable {

    @PersistenceContext(unitName = "ProjetoImobiliariaWebPU")
    private EntityManager em;

    public ConverterMensalidade() {

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("Selecione uma opção")) {
            return null;
        }
        return em.find(Mensalidade.class, Integer.parseInt(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Mensalidade obj = (Mensalidade) value;
        return (value != null) ? ((Mensalidade) value).getId().toString() : null;
    }

}
