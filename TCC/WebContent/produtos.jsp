<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<form class="form-search" action="mvc" method="post">
<input type="hidden" name="logica" value="PesquisaProdutoLogica" />
  <input type="text" class="input-medium search-query" name="pesquisa" id="pesquisa">
  <button type="submit" class="btn">Pesquisar</button>
</form>

<div class="container"> 
<ul class="nav nav-pills">
	<c:forEach var="grupoproduto" items="${requestScope.grupoProduto}" varStatus="cont">
	<li><a href="/TCC/mvc?id=${grupoproduto.id}&logica=MenuProdutoLogica">${grupoproduto.descricao}</a></li>
	</c:forEach>
</ul>
</div>

<div class="container"> 
<h2>Produtos</h2>
<ul class="thumbnails">
<c:forEach var="produto" items="${requestScope.produtos}" varStatus="cont">
<c:if test="${cont.count % 4 == 0 }"><br></c:if>
		<li class="span2">
			<div class="thumbnail">
				<img src="${produto.diretorio}" alt="" class="img-rounded"> <br>
				<a href="/TCC/mvc?id=${produto.id}&logica=ProdutosDetalhesLogica" class="btn btn-small btn-inverse disabled">Detalhes</a>
			</div>	
		</li>
</c:forEach>
</ul>
</div>


<c:import url="rodape.jsp"></c:import>