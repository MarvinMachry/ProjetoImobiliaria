package br.edu.ifsul.DAO;

import br.edu.ifsul.converter.ConverterOrdem;
import java.io.Serializable;
import javax.ejb.Stateful;
import br.edu.ifsul.modelo.Locatario;
import java.util.List;



@Stateful
public class LocatarioDAO<TIPO> extends DAOGenerico<Locatario> implements Serializable{
 
    public LocatarioDAO() {
        super(Locatario.class);
        
        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
    
    @Override
    public List<Locatario> getListaObjetos() {
        String jpql = String.format("FROM %s WHERE tipo='LOC'", Locatario.class.getSimpleName());
        return em.createQuery(jpql).getResultList();
    }
    
}
