package br.com.rp.services;

import br.com.rp.domain.Cliente;
import br.com.rp.domain.Regiao;
import br.com.rp.repository.RegiaoRepository;
import br.com.rp.repository.impl.SolicitacaoPropostaRepositoryImpl;

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
    public Regiao save(Regiao regiao) {
        return repository.save(regiao);
    }
    public Regiao findById(Long id){
    	return repository.findById(id);
    }
    
    public Regiao setarRegiaoPorCep(Cliente clienteAVerificar){
    	Regiao regiaoParaSetar = new Regiao();
    	List<Regiao> todasRegioes = repository.getAll();
    	
    	for (Regiao regiao : todasRegioes) {
    	List<Cliente> clientesDaRegiao = repository.buscarClientesPorRegiao(regiao);
    		for (Cliente cliente : clientesDaRegiao) {
				if (clienteAVerificar.getRegiao()== cliente.getRegiao()) {
					regiaoParaSetar = regiao;
				}
			}
  
		}   	
    	
    	return regiaoParaSetar;
    }
    
    
    
    
}
