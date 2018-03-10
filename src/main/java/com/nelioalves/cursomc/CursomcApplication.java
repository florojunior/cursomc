package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import com.nelioalves.cursomc.repository.CidadeRepository;
import com.nelioalves.cursomc.repository.EstadoRepository;
import com.nelioalves.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria = new Categoria(null, "Informatica");
		Categoria categoria2 = new Categoria(null, "Escritorio");

		Produto produto = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);

		categoria.getProdutos().addAll(Arrays.asList(produto, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));

		produto.getCategorias().add(categoria);
		produto2.getCategorias().addAll(Arrays.asList(categoria, categoria2));
		produto3.getCategorias().add(categoria);

		Estado estado = new Estado(null, "São Paulo");
		Estado estado2 = new Estado(null, "Minas Gerais");

		Cidade cidade = new Cidade(null, "Uberlandia", estado2);
		Cidade cidade2 = new Cidade(null, "Sao Paulo", estado);
		Cidade cidade3 = new Cidade(null, "Campinas", estado);

		estado.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		estado2.getCidades().addAll(Arrays.asList(cidade));

		cidade.setEstado(estado2);
		cidade2.setEstado(estado);
		cidade3.setEstado(estado);

		categoriaRepository.saveAll(Arrays.asList(categoria, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto, produto2, produto3));

		estadoRepository.saveAll(Arrays.asList(estado, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade, cidade2, cidade3));
		

	}

}
