package br.com.ia.bdd.domain;

/**
 * Classe que representa o horário núcleo padrão da empresa
 * */
public class HorarioNucleo implements Horario {

	private static final long serialVersionUID = 321917119930101069L;
	private static final Marcacao INICIO_HORARIO_NUCLEO = new Marcacao("7:30", TipoDeMarcacao.ENTRADA); // O horário núcleo no período da manhã se inicia as 09:00 com tolerência de 10 minutos
	private static final Marcacao ENTRADA_LIMITE = new Marcacao("9:00", TipoDeMarcacao.ENTRADA); // O horário núcleo no período da manhã se inicia as 09:00 com tolerência de 10 minutos
    private static final Marcacao SAIDA = new Marcacao("17:00", TipoDeMarcacao.SAIDA); // O horário núcleo finaliza as 17h
    private static final Marcacao RETORNO_ALMOCO = new Marcacao("13:30", TipoDeMarcacao.RETORNO_ALMOCO); // O horário núcleo no período da tarde se inicia as 13:30 com tolerência de 10 minutos
    private static final Marcacao SAIDA_ALMOCO = new Marcacao("11:30", TipoDeMarcacao.SAIDA_ALMOCO); // O horário núcleo no período da manhã se encerra as 11:30
    private static final Marcacao TERMINO_HORARIO_NUCLEO = new Marcacao("18:30", TipoDeMarcacao.SAIDA); // horário inicial para a marcacao de hora extra
    private static final int CARGA_HORARIA_PADRAO = 8;
    
	public Marcacao getInicioHorario() {
		return INICIO_HORARIO_NUCLEO;
	}
	public Marcacao getEntradaLimite() {
		return ENTRADA_LIMITE;
	}
	public Marcacao getSaida() {
		return SAIDA;
	}
	public Marcacao getRetornoAlmoco() {
		return RETORNO_ALMOCO;
	}
	public Marcacao getSaidaAlmoco() {
		return SAIDA_ALMOCO;
	}
	public Marcacao getTerminoHorario() {
		return TERMINO_HORARIO_NUCLEO;
	}
	public int getCargaHorariaPadrao() {
		return CARGA_HORARIA_PADRAO;
	}
	
}