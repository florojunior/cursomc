package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import com.nelioalves.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria = new Categoria(null,"Informatica");
		Categoria categoria2 = new Categoria(null,"Escritorio");
		
		Produto produto = new Produto(null,"Computador",2000.00);
		Produto produto2 = new Produto(null,"Impressora",800.00);
		Produto produto3 = new Produto(null,"Mouse",80.00);
		
		categoria.getProdutos().addAll(Arrays.asList(produto,produto2,produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto.getCategorias().add(categoria);
		produto2.getCategorias().addAll(Arrays.asList(categoria,categoria2));
		produto3.getCategorias().add(categoria);
		
		categoriaRepository.saveAll(Arrays.asList(categoria,categoria2));
		produtoRepository.saveAll(Arrays.asList(produto,produto2,produto3));
		
	}
	
}
