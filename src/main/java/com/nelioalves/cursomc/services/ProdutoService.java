package com.nelioalves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelioalves.cursomc.repository.ProdutoRepository;

public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
}
