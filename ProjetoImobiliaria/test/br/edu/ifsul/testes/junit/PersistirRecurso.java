/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Recurso;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author 20171PF.CC0054
 */
public class PersistirRecurso {
    EntityManagerFactory emf;
    EntityManager em;

    public PersistirRecurso() {
    }
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("ProjetoImobiliariaPU");
        em = emf.createEntityManager();        
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste(){
        
        Locatario lo = new Locatario();
        lo.setLocalTrabalho("aaa");
        lo.setRenda(1000.00);
        lo.setTelefoneTrabalho("1111111111");
        
        lo.setCpf("03348363012");
        lo.setEmail("teste");
        lo.setNome("marvin");
        lo.setTelefone("222225");
        em.getTransaction().begin();
        em.persist(lo);//o status do objeto muda e passa para: Managed
        em.getTransaction().commit();
   
        Recurso re = new Recurso();
        re.setDescricao("testeRec");
        em.getTransaction().begin();
        em.persist(re);//o status do objeto muda e passa para: Managed
        em.getTransaction().commit();
        
        Pessoa pe = new Pessoa();
        pe.setCpf("03348363012");
        pe.setEmail("teste@teste");
        pe.setNome("Marvin");
        pe.setTelefone("40028922");
        em.getTransaction().begin();
        em.persist(pe);
        em.getTransaction().commit();
        
        Pessoa pe1 = new Pessoa();
        pe1.setCpf("03348363012");
        pe1.setEmail("teste@teste");
        pe1.setNome("Joao");
        pe1.setTelefone("40028922");
        em.getTransaction().begin();
        em.persist(pe1);
        em.getTransaction().commit();
    }
}
