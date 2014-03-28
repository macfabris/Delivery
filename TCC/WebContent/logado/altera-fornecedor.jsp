<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tcc" %>

<c:import url="cabecalho-adm.jsp"></c:import>

<div class="span12">
<form class="form-horizontal" id="formAtualiza" name="cadastroFornecedor" action="mvc" method="post">

<input type="hidden" name="logica" value="AlteraFornecedorLogica" />

<fieldset><legend>Cadastro de Fornecedores</legend> 


<div class="control-group">
	<div class="controls form-horizontal">
		<label for="nome">Nome*:</label><input class="span5" id="nome" type="text" name="nome" value="${param.nome }">

		<label for="email">Email*:</label><input class="span3"id="email" type="text" name="email" value="${param.email }"> 

	</div>
</div>
<br>
<input type="hidden" name="idFornecedor" value="${param.id }" />
<input type="submit" class="btn btn-small btn-inverse disabled" value="Alterar" /> 
<div align="left"><a href="/TCC/mvc?logica=VoltarAdmPaginaLogica" class="btn btn-small btn-inverse disabled">Voltar</a></div>
<div class="row">
<br>
</div>
</fieldset>
</form>
</div>
<c:import url="rodape.jsp"></c:import>
