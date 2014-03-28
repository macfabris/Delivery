<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="cabecalho_logado.jsp"></c:import>

<div class="container">
<div class="row">
<div class="span12">
<div class="tabbable" align="center">
<ul class="nav nav-tabs">
	<li class="active"><a href="#tab1" data-toggle="tab">Cliente</a></li>
	<li><a href="#tab2" data-toggle="tab">Pedidos</a></li>
</ul>
<div class="tab-content">
<div class="tab-pane active" id="tab1">
<h4>Informações Pessoais</h4>
<p>Nome:${cliente.nome}</p>
<p>CPF:${cliente.cpf}</p>
<p>Nascimento:<fmt:formatDate value="${cliente.dataNascimento.time}" pattern="dd/MM/yyyy" /></p>
<p>Email:${email.descricao}</p>
<p>Login:${usuario.login}</p>

<a href="/TCC/logado/altera-cliente.jsp?id=${cliente.id}&nome=${cliente.nome}&cpf=${cliente.cpf}&dataNascimento=<fmt:formatDate value="${cliente.dataNascimento.time}" pattern="dd/MM/yyyy" />&idEmail=${email.id}&email=${email.descricao}&idUsuario=${usuario.id}&login=${usuario.login}&senha=${usuario.senha}" class="btn btn-small btn-inverse disabled">Alterar Dados</a>
<h4 align="center">Endereços</h4>
<table class="table list">
	<thead>
	<tr>
		<th>Logradouro</th>
		<th>Numero</th>
		<th>Complemento</th>
		<th>Bairro</th>
		<th>Cidade</th>
		<th>Estado</th>
		<th>Excluir</th>
	</tr>
	</thead>
	<c:forEach var="endereco" items="${requestScope.endereco}" varStatus="cont">
		<tbody class="">
		<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
			<td>${endereco.logradouro}</td>
			<td>${endereco.numero}</td>
			<td>${endereco.complemento}</td>
			<td>${endereco.bairro}</td>
			<td>${endereco.cidade}</td>
			<td>${endereco.estado}</td>
			<td><a href='#' onClick='apagarEnderecoCliente(${endereco.id})'>Excluir</a></td>
		</tr>
		</tbody>
	</c:forEach>
</table>
<a href="/TCC/logado/cadastro-cliente-endereco.jsp?idCliente=${cliente.id}" class="btn btn-small btn-inverse disabled">Adicionar Endereço</a>
</div>
<div class="tab-pane" id="tab2">
<h4 align="center">Pedidos</h4>
<table class="table list">
	<thead>
	<tr>
		<th>Data Pedido</th>
		<th>Total</th>
		<th>Forma de Pagamento</th>
		<th>Status</th>
		<th>Logradouro</th>
		<th>Numero</th>
		<th>Complemento</th>
		<th>Bairro</th>
		<th>Cidade</th>
		<th>Estado</th>
	</tr>
	</thead>
	<c:forEach var="pedido" items="${requestScope.pedido}" varStatus="cont">
		<tbody class="">
		<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
			<td><fmt:formatDate value="${pedido.dataPedido.time}"pattern="dd/MM/yyyy" /></td>
			<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${pedido.valor}" /></td>
			<td>${pedido.formasDePagamento.descricao}</td>
			<td>${pedido.statusPedido.descricao}</td>
			<td>${pedido.endereco.logradouro}</td>
			<td>${pedido.endereco.numero}</td>
			<td>${pedido.endereco.complemento}</td>
			<td>${pedido.endereco.bairro}</td>
			<td>${pedido.endereco.cidade}</td>
			<td>${pedido.endereco.estado}</td>
		</tr>
		</tbody>
	</c:forEach>
</table>
</div>
<br>
</div>
</div>
</div>
</div>
</div>
<c:import url="rodape.jsp"></c:import>