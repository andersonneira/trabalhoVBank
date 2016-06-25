package br.com.rp;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import br.com.rp.anotations.Cep;
import br.com.rp.anotations.validators.CepValidator;
import br.com.rp.domain.Log;
import br.com.rp.dto.ContaCorrenteResumoDTO;
import br.com.rp.enums.StatusConta;
import br.com.rp.repository.LogRepositoryTest;
import br.com.rp.repository.Repository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;
import br.com.rp.rest.LogRest;
import br.com.rp.rest.LogRestTest;
import br.com.rp.service.LogServiceTest;
import br.com.rp.services.LogService;

@RunWith(Arquillian.class)
public abstract class AbstractTest {

	@PersistenceContext
	EntityManager em;

	@Before
	public void setup() {

	}   

	@After
	public void tearDown() {
	}

	@Deployment(testable = true)
	public static WebArchive createTestArchive() {

		File[] deps = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeAndTestDependencies().resolve()
				.withTransitivity().asFile();

		WebArchive archive = ShrinkWrap.create(WebArchive.class, "vbank.war").addPackages(false, Log.class.getPackage())
				.addPackages(false, Repository.class.getPackage())
				.addPackages(false, AbstractRepositoryImpl.class.getPackage())
				.addPackages(false, AbstractTest.class.getPackage())
				.addPackages(false, Cep.class.getPackage())
				.addPackages(false, CepValidator.class.getPackage())

                                .addPackage(LogRest.class.getPackage())
                                .addPackage(LogRestTest.class.getPackage())
                        
                                .addPackage(LogRepositoryTest.class.getPackage())
				.addPackage(LogService.class.getPackage())
				.addPackage(LogServiceTest.class.getPackage())
				.addPackage(StatusConta.class.getPackage())
				.addPackage(ContaCorrenteResumoDTO.class.getPackage())
                                
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("cesumar-ds.xml")
				.addAsLibraries(deps);

		return archive;
	}

}
