package br.com.ia.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ia.dao.DaoDomainStore;

@Service
public class CategoriaRepository implements Serializable {

	private static final long serialVersionUID = -6817564649591930307L;
	@Autowired
	private DaoDomainStore<Categoria> dao;
	
	public List<Categoria> categoriasPorAssunto(Assunto assunto){
		EntityManager entityManager = dao.getEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Categoria> cQuery = cb.createQuery(Categoria.class);
		Root<Categoria> from = cQuery.from(Categoria.class);
		CriteriaQuery<Categoria> select = cQuery.select(from);
		//Adicionando as restrições
		if( assunto!=null && assunto.getId()!=0){
			Predicate predicate = cb.equal(from.get("assunto").get("id"), assunto.getId());
			cQuery.where(predicate);
		}
		TypedQuery<Categoria> typedQuery = entityManager.createQuery(select);
		List<Categoria> results = typedQuery.getResultList();
		return results;
	}

}
