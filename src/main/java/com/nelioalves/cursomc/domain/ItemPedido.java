package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Double preco;
	private Integer quantidade;
	private Double desconto;

	public ItemPedido() {

	}

	public ItemPedido(Pedido pedido, Produto produto, java.lang.Double preco, Integer quantidade, java.lang.Double d) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.preco = preco;
		this.quantidade = quantidade;
		this.desconto = d;
	}

	public Pedido getPedido() {
		return this.id.getPedido();
	}

	public Produto getProduto() {
		return this.id.getProduto();
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

}
