package br.ufrn.imd.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.imd.financeiro.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>{
	
	

}
