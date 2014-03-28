<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="grupoProdutoDao" class="br.com.ffsd.tcc.dao.GrupoProdutoDao" />
<jsp:useBean id="unidadeDeMedidaDao" class="br.com.ffsd.tcc.dao.UnidadeDeMedidaDao" />

<c:import url="cabecalho-adm.jsp"></c:import>

<div class="span10">
<form id="form" action="mvc" method="post">
<input type="hidden" name="logica" value="AlteraProdutoLogica" />
<input type="hidden" name="id" value="${param.id}" />
<fieldset>
<legend>Altera</legend>

<div class="control-group">
	<div class="controls form-inline">
		<label for="nome">Nome:</label><input type="text" class="span3" name="nome" value="${param.nome}"/><br>

		<label for="descricao">Descrição:</label><input type="text" class="span4" name="descricao" value="${param.descricao}"/><br>
	</div>
</div>

<div class="control-group">
	<div class="controls form-inline">
			<label>Grupo Produto:<select class="span3" name="idGrupoProduto" id="idGrupoProduto">
				<c:forEach var="grupoProduto" items="${grupoProdutoDao.lista}">
					<c:if test="${param.idGrupoProduto != grupoProduto.id}">
						<option value="${grupoProduto.id}">${grupoProduto.descricao}</option>	
					</c:if>
					<c:if test="${param.idGrupoProduto == grupoProduto.id}">
						<option value="${grupoProduto.id}" selected="selected" >${grupoProduto.descricao}</option>	
					</c:if>
				</c:forEach>
			</select>
			</label><br>
			
			<label>Unidade De Medida:<select class="span2" name="idUnidadeDeMedida" id="idUnidadeDeMedida">
				<c:forEach var="unidadeDeMedida" items="${unidadeDeMedidaDao.lista}">
					<c:if test="${param.idUnidadeDeMedida != unidadeDeMedida.id}">
						<option value="${unidadeDeMedida.id}">${unidadeDeMedida.descricao}</option>	
					</c:if>
					<c:if test="${param.idUnidadeDeMedida == unidadeDeMedida.id}">
						<option value="${unidadeDeMedida.id}" selected="selected" >${unidadeDeMedida.descricao}</option>	
					</c:if>
				</c:forEach>
				</select>
				</label><br>
		</div>
	</div>
		
	<label for="pcusto">Preço Custo:</label><input type="text" class="span2" name="pcCusto" value="${param.pcusto}"/><br>
	
	<label for="porcent">Lucro(%):</label><input type="text" class="span2" name="porcentLucro" value="${param.porcent}"/><br>

</fieldset>
<jsp:useBean id="data" class="java.util.Date" /><input type="hidden" name="dataCadastro" value="<fmt:formatDate value="${data}"/>"/>
<input type="submit" class="btn btn-small btn-inverse disabled" value="Gravar"/>
</form>
<div align="left"><a href="/TCC/mvc?logica=VoltarAdmPaginaLogica" class="btn btn-small btn-inverse disabled">Voltar</a></div>
</div>
<c:import url="rodape.jsp" />