<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tcc" %>

<c:import url="cabecalho_logado.jsp"></c:import>

<div class="span12">
<form class="form-horizontal" id="form" name="cliente" action="mvc" method="post">

<input type="hidden" name="logica" value="AlteraClienteLogica" />
<input type="hidden" name="idCliente" value="${param.id}" />
<input type="hidden" name="idEmail" value="${param.idEmail}" />
<input type="hidden" name="idUsuario" value="${param.idUsuario}" />

<fieldset><legend>Cadastro de Clientes</legend> 


<div class="control-group">
	<div class="controls form-horizontal">
	${param.dataNascimento}
		<label for="nome">Nome*:</label><input class="span5" id="nome" type="text" name="nome" value="${param.nome}">

		<label for="cpf">CPF*:</label><input class="span3" id="cpf" type="text" name="cpf"  value="${param.cpf}" readonly="readonly">
		
		<label for="dataNascimento">Data Nascimento*:</label>
		
		<input class="span3" id="dataNascimento" type="text" name="dataNascimento" readonly="readonly" value="${param.dataNascimento}" />
	</div>
</div>
<div class="control-group">
	<div class="controls form-inline">
		<label for="email">Email*:</label><input class="span3"id="email" type="text" name="email" value="${param.email}"> 

		<label for="usuario">Usuário*:</label><input class="span2" id="usuario" type="text" name="usuario" value="${param.login}" readonly="readonly"> 

		<label for="senha">Senha*:</label><input class="span2" id="senha" type="password" name="senha" value="${param.senha}"> 
	</div>
</div>
<br>
<div align="left"><a href="/TCC/mvc?logica=PainelLogica" class="btn btn-small btn-inverse disabled">Voltar</a></div>
<input type="submit" class="btn btn-small btn-inverse disabled" value="Salvar Dados" /> <br>
<br>
</fieldset>
</form>

</div>
<c:import url="rodape.jsp"></c:import>


