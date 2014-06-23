/**
 * Função que limpa as opções da combo
 * */
function limpaOpcoesCombo(combo){
	combo.html("");
}

/**
 * Função que adiciona a opção seleciona opção
 * */
function adicionaOpcaoPadrao(combo){
	combo.append(new Option("--Selecione opção--",""));
}

/**Função que adiciona a opção padrão da aplicação*/
function resetaCombo(combo){
	limpaOpcoesCombo(combo);
	adicionaOpcaoPadrao(combo);
}