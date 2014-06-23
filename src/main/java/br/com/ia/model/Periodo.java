package br.com.ia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.ia.view.DeserializadorData;
import br.com.ia.view.SerializadorData;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Classe que representa um periodo de datas.
 * */
@Embeddable
public class Periodo implements Serializable {

	private static final long serialVersionUID = 2947890373348402248L;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dtVigenciaInicial;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dtVigenciaFinal;
	@Transient
	private int qtdDiasNoPeriodo;

	public Periodo() {}
	
	//Recupera a data inicial e final de um mês
	public Periodo(Date dataMes){
		DateTime hoje = new DateTime(dataMes.getTime());
		this.dtVigenciaInicial = hoje.dayOfMonth().withMinimumValue().toDate();
		this.dtVigenciaFinal = hoje.dayOfMonth().withMaximumValue().toDate();
	}
	
	public Periodo(Date dtVigenciaInicial, Date dtVigenciaFinal) {
		this.dtVigenciaInicial = dtVigenciaInicial;
		this.dtVigenciaFinal = dtVigenciaFinal;
		calculaQtdeDiasEntrePeriodo();
	}

	@JsonSerialize(using = SerializadorData.class)
	public Date getDtVigenciaInicial() {
		return dtVigenciaInicial;
	}

	@JsonDeserialize(using = DeserializadorData.class)
	public void setDtVigenciaInicial(Date dtVigenciaInicial) {
		this.dtVigenciaInicial = dtVigenciaInicial;
	}

	@JsonSerialize(using = SerializadorData.class)
	public Date getDtVigenciaFinal() {
		return dtVigenciaFinal;
	}

	@JsonDeserialize(using = DeserializadorData.class)
	public void setDtVigenciaFinal(Date dtVigenciaFinal) {
		this.dtVigenciaFinal = dtVigenciaFinal;
	}
	
	public int getQtdDiasNoPeriodo() {
		return qtdDiasNoPeriodo;
	}

	public void setQtdDiasNoPeriodo(int qtdDiasNoPeriodo) {
		this.qtdDiasNoPeriodo = qtdDiasNoPeriodo;
	}

	public void calculaQtdeDiasEntrePeriodo() {
		if (this.dtVigenciaInicial != null && this.dtVigenciaFinal != null) {
			DateTime dataInicial = new DateTime(this.dtVigenciaInicial);
			DateTime dataFinal = new DateTime(this.dtVigenciaFinal);
			Days d = Days.daysBetween(dataInicial, dataFinal);
			this.qtdDiasNoPeriodo = d.getDays();	
		}
	}

	/**
	 * Se @param for verdadeiro a dTInicial sera igual a DtFinal, senão a dtInicial sera a dtFinalMaisum
	 * @param flagDtInicialIgualDtFinal
	 * @return
	 */
	public List<Periodo> retornaPeriodosEntrePeriodo(boolean flagDtInicialIgualDtFinal) {
		boolean flagContinue = false;
		Date dtInicial = this.dtVigenciaInicial;
		Date dtFinal = this.dtVigenciaFinal;
		List<Periodo> periodos = new ArrayList<Periodo>();
		Date dtFinalMaisUm = new DateTime(dtFinal).plusDays(1).toDate();
		do {
			Periodo periodoAux = null;
			if (flagDtInicialIgualDtFinal) {
				dtFinal=dtInicial;
				periodoAux = new Periodo(dtInicial, dtFinal);
				dtFinal = new DateTime(dtInicial).plusDays(1).toDate();
			}else{
				dtFinal = new DateTime(dtInicial).plusDays(1).toDate();
				periodoAux = new Periodo(dtInicial, dtFinal);				
			}
			dtInicial = dtFinal;
			periodos.add(periodoAux);
			flagContinue = dtFinal.before(dtFinalMaisUm);
		} while (flagContinue);

		return periodos;
	}

}