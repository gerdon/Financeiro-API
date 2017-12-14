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

import br.ufrn.imd.financeiro.model.Categoria;
import br.ufrn.imd.financeiro.repository.CategoriaRepository;

@RestController
@RequestMapping("categorias")
public class CategoriaRest {
	
	/**
	 * Repositorio para categoria
	 */
	@Autowired
	private CategoriaRepository repository;
	
	/**
	 * Salvar uma categoria
	 * @param categoria
	 * @return
	 */
	@PostMapping
	public Categoria save(@Valid @RequestBody Categoria categoria){
		return repository.save(categoria);
	}
	
	/**
	 * Listar todas as categorias
	 * @return
	 */
	@GetMapping
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	/**
	 * Buscar uma categoria pelo ID
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable(value = "id") Integer id){
		Categoria categoria = repository.findOne(id);
		
		if(categoria == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(categoria);
	}
	
	/**
	 * Atualizar uma categoria pelo ID
	 * @param id
	 * @param categoriaInformacoes
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> update(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody Categoria categoriaInformacoes){
		Categoria categoria = repository.findOne(id);
		
		if(categoria == null)
			return ResponseEntity.notFound().build();
		
		categoria.setDescricao(categoriaInformacoes.getDescricao());
		categoria.setTipo(categoriaInformacoes.getTipo());
		
		Categoria updateCategoria = repository.save(categoria);
		return ResponseEntity.ok(updateCategoria);
	}
	
	/**
	 * Deletar uma categoria pelo ID
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable(value = "id") Integer id){
		Categoria categoria = repository.findOne(id);
		
		if(categoria == null)
			return ResponseEntity.notFound().build();
		
		repository.delete(categoria);
		return ResponseEntity.ok().build();
	}

}
