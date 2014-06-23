package br.com.ia.bdd.domain;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Representa a marcação efetuada no ponto eletrônico
 * */
public class Marcacao implements Serializable{
	
	private static final long serialVersionUID = 8058742637568103755L;
	private int hora;
    private int minuto;
    private Period periodo;
    private DateTime datetime;
    private TipoDeMarcacao tipoDeMarcacao;
    
    private Pattern pattern;
	private Matcher matcher;
	private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
	

    public Marcacao(String time, TipoDeMarcacao tipoDeMarcacao){
    	pattern = Pattern.compile(TIME24HOURS_PATTERN);
    	if(validaHora(time)){
    		String[] horaMinuto = time.split(":");
    		this.hora = new Integer(horaMinuto[0]);
	        this.minuto = new Integer(horaMinuto[1]);
    	}
        this.periodo = new Period(0, 0, 0, 0, hora, minuto, 0, 0);
        this.datetime = new DateTime(2013, 11, 1, hora, minuto, 0, 0);
        this.tipoDeMarcacao = tipoDeMarcacao;
    }
    
	  public boolean validaHora(final String time){
		  boolean flagHoraValida=false;
		  try {
			  matcher = pattern.matcher(time);
			  flagHoraValida=matcher.matches();
			  if(!flagHoraValida){
				  throw new Exception("Hora inválida");
			  }
			} catch (Exception e) {
				e.printStackTrace();
			}
		  return flagHoraValida;
	  }

	public int getHora() {
		return hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public Period getPeriodo() {
		return periodo;
	}

	public DateTime getDatetime() {
		return datetime;
	}

	public TipoDeMarcacao getTipoDeMarcacao() {
		return tipoDeMarcacao;
	}
	
    
}
