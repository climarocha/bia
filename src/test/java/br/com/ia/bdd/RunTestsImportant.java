package br.com.ia.bdd;

import org.junit.runner.RunWith;
import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(format = { "pretty", "html:target/cucumber" }, tags="@important", features="src\\test\\resources\\br\\com\\ia\\bdd\\scenarios\\BancoDeHoras.feature", monochrome=false, strict=true)
/** TAGS - ao anotar os scenarios e/ou features com tags você define quais testes devem ou não ser executados
 * features - executa apenas os arquivos definidos em features
 * monochorme - define o layout da saida
 * strict - restringe a execução apenas dos scenarios implementados, caso um scenario não esteja implementado e o valor do strict for true ocorrerá um erro na falha
 * */
public class RunTestsImportant {
}