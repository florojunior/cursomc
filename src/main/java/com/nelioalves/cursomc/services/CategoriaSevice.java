package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import com.nelioalves.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaSevice {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if(!categoria.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado com id ="+ id);
		}
		return categoria.get();		 
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
}
