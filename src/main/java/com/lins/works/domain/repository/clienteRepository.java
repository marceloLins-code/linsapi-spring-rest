package com.lins.works.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lins.works.domain.model.Cliente;

// CALSSE QUE O SPRING ABSTARI AS CONSULTAS
@Repository
public interface clienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);
	
}
