
package br.edu.ifsul.DAO;

import br.edu.ifsul.converter.ConverterOrdem;
import java.io.Serializable;
import br.edu.ifsul.modelo.Aluguel;
import javax.ejb.Stateful;

@Stateful
public class AluguelDAO<TIPO> extends DAOGenerico<Aluguel> implements Serializable{
    public AluguelDAO() {
        super(Aluguel.class);
     
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("valor", "Valor", "="));
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }

    @Override
    public Aluguel getObjectById(Object id) throws Exception {
        Aluguel obj = em.find(Aluguel.class, id);
        obj.getMensalidades().size();
        return obj;
    }
}
