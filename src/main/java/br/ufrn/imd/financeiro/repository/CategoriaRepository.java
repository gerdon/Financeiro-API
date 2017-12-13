package br.ufrn.imd.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufrn.imd.financeiro.model.Categoria;
import br.ufrn.imd.financeiro.model.Tipo;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	Categoria findByDescricao(String descricao);
	
	List<Categoria> findByTipo(Tipo tipo);
}
