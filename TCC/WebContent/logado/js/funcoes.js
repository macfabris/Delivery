function apagarProduto(id) {
if (window.confirm('Deseja apagar este registro?')) {
 window.location.href = '/TCC/mvc?logica=RemoveProdutoLogica&id='+id
}
}

function apagarGrupoProduto(id) {
	if (window.confirm('Deseja apagar este registro?')) {
	 window.location.href = '/TCC/mvc?logica=RemoveGrupoProdutoLogica&id='+id
	}
	}
function apagarUnidadeDeMedida(id) {
	if (window.confirm('Deseja apagar este registro?')) {
	 window.location.href = '/TCC/mvc?logica=RemoveUnidaDeMedidaLogica&id='+id
	}
	}
function apagarEnderecoCliente(id) {
	if (window.confirm('Deseja apagar este registro?')) {
	 window.location.href = '/TCC/mvc?logica=RemoveClienteEnderecoLogica&id='+id
	}
	}
function apagarEnderecoEmpresa(id) {
	if (window.confirm('Deseja apagar este registro?')) {
	 window.location.href = '/TCC/mvc?logica=RemoveEmpresaEnderecoLogica&id='+id
	}
	}
function apagarProdutoCarrinho(id) {
	if (window.confirm('Deseja apagar este registro?')) {
	 window.location.href = '/TCC/mvc?logica=RemoveProdutoCarrinhoLogica&id='+id
	}
	}

function apagarFornecedor(id) {
	if (window.confirm('Deseja apagar este registro?')) {
	 window.location.href = '/TCC/mvc?logica=RemoveFornecedorLogica&id='+id
	}
	}

function finalizaPedido(usuario) {
	if (usuario == null) {
		alert('Logue-se para finalizar o pedido');	
		window.location.href = '/TCC/login-cadastro.jsp';
	}else{
		window.location.href = '/TCC/mvc?logica=FinalizarPedidoLogica';
	}
}
function verificaCarrinho(totalPedido) {
	if (totalPedido== 0) {
		alert('Carrinho vazio');	
	}else{
		 window.location.href = '/TCC/carrinho.jsp';
	}
}
function abrir(URL) {
	 
	  var width = 400;
	  var height = 450;
	 
	  var left = 99;
	  var top = 99;
	 
	  window.open(URL,'janela', 'width='+width+', height='+height+', top='+top+', left='+left+', scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
	 
	}
function logar(){
window.alert('This is the old edition of our webiste. The new one will be avaliable soon. You would be redirected')
window.location.href='/TCC/login-cadastro.jsp';
}