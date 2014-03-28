<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:import url="cabecalho-adm.jsp"></c:import>

<div class="span8">
<form id="form" name="produto" enctype="multipart/form-data"  action="mvc" method="post" >
<input type="hidden" name="logica" value="AdicionaProdutoLogica"/>

<fieldset>
<legend>Cadastro de Produtos</legend>
<div class="control-group">
	<div class="controls form-inline">
		<label for="nome">Nome:</label><input type="text" class="span3" name="nome" /><br>

		<label for="descricao">Descrição:</label><input type="text" class="span4" name="descricao" /><br>
	</div>
</div>

<label for="pcusto">Preço Custo:</label><input type="text" class="span2" name="pcustor"/><br>

<label for="porcentagem">Lucro(%):</label><input type="text" class="span2" name="porcentagem"/><br>

<jsp:useBean id="data" class="java.util.Date" /><input type="hidden" name="dataCadastro" value="<fmt:formatDate value="${data}"/>" /> 

<div class="control-group">
	<div class="controls form-inline">		
			<label>Grupo Produto:<select class="span3" name="idGrupoProduto" id="idGrupoProduto">
				<option value="">Selecione</option>
				<c:forEach var="grupoProduto" items="${requestScope.gruposProduto}">
						<option value="${grupoProduto.id}">${grupoProduto.descricao}</option>	
				</c:forEach>
			</select>
			</label><br>
			
			<label>Unidade De Medida:<select class="span2" name="idUnidadeDeMedida" id="idUnidadeDeMedida">
				<option value="">Selecione</option>
				<c:forEach var="unidadeDeMedida" items="${requestScope.unidadesDeMedida}">
						<option value="${unidadeDeMedida.id}">${unidadeDeMedida.descricao}</option>	
				</c:forEach>
				</select>
				</label><br>
				
				<label>Fornecedor:<select class="span2" name="idFornecedor" id="idFornecedor">
				<option value="">Selecione</option>
				<c:forEach var="fornecedor" items="${requestScope.fornecedores}">
						<option value="${fornecedor.id}">${fornecedor.nome}</option>	
				</c:forEach>
				</select>
				</label><br>
		</div>
	</div>
	
	<label for="estoque">Estoque:</label><input type="text" class="span2" name="estoque"/><br>

	<label for="foto">Foto:</label><input id="foto" type="file" name="foto"><br> 


<div align="left"><a href="/TCC/mvc?logica=VoltarAdmPaginaLogica" class="btn btn-small btn-inverse disabled">Voltar</a></div>
<input type="submit" class="btn btn-small btn-inverse disabled" value="Gravar"/>
</fieldset>

<br><br><br><br>
</form>
</div>
<c:import url="rodape.jsp"></c:import>
