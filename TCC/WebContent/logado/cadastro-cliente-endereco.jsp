<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="cabecalho_logado.jsp"></c:import>

<div class="span10">
<form  method="post" action="mvc">
									<input type="hidden" name="logica" value="BuscaCepLogica" />
									<input type="hidden" name="pagina" value="conf-cadastro-cliente-endereco.jsp" />
									 <fieldset>
									<legend>Cadastro Endereco</legend>
									<label for="cep">CEP:</label><input type="text" name="cep" id="cep"/><br>
									</fieldset>
									<div align="left"><a href="/TCC/mvc?logica=VoltarAdmPaginaLogica" class="btn btn-small btn-inverse disabled">Voltar</a></div>
									<input type="submit" value="Cadastre-se" class="btn btn-small btn-inverse disabled"/><br>
								</form>
</div>
<c:import url="rodape.jsp"></c:import>