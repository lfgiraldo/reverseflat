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
<form action="PublicArea?option=register" method="post" id="registerForm">
  <h1>REG&Iacute;STRATE</h1>
      <h2>Complete el siguiente formulario:</h2>
  
  <label>Nombre:</label>
	<input name="name" type="text" class="textm validate['required']" id="name">
  
      <label>Apellidos:</label>
	<input name="lastName" type="text" class="textm validate['required']" id="lastName">
    
    <label>Nombre Usuario:</label>
	<input name="userName" type="text" class="textm validate['required']" id="userName">
  
      <label>Contrase&ntilde;a:</label>
	<input name="password1" type="password" class="textm validate['required','length[6,-1]','alphanum']">

      <label>Repita contrase&ntilde;a:</label>
	<input name="password2" type="password" class="textm validate['confirm[password1]']">
		  
      <label>Email:</label>
	<input name="email" type="text" class="textm validate['required','email']" id="email">
    <%
		String captchaSalt = ("" + Math.random() * 10).substring(2);
	%>
    <label>Captcha:</label>
		<img src="jcaptcha?salt=<%=captchaSalt%>" alt="captcha" />
    
      <label>C&oacute;digo:</label>
	<input name="captcha" type="text" class="textm validate['required','alphanum']">
	<input name="captchaSalt" type="hidden" id="hidden" value="<%=captchaSalt%>">
    <input name="locale" type="hidden" id="hidden" value="<%=request.getAttribute("locale")%>">
      
      <label>&nbsp;</label>
	<input type="submit" value="Reg&iacute;strame!" class="button">
    </form>
</div>

<!-------------------------------------------SCRIPTS------------------------------------------> 
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