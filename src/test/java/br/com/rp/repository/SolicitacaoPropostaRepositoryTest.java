/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.repository;

import br.com.rp.AbstractTest;
import br.com.rp.domain.SolicitacaoProposta;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author anderson
 */
public class SolicitacaoPropostaRepositoryTest extends AbstractTest {
    
    @EJB
    private SolicitacaoPropostaRepository repository;
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void deveNaoInserirPorFaltaDeCaracteresDoNome() {
        SolicitacaoProposta sp = new SolicitacaoProposta();
        sp.setNome("1");
        sp.setEmail("andersonneira@gmail.com");
        sp.setCep("87040-000");
        sp.setDocumento("866.576.159-49");
        repository.save(sp);
        
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void deveNaoInserirPorNaoTerUmEmailValido() {
        SolicitacaoProposta sp = new SolicitacaoProposta();
        sp.setNome("Anderson B");
        sp.setEmail("an");
        sp.setCep("87040-000");
        sp.setDocumento("866.576.159-49");
        repository.save(sp);
    }
    
    @Test
    public void deveInserirUmaSolicitacao() {
        SolicitacaoProposta sp = new SolicitacaoProposta();
        sp.setNome("Anderson B");
        sp.setEmail("teste@gmail.com");
        sp.setCep("87040-000");
        sp.setDocumento("866.576.159-49");
        sp = repository.save(sp);
        Assert.assertNotNull(sp.getId());
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void naoDeveInserirPorFormatoCepInvalido() {
    	SolicitacaoProposta sp = new SolicitacaoProposta();
    	sp.setNome("Anderson B");
    	sp.setEmail("teste@gmail.com");
    	sp.setCep("87.04.00.00");
    	sp.setDocumento("866.576.159-49");
    	sp = repository.save(sp);
    	Assert.assertNotNull(sp.getId());
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void naoDeveInserirPorFaltaDeDocumento() {
        SolicitacaoProposta sp = new SolicitacaoProposta();
        sp.setNome("Anderson B");
        sp.setEmail("teste@gmail.com");
        sp.setCep("87040-000");
        sp = repository.save(sp);
        Assert.assertNotNull(sp.getId());
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void naoDeveInserirPorFormatoDocumentoInvalido() {
    	SolicitacaoProposta sp = new SolicitacaoProposta();
    	sp.setNome("Anderson B");
    	sp.setEmail("teste@gmail.com");
    	sp.setCep("87040-000");
    	sp.setDocumento("88.9999.88.8-12");
    	sp = repository.save(sp);
    	Assert.assertNotNull(sp.getId());
    }
    

    @Test(expected = EJBTransactionRolledbackException.class)
    public void naoDeveInserirPorDocumentoDuplicado() {
    	SolicitacaoProposta sp = new SolicitacaoProposta();
    	sp.setNome("Anderson B");
    	sp.setEmail("teste@gmail.com");
    	sp.setCep("87040-000");
    	sp.setDocumento("866.576.159-49");
    	sp = repository.save(sp);
    	
    	SolicitacaoProposta sp1 = new SolicitacaoProposta();
    	sp1.setNome("Anderson B");
    	sp1.setEmail("teste@gmail.com");
    	sp1.setCep("87040-000");
    	sp1.setDocumento("866.576.159-49");
    	sp1 = repository.save(sp1);
    	
    	
    	Assert.assertNotNull(sp.getId());
    }
}
