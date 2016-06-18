/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rp.repository;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Regiao;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author anderson
 */
public class RegiaoRepositoryTest extends AbstractTest {

    @EJB
    private RegiaoRepository repository;

    @Test(expected = EJBTransactionRolledbackException.class)
    public void deveNaoInserirPorFaltaDeCaracteresDoNome() {
        Regiao sp = new Regiao();
        sp.setNome("1");
        repository.save(sp);
    }

    @Test
    public void deveInserir() {
        Regiao sp = new Regiao();
        sp.setNome("Norte");
        sp = repository.save(sp);
        Assert.assertNotNull(sp.getId());
    }
    
}
