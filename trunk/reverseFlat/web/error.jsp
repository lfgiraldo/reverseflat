    <%-- IMPRESCINDIBLE INCLUIR commonVariables.jsp EN LAS PÁGINAS QUE INCLUYAN publicHeader.jsp --%>
    <%@ include file="commonVariables.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>Snoits | be smart :)</title>
    <%@ include file="metatags.jsp" %>
    <%@ include file="cssSelector.jsp" %>
    <link href="js/formcheck/theme/classic/formcheck.css" rel="stylesheet" type="text/css" />
    <link href="css/SqueezeBox.css" rel="stylesheet" type="text/css" />
    <link rel="icon" type="image/png" href="img/favicon.png" />
</head>

<body>
<a style="background-color:rgb(34, 34, 34);" id="fdbk_tab" href="feedback.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:470}}" title="Feedback">FEEDBACK</a>
<!-------------------------------------------HEADER-------------------------------------------> 
	<%@ include file="commonScripts.jsp" %>
    <%@ include file="publicHeader.jsp" %>
<%-- NO BORRAR LOS DIVs VACÍOS. Son necesarios para que no salga error con mootools al incluir CommonScripts  --%>
<%-- Es para evitar posibles errores con la Base de datos cuando se ha generado un error --%>
<div id="cierres">
	<div id="cierres_tit"></div>
	<div id="SlideItMoo_info_outer">
		<div id="SlideItMoo_info_inner">
			<div id="SlideItMoo_info_items">
            </div>  <!-- SlideItMoo_info_items -->
        </div> <!-- SlideItMoo_info_inner -->
    </div>	<!-- SlideItMoo_info_outter -->
</div> <!-- Cierres -->

<!-------------------------------------------SUBMENU-------------------------------------------> 
<%@ include file="publicSubmenu.jsp" %>
<!-------------------------------------------CUERPO / COLUMNA-------------------------------------------> 	 

<div id="cuerpo">

    <div id="center" style="margin:auto; width:650px;">
    <!-------------------------------------------CUERPO / GAME -------------------------------------------> 
    
    <span id="game">
        <div id="gameHead">
        <img src="img/game_c_ul.gif" class="c_l" />
        <img src="img/game_c_ur.gif" class="c_r" />
        </div>
        <div id="gameBody">
            <div id="art_bg" >
            <img src="img/dialog-error.gif" width="48" height="48" style="margin-top:40px;margin-left:auto; margin-right:auto;" />
          </div>
        
            <h1>&nbsp;</h1>
            <h1>Lamentamos comunicarte <br />
            que se ha producido un error inesperado</h1>
            <p><br/>
        </p>
      <div id="game_line">&nbsp;</div><br /><br />
            <p align="center">Utiliza la pesta&ntilde;a que se encuentra en el margen derecho para explicarnos qu&eacute; te ha ocurrido.</p>
            </br>
          <div id="game_line">&nbsp;</div>
        </div>
      <div id="gameKicker">
        <img src="img/game_c_ll.gif" class="c_l" />
        <img src="img/game_c_lr.gif" class="c_r" />
        </div>
    </span>
    </div>
    <!------------------------------------------- KICKER ------------------------------------------->
            <%@ include file="publicKicker.jsp" %>
    </div>
</div>


</body>
</html>

