package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repository.ClienteRepository;
import com.nelioalves.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteSevice {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id){
		Optional<Cliente> categoria = clienteRepository.findById(id);
		
		if(!categoria.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado com id ="+ id);
		}
		return categoria.get();		 
	}
}
