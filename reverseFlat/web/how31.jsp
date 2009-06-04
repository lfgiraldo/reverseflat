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
      
      <p style="font-size:16px"><strong>Barra de estado  Snoits</strong></p>  
      
      <p>Empieza a idear tu estrategia para ser el ganador !!</p>
        
      <p>La Barra de estado Snoits indica el progreso de la subasta... La barra se va completando conforme se reciben las pujas. Pero cuidado,<strong> el coste de participar en cada subasta es variable! </strong>Todo depende de cu&aacute;ndo realices tu apuesta! :) Por eso tienes que pensar en qu&eacute; momento posicionar tus pujas...</p>
<div class="prod_img">
    	  <img class="none" src="img/how31a.gif" />
		</div>
        
      <p>En el <strong>primer tramo </strong>participar en la subasta s&oacute;lo te costar&aacute; <strong>1 snoits por puja</strong>... es el mejor momento para posicionar estrat&eacute;gicamente tus pujas...</p>
      <p>En el <strong>segundo tramo</strong> entrar en la subasta te costar&aacute; <strong>2 snoits por puja</strong>... Es un buen momento para posicionar tus apuestas, pero mejor a&uacute;n para eliminar a los competidores... M&aacute;s adelante te ense&ntilde;amos c&oacute;mo.... Apunta y dispara ! </p>
      <p>En el <strong>tercer tramo</strong> cada puja te costar&aacute; <strong>3 snoits por puja</strong>, ya que la informaci&oacute;n que se te ofrece por cada puja es muy valiosa para encontrar la puja ganadora... Adem&aacute;s la subasta est&aacute; pr&oacute;xima</p>
      <p>Desde el momento en que se haya <strong>completado la barra</strong> s&oacute;lo tendr&aacute;s <strong>60 minutos</strong> para hacer tus apuestas... Adem&aacute;s cada apuesta te costar&aacute; <strong>4 snoits por puja</strong>, as&iacute; que la lucha ser&aacute; dura por hacerse con el premio!!</p>
      <p>Vamos a ver quien es el verdadero <strong>estratega</strong> !! :D</p>
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
        System.out.println("Error en how31.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>