<%@page import="com.reverseFlat.ejb.persistence.*, java.util.*" %>
<%
	try {
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Snoits | be smart :)</title>
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
    <link href="js/formcheck/theme/classic/formcheck.css" rel="stylesheet" type="text/css" />
    <script language="javascript" type="text/javascript" src="js/browserCapabilities.js"></script>
</head>

<body bgcolor="#FFB206" style="background-color:#FFB206;">

<div id="pop">
<form action="PublicArea?option=feedback" method="post" id="feedbackForm" target="_parent">
  <p><img src="txt/Feedback.gif" width="124" height="33" style="width:inherit; height:inherit;"/>  </p>
<%
	User user = (User) request.getSession().getAttribute("user");
	if (user == null){
%>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <h2>Debes autenticarte para poder enviar un mensaje de feedback </h2>
<%
	}else{
%>
  
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>En nombre del equipo de Snoits.com te damos las gracias por ayudarnos a mejorar nuestro servicio. Por favor, rellena el siguiente formulario para que podemos arreglar el problema lo m&aacute;s r&aacute;pido posible.</p>
 
  

    
    <label>web? d&oacute;nde te pas&oacute;? *</label>
	<input name="web" type="text" class="textm validate['required']" id="web">
  
      <label>qu&eacute; problema tuviste? *</label>
	<%-- <input name="problem" type="text" class="textm validate['required']" id="problem"> --%>
	<div id="textarea_div">
    	<textarea name="problem" rows="6" class="textm validate['required']" id="problem"></textarea>
    </div>
    
    <%
		//String captchaSalt = ("" + Math.random() * 10).substring(2);
	%>
    <label>&nbsp;</label>
	<input name="userName" type="hidden" id="userName" value="<%=user.getNickname().toUpperCase()%>" style="visibility:hidden">
	<input name="explorer" type="hidden" id="explorer" style="visibility:hidden">
    <input name="location" type="hidden" id="location" style="visibility:hidden">
    <script>
		var browser = BrowserDetect.browser + ' ' + BrowserDetect.version + ' on ' + BrowserDetect.OS;
		document.getElementById('explorer').value = browser;
		document.getElementById('location').value = parent.location;
    </script>
      <label>&nbsp;</label>
	<input type="submit" value="Enviar" class="button">
    </form>
</div>

<!-------------------------------------------SCRIPTS------------------------------------------> 
	<script type="text/javascript" src="js/formcheck/lang/<%=(String)request.getSession().getAttribute("locale")%>.js"></script>
	<script type="text/javascript" src="js/mootools-1.2.1-core-yc.js"></script>
    <script type="text/javascript" src="js/mootools-1.2-more.js"></script>
	<script type="text/javascript" src="js/formcheck.js"></script> 

	<script type="text/javascript">
		window.addEvent('domready', function(){
			new FormCheck('feedbackForm');
		});
	</script>
    <%
    }
    %>


</body>
</html>

<%
    }catch(Exception ex){
        System.out.println("Error en feedback.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>