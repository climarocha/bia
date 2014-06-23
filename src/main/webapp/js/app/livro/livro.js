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
			"sZeroRecords":  "N�o foram encontrados resultados",
			"sInfo":         "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
			"sInfoEmpty":    "Mostrando de 0 at� 0 de 0 registros",
			"sInfoFiltered": "(filtrado de _MAX_ registros no total)",
			"sInfoPostFix":  "",
			"sSearch":       "Buscar:",
			"sUrl":          "",
			"oPaginate": {
				"sFirst":    "Primeiro",
				"sPrevious": "Anterior",
				"sNext":     "Seguinte",
				"sLast":     "�ltimo"
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
	
	//Adiciona a valida��o dos formul�rios
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
				required: 'O campo (T�tulo) � de preenchimento obrigat�rio',
				remote: 'J� existe um livro com este nome'
			},
			"descricao": {
				required: 'O campo (Descricao) � de preenchimento obrigat�rio'
			},
			"cdd": {
				required: 'O campo (CDD) � de preenchimento obrigat�rio'
			},
			"categoria.assunto.id": {
				required: 'O campo (Assunto) � de preenchimento obrigat�rio'
			},
			"categoria.id": {
				required: 'O campo (Categoria) � de preenchimento obrigat�rio'
			},
			"idioma.id": {
				required: 'O campo (Idioma) � de preenchimento obrigat�rio'
			},
			"anoLancamento": {
				required: 'O campo (Ano de Lan�amento) � de preenchimento obrigat�rio'
			},
			"autor": {
				required: 'O campo (Autor) � de preenchimento obrigat�rio'
			},
			"editora": {
				required: 'O campo (Editora) � de preenchimento obrigat�rio'
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