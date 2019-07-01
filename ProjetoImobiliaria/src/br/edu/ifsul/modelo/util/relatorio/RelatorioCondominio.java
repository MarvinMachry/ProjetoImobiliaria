package br.edu.ifsul.modelo.util.relatorio;

import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.UnidadeCondominial;
import java.util.ArrayList;
import java.util.List;

public class RelatorioCondominio {

    public static List<UnidadeCondominial> carregar() {
        List<UnidadeCondominial> lista = new ArrayList<>();
        Condominio c = new Condominio();
        c.setNome("Chronos");
        UnidadeCondominial un = new UnidadeCondominial();
        un.setCondominio(c);
        un.setId(1);
        un.setNumero("700");
        un.setDescricao("AP");
        un.setArea(100.00);
        un.setNumeroQuarto(83);
        lista.add(un);
        UnidadeCondominial un2 = new UnidadeCondominial();
        un2.setCondominio(c);
        un2.setId(1);
        un2.setNumero("999");
        un2.setDescricao("AP");
        un2.setArea(99.00);
        un2.setNumeroQuarto(99);
        return lista;
    }
}
