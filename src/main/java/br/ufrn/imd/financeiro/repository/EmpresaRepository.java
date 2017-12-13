package br.ufrn.imd.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufrn.imd.financeiro.model.Conta;
import br.ufrn.imd.financeiro.model.Empresa;
import br.ufrn.imd.financeiro.model.Tipo;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
	
	Empresa findByCnpj(String cnpj);
	
	@Query("SELECT c "
			+ "FROM Conta c "
			+ "WHERE c.empresa = ?1 " 
			+ "AND c.categoria.tipo = ?2")
	List<Conta> contasCategoriaTipo(Empresa empresa, Tipo tipo);
}
