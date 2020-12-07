package br.com.gft.desafio.api.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.desafio.api.model.Fornecedor;
import br.com.gft.desafio.api.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Fornecedor fornecedorAtualizar(Long id, @Valid Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = buscarPeloId(id);
		BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "id");
		
		return fornecedorRepository.save(fornecedorSalvo);
	}

	private Fornecedor buscarPeloId(Long id) {
		if(fornecedorRepository.findById(id).isPresent()) {
			Fornecedor fornecedorEncontrado = fornecedorRepository.findById(id).get();
			return fornecedorRepository.save(fornecedorEncontrado);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}

}
