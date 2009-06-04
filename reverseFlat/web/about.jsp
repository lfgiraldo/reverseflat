<%
    try{

%>

    <%-- IMPRESCINDIBLE INCLUIR commonVariables.jsp EN LAS PÁGINAS QUE INCLUYAN publicHeader.jsp --%>
    <%@ include file="commonVariables.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;  charset=iso-8859-1" />
    <title>Snoits | be smart :)</title>
	<%@ include file="cssSelector.jsp" %>
    <link href="js/formcheck/theme/classic/formcheck.css" rel="stylesheet" type="text/css" />
    <link href="css/SqueezeBox.css" rel="stylesheet" type="text/css" />
    <link rel="icon" type="image/png" href="img/favicon.png" />
</head>

<body>

<a style="background-color:rgb(34, 34, 34);" id="fdbk_tab" href="feedback.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:470}}" title="Feedback">FEEDBACK</a>
<!-------------------------------------------HEADER-------------------------------------------> 

	<!-------------------------------------------SCRIPTS-------------------------------------->
	<%@ include file="commonScripts.jsp" %>
    
    <%@ include file="publicHeader.jsp" %>

<!-------------------------------------------CIERRES-------------------------------------------> 
	<%@ include file="publicClosing.jsp" %>

<!-------------------------------------------SUBMENU-------------------------------------------> 
	<%@ include file="publicSubmenu.jsp" %>

<!-------------------------------------------CUERPO / COLUMNA-------------------------------------------> 	 
<div id="cuerpo">

<%@ include file="itemsColum.jsp" %>

<!-------------------------------------------CUERPO / GAME -------------------------------------------> 
<span id="game">
    <div id="gameHead">
	<img src="img/game_c_ul.gif" class="c_l" />
	<img src="img/game_c_ur.gif" class="c_r" />
    </div>
    <div id="gameBody">	
      <h1>Sobre nosotros    </h1>
      <div id="game_line">&nbsp;</div></br>
      <p> <strong>Snoits.com</strong> es el portal web de subastas mas innovador, transparente y dinamico del mercado, ofrece a sus usuarios  participar en una competici&oacute;n justa donde adquirir productos nuevos a precios  realmente bajos es el objetivo. El sistema ofrece una experiencia de diversi&oacute;n  en un entorno competitivo donde la habilidad y la estrategia de cada usuario  determina su propio resultado. </p>
      <p>De esta manera <strong>Snoits </strong>se presenta con mejoras  sustanciales respecto a otras empresas del sector; el tiempo, la estrategia y  el uso de informacion convierten la componente de azar en una cuestion de  habilidad y decisi&oacute;n. Por otra parte,<strong> el coste de cada ficha es el mas bajo del  mercado</strong> y adem&aacute;s se generan 4 oportunidades de compra a precios muy bajos por  cada subasta, por lo que <strong>Snoits</strong> se convierte en un referente del sector en  cuanto estrategia, competitividad, precios bajos y generaci&oacute;n de oportunidades.  </p>
        <p> El equipo <strong>Snoits</strong> lo  configuran professionales especializados en diferentes areas, enfocados a la  eficiencia del sistema y la satisfacci&oacute;n del consumidor. </p>
        <p> No queremos desearos suerte,  sino que decidais bien vuestra estrategia para conseguir vuestro objetivo. <br />
      El equipo <strong>Snoits</strong> os da la  bienvenida y espera que disfruteis de la experiencia <strong>Snoits</strong>. Para cualquier  sugerencia o propuesta no dudeis en contactar con nosotros.</p>
      <div id="game_line">&nbsp;</div>
      <h2>El equipo de <strong>Snoits.com</strong></h2>
      <br />
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
        System.out.println("Error en about.jsp \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>