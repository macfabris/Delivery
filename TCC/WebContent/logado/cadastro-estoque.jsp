<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="cabecalho-adm.jsp"></c:import>

<div class="span10">
<form id="form" name="grupoproduto"action="mvc" method="post" >
<input type="hidden" name="logica" value="AdicionaEstoqueLogica"/>
<fieldset>
<legend>Cadastro de Estoque</legend>

<label>Produto:<select class="span2" name="idProduto" id="idProduto">
				<option value="">Selecione</option>
				<c:forEach var="produto" items="${requestScope.produto}">
						<option value="${produto.id}">${produto.nome}</option>	
				</c:forEach>
				</select>
				</label><br>
<label for="estoque">Quantidade:</label><input type="text" class="span2" name="estoque"/><br>

<label for="pcusto">Preço Custo:</label><input type="text" class="span2" name="pcusto" value="${param.pcCusto}"/><br>
	
<label for="porcent">Lucro(%):</label><input type="text" class="span2" name="porcent" value="${param.porcenLucro}"/><br>

</fieldset>
<input type="submit" class="btn btn-small btn-inverse disabled" value="Gravar"/>
</form>
<div align="left"><a href="/TCC/mvc?logica=VoltarAdmPaginaLogica" class="btn btn-small btn-inverse disabled">Voltar</a></div>
</div>
<c:import url="rodape.jsp"></c:import>