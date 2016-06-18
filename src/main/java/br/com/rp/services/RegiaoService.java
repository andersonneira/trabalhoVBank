package br.com.rp.services;

import br.com.rp.domain.Regiao;
import br.com.rp.repository.RegiaoRepository;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class RegiaoService {

    @EJB
    private RegiaoRepository repository;

    public List<Regiao> getAllRegioes() {
        return repository.getAll();
    }
}
