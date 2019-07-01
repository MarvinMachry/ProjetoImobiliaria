
package br.edu.ifsul.DAO;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Recurso;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class RecursoDAO<TIPO> extends DAOGenerico<Recurso> implements Serializable{
    public RecursoDAO() {
        super(Recurso.class);
        
        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("id", "ID", "="));
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
    
}