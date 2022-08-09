package com.israel.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.israel.bookstore.domain.Livro;
import com.israel.bookstore.dtos.LivroDTO;
import com.israel.bookstore.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro>findById(@PathVariable Integer id){
		Livro obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	// metodo listar todos os livros pela categoria
	
	@GetMapping(value="categoria/{id}")
	public ResponseEntity<List<LivroDTO>>findAllByCategoria(@PathVariable Integer id){
		List<Livro>list = service.findAllByCategory(id);
		List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	 // metodo UPDATE p/ atualizar tudo
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro obj){
		Livro newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	// metodo UPDATEPATCH p/ atualizar apenas uma informação
	
	@PatchMapping(value = "{id}")
	public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro obj){
		Livro newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	//metodo para CREATE implementado no curso que não deu certo
	//@PostMapping
	//public ResponseEntity<Livro> create(@RequestParam(value= "categoria", defaultValue = "0") Integer id_cat, @RequestBody Livro obj){
		//Livro newObj = service.create(id_cat, obj);
		//URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
		//return ResponseEntity.created(uri).build();
	//}
	
	//metodo CREATE
	
	@PostMapping(value = "categoria/{id}")
	public ResponseEntity<Livro> create(@PathVariable Integer id, @RequestBody Livro obj){
		Livro newObj = service.create(id, obj);
		//return ResponseEntity.ok().body(newObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}

