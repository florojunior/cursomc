package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import com.nelioalves.cursomc.services.exception.DataIntegrityException;
import com.nelioalves.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaSevice {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id){
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

	public Categoria update(Categoria obj) {
		Categoria categoria = find(obj.getId());
		updateData(categoria, obj);
		
		return categoriaRepository.save(categoria);
	}

	public void delete(Integer id) {
		find(id);
		
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que posseui produtos");
		}
		
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPage, Sort.Direction.fromString(direction), orderBy);
		
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoria) {
		return new Categoria(categoria.getId(), categoria.getNome());
		
	}
	
	private void updateData(Categoria categoriaNew, Categoria categoriaOld) {
		categoriaNew.setNome(categoriaOld.getNome());
	}
	
}
