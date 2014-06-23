//Converte uma tabela para um objeto json
function tableToJson(table, nmTableObj, fields) {
	if(nmTableObj==undefined || nmTableObj==null){
		return;
	}
	var o = {};
	$('tr', table).each(function(j, item){
	// here you can add whatever you like for each TR, then you can browse td  //for each tr
		$('td',$(this)).each(function(i,item){
			// now you have all td as well
			var aux = nmTableObj+"["+(j-1)+"]."+fields[i];
			if (o[aux] !== undefined) {
				if (!o[aux].push) {
					o[aux] = [o[aux]];
				}
				o[aux].push(item.textContent || '');
			} else {
				o[aux] = item.textContent || '';
			}
		});
	});
	return o;
}

//Popula os checks e radios
function jsonToCheckBox(idField, data, propriedade){
	var field = $("input[id="+idField+"]");
	$.each(data, function(key, obj){
		var i=0;
		var value = getPropertyInObject(obj, propriedade);
		field.each(function(){
			i++;
			if($(this).attr('value') == value) {
				$(this).prop('checked', true);
			}
	   });
	});
}

//Popula imagens
function jsonToImagens(imagens){
	$.each(imagens, function (index, imagemObj) {
		var file = imagemObj.imagem;
		var id = imagemObj.id;
		$("#uploaded-files").append(
			$('<tr id="upload'+id+'"/>').append($('<td />').html("<img  name='foto["+id+"]' src="+file.dataUrl+" alt='your image' width='128px' height='128px' />"))
    		.append($('<td />').html("<button type='button' class='btn btn-danger' onclick='deleteImagem("+id+");'>Deletar</button>"))
    	);
		
	});
}

//Converte um Form para um Json
function formToJson(frm){
	var o = {};
	var a = frm.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	
	return o;
};

//Converte um Json para um Form
function jsonToForm(frm, data, keyAux) {
		$.each(data, function(key, value){
			if(keyAux !=null && keyAux != "" && keyAux != undefined){
				key = keyAux+"."+key;
			}
			//Chamada recursiva para objetos complexos
			if(value instanceof Object){
				jsonToForm(frm, value, key);
			}
			var field = $('[name="'+key+'"]', frm);
			//Tratamento para image
			if(field.attr("type") == null || field.attr("type") == undefined){
				//TODO
			}
			//Tratamento para input e texts ares
			if (field.is("input") || field.is("textarea")) {
				field.val(value);
			} 
			//Tratamento para selects e multiselect
			else if (field.is("select") || field.is("multi-select")) {
				field.val(value);
			} 
			
			else if (field.is("radio") || field.is("checkbox")) {
				field.each(function(){
				   if($(this).attr('value') == value) {  
					   $(this).attr("checked",value); 
				   }
			   });
			}
	});
}

function jsonConcat(o1, o2) {
	for (var key in o2) {
		o1[key] = o2[key];
	}
	return o1;
}

function jsonToOptions(opcoes, objetoHTML){
	var options = "";
    jQuery.each(opcoes, function(i, opcao){
       options += '<option value="' + opcao.id + '">' + opcao.descricao + '</option>';
    });
    objetoHTML.html(options);
}


function getPropertyInObject(obj, p) {
  p = p.split('.');
  for (var i = 0, len = p.length; i < len - 1; i++)
    obj = obj[p[i]];
  return obj[p[len - 1]];
};

function setPropertyInObject(obj, p, value) {
  var obj = this;
  p = p.split('.');
  for (var i = 0, len = p.length; i < len - 1; i++)
    obj = obj[p[i]];

  obj[p[len - 1]] = value;
};