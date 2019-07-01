/*
 * 
 */
package br.edu.ifsul.DAO;

import br.edu.ifsul.converter.ConverterOrdem;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Telmo
 */
@Stateful
public class UsuarioDAO<TIPO> extends DAOGenerico<Usuario> implements Serializable{
    
    public UsuarioDAO() {
        super(Usuario.class);
    }

    @Override
    public Usuario getObjectById(Object id) throws Exception {
        Usuario obj = em.find(Usuario.class, id);
        obj.getPermissoes().size();
        return obj;
    }
}
