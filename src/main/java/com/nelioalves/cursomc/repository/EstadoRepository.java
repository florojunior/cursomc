package com.nelioalves.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
