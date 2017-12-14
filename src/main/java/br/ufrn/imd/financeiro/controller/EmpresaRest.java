package br.ufrn.imd.financeiro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.financeiro.model.Empresa;
import br.ufrn.imd.financeiro.repository.EmpresaRepository;

@RestController
@RequestMapping("empresas")
public class EmpresaRest {
	
	@Autowired
	private EmpresaRepository repository;
	
	@PostMapping
	public Empresa save(@Valid @RequestBody Empresa empresa){
		return repository.save(empresa);
	}
	
	/**
	 * Listar todas as empresas
	 * @return
	 */
	@GetMapping
	public List<Empresa> findAll(){
		return repository.findAll();
	}
	
	/**
	 * Buscar uma empresa pelo ID
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Empresa> getById(@PathVariable(value = "id") Integer id){
		Empresa empresa = repository.findOne(id);
		
		if(empresa == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(empresa);
	}
	
	/**
	 * Atualizar uma empresa pelo ID
	 * @param id
	 * @param empresaInformacoes
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Empresa> update(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody Empresa empresaInformacoes){
		Empresa empresa = repository.findOne(id);
		
		if(empresa == null)
			return ResponseEntity.notFound().build();
		
		empresa.setCnpj(empresaInformacoes.getCnpj());
		empresa.setNomeFantasia(empresaInformacoes.getNomeFantasia());
		empresa.setContas(empresaInformacoes.getContas());
		
		Empresa updateEmpresa = repository.save(empresa);
		return ResponseEntity.ok(updateEmpresa);
	}
	
	/**
	 * Deletar uma empresa pelo ID
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Empresa> delete(@PathVariable(value = "id") Integer id){
		Empresa empresa = repository.findOne(id);
		
		if(empresa == null)
			return ResponseEntity.notFound().build();
		
		repository.delete(empresa);
		return ResponseEntity.ok().build();
	}
}
