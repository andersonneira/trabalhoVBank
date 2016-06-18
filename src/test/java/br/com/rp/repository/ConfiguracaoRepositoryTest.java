package br.com.rp.repository;

import java.math.BigDecimal;
import java.util.Calendar;
import javax.ejb.EJB;
import org.junit.Assert;
import org.junit.Test;
import br.com.rp.AbstractTest;
import br.com.rp.domain.Configuracao;

public class ConfiguracaoRepositoryTest extends AbstractTest {
	@EJB
    private ConfiguracaoRepository repository;
    
    @Test
    public void deveInserirUmaConfiguracao() {
        Configuracao cg = new Configuracao();
        Calendar cl = Calendar.getInstance();
        BigDecimal bg = new BigDecimal(600);
        cg.setHoraAberturaOperacao(cl.getTime());
        cg.setHoraFechamentoOperacao(cl.getTime());
        cg.setLimitePadrao(bg);
        cg = repository.save(cg);
        Assert.assertNotNull(cg.getId());
    }
}
