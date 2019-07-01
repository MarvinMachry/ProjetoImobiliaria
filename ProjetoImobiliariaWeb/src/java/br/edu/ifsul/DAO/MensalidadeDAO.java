package br.edu.ifsul.DAO;

import br.edu.ifsul.converter.ConverterOrdem;
import java.io.Serializable;
import javax.ejb.Stateful;
import br.edu.ifsul.modelo.Mensalidade;


@Stateful
public class MensalidadeDAO<TIPO> extends DAOGenerico<Mensalidade> implements Serializable {

    public MensalidadeDAO() {
        super(Mensalidade.class);

        // inicializar as ordenações possiveis        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("valor", "Valor", "="));
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }

}
