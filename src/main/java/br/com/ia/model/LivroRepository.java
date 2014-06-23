package br.com.ia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ia.dao.DaoDomainStore;
import br.com.ia.util.StringUtil;

@Service
public class LivroRepository implements Serializable {

	private static final long serialVersionUID = 8947305995765571158L;
	@Autowired
	private DaoDomainStore<Livro> dao;
	
	public Livro salvar(Livro livro){
		return dao.createWithReturn(livro);
	}

	public Livro recuperar(long id) {
		return dao.retrieve(Livro.class, id);
	}

	public void pesquisa(LivroTable pageInfo) {
		EntityManager entityManager = dao.getEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Livro> cQuery = cb.createQuery(Livro.class);
		Root<Livro> from = cQuery.from(Livro.class);
		CriteriaQuery<Livro> select = cQuery.select(from);
		Livro livro = pageInfo.getLivro()!=null?pageInfo.getLivro():new Livro();
		Categoria categoria = (livro!=null)?livro.getCategoria():new Categoria();
		Assunto assunto = (categoria!=null)?categoria.getAssunto():new Assunto();
		Idioma idioma = (livro!=null)?livro.getIdioma():new Idioma();
		//Adicionando as restrições
		List<Predicate> restricoes = new ArrayList<Predicate>();
		if( new StringUtil( livro.getTitulo()).isNotEmpty()){
			Predicate predicate = cb.like(cb.upper(from.<String>get("titulo")), "%"+livro.getTitulo().toUpperCase()+"%");
			restricoes.add(predicate);
		}
		if( new StringUtil( livro.getAutor()).isNotEmpty()){
			Predicate predicate = cb.like(cb.upper(from.<String>get("autor")), "%"+livro.getAutor().toUpperCase()+"%");
			restricoes.add(predicate);
		}
		if(assunto!=null && assunto.getId()>0){
			Predicate predicate = cb.equal(from.get("categoria").get("assunto").get("id"), assunto.getId());
			restricoes.add(predicate);
		}
		if(categoria!=null && categoria.getId()>0){
			Predicate predicate = cb.equal(from.get("categoria").get("id"), categoria.getId());
			restricoes.add(predicate);
		}
		if(idioma!=null && idioma.getId()>0){
			Predicate predicate = cb.equal(from.get("idioma").get("id"), idioma.getId());
			restricoes.add(predicate);
		}
		//Calculondo o count
		CriteriaQuery<Livro> cqEntity = cb.createQuery(Livro.class);
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<Livro> entityRoot = cqCount.from(cqEntity.getResultType());
		cqCount.select(cb.count(entityRoot));
		Path<Long> tituloPath = entityRoot.get("titulo");
		cqCount.where(
				cb.and(
						cb.equal(tituloPath,livro.getTitulo())
				)
				);
		Long count = entityManager.createQuery(cqCount).getSingleResult();
		pageInfo.setNumeroTotalResultados(count);

		//Order by
		select.orderBy(cb.asc(from.get("titulo")));
		int tuplaInicial = pageInfo.getiDisplayStart();
		int tuplaFinal = tuplaInicial+pageInfo.getiDisplayLength();
		cQuery.where(cb.and(restricoes.toArray(new Predicate[restricoes.size()])));
		
		//Código temporario para o Count - TODO refatorar
		TypedQuery<Livro> typedQueryCount = entityManager.createQuery(select);
		int countAux = typedQueryCount.getResultList().size();
		pageInfo.setNumeroTotalResultados(countAux);
		
		TypedQuery<Livro> typedQuery = entityManager.createQuery(select);
		
		if(tuplaFinal>0){
			typedQuery.setFirstResult(tuplaInicial);
			typedQuery.setMaxResults(tuplaFinal);
		}
		List<Livro> resultList = typedQuery.getResultList();
		pageInfo.setResultadoPesquisa(resultList);
		long totalRecords =  pageInfo.getNumeroTotalResultados();
		int totalPageRecords = pageInfo.getiDisplayLength();
		pageInfo.setiDisplayLength(totalPageRecords);
		pageInfo.setiDisplayStart(1);
		pageInfo.setiTotalDisplayRecords(totalRecords);
		pageInfo.setiTotalRecords(totalRecords);

	}
	
}