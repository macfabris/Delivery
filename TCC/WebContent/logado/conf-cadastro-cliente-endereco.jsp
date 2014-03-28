<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="cabecalho_logado.jsp"></c:import>

<div class="span8">
<form id="cadastroClienteEndereco" name="cliente" action="mvc" method="post">
<input type="hidden" name="logica" value="AdicionaClienteEnderecoLogica" />
<div class="control-group">
	<div class="controls form-inline">
		
		<label>Logradouro*:</label><input class="span3" type="text" name="logradouro" id="logradouro"  value="${endereco.tipo_logradouro} ${endereco.logradouro}" readonly="readonly">

		<label>Número*:</label><input class="span1" type="text" name="numero" id="numero">
		
		<label>Complemento:</label><input class="span2" type="text" name="complemento" id="complemento"> 
	</div>
</div>
<div class="control-group">
	<div class="controls form-inline">
		<label>Bairro*:</label><input class="span2" type="text" name="bairro" id="bairro" value="${endereco.bairro}" readonly="readonly" /> 

		<label>Cidade*:</label><input class="span2" type="text" name="cidade" id="cidade" value="${endereco.cidade}" readonly="readonly" /> 

		<label>Estado*:</label><input class="span1" type="text" name="estado" id="estado"  value="${endereco.estado}" readonly="readonly" /> 
	</div>
</div>

<input type="submit" class="btn btn-small btn-inverse disabled" value="Salvar Dados" />
<br><br><br><br>
</form>
</div>
<c:import url="rodape.jsp"></c:import>



