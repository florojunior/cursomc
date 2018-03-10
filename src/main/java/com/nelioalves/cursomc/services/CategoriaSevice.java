package com.nelioalves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaSevice {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id){
		Categoria categoria = categoriaRepository.getOne(id);
		return categoria;		 
	}
}
