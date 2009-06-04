<%
    try{
%>

<%-- IMPRESCINDIBLE INCLUIR commonVariables.jsp EN LAS PÁGINAS QUE INCLUYAN publicHeader.jsp --%>
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
<div id="cuerpo">

<%@ include file="itemsColum.jsp" %>

<!-------------------------------------------CUERPO / GAME -------------------------------------------> 
<span id="game">
    <div id="gameHead">
	<img src="img/game_c_ul.gif" class="c_l" />
	<img src="img/game_c_ur.gif" class="c_r" />
    </div>
    <div id="gameBody">
        <div id="art_bg">
		<img src="img/club.jpg" width="150" height="113" />
      </div>
	
        <h1>Qu&eacute; es &quot;The Club?</h1>
        
        <p>Estamos trabajando para ofrecerte el  modelo m&aacute;s innovador de Subastas Estrat&eacute;gicas que lanzaremos en breve. 
        <h2>Algo  nunca visto hasta ahora.</h2> <br />
        Est&aacute;s invitado a la inauguraci&oacute;n</p>.
        <div id="game_line">&nbsp;</div></br>
        <p align="center">Para poder pujar debes estar registrado. <a href="register.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:470}}">Registrate aqu&iacute;</a></p>
</br>
		<div id="game_line">&nbsp;</div>
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
        System.out.println("Error en howclub.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>