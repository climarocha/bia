package br.com.ia.dao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DaoDomainStore<T extends Serializable>{

	@PersistenceContext
	private transient EntityManager entityManager;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public T createWithReturn(T entity){
		entity = entityManager.merge(entity);
		return entity;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void create(T entity){
		entityManager.persist(entity);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public T update(T entity){
		habilitarFiltros();
		entity = entityManager.merge(entity);
		return entity;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public T createUpdate(T entity){
		entity = entityManager.merge(entity);
		return entity;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(T entity){
		entityManager.remove(entity);
	}
	
	public T retrieve(Class<T> entityClass, Object id){
		T retorno = null;
		try {
			habilitarFiltros();
			retorno = entityManager.find(entityClass, id);
			//entityManager.refresh(retorno);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//entityManager.flush();
		
		return retorno;
	}
	
	public List<T> listAllCriteriaPesquisa(Class<T> entityClass, CriteriaQuery<T> select){
		TypedQuery<T> typedQuery = entityManager.createQuery(select);
		List<T> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	
	public T objectCriteriaPesquisa(String namedQuery, Class<T> entityClass, List<Object> params){
		T retorno = null;
		TypedQuery<T> typedQuery =  entityManager.createNamedQuery(namedQuery, entityClass);
		if(params!=null && params.size()>0){
			int i=1;
			for (Object value : params) {
				typedQuery.setParameter(i, value);
				i++;
			}
		}
		List<T> retornoLista = typedQuery.getResultList();
		retorno =  (T) (retornoLista.size()>0 ?retornoLista.get(0):null);
		return retorno;
	}

	private void habilitarFiltros() {
		Session session = entityManager.unwrap(Session.class);
		//session.enableFilter("nomeDoFiltro");
	}

	@SuppressWarnings("unchecked")
	public T objectByProperty(Class<T> entityClass, String propertyName, Object propertyValue){
		T retorno = null;
		String query = "select o from " + entityClass.getSimpleName() + "  o  where o." + propertyName + "  = :propertyValue ";
		Query q = entityManager.createQuery(query).setParameter("propertyValue", propertyValue);
		List<T> retornoLista = q.getResultList();
		retorno =  (T) (retornoLista.size()>0 ?retornoLista.get(0):null);
		return retorno;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(Class<T> entityClass, String[] orderbys){
		List<T> retorno = null;
		String query = "select o from " + entityClass.getSimpleName()+ "  o  order by ";
		for (String order : orderbys) {
			query += order + " , ";
		}
		query = query.substring(0, query.length() - 2);
		Query q = entityManager.createQuery(query);
		retorno = q.getResultList();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll(Class<T> entityClass){
		List<T> retorno = null;
		habilitarFiltros();
		String query = "select o from " + entityClass.getSimpleName()+ "  o ";
		Query q = entityManager.createQuery(query);
		retorno = q.getResultList();
		//entityManager.flush();
		return retorno;
	}
	

	@SuppressWarnings("unchecked")
	public List<T> listAllConstructor(Class<T> entityClass, String[] fieldsConstrutor){
		List<T> retorno = null;
		habilitarFiltros();
		entityClass.getName();
		String query = "select new "+entityClass.getName()+"(";
		if(fieldsConstrutor!=null && fieldsConstrutor.length>0){
			for (String s : fieldsConstrutor) {
				query+="o."+s+", ";
			}
			query = query.substring(0, query.length() - 2);
		}
		query += ") from " + entityClass.getSimpleName()+ "  o ";
		Query q = entityManager.createQuery(query);
		retorno = q.getResultList();
		//entityManager.flush();
		return retorno;
	}
	
	
	@SuppressWarnings("unchecked")
	public T objectByProperty(Class<T> entityClass, String[] fieldsConstrutor, String propertyName, Object propertyValue){
		T retorno = null;
		//String query = "select o from " + entityClass.getSimpleName() + "  o  where o." + propertyName + "  = :propertyValue ";
		String query = "select new "+entityClass.getName()+"(";
		if(fieldsConstrutor!=null && fieldsConstrutor.length>0){
			for (String s : fieldsConstrutor) {
				query+="o."+s+", ";
			}
			query = query.substring(0, query.length() - 2);
		}
		query += ") from " + entityClass.getSimpleName()+ "  o  where o." + propertyName + "  = :propertyValue ";
		Query q = entityManager.createQuery(query).setParameter("propertyValue", propertyValue);
		List<T> retornoLista = q.getResultList();
		retorno =  (T) (retornoLista.size()>0 ?retornoLista.get(0):null);
		return retorno;
	}
	
	public List<T> listAllNamedQuery(String namedQuery, Class<T> entityClass){
		List<T> retorno = null;
		TypedQuery<T> query =  entityManager.createNamedQuery(namedQuery, entityClass);
		retorno = query.getResultList();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<T> listByProperty(Class<T> entityClass, String propertyName, Object propertyValue){
		List<T> retorno = null;
		String query = "select o from " + entityClass.getSimpleName()+ "  o  where o." + propertyName + "  = :propertyValue ";
		Query q = entityManager.createQuery(query).setParameter("propertyValue", propertyValue);
		retorno = q.getResultList();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<T> listByPropertyLikeBegin(Class<T> entityClass, String propertyName, Object propertyValue){
		List<T> retorno = null;
		String query = "select o from " + entityClass.getSimpleName() + "  o  where o." + propertyName + " like :propertyValue";
		Query q = entityManager.createQuery(query).setParameter("propertyValue", "%" + propertyValue + "%");
		retorno = q.getResultList();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<T> listByPropertyLikeBeginJoin(Class<T> entityClass,String propertyJoinName, Object propertyJoinValue,
											  String propertyLikeName, Object propertyLikeValue){
		List<T> retorno = null;
		StringBuilder query = new StringBuilder("select o from " + entityClass.getSimpleName() + "  o  where " + "o." + propertyJoinName);
		query.append("=:propertyValueJoin and " + " o." + propertyLikeName);
		query.append(" like :propertyLikeValue");
		Query q = entityManager.createQuery(query.toString());
		q.setParameter("propertyValueJoin", propertyJoinValue);
		q.setParameter("propertyLikeValue", propertyLikeValue + "%");
		retorno = q.getResultList();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<T> listFindByPropertyString(Class<T> entityClass, ArrayList<HashMap> parametrosConsulta){
		List<T> retorno = null;
		HashMap map;
		Collection<Entry<String, String>> colecao;
		Entry<String, String> aux = null;
		String query = "select o from " + entityClass.getSimpleName()+ "  o  ";
		// Monta query
		for (int i = 0; i < parametrosConsulta.size(); i++) {
			map = parametrosConsulta.get(i);
			colecao = map.entrySet();
			aux = colecao.iterator().next();
			if (i == 0) {
				query += "where o." + aux.getKey() + " like :propertyValue" + i;
			} else {
				query += " and o." + aux.getKey() + " like :propertyValue" + i;
			}
		}
		Query q = entityManager.createQuery(query);
		// Seta parametros na query
		for (int i = 0; i < parametrosConsulta.size(); i++) {
			map = parametrosConsulta.get(i);
			colecao = map.entrySet();
			aux = colecao.iterator().next();
			q.setParameter("propertyValue" + i, "%" + aux.getValue() + "%");
		}
		retorno = q.getResultList();
		return retorno;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}