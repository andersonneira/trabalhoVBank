package br.com.rp.services;

import br.com.rp.domain.ContaCorrente;
import br.com.rp.repository.ContaCorrenteRepository;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ContaCorrenteService {

    @EJB
    private ContaCorrenteRepository repository;

    public List<ContaCorrente> getAllRegioes() {
        return repository.getAll();
    }
}
