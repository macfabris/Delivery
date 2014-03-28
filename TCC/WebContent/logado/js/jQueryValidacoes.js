$(document).ready( function() {
	    $("#form").validate({ 	
	    	onfocusout: function(element) {
	         $(element).valid();
	     },
	        rules:{
	            numero:{ required: true,number:true },
	            nome:{required: true},
	            descricao:{required:true},
	            idGrupoProduto:{required:true},
	            idUnidadeDeMedida:{required:true}
	        },
	        messages:{
	            numero:{required: "Campo Requerido",number:"Digite um valor válido"},
	            nome:{required: "Campo Requerido"},
				descricao:{required:"Campo Requerido"},
	            idGrupoProduto:{required:"Campo Requerido"},
	            idUnidadeDeMedida:{required:"Campo Requerido"}
	            
	        }
	    });
	});