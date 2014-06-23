package br.com.ia.bdd.util;

import java.io.Serializable;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * Classe utilitária que manipula e efetua operações sobre o período
 * */
public class PeriodoUtil implements Serializable {

	private static final long serialVersionUID = 4520871890170750335L;
	private Period periodo;
	private PeriodFormatter formatter; 
	
	public PeriodoUtil(Period periodo) {
		this.periodo = periodo;
		formatter = new PeriodFormatterBuilder()
		.minimumPrintedDigits(2).printZeroAlways()
		.appendHours()		
		.appendSeparator(":")
		.appendMinutes()
        .toFormatter();
	}
	
	public String periodoFormatado(){
		return formatter.print(periodo);
	}

	public Period getPeriodo() {
		return periodo;
	}
	
}