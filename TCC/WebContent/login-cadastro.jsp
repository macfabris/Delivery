<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="cabecalho.jsp"></c:import>
<div class="container">
        	<div class="row-fluid">
            	<div class="span12">
            		<div class="span6">
                    	<div class="area">
								<form  method="post" action="mvc">
								<input type="hidden" name="logica" value="LoginLogica" />
								 <fieldset>
								<legend>Login</legend>
								<label for="login">Login:</label><input type="text" name="login" id="login"/><br>
								<label for="senha">Senha:</label><input type="password" name="senha" id="senha"/><br>
								</fieldset>
								<input type="submit" value="Login" class="btn btn-small btn-inverse disabled"/><br>
								</form>
								<p>${msg}</p>
						</div>
					</div>
            		<div class="span6">
                    	<div class="area">
								<form  method="post" action="mvc">
									<input type="hidden" name="logica" value="BuscaCepLogica" />
									<input type="hidden" name="pagina" value="cadastro-cliente.jsp" />
									 <fieldset>
									<legend>Cadastre-se</legend>
									<label for="cep">CEP:</label><input type="text" name="cep" id="cep"/><br>
									</fieldset>
									<input type="submit" value="Cadastre-se" class="btn btn-small btn-inverse disabled"/><br>
								</form>
								<p>${msgCep}</p>
					</div>
				</div>
			</div>
		</div>
	</div>
<c:import url="rodape.jsp"></c:import>
