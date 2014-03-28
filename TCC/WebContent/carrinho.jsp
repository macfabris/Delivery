<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	if (session.getAttribute("usuario") == null) {
%>
<c:import url="cabecalho.jsp"></c:import>
<%
	} else {
%>
<c:import url="cabecalho_logado.jsp"></c:import>
<%
	}
%>

<div class="container">
	<div class="row">
		<div class="span12">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab1" data-toggle="tab">Carrinho</a></li>
			</ul>
			<c:if test="${totalPedido > 0}">
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
							<c:forEach var="itensPedido" items="${sessionScope.itensPedido}"
								varStatus="cont">
								<tbody class="">
									<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
										<td><div class="span2">
												<img src="${itensPedido.produto.diretorio}"
													class="img-rounded">
											</div></td>
										<td>${itensPedido.quantidade}</td>
										<td>${itensPedido.preco}</td>
										<td>${itensPedido.total}</td>
										<td><a
											href="/TCC/mvc?logica=RemoveProdutoCarrinhoLogica&id=${cont.count}"><i
												class="icon-remove"></i></a></td>
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>
				</div>
				<div align="right">
					<h3>
						Total do Carrinho R$:
						<fmt:formatNumber type="number" minFractionDigits="2"
							maxFractionDigits="2" value="${totalPedido}" />
					</h3>
				</div>

				<a href="/TCC/mvc?logica=FinalizarPedidoLogica"><c:if
						test="${usuario.login != null}">Finalizar Pedido</c:if></a>
				<a href="/TCC/login-cadastro.jsp"><c:if
						test="${usuario.login == null}">Logue-se</c:if></a>
			</c:if>
			<c:if test="${totalPedido == null}">
				<h3>Carrinho vazio</h3>
			</c:if>
			<a href="/TCC/mvc?logica=PaginaProdutosLogica">Continuar
				Comprando</a>
		</div>
	</div>
</div>

<c:import url="rodape.jsp"></c:import>