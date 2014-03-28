<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%
if(session.getAttribute("usuario")==null) {
%>
<c:import url="cabecalho.jsp"></c:import>
<%
} else {
%>
<c:import url="cabecalho_logado.jsp"></c:import>
<%
}%>

<div class="row">
<br>
</div>
<c:forEach var="produto" items="${requestScope.produtos}" varStatus="cont">
<div class="container">
<div class="span3"><img src="${produto.diretorio}" class="img-rounded"></div>
</div>
<div class="container">
<h3>${produto.nome}</h3>
<p>${produto.descricao}</p>
<p>R$:<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${produto.valor}" /></p>
<p>${produto.unidadeDeMedida.descricao}</p>
<form id="form" name="cadastroCliente" action="mvc" method="post" >
<input type="hidden" name="logica" value="AdicionaProdutoCarrinhoLogica"> 
<input type="hidden" name="id" value="${produto.id}">
<input type="hidden" name="diretorio" value="${produto.diretorio}">
<input type="hidden" name="valorProduto" value="${produto.valor}">
<input type="hidden" name="nomeProduto" value="${produto.nome}">
<label for="numero">Quantidade:</label><input class="span1" type="text" name="numero" id="numero">
<input type="submit" class="btn btn-small btn-inverse disabled" value="Adicionar ao Carrinho"/>
</form>
<br> 
</div>
</c:forEach>
<c:import url="rodape.jsp"></c:import>