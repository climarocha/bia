package br.com.ia.util;

import java.io.Serializable;

public class StringUtil implements Serializable {
	
	private static final long serialVersionUID = -9047549366317097108L;
	private String valor;

	public StringUtil(String valor) {
		this.valor = valor;
	}
	
	public StringUtil() {
	}
	
	public boolean isNotEmpty(){
		if(this.valor!=null && this.valor!=""){
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(){
		return !isNotEmpty();
	}
	
}