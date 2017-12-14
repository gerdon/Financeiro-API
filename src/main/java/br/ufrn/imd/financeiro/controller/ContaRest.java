package br.ufrn.imd.financeiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.financeiro.model.Conta;
import br.ufrn.imd.financeiro.repository.ContaRepository;

@RestController
@RequestMapping("empresas")
public class ContaRest {
	
	/**
	 * Repositorio de empresas
	 */
	@Autowired
	private ContaRepository repository;
	
	/**
	 * Salvar uma empresa
	 * @param conta
	 * @return
	 */
	@PostMapping
	public Conta save(Conta conta) {
		return repository.save(conta);
	}
	
	/**
	 * Listar todas as empresas
	 * @return
	 */
	@GetMapping
	public List<Conta> findAll(){
		return repository.findAll();
	}
	
	/**
	 * Listar uma empresa pelo Id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Conta> getById(Integer id){
		Conta conta = repository.findOne(id);
		
		if(conta == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(conta);
	}
	
	/**
	 * Atualizar uma empresa pelo Id
	 * @param id
	 * @param contaInformacoes
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Conta> update(Integer id, Conta contaInformacoes){
		Conta conta = repository.findOne(id);
		
		if(conta == null)
			return ResponseEntity.notFound().build();
		
		conta.setValor(contaInformacoes.getValor());
		conta.setCategoria(contaInformacoes.getCategoria());
		conta.setData(contaInformacoes.getData());
		conta.setEmpresa(contaInformacoes.getEmpresa());
		
		Conta updateConta = repository.save(conta);	
		return ResponseEntity.ok().body(updateConta);
	}
	
	/**
	 * Deletar uma empresa pelo ID
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Conta> delete(Integer id){
		Conta conta = repository.findOne(id);
		
		if(conta == null)
			return ResponseEntity.notFound().build();
		
		repository.delete(conta);	
		return ResponseEntity.ok().build();
	}

}
