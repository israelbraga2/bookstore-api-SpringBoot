package com.israel.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.israel.bookstore.domain.Categoria;
import com.israel.bookstore.domain.Livro;
import com.israel.bookstore.repositories.LivroRepository;
import com.israel.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
		//com.israel.bookstore.service.exceptions.ObjectNotFoundException 
	}
	
// metodo listar todos os livros pela categoria
	
	//public List<Livro>findAll(){
		//return repository.findAll();
	//}
	
	public List<Livro> findAll(Integer id) {
		Categoria obj = categoriaService.findById(id);
		return obj.getLivros();
	}
//metodo UPDATE
	
	public Livro update(Integer id, Livro obj) {
		Livro newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}

private void updateData(Livro newObj, Livro obj) {
	newObj.setTitulo(obj.getTitulo());
	newObj.setNome_autor(obj.getNome_autor());
	newObj.setTexto(obj.getTexto());
	
}
// metodo CREATE
public Livro create(Integer id, Livro obj) {
	obj.setId(null);
	Categoria cat = categoriaService.findById(id);
	obj.setCategoria(cat);
	return repository.save(obj);
}

public void delete(Integer id) {
	Livro obj = findById(id); //ver se existe
	repository.delete(obj);
}
	
}
