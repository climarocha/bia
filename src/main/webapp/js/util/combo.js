/**
 * Fun��o que limpa as op��es da combo
 * */
function limpaOpcoesCombo(combo){
	combo.html("");
}

/**
 * Fun��o que adiciona a op��o seleciona op��o
 * */
function adicionaOpcaoPadrao(combo){
	combo.append(new Option("--Selecione op��o--",""));
}

/**Fun��o que adiciona a op��o padr�o da aplica��o*/
function resetaCombo(combo){
	limpaOpcoesCombo(combo);
	adicionaOpcaoPadrao(combo);
}