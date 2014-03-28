<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="cabecalho-adm.jsp"></c:import>

<div class="container">
<div class="row">
<div class="span12">
<ul class="nav nav-tabs">
	<li class="active"><a href="#tab1" data-toggle="tab">Finalizar
	Compra </a></li>
</ul>
<div class="tab-content">
<div class="tab-pane active" id="tab1">
<h4 align="center">Itens Do Carrinho</h4>
<table class="table list">
	<thead>
	<tr>
		<th>Produto</th>
		<th>Quantidade</th>
		<th>Preço Unitário</th>
		<th>Total</th>
		<th>Excluir</th>
	</tr>
	</thead>
	<c:forEach var="itensPedido" items="${sessionScope.itensPedido}" varStatus="cont">
		<tbody class="">
		<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
			<td><div class="span2"><img src="${itensPedido.produto.diretorio}" class="img-rounded"></div></td>
			<td>${itensPedido.quantidade}</td>
			<td>${itensPedido.preco}</td>
			<td>${itensPedido.total}</td>
			<td><a href="/TCC/mvc?logica=RemoveProdutoCarrinhoLogica&id=${cont.count}"><i class="icon-remove"></i></a></td>
		</tr>
	</c:forEach>
</table>
<div align="right">
<h3>Total do Carrinho : ${totalPedido}</h3>
</div>
<form id="finalizarCompra" name="finalizaCompra" action="mvc" method="post">
<input type="hidden" name="logica" value="FinalizarCompraLogica" /> 
<jsp:useBean id="data" class="java.util.Date" /><input type="hidden" name="dataPedido" value="<fmt:formatDate value="${data}"/>" /> 
<label>Endereco: <select class="span5" name="idEndereco" id="idEndereco">
	<option value="">Selecione</option>
	<c:forEach var="endereco" items="${requestScope.endereco}" varStatus="cont">
		<option value="${endereco.id}">${endereco.logradouro} -
		${endereco.numero} - ${endereco.complemento} - ${endereco.bairro} -
		${endereco.cidade} - ${endereco.estado}</option>
	</c:forEach>
</select> </label> 
<label>Forma De Pagamento: <select class="span3"
	name="idfPagamento" id="idfPagamento">
	<option value="">Selecione</option>
	<c:forEach var="fpagamento" items="${requestScope.formasDePagamento}" varStatus="cont">
		<option value="${fpagamento.id}">${fpagamento.descricao}</option>
	</c:forEach>
</select> </label> 
	<input type="submit" class="btn btn-small btn-inverse disabled" value="Finalizar Compra" /></form>

</div>
</div>
</div>
</div>
</div>
<c:import url="rodape.jsp"></c:import>