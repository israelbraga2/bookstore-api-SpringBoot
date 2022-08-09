package com.israel.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.israel.bookstore.domain.Categoria;
import com.israel.bookstore.domain.Livro;
import com.israel.bookstore.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new com.israel.bookstore.service.exceptions.ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
// metodo listar todos os livros pela categoria
	
	public List<Livro>findall(){
		return repository.findAll();
	}
	
	public List<Livro> findAllByCategory(Integer id) {
		Categoria obj = categoriaService.findById(id);
		return obj.getLivros();
	}
	
}
