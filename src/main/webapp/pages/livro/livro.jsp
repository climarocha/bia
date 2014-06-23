<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BIA - Biblioteca IA</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dropzone.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/bootstrap/css/bootstrap.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/bootstrap/css/bootstrap-responsive.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/libs/bootstrap/css/datepicker.css" />" />	
	<script>var contextPath = "${pageContext.request.contextPath}";</script>
</head>

<body>
	<!-- Menu -->
	<jsp:include page="../templates/menu.jsp" ></jsp:include>
	
	<div id="modalCadastro" class="modal hide fade">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3>Cadastrar livro</h3>
		</div>
		<div class="modal-body">
			<p>Deseja realmente cadastrar um livro?</p>
		</div>
		<div class="modal-footer">
			<button id="btnCadastro" type='button' class="btn btn-success" onclick="submitFormLivro();">Cadastrar</button>
			<button type="button" class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Cancelar</button>
		</div>
	</div>

	<form id="livroForm" class="span12" method="POST">
		<div class="row">
			<div id="confirmacao" class="alert alert-success span12" style="display: none;">
				<strong>Livro</strong> cadastrado com Sucesso!
			</div>
			
			<div id="atualizar" class="alert alert-info span12" style="display: none;">
				<strong>Livro</strong> atualizado com Sucesso!
			</div>
			
			<div id="erroCadastro" class="alert alert-error span12" style="display: none;">
				<strong>Erro!</strong> Por favor, verifique seus dados!
			</div>
			
			<div class="span3">
				<input type="hidden" name="id" id="idLivro" value="0"/>
				<input type="hidden" name="type" id="type" value="livro"/>

				<label>Título</label>
				<input type="text" class="span3" id="titulo" name="titulo" placeholder="Título do Livro" maxlength="100">
				
				<label>Descrição</label>
				<input type="text" class="span3" id="descricao" name="descricao" placeholder="Descrição do Livro" maxlength="200">
				
				<label>CDD</label>
				<input type="text" class="span3" name="cdd" id="cdd" placeholder="Classificação decimal de Dewey" maxlength="20">
				
				<label>Assunto:</label>
				<select id="assuntos" name="categoria.assunto.id" class="span3" onchange="listaCategoriasPorAssunto(this.value,'');"></select>
				
				<label>Categoria:</label>
				<select id="categorias" name="categoria.id" class="span3"></select>
				
				<label>Idioma:</label>
				<select id="idiomas" name="idioma.id" class="span3"></select>
				
				<label>Ano de lançamento</label>
				<input type="text" class="span3" name="anoLancamento" maxlength="4" id="anoLancamento" placeholder="Ano de lançamento">
				
				<label>Autor</label>
				<input type="text" class="span3" name="autor" id="autor" placeholder="Autor" maxlength="100">
				
				<label>Editora</label>
				<input type="text" class="span3" name="editora" id="editora" placeholder="Editora" maxlength="50">
				
				<label>Observação</label>
				<input type="text" class="span3" name="observacao" id="observacao" placeholder="Observação" maxlength="100" value="">

				<div class="pull-left">
					<button type='submit' id="submit" class='btn btn-success'>Cadastrar</button>
					<button type='button' class='btn btn-primary' onclick="pesquisarLivros();">Pesquisar</button>
					<button type='button' id="limpar" class='btn btn-warning' onclick="limparCamposLivro();">Limpar</button>
				</div>
			</div>
			<div class="span9">
				<table id="tableLivros" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Título</th>
							<th>Categoria</th>
							<th>Autor</th>
							<th>Ano de lançamemnto</th>
							<th>Idioma</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody id="bodyLivros"></tbody>
				</table>
		</div>
		</div>
	</form>
	
	<script id="templateLivros" type="text/html">
	<tr id="livrosRow">
		<td>{{= titulo}}</td>
		<td>{{= categoria.descricao}}</td>
		<td>{{= autor }}</td>
		<td>{{= anoLancamento }}</td>
		<td>{{= idioma.descricao}}</td>
		<td><button type='button' class='btn btn-primary' onclick='editarLivro("{{= id }}");'>Editar</button></td>
	</tr>
	</script>
	
</body>
	 <!-- Jquery -->
	<script src="<c:url value="/resources/libs/jquery/jquery-1.9.1.min.js" />"></script>
	<script src="<c:url value="/resources/libs/jquery/jquery.tmpl.min.js" />"> </script>
	<script src="<c:url value="/resources/libs/jquery/jquery.validate.-1.9.0.min.js" />"> </script>
	<!-- Bootstrap -->
	<script src="<c:url value="/resources/libs/bootstrap/js/bootstrap.min.js" />"> </script>
	<script src="<c:url value="/resources/libs/bootstrap/js/bootstrap-datepicker.js"/>"></script>
	<!-- Datatables -->
	<script type="text/javascript" src="<c:url value="/resources/libs/datatables/js/jquery.dataTables.js" />"> </script>
	<script type="text/javascript" src="<c:url value="/resources/libs/datatables/js/dataTables.editor.js" />"> </script>
	<script type="text/javascript" src="<c:url value="/resources/libs/datatables/js/DT_bootstrap.js" />"> </script>
	<!-- Util -->
	<script src="<c:url value="/resources/libs/json2form/json2form.js" />"> </script>	
	<script src="<c:url value="/resources/js/util/combo.js" />"> </script>
	<script src="<c:url value="/resources/js/util/convertTypes.js" />"> </script>
	<script src="<c:url value="/resources/js/util/validaFields.js" />"> </script>
	<!-- App -->
	<script src="<c:url value="/resources/js/app/livro/livro.js" />"> </script>
</html>