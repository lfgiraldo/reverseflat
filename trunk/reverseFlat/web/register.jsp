<%
	try {
		
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Snoits | be smart :)</title>
	<%@ include file="cssSelector.jsp" %>
    <link href="js/formcheck/theme/classic/formcheck.css" rel="stylesheet" type="text/css" />
</head>


<body bgcolor="#FFB206" style="background-color:#FFB206;">

<div id="pop" >
<form id="registerForm" method="post">
  <p>&nbsp;</p>  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <img src="txt/Registrate.gif" width="124" height="33" style="width:inherit; height:inherit;"/>
  <p>&nbsp;</p>
  <p>&nbsp;</p>  <p>&nbsp;</p>
  <p>&nbsp;</p>  <p>&nbsp;</p>
  <p>En estos momentos,<strong> Snoits.com</strong> se encuentra en fase <strong>Beta privada</strong> y no es posible registrarse en el dominio como nuevo usuario. Si conoces a alguien que ya sea BetaTester, consigue que nos env&iacute;e un mensaje para crear un nombre de usuario v&aacute;lido.</p>
  <p>&nbsp;</p>

</form>
</div>



<!-- -----------------------------------------SCRIPTS---------------------------------------- --> 
	<script type="text/javascript" src="js/formcheck/lang/<%=(String)request.getSession().getAttribute("locale")%>.js"></script>
	<script type="text/javascript" src="js/mootools-1.2.1-core-yc.js"></script>
    <script type="text/javascript" src="js/mootools-1.2-more.js"></script>
	<script type="text/javascript" src="js/formcheck.js"></script>

	<script type="text/javascript">
		window.addEvent('domready', function(){
			new FormCheck('registerForm');
		});
	</script>

</body>
</html>

<%
    }catch(Exception ex){
        System.out.println("Error en register.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>