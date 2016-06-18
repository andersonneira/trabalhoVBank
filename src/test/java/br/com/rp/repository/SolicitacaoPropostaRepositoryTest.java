/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.repository;

import br.com.rp.AbstractTest;
import br.com.rp.domain.SolicitacaoProposta;
import br.com.rp.repository.SolicitacaoPropostaRepository;
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
        repository.save(sp);
        
    }
    
    @Test(expected = EJBTransactionRolledbackException.class)
    public void deveNaoInserirPorNaoTerUmEmailValido() {
        SolicitacaoProposta sp = new SolicitacaoProposta();
        sp.setNome("Anderson B");
        sp.setEmail("an");
        sp.setCep("87040-000");
        repository.save(sp);
    }
    
    public void deveInserirUmaSolicitacao() {
        SolicitacaoProposta sp = new SolicitacaoProposta();
        sp.setNome("Anderson B");
        sp.setEmail("an");
        sp.setCep("87040-000");
        sp = repository.save(sp);
        Assert.assertNotNull(sp.getId());
    }
}
