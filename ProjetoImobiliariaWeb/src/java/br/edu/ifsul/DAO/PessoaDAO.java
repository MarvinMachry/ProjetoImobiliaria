
package br.edu.ifsul.DAO;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

@Stateful
public class PessoaDAO<TIPO> extends DAOGenerico<Pessoa> implements Serializable{
    public PessoaDAO() {
        super(Pessoa.class);

        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }

    @Override
    public List<Pessoa> getListaObjetos() {
        String jpql = String.format("FROM %s WHERE tipo='PES'", Pessoa.class.getSimpleName());
        System.out.println(jpql);
        List<Pessoa> ll = em.createQuery(jpql).getResultList();
        System.out.println(ll.size());
        return ll;
    }

}
