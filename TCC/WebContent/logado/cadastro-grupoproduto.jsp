<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="cabecalho-adm.jsp"></c:import>

<div class="span10">
<form id="form" name="grupoproduto"action="mvc" method="post" >
<input type="hidden" name="logica" value="AdicionaGrupoProdutoLogica"/>
<fieldset>
<legend>Cadastro de Grupo Produto</legend>

<label for="descricao">Descricao:</label><input id="descricao" type="text" name="descricao">
<br>

</fieldset>
<div align="left"><a href="/TCC/mvc?logica=VoltarAdmPaginaLogica" class="btn btn-small btn-inverse disabled">Voltar</a></div>
<input type="submit" class="btn btn-small btn-inverse disabled" value="Gravar"/>
</form>
</div>
<c:import url="rodape.jsp"></c:import>