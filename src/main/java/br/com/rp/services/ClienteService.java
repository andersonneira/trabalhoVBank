package br.com.rp.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Cliente;
import br.com.rp.repository.ClienteRepository;

@Stateless
public class ClienteService {
    
    @EJB
    private ClienteRepository repository;
    
    public List<Cliente> getAll() {
        return repository.getAll();
    }
    
    public boolean verificaSeJaExisteEsteDocumentoInserido(String documento) {
        return repository.verificaSeJaExisteEsteDocumentoInserido(documento);
    }
    public Cliente save(Cliente cliente){
    	return repository.save(cliente);
    }
}
