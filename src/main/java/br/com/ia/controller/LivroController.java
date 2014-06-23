package br.com.ia.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ia.model.Assunto;
import br.com.ia.model.AssuntoRepository;
import br.com.ia.model.Categoria;
import br.com.ia.model.CategoriaRepository;
import br.com.ia.model.ILivro;
import br.com.ia.model.Idioma;
import br.com.ia.model.IdiomaRepository;
import br.com.ia.model.Livro;
import br.com.ia.model.LivroRepository;
import br.com.ia.model.LivroTable;

@Controller
@RequestMapping("/livro")
@Scope("request")
public class LivroController implements Serializable{
	
	private static final long serialVersionUID = 6453260167465794656L;
	private static final Logger LOG = Logger.getLogger( LivroController.class );
	
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private IdiomaRepository idiomaRepository;
	@Autowired
	private AssuntoRepository assuntoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	private List<Assunto> assuntos=null;
	private List<Idioma> idiomas=null;
	
	@PostConstruct
	public void init() {
		criarCacheAssuntosLivro();
		criarCacheIdiomas();
	}
	
	private void criarCacheAssuntosLivro() {
		assuntos = assuntoRepository.lista();
	}

	private void criarCacheIdiomas() {
		idiomas = idiomaRepository.lista();
	}
	
	//Método responsável por salvar um livro
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public @ResponseBody long salvar(Livro livro) {
		LOG.debug("Salvar Livro");
		//Livro livro = (Livro) iLivro;
		livro = livroRepository.salvar(livro);
		return livro.getId();
	}
	
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public @ResponseBody LivroTable listarLivro(LivroTable pageInfo) {
		livroRepository.pesquisa(pageInfo); 
		return pageInfo;
	}
	
	//Método responsável por recuperar um livro
	@RequestMapping(value = "pesquisa/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Livro pesquisar(@PathVariable long id) {
		Livro livro = livroRepository.recuperar(id); 
		return livro;
	}
	
	@RequestMapping(value = "assuntos", method = RequestMethod.GET)
	@ResponseBody
	public List<Assunto> listaAssuntos() {
		return assuntos;
	}
	
	@RequestMapping(value = "idiomas", method = RequestMethod.GET)
	@ResponseBody
	public List<Idioma> listarIdiomas() {
		return idiomas;
	}

	@RequestMapping(value = "categoriasPorAssunto/{assuntoId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Categoria> listarCategoriasPorAssunto(@PathVariable long assuntoId) {
		return categoriaRepository.categoriasPorAssunto(new Assunto(assuntoId));
	}
	
}