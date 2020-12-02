package br.com.gft.desafio.api.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.desafio.api.model.Cliente;
import br.com.gft.desafio.api.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository clienteRepository;

	public Cliente clienteAtualizar(Long id, @Valid Cliente cliente) {
		Cliente clienteSalvo = buscarPeloId(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		
		return clienteRepository.save(clienteSalvo);
	}

	private Cliente buscarPeloId(Long id) {
		if(clienteRepository.findById(id).isPresent()) {
			Cliente clienteEncontrado = clienteRepository.findById(id).get();
			return clienteRepository.save(clienteEncontrado);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
}
