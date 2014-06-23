package br.com.ia.bdd.domain;

import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * Classe responsável por realizar os cálculos da hora padrão
 * */
public class CalculoDeHorasDiaUtil extends CalculoDeHoras {

	private static final long serialVersionUID = -79800458483859740L;
	
	public CalculoDeHorasDiaUtil(Funcionario funcionario) {
		super(funcionario);
	}

	@Override
	public String calculaHorasExtras() {
		
		//Duracao entrada - saida para o almoço
		/*Marcacao entrada=null;
		Marcacao saidaAlmoco=null;
		Marcacao retornoAlmoco=null;
		Marcacao saida=null;
		Period horasNegativas=null;*/
		try {
			PeriodFormatter formatter = new PeriodFormatterBuilder().minimumPrintedDigits(2).printZeroAlways()
			.appendHours()		
			.appendSeparator(":")
			.appendMinutes()
            .toFormatter();
			
			 System.out.println("[HORAS TRABALHADAS - TODAS] - "+formatter.print(horasTrabalhada));
			 System.out.println("[HORAS TRABALHADAS - HORÁRIO NÚCLEO] - "+formatter.print(horasTrabalhadasNoHorarioDoFuncionario));
			 //System.out.println("[HORAS TRABALHADAS NO HORÁRIO NÚCLEO] - "+formatter.print(horasTrabalhadaNoHorarioNucleo));
			 System.out.println("[HORAS TRABALHADAS - HORAS EXTRAS] - "+formatter.print(horasExtra));
			 System.out.println("[HORAS TRABALHADAS - HORAS EXTRAS BANCO] - "+formatter.print(horasExtraBanco));
			 System.out.println("[HORAS ALMOÇO] - "+formatter.print(horaAlmoco));
			 System.out.println("[HORAS ATRASO - DESCONTO] - "+formatter.print(horasAtraso));
			 System.out.println("[HORAS ATRASO - BANCO] - "+formatter.print(horasAtrasoBanco));
			/*entrada = marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.ENTRADA);
			saidaAlmoco = marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA_ALMOCO);
			retornoAlmoco = marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.RETORNO_ALMOCO);
			saida = marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA);*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
