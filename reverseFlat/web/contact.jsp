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
<form action="PublicArea?option=contact" method="post" id="feedbackForm" target="_parent">
  <h1>Contacto</h1>
  <p>&nbsp;</p>
<p>Si quieres contactar con nosotros, sólo tienes que rellenar el siguiente formulario</p>
 
  

    
    <label>Email *</label>
	<input name="email" type="text" class="textm validate['required','email']" id="email">
  
      <label>Asunto *</label>
	<div id="textarea_div">
    	<textarea name="contactSubject" rows="6" class="textm validate['required']" id="contactSubject"></textarea>
    </div>
    <%
		String captchaSalt = ("" + Math.random() * 10).substring(2);
	%>
    <label>Captcha:</label>
		<img src="jcaptcha?salt=<%=captchaSalt%>" alt="captcha" />
    
      <label>C&oacute;digo: *</label>
	<input name="captcha" type="text" class="textm validate['required','alphanum']">
    
      <label>&nbsp;</label>
	<input type="submit" value="Enviar" class="button">
    
    <input name="captchaSalt" type="hidden" id="hidden" value="<%=captchaSalt%>" style="visibility:hidden">
    <input name="locale" type="hidden" id="hidden" value="<%=request.getAttribute("locale")%>" style="visibility:hidden">
  	<input name="explorer" type="hidden" id="explorer" style="visibility:hidden">
    <input name="location" type="hidden" id="location" style="visibility:hidden">
    <script>
		var browser = BrowserDetect.browser + ' ' + BrowserDetect.version + ' on ' + BrowserDetect.OS;
		document.getElementById('explorer').value = browser;
		document.getElementById('location').value = parent.location;
    </script>
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


</body>
</html>

<%
    }catch(Exception ex){
        System.out.println("Error en contact.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>