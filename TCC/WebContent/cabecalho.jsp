<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>De Paula &#38;  Fabris Delivery</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/jQueryValidateAddMetodos.js"></script>
<script type="text/javascript" src="js/jQueryValidacoes.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/funcoes.js"></script>

<link type="text/css" href="css/jquery.css" rel="stylesheet" />
<link type="text/css" href="css/prettify.css" rel="stylesheet" />
<link type="text/css" href="css/bootstrap.css" rel="stylesheet" media="screen">
<link type="text/css" href="css/example-fluid-layout.css" rel="stylesheet">
<link type="text/css" href="css/bootstrap-responsive.css" rel="stylesheet">
<link type="text/css" href="css/style.css" rel="stylesheet" />
<link type="text/css" href="css/validate.css" rel="stylesheet" />

</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
<div class="navbar-inner">
<div class="container"><a class="btn btn-navbar"
	data-toggle="collapse" data-target=".nav-collapse"> <span
	class="icon-bar"></span> <span class="icon-bar"></span> <span
	class="icon-bar"></span> </a> <a class="brand" href="index.jsp">De Paula &#38; Fabris Delivery</a>
<div class="nav-collapse collapse">
<ul class="nav">
	<li><a href="/TCC/mvc?logica=PaginaProdutosLogica"><i class=" icon-glass"></i><br />Produtos</a></li>
	<li><a href="login-cadastro.jsp"><i class="icon-user"></i><br />Logue ou Cadastre-se</a></li>
	<li><a href="carrinho.jsp"><i class="icon-shopping-cart"></i><br />Total do Carrinho R$:<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${totalPedido}" /></a></li>

<br>
</div>
<!--/.nav-collapse --></div>
</div>
</div>
<div class="row">
<br>
</div>