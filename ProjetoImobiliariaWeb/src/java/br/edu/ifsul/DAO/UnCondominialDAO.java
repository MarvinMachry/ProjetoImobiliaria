package br.edu.ifsul.DAO;

import br.edu.ifsul.converter.ConverterOrdem;
import java.io.Serializable;
import javax.ejb.Stateful;
import br.edu.ifsul.modelo.UnidadeCondominial;

/**
 *
 * @author vinirafaelsch
 */
@Stateful
public class UnCondominialDAO<TIPO> extends DAOGenerico<UnidadeCondominial> implements Serializable {

    public UnCondominialDAO() {
        super(UnidadeCondominial.class);

        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("numero", "Número", "="));
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }

}
