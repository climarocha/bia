package br.com.ia.bdd.scenarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import br.com.ia.bdd.domain.CalculoDeHoras;
import br.com.ia.bdd.domain.CalculoDeHorasDiaUtil;
import br.com.ia.bdd.domain.Funcionario;
import br.com.ia.bdd.domain.FuncionarioBuilder;
import br.com.ia.bdd.domain.HorarioNucleo;
import br.com.ia.bdd.domain.Marcacao;
import br.com.ia.bdd.domain.Marcacoes;
import br.com.ia.bdd.domain.TipoDeMarcacao;
import br.com.ia.bdd.util.PeriodoUtil;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

public class BancoDeHorasDefinicao implements Serializable{
	
	private static final long serialVersionUID = -4335130561363624216L;
	private Funcionario funcionario;
	private CalculoDeHoras calculadorDeHoras;
	
	@Given("^Um funcionario for trabalhar e entrar as \"([^\"]*)\", sair para o almoco as \"([^\"]*)\", retornar do almoco as \"([^\"]*)\" e sair as \"([^\"]*)\"$")
	public void Um_funcionario_for_trabalhar_e_entrar_as_sair_para_o_almoco_as_retornar_do_almoco_as_e_sair_as(String entrada, String saidaAlmoco, String retornoAlmoco, String saida) {
		try {
			FuncionarioBuilder builder = new FuncionarioBuilder();
			builder.paraFuncionario("Cleilton")
			.comMatricula(1)
			.comHorario(new HorarioNucleo())
			.entrouNaEmpresaAs(entrada)
			.foiAlmocarAs(saidaAlmoco)
			.retornouDoAlmocoAs(retornoAlmoco)
			.foiEmboraAs(saida);
			
			funcionario = builder.constroi();
		} catch (Exception e) {
			
		}
		
	}
	
	@Given("^Um funcionario for trabalhar e entrar as \"([^\"]*)\" e sair as \"([^\"]*)\"$")
	public void Um_funcionario_for_trabalhar_e_entrar_as_e_sair_as(String entrada, String saida) {
		try {
			FuncionarioBuilder builder = new FuncionarioBuilder();
			builder.paraFuncionario("Cleilton")
			.comMatricula(1)
			.comHorario(new HorarioNucleo())
			.entrouNaEmpresaAs(entrada)
			.foiEmboraAs(saida);
			funcionario = builder.constroi();
		} catch (Exception e) {
			
		}
	}

	@When("^em um dia util da semana$")
	public void em_um_dia_util_da_semana() {
		calculadorDeHoras = new CalculoDeHorasDiaUtil(funcionario);
	}
	
	@Then("^O funcionario trabalhou \"([^\"]*)\" horas, tem saldo de horas do banco de \"([^\"]*)\", tem saldo de horas de atraso do banco de \"([^\"]*)\", tem \"([^\"]*)\" horas extras e atrasou: \"([^\"]*)\"$")
	public void O_funcionario_trabalhou_horas_tem_saldo_de_horas_do_banco_de_tem_saldo_de_horas_de_atraso_do_banco_de_tem_horas_extras_e_atrasou(String horasTrabalhada, String horasExtraBanco, String horasAtrasoBanco, String horasExtra, String horasAtraso){
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasTrabalhada()).periodoFormatado(), horasTrabalhada);
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasExtraBanco()).periodoFormatado(), horasExtraBanco);
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasAtrasoBanco()).periodoFormatado(), horasAtrasoBanco);
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasExtra()).periodoFormatado(), horasExtra);
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasAtraso()).periodoFormatado(), horasAtraso);
		
	}

	
	/*@Given("^Um funcionario for trabalhar em um dia util e entrar as \"([^\"]*)\", sair para o almoco as \"([^\"]*)\", retornar do almoco as \"([^\"]*)\" e sair as \"([^\"]*)\"$")
	public void Um_funcionario_for_trabalhar_em_um_dia_util_e_entrar_as_sair_para_o_almoco_as_retornar_do_almoco_as_e_sair_as(String entrada, String saidaAlmoco, String retornoAlmoco, String saida) throws Throwable {
		
		FuncionarioBuilder builder = new FuncionarioBuilder();
		builder.paraFuncionario("Cleilton")
		.comMatricula(1)
		.comHorario(new HorarioNucleo())
		.entrouNaEmpresaAs(entrada)
		.foiAlmocarAs(saidaAlmoco)
		.retornouDoAlmocoAs(retornoAlmoco)
		.foiEmboraAs(saida);
		
		funcionario = builder.constroi();
		
	    throw new PendingException();
	}

	@When("^em um dia util da semana$")
	public void em_um_dia_util_da_semana() throws Throwable {
		calculadorDeHoras = new CalculoDeHorasDiaUtil(funcionario);
	    throw new PendingException();
	}

	@Then("^O saldo de horas do banco ? \"([^\"]*)\"$")
	public void O_saldo_de_horas_do_banco_(String horasBanco) throws Throwable {
		assertEquals(new PeriodoUtil(calculadorDeHoras.getHorasExtraBanco()), horasBanco);
	    throw new PendingException();
	}*/

}
