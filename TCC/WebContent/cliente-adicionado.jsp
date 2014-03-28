<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="cabecalho.jsp"></c:import>
<c:if test="${sucesso == true}">
	<script>
	alert('Cliente adicionado com sucesso');
	window.location = '/TCC/login-cadastro.jsp';
	</script>
</c:if>
<c:if test="${sucesso != true}">
	<script>
		alert('Ocorreu um problema ao cadastrar');
		window.location = '/TCC/login-cadastro.jsp';
	</script>	
</c:if>
	
<c:import url="rodape.jsp"></c:import> 
