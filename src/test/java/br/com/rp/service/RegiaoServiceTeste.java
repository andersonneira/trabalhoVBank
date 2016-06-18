package br.com.rp.service;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Regiao;
import br.com.rp.services.RegiaoService;

public class RegiaoServiceTeste extends AbstractTest {

    @EJB
    private RegiaoService service;

    @Test
    @UsingDataSet("db/regiao.xml")
    public void deveRetornar1Regiao() {
        List<Regiao> regioes = service.getAllRegioes();
        Assert.assertEquals(1, regioes.size());
    }

}
