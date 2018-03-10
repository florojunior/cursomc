package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.repository.EnderecoRepository;
import com.nelioalves.cursomc.services.exception.ObjectNotFoundException;

@Service
public class EnderecoSevice {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco buscar(Integer id){
		Optional<Endereco> categoria = enderecoRepository.findById(id);
		
		if(!categoria.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado com id ="+ id);
		}
		return categoria.get();		 
	}
}
