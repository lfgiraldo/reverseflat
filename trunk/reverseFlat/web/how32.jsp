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
    <h1>3. Herramientas estrat&eacute;gicas</h1>
      
    <p style="font-size:16px"><strong>La l&iacute;nea Snoits (tus pujas)</strong></p>
    
    <p>En la linea Snoits ver&aacute;s reflejada todas las pujas que has hecho de manera sencilla... Tienes dos tipos: las <strong>pujas simples </strong>y las<strong> pujas por rango</strong>.</p>
	
<div class="prod_img">
   	    <img class="none" src="img/how32a.gif" />
	</div>

	<p>La <strong>puja simple</strong> te sirve para tantear y afinar tu puja.&nbsp; &nbsp;</p>
    
<div class="prod_img">
   	    <img class="none" src="img/how32b.gif" />
	</div>
	
    <p>Puedes usar el <strong>rango</strong> para incrementar las posibilidades de<strong> destruir las pujas ganadoras</strong>   de tus contrincantes y para <strong>aumentar tus posibilidades de ganar</strong>.</p>
    
    <div class="prod_img">
   	    <img class="none" src="img/how32c.gif" />
	</div>
    
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
        System.out.println("Error en how32.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>