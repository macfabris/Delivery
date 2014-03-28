<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tcc" %>

<c:import url="cabecalho.jsp"></c:import>

<script type="text/javascript">
$(document).ready(function() {
		$("#datepicker").datepicker({
			dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
	        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
	        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
	        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
	        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			yearRange: '1900:2013',
			changeMonth: true,
	    	changeYear: true,
	    	showButtonPanel:true,
			dateFormat: 'dd/mm/yy'});
			
	});
</script>

<div class="span12">
<form class="form-horizontal" id="form" name="cadastroCliente" action="mvc" method="post">

<input type="hidden" name="logica" value="AdicionaClienteLogica" />

<fieldset><legend>Cadastro de Clientes</legend> 

<p>* = Campos obrigatórios</p>
<div class="control-group">
	<div class="controls form-inline">
		<label>Logradouro*:</label><input class="span3" type="text" name="logradouro" id="logradouro"  value="${endereco.tipo_logradouro} ${endereco.logradouro}" readonly="readonly">

		<label>Número*:</label><input class="span1" type="text" name="numero" id="numero">
		
		<label>Complemento:</label><input class="span2" type="text" name="complemento" id="complemento"> 
	</div>
</div>
<div class="control-group">
	<div class="controls form-inline">
		<label>Bairro*:</label><input class="span3" type="text" name="bairro" id="bairro" value="${endereco.bairro}" readonly="readonly" /> 

		<label>Cidade*:</label><input class="span3" type="text" name="cidade" id="cidade" value="${endereco.cidade}" readonly="readonly" /> 

		<label>Estado*:</label><input class="span1" type="text" name="estado" id="estado"  value="${endereco.estado}" readonly="readonly" /> 
	</div>
</div>
<div class="control-group">
	<div class="controls form-horizontal">
		<label for="nome">Nome*:</label><input class="span5" id="nome" type="text" name="nome">

		<label for="cpf">CPF*:</label><input class="span3" id="cpf" type="text" name="cpf"  maxlength="14">
	</div>
</div>
<div class="control-group">
	<div class="controls form-inline">
		<jsp:useBean id="data" class="java.util.Date" /><input class="span2" id="dataCadastro" type="hidden" name="dataCadastro" value="<fmt:formatDate value="${data}"/>"> <br>
 
		<label for="dataNascimento">Data Nascimento*:</label><input type="text" id="datepicker" name="dataNascimento" />
	</div>
</div>
<div class="control-group">
	<div class="controls form-inline">
		<label for="email">Email*:</label><input class="span3"id="email" type="text" name="email"> 

		<label for="usuario">Usuário*:</label><input class="span2" id="usuario" type="text" name="usuario"> 

		<label for="senha">Senha*:</label><input class="span2" id="senha" type="password" name="senha"> 
	</div>
</div>
<br>
<div>&nbsp;</div>

<input type="submit" class="btn btn-small btn-inverse disabled" value="Cadastrar" /> 
<a href="/TCC/index.jsp" class="btn btn-small btn-inverse disabled">Voltar</a>
<div class="row">
<br>
</div>
</fieldset>
</form>
</div>
<c:import url="rodape.jsp"></c:import>
