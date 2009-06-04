<%
    try{
%>

<%--IMPRESCINDIBLE INCLUIR commonVariables.jsp EN LAS PÁGINAS QUE INCLUYAN publicHeader.jsp --%>
	<%@ include file="commonVariables.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>Snoits | be smart :)</title>
	<%@ include file="cssSelector.jsp" %>
    <link href="js/formcheck/theme/classic/formcheck.css" rel="stylesheet" type="text/css" />
    <link href="css/SqueezeBox.css" rel="stylesheet" type="text/css" />
    <link rel="icon" type="image/png" href="img/favicon.png" />
</head>

<body>
<a style="background-color:rgb(34, 34, 34);" id="fdbk_tab" href="feedback.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:470}}" title="Feedback">FEEDBACK</a>
<!-------------------------------------------HEADER-------------------------------------------> 

    <!-----------------------------------SCRIPTS------------------------------------------>
    <%@ include file="commonScripts.jsp" %>

    <%@ include file="publicHeader.jsp" %>

<!-------------------------------------------CIERRES-------------------------------------------> 
	<%@ include file="publicClosing.jsp" %>

<!-------------------------------------------SUBMENU-------------------------------------------> 
	<%@ include file="publicSubmenu.jsp" %>

<!-------------------------------------------CUERPO / COLUMNA-------------------------------------------> 	 
	<%@ include file="how_menu.jsp" %>

<!-------------------------------------------CUERPO / GAME -------------------------------------------> 
<span id="game">
    <div id="gameHead">
	<img src="img/game_c_ul.gif" class="c_l" />
	<img src="img/game_c_ur.gif" class="c_r" />
    </div>
    <div id="gameBody">
    
    
      <h1>4. Premios por subasta</h1>
      
      <p>Las subastas Snoits son las que mas   oportunidades generan por subasta! 4 oportunidades por art&iacute;culo ! &iquest; Alguien te da m&aacute;s ?</p>
    <p>Con cada subasta estrat&eacute;gica Snoits se genera una   nueva oportunidad al final de cada secci&oacute;n. Tienes 3 oportunidades por conseguir un premio durante el desarrollo de la subasta... y lo mejor lo dejamos para el final!! Cuando la cuenta atr&aacute;s se activa tienes 30 minutos para encontrar el precio &uacute;nico!    </p>
    <div class="prod_img">
        <img class="none" src="img/how4a.gif" />	
    </div>
    
<p>Los premios que podr&aacute;s encontrar durante el transcurso de la subasta estrat&eacute;gica son variados... Apple iPods, Snoits gratuitos,   PlayStation Portable, vales en efectivo&hellip; y muchos otros premios !! A qu&eacute; est&aacute;s esperando?? YA ESTAS PREPARADO PARA GANAR??</p>
  </div>
    <div id="gameKicker">
	<img src="img/game_c_ll.gif" class="c_l" />
	<img src="img/game_c_lr.gif" class="c_r" />
    </div>
</span>
<!-------------------------------------------CUERPO / COLUMNA-------------------------------------------> 

    <%@ include file="publicKicker.jsp" %>
</div>


</body>
</html>


<%
    }catch(Exception ex){
        System.out.println("Error en how4.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>