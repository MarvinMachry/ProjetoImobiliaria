


package br.edu.ifsul.DAO;

import br.edu.ifsul.converter.ConverterOrdem;
import java.io.Serializable;
import javax.ejb.Stateful;
import br.edu.ifsul.modelo.Condominio;

@Stateful
public class CondominioDAO<TIPO> extends DAOGenerico<Condominio> implements Serializable{
    public CondominioDAO() {
        super(Condominio.class);

        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
    
    @Override
    public Condominio getObjectById(Object id) throws Exception {
        Condominio obj = em.find(Condominio.class, id);
        obj.getRecursos().size();
        obj.getUnidade_condominial().size();
        return obj;
    } 

}

