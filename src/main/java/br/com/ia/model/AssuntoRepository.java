package br.com.ia.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ia.dao.DaoDomainStore;

@Service
public class AssuntoRepository implements Serializable {

	private static final long serialVersionUID = -3642316647711510141L;
	@Autowired
	private DaoDomainStore<Assunto> dao;
	
	public List<Assunto> lista(){
		String[] orderbys={"descricao"};
		return dao.listAll(Assunto.class, orderbys);
	}

}
