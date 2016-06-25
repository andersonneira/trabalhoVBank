package br.com.rp.repository;

import java.util.Calendar;

import javax.ejb.EJB;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cliente;
import br.com.rp.domain.Regiao;
import org.jboss.arquillian.persistence.UsingDataSet;

public class ClienteRepositoryTest extends AbstractTest {

    private static final String _DOCUMENTO_VALIDO = "828.588.613-15";
    private static final String _NOME_VALIDO = "Teste";
    private static final String _EMAIL_VALIDO = "teste@gmail.com";
    private static final String _CEP_VALIDO = "86800-005";

    @EJB
    private ClienteRepository repository;

    @EJB
    private RegiaoRepository regiaoRepository;
    private Regiao regiao;
    private Cliente cli;

    @Before
    public void before() {
        regiao = new Regiao();
        regiao.setNome("Teste");
        regiao.setCepFinal("86800-005");
        regiao.setCepInicial("86800-005");
        regiaoRepository.save(regiao);

        cli = new Cliente();
        cli.setEmail(_EMAIL_VALIDO);
        cli.setCep(_CEP_VALIDO);
        cli.setNome(_NOME_VALIDO);
        cli.setDataCadastro(Calendar.getInstance().getTime());
        cli.setDocumento(_DOCUMENTO_VALIDO);
        cli.setRegiao(regiaoRepository.findById(regiao.getId()));
    }

    @After
    public void after() {
        repository.remove(cli.getId());
        regiaoRepository.remove(regiao.getId());
    }

    @Test
//	@UsingDataSet("db/regiao.xml")
//	@CleanupUsingScript("db/cleanup.xml")
    public void deveInserirComSucesso() {
        repository.save(cli);
        Assert.assertNotNull(cli.getId());
    }

    @Test(expected = Exception.class)
    public void naoDeveInserirPorFaltaDeEmail() {
        cli.setEmail(null);
        repository.save(cli);
    }

    @Test(expected = Exception.class)
    public void naoDeveInserirPorFormatoIncorretoEmail() {
        cli.setEmail("testebr");
        repository.save(cli);
    }

    @Test(expected = Exception.class)
    public void naoDeveInserirPorFaltaDeNome() {
        cli.setNome(null);
        repository.save(cli);
    }

    @Test(expected = Exception.class)
    public void naoDeveInserirPorNomeMuitoCurto() {
        cli.setNome("T");
        repository.save(cli);
    }

    @Test(expected = Exception.class)
    public void naoDeveInserirPorNomeMuitoLongo() {
        String nome = "";
        for (int i = 0; i < 50; i++) {
            nome += "T";
        }
        cli.setNome(nome);
        repository.save(cli);
    }

    @Test(expected = Exception.class)
    public void naoDeveInserirPorFaltaDeCep() {
        cli.setCep(null);
        repository.save(cli);
    }

    @Test(expected = Exception.class)
    public void naoDeveInserirPorFormatoCepInvalido() {
        cli.setCep("86800005");
        repository.save(cli);
    }

    @Test(expected = Exception.class)
    public void naoDeveInserirPorFaltaDeDocumento() {
        cli.setDocumento(null);
        repository.save(cli);
    }

    @Test(expected = Exception.class)
    public void naoDeveInserirPorFaltaDeRegiao() {
        cli.setRegiao(null);
        repository.save(cli);
    }

    @Test
    public void deveTerEsteDocumentoCadastrado() {
        repository.save(cli);
        Assert.assertEquals(true, repository.verificaSeJaExisteEsteDocumentoInserido("828.588.613-15"));
    }
    
    @Test
    public void deveNaoTerEsteDocumentoCadastrado() {
        repository.save(cli);
        Assert.assertEquals(false, repository.verificaSeJaExisteEsteDocumentoInserido("928.588.613-15"));
    }
}
