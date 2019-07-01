package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.Aluguel;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "ConverterAluguel")
@RequestScoped
public class ConverterAluguel implements Converter, Serializable {

    @PersistenceContext(unitName = "ProjetoImobiliariaWebPU")
    private EntityManager em;

    public ConverterAluguel() {

    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("Selecione uma opção")) {
            return null;
        }
        return em.find(Aluguel.class, Integer.parseInt(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        Aluguel obj = (Aluguel) value;
        return (value != null) ? ((Aluguel) value).getId().toString() : null;
    }

}
