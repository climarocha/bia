var oTable = null;

$(document).ready(function() {
	onLoad();
	listaAssuntos();
	listaIdiomas();
});

function listaAssuntos() {
	$.getJSON(contextPath+'/app/livro/assuntos', function(assuntos) {
		resetaCombo($('#assuntos'));
		$.each(assuntos, function(index, assunto) {
			$('#assuntos').append(new Option(assunto.descricao,assunto.id));
		});
	});
}

function listaIdiomas() {
	$.getJSON(contextPath+'/app/livro/idiomas', function(idiomas) {
		resetaCombo($('#idiomas'));
		$.each(idiomas, function(index, idioma) {
			$('#idiomas').append(new Option(idioma.descricao,idioma.id));
		});
	});
}

function listaCategoriasPorAssunto(assuntoId,categoriaID) {
	$.getJSON(contextPath+'/app/livro/categoriasPorAssunto/'+assuntoId, function(categorias) {
		resetaCombo($('#categorias'));
		$.each(categorias, function(index, categoria) {
			option = new Option(categoria.descricao, categoria.id);
			$('#categorias').append(option);			
		});
		$('#categorias').val(categoriaID);
	});
}


function onLoad(){
	//Inicia o table de livros
	oTable = $('#tableLivros').dataTable( {
		"sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
		"sPaginationType": "bootstrap",
		"oLanguage": {
			"sProcessing":   "Processando...",
			"sLengthMenu":   "Mostrar _MENU_ registros",
			"sZeroRecords":  "Não foram encontrados resultados",
			"sInfo":         "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty":    "Mostrando de 0 até 0 de 0 registros",
			"sInfoFiltered": "(filtrado de _MAX_ registros no total)",
			"sInfoPostFix":  "",
			"sSearch":       "Buscar:",
			"sUrl":          "",
			"oPaginate": {
				"sFirst":    "Primeiro",
				"sPrevious": "Anterior",
				"sNext":     "Seguinte",
				"sLast":     "Último"
			}
		},
		
		"bFilter": false,
		"bServerSide": true,
        "sAjaxSource": contextPath+'/app/livro/lista',
        "bProcessing": true,
        "sAjaxDataProp": "resultadoPesquisa",

          "fnServerData": function ( sSource, aoData, fnCallback ) {
              aoData.push( { "name" : "livro.titulo", "value" : function() {return $('#titulo').val(); } } );
              aoData.push( { "name" : "livro.autor", "value" : function() {return $('#autor').val(); } } );
              aoData.push( { "name" : "livro.categoria.assunto.id", "value" : function() {return strToInt($('#assuntos').val()); } } );
              aoData.push( { "name" : "livro.categoria.id", "value" : function() {return strToInt($('#categorias').val()); } } );
              aoData.push( { "name" : "livro.idioma.id", "value" : function() {return strToInt($('#idiomas').val()); } } );
              $.getJSON( sSource, aoData, function (json) {
                  fnCallback(json);
              });
        },

        "aoColumns": [
                    { "mDataProp": "titulo" },
                    { "mDataProp": "categoria.descricao" },
                    { "mDataProp": "autor" },
                    { "mDataProp": "anoLancamento" },
                    { "mDataProp": "idioma.descricao" },
                    {
                        "mData": "id",
                        "sClass": "center",
                        "mRender": function ( data, type, full ) {
                            return "<button type='button' class='btn btn-primary' onclick='editarLivro("+data+");'>Editar</button>";
                          }
                    }
                ]
	});
	
	//Adiciona a validação dos formulários
	$("#livroForm").validate({
		onkeyup: false,
		submitHandler: function() {
			$('#modalCadastro').modal('show');
        },
		rules: {
			"titulo": {
				required: true,
			    /*remote: {
			    	url: contextPath+'/app/livro/validar',
			    	data: {
			    		'titulo': function() {return $('#titulo').val();}
			    	},
                    async:false
			   }*/
			},
			"descricao": {
				required: true
			},
			"cdd": {
				required: true
			},
			"categoria.assunto.id": {
			    required: true
			},
			"categoria.id": {
			    required: true
			},
			"idioma.id": {
			    required: true
			},
			"anoLancamento": {
			    required: true
			},
			"autor": {
			    required: true
			},
			"editora": {
			    required: true
			}
			
		},
		messages: {
			"titulo": {
				required: 'O campo (Título) é de preenchimento obrigatório',
				remote: 'Já existe um livro com este nome'
			},
			"descricao": {
				required: 'O campo (Descricao) é de preenchimento obrigatório'
			},
			"cdd": {
				required: 'O campo (CDD) é de preenchimento obrigatório'
			},
			"categoria.assunto.id": {
				required: 'O campo (Assunto) é de preenchimento obrigatório'
			},
			"categoria.id": {
				required: 'O campo (Categoria) é de preenchimento obrigatório'
			},
			"idioma.id": {
				required: 'O campo (Idioma) é de preenchimento obrigatório'
			},
			"anoLancamento": {
				required: 'O campo (Ano de Lançamento) é de preenchimento obrigatório'
			},
			"autor": {
				required: 'O campo (Autor) é de preenchimento obrigatório'
			},
			"editora": {
				required: 'O campo (Editora) é de preenchimento obrigatório'
			}
		}
	});
	
	$("#cancelar").click(function(){
		parent.history.back();
		return false;
	});
	
}

function pesquisarLivros(){
    oTable.fnDraw();
}

function editarLivro(id) {
	$.getJSON(contextPath+'/app/livro/pesquisa/'+id, function(livro) {
		listaCategoriasPorAssunto(livro.categoria.assunto.id,livro.categoria.id);
		jsonToForm($("#livroForm"),livro, "");
		$("#submit").html('Atualizar');
		$('.modal-header').html('<h3>Alterar livro</h3>');
		$('.modal-body').html('Deseja alterar esse livro?');
		$('#btnCadastro').html('Alterar');
	});
}

function novoCadastro(){
	$('#containerCadastro').show();
	$("#modalCadastro").modal("show");
	$('#tab')[0].reset();
	$('#containerPesquisa').hide();
}

function mensagemConfirmacao(){
	if (document.getElementById('submit').innerHTML == "Atualizar") {
		$('#atualizar').fadeIn(1000);
	}else {
		$('#confirmacao').fadeIn(1000);
	}
}
function submitFormLivro() {
	$('#confirmacao').hide();
	$('#atualizar').hide();
	$('#erroCadastro').hide();
	$.ajax({
		type: 'POST',
		url: contextPath+'/app/livro/salvar',
		dataType: 'json',
		data: formToJson($('#livroForm')),
		complete: function (xhr, status) {
			if ((status === 'error' || !xhr.responseText) && xhr.status!=200) {
				$('#erroCadastro').fadeIn(1000);
			}
			else {
				$("#idLivro").val(xhr.responseText);
				$('#modalCadastro').modal('hide');
				mensagemConfirmacao();
				$('#submit').html('Atualizar');
			}
		}
	});
	pesquisarLivros();
	//listarLivros();
}

function limparCamposLivro() {
	$('#livroForm')[0].reset();
	$('#id').val('0');
	$('#submit').html('Cadastrar');
	$('label.error, .alert').hide();
}

function listarLivros() {
	$.getJSON(contextPath+'/app/livro/lista', function(livros) {
		$('#bodyLivros').html("");
		$("#templateLivros").tmpl(livros).appendTo("#bodyLivros");
	});
}

function deletarLivro(idLivro) {
	$.getJSON(contextPath+'/app/livro/remove/'+idLivro);
	$('#livrosRow'+idLivro).each(function() {
		$(this).remove();
	});
}