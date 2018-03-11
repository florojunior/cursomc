package com.nelioalves.cursomc;

import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enumerated.EstadoPagamento;
import com.nelioalves.cursomc.domain.enumerated.TipoCliente;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import com.nelioalves.cursomc.repository.CidadeRepository;
import com.nelioalves.cursomc.repository.ClienteRepository;
import com.nelioalves.cursomc.repository.EnderecoRepository;
import com.nelioalves.cursomc.repository.EstadoRepository;
import com.nelioalves.cursomc.repository.ItemPedidoRepository;
import com.nelioalves.cursomc.repository.PagamentoRepository;
import com.nelioalves.cursomc.repository.PedidoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria = new Categoria(null, "Informatica");
		Categoria categoria2 = new Categoria(null, "Escritorio");
		Categoria categoria3 = new Categoria(null, "Cama , mesa e banho");
		Categoria categoria4 = new Categoria(null, "Eletronicos");
		Categoria categoria5 = new Categoria(null, "Limpeza");
		Categoria categoria6 = new Categoria(null, "Perfumaria");

		Produto produto = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);

		categoria.getProdutos().addAll(Arrays.asList(produto, produto2, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2));

		produto.getCategorias().add(categoria);
		produto2.getCategorias().addAll(Arrays.asList(categoria, categoria2,categoria3, categoria4,categoria5, categoria6));
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
		
		Cliente cliente1 = new Cliente(null, "jrlima@gmail.com", "Floro Lima Reis Junio", "01081406283", TipoCliente.PESSOAFISICA);

		cliente1.getTelefones().addAll(Arrays.asList("991955617","999999999"));
		
		Endereco endereco = new Endereco(null, "Rua Nilo Geber", "73", "Ouro verde", "69082550", cidade2, cliente1);
		Endereco endereco2 = new Endereco(null, "Rua Nilo Geber 2", "773", "Coroad", "69082550", cidade2, cliente1);
		
		Pedido pedido = new Pedido(null, Calendar.getInstance().getTime(),  cliente1, endereco);
		Pedido pedido2 = new Pedido(null, Calendar.getInstance().getTime(),  cliente1, endereco2);
		
		Pagamento pagamento = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, pedido,Calendar.getInstance().getTime(),null);
		pedido.setPagamento(pagamento);
		Pagamento pagamento2 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, pedido2,3);
		pedido2.setPagamento(pagamento2);
		
		
		
		
		
		categoriaRepository.saveAll(Arrays.asList(categoria, categoria2,categoria3, categoria4,categoria5, categoria6));
		produtoRepository.saveAll(Arrays.asList(produto, produto2, produto3));

		estadoRepository.saveAll(Arrays.asList(estado, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade, cidade2, cidade3));
		
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco,endereco2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagamento,pagamento2));
		pedidoRepository.saveAll(Arrays.asList(pedido,pedido2));
		
		ItemPedido ip1 = new ItemPedido(pedido, produto, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(pedido, produto3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);
		
		pedido.getItens().addAll(Arrays.asList(ip1, ip2));
		pedido2.getItens().addAll(Arrays.asList(ip3));
	
		produto.getItens().addAll(Arrays.asList(ip1));
		produto2.getItens().addAll(Arrays.asList(ip3));
		produto3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
