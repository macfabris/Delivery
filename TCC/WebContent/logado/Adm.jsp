<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="cabecalho-adm.jsp"></c:import>

<div class="container">
<div class="row">
<div class="span12">
<div class="tabbable" align="center">
<ul class="nav nav-tabs">
	<li class="active"><a href="#tab1" data-toggle="tab">Pedidos</a></li>
	<li><a href="#tab2" data-toggle="tab">Produtos</a></li>
	<li><a href="#tab3" data-toggle="tab">Clientes</a></li>
	<li><a href="#tab4" data-toggle="tab">Fornecedores</a></li>
</ul>
<div class="tab-content">
<div class="tab-pane active" id="tab1">
<form class="form-search" action="mvc" method="post">
<input type="hidden" name="logica" value="PesquisaPedidoLogica" />
 <label>Status Pedido:<select class="span3" name="idStatusPedido" id="idStatusPedido">
				<option value="">Selecione</option>
				<c:forEach var="status" items="${requestScope.status}">
						<option value="${status.id}">${status.descricao}</option>	
				</c:forEach>
			</select>
			</label>
  <button type="submit" class="btn">Pesquisar</button>
</form>
<h4 align="center">Lista de Pedidos</h4>
<table class="table list" id="table">
	<thead>
		<tr>
			<th>Id</th>
			<th>Valor</th>
			<th>Data</th>
			<th>Cliente</th>
			<th>Status</th>
			<th>Forma de Pagamento</th>
			<th>Logradouro</th>
			<th>Numero</th>
			<th>Complemento</th>
			<th>Bairro</th>
			<th>Cidade</th>
			<th>Estado</th>
			<th>Detalhes</th>
		</tr>
		</thead>
		<c:forEach var="pedido" items="${requestScope.pedidos}"	varStatus="cont">
		 <tbody class="">
                <tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
				<td>${pedido.id}</td>
				<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${pedido.valor}" /></td>
				<td><fmt:formatDate value="${pedido.dataPedido.time}"pattern="dd/MM/yyyy" /></td>
				<td>${pedido.cliente.nome}</td>
				<td>${pedido.statusPedido.descricao}</td>
				<td>${pedido.formasDePagamento.descricao}</td>
				<td>${pedido.endereco.logradouro}</td>
				<td>${pedido.endereco.numero}</td>
				<td>${pedido.endereco.complemento}</td>
				<td>${pedido.endereco.bairro}</td>
				<td>${pedido.endereco.cidade}</td>
				<td>${pedido.endereco.estado}</td>
				<td><a href="/TCC/mvc?id=${pedido.id}&idStatusPedido=${pedido.statusPedido.id}&logica=ItensPedidoLogica"><i class="icon-search"></i></a></td>
			</tr>
		 </tbody>
		</c:forEach>
</table>
</div>

<div class="tab-pane" id="tab2">
<h4 align="center">Lista de Produtos</h4>
<table class="table list">
		<thead>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Descrição</th>
			<th>Preço Custo</th>
			<th>Valor</th>
			<th>Data Cadastro</th>
			<th>Unidade De Medida</th>
			<th>Grupo Produto</th>
			<th>Estoque</th>
			<th>Fornecedor</th>
			<th>Foto</th>
			<th>Alterar</th>
			<th>Excluir</th>
		</tr>
	</thead>
	<c:forEach var="produto" items="${requestScope.produtos}" varStatus="cont">
		<tbody class="">
			<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
				<td>${produto.id}</td>
				<td>${produto.nome}</td>
				<td>${produto.descricao}</td>
				<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${produto.pcCusto}" /></td>
				<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${produto.valor}" /></td>
				<td><fmt:formatDate value="${produto.dataCadastro.time}" pattern="dd/MM/yyyy" /></td>
				<td>${produto.unidadeDeMedida.descricao}</td>
				<td>${produto.grupoProduto.descricao}</td>
				<td>${produto.estoque}</td>
				<td>${produto.fornecedor.nome}</td>
				<td><div class="span1"><img src="${produto.diretorio}" class="img-rounded"></div></td>
				<td><a href="/TCC/logado/altera-produto.jsp?id=${produto.id}&nome=${produto.nome}&pcusto=${produto.pcCusto}&porcent=${produto.porcentLucro}&descricao=${produto.descricao }&idGrupoProduto=${produto.grupoProduto.id}&grupoProduto=${produto.grupoProduto.descricao}&idUnidadeDeMedida=${produto.unidadeDeMedida.id}&unidadeDeMedida=${produto.unidadeDeMedida.descricao}">Alterar</a></td>
				<td><a href='#' onClick='apagarProduto(${produto.id})'>Excluir</a></td>
			</tr>
		</tbody>	
	</c:forEach>	
</table>
<div align="left"><a href="/TCC/mvc?logica=CadastroProdutoLogica" class="btn btn-small btn-inverse disabled">Adiciona Produto</a></div>
<div align="left"><a href="/TCC/mvc?logica=CadastroEstoqueLogica" class="btn btn-small btn-inverse disabled">Adiciona Estoque</a></div>
<br>
<h4 align="center">Lista de Grupo Produtos</h4>
<table class="table list">
	<thead>
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Alterar</th>
			<th>Excluir</th>
		</tr>
	</thead>
	<c:forEach var="grupoProduto" items="${requestScope.gruposProduto}" varStatus="cont">
		<tbody class="">
			<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
				<td>${grupoProduto.id}</td>
				<td>${grupoProduto.descricao}</td>
				<td><a href="/TCC/logado/altera-grupoproduto.jsp?id=${grupoProduto.id}&descricao=${grupoProduto.descricao}">Alterar</a></td>
				<td><a href='#' onClick='apagarGrupoProduto(${grupoProduto.id})'>Excluir</a></td>
			</tr>
		</tbody>	
	</c:forEach>
</table>
<div align="left"><a href="/TCC/logado/cadastro-grupoproduto.jsp" class="btn btn-small btn-inverse disabled">Adiciona Grupo Produto</a></div>
<br>
<h4 align="center">Lista de Unidades De Medida</h4>
<table class="table list">
	<thead>
		<tr>
			<td>Id</td>
			<td>Descrição</td>
			<td>Alterar</td>
			<td>Excluir</td>
		</tr>
	</thead>
	<c:forEach var="unidadeDeMedida" items="${requestScope.unidadesDeMedida}" varStatus="cont">
		<tbody class="">
			<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
				<td>${unidadeDeMedida.id}</td>
				<td>${unidadeDeMedida.descricao}</td>
				<td><a href="/TCC/logado/altera-unidadedemedida.jsp?id=${unidadeDeMedida.id}&descricao=${unidadeDeMedida.descricao}">Alterar</a></td>
				<td><a href='#' onClick='apagarUnidadeDeMedida(${unidadeDeMedida.id})'>Excluir</a></td>
			</tr>
		</tbody>
	</c:forEach>
</table>
<div align="left"><a href="/TCC/logado/cadastro-unidadedemedida.jsp" class="btn btn-small btn-inverse disabled">Adiciona Unidade De Medida</a></div>
</div>
<br>

<div class="tab-pane" id="tab3">
<h4 align="center">Lista de Clientes</h4>
<table class="table list">
	<thead>
	<tr>
		<th>Id</th>
		<th>Nome</th>
		<th>CPF</th>
		<th>Data Nascimento</th>
		<th>Data Cadastro</th>
		<th>Status</th>
	</tr>
	</thead>
	<c:forEach var="cliente" items="${requestScope.clientes}" varStatus="cont">
	<tbody class="">
		<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
			<td>${cliente.id}</td>
			<td>${cliente.nome}</td>
			<td>${cliente.cpf}</td>
			<td><fmt:formatDate value="${cliente.dataNascimento.time}" pattern="dd/MM/yyyy" /></td>
			<td><fmt:formatDate value="${cliente.dataCadastro.time}" pattern="dd/MM/yyyy" /></td>
			<td>${cliente.statusCliente.descricao}</td>
		</tr>
		</tbody>
	</c:forEach>
</table>
</div>
<div class="tab-pane" id="tab4">
<h4 align="center">Lista de Fornecedores</h4>
<table class="table list">
	<thead>
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Email</td>
			<td>Alterar</td>
			<td>Excluir</td>
		</tr>
	</thead>
	<c:forEach var="fornecedores" items="${requestScope.fornecedores}" varStatus="cont">
		<tbody class="">
			<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
				<td>${fornecedores.id}</td>
				<td>${fornecedores.nome}</td>
				<td>${fornecedores.email.descricao}</td>
				<td><a href="/TCC/logado/altera-fornecedor.jsp?id=${fornecedores.id}&nome=${fornecedores.nome}&email=${fornecedores.email.descricao}">Alterar</a></td>
				<td><a href='#' onClick='apagarFornecedor(${fornecedores.id})'>Excluir</a></td>
			</tr>
		</tbody>
	</c:forEach>
</table>
<div align="left"><a href="/TCC/logado/cadastro-fornecedor.jsp" class="btn btn-small btn-inverse disabled">Adiciona Fornecedor</a></div>
</div>
<br>
</div>
</div>
</div>
</div>
</div>


<c:import url="rodape.jsp"></c:import>