package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repository.PedidoRepository;
import com.nelioalves.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoSevice {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (!pedido.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado com id =" + id);
		}
		return pedido.get();
	}
}
