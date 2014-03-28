<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="cabecalho-adm.jsp"></c:import>

<div class="container">
<div class="row">
<div class="span12">
<div class="tabbable" align="center">
<div class="tab-content">
<h4 align="center">Lista de Clientes</h4>
<table class="table list">
	<thead>
		<tr>
			<th>Produto</th>
			<th>Quantidade</th>
			<th>Valor Unitario</th>
		</tr>
	</thead>
	<c:forEach var="itensPedido" items="${requestScope.itensPedido}" varStatus="cont">
		<tbody class="">
			<tr bgcolor="#${cont.count % 2 == 0 ? 'aaee88': 'ffffff' }">
				<td>${itensPedido.produto.nome}</td>
				<td>${itensPedido.quantidade}</td>
				<td>${itensPedido.preco}</td>
			</tr>
		</tbody>
	</c:forEach>
</table>
</div>
<form class="form-horizontal" id="statusPedido" name="statusPedido" action="mvc" method="post">
<input type="hidden" name="logica" value="AlterarStatusPedido" />
<input type="hidden" name="idPedido" value="${idPedido}" />
<label>Status do Pedido:<select class="span3" name="idStatusPedido" id="idStatusPedido">
				<c:forEach var="statusPedido" items="${requestScope.statusPedido}">
					<c:if test="${idStatusPedidoSelecionado != statusPedido.id}">
						<option value="${statusPedido.id}">${statusPedido.descricao}</option>	
					</c:if>
					<c:if test="${idStatusPedidoSelecionado == statusPedido.id}">
						<option value="${statusPedido.id}" selected="selected" >${statusPedido.descricao}</option>	
					</c:if>
				</c:forEach>
			</select>
			</label><br>
			<input type="submit" class="btn btn-small btn-inverse disabled" value="Salvar Dados" /> <br>
</form>
	
</div>
</div>
</div>
<div align="left"><a href="/TCC/mvc?logica=AdmLogica" class="btn btn-small btn-inverse disabled">Voltar</a></div>
</div>
<c:import url="rodape.jsp"></c:import>