package br.com.ia.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ia.dao.DaoDomainStore;

@Service
public class IdiomaRepository implements Serializable {

	private static final long serialVersionUID = 4846556937817635838L;
	@Autowired
	private DaoDomainStore<Idioma> dao;
	
	public List<Idioma> lista(){
		String[] orderbys={"descricao"};
		return dao.listAll(Idioma.class, orderbys);
	}
	
}