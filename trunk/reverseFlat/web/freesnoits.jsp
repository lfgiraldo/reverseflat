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

<%@ include file="publicClosing.jsp" %>
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
            	<img src="img/freesnoits.gif" style="margin-top:40px;margin-left:auto; margin-right:auto;" />
            </div>
        
            <h1>Consigue free-snoits !</h1>

			<p><strong>Snoits</strong> te regala constantemente fichas gratis (<em>free-snoits</em>). Se contabilizar&aacute;n en tu cuenta de usuario y podrás gastarlas en todas las subastas estratégicas gratuitas. Para comenzar a utilizar tus <em>free-snoits</em> sólo tienes que estar dado de alta y haber participado ya en alguna subasta activa.</p>
			
            <p>&nbsp;</p>

			<h2 style="text-align:left;">&nbsp;&nbsp;¿C&oacute;mo conseguir free-snoits totalmente gratis?</h2>
          
			<p>S&oacute;lo por Registrarte te regalamos 40 free-snoits</p>
			<p>Adem&aacute;s, tambi&eacute;n te regalamos free-snoits por cualquier compra que realices !!</p><p>&nbsp;</p>
            
			<div id="game_line"><p>&nbsp;</p></div>           
            
            <p>&nbsp;</p>
            
			<h2 style="text-align:left;">&nbsp;&nbsp;Tambi&eacute;n te regalamos free-snoits cuando haces alguna compra</h2>
			<p style="width:400px;text-align:right;">
			  <label style="display:inline; font-weight:normal; padding-right: 47px; width:300px;">30 snoits + 10 free-snoits</label><strong>9,90€</strong></p>
          
			<p style="width:400px;text-align:right;">
			  <label style="text-align:left;display:inline; font-weight:normal; padding-right: 40px; width:300px;">70 snoits + 20 free-snoits</label><strong>19,90€</strong></p>
          
			<p style="width:400px;text-align:right;">
			  <label style="text-align:left;display:inline; font-weight:normal; padding-right: 40px; width:300px;">160 snoits + 40 free-snoits</label><strong>39,90€</strong></p>
          
			<p style="width:400px;text-align:right;">
			  <label style="text-align:left;display:inline; font-weight:normal; padding-right: 40px; width:300px;">500 snoits + 120 free-snoits</label><strong>99,90€</strong></p>
          
          <p style="font-size:14px;"><strong>Aprov&eacute;chate!! Tenemos el precio por puja m&aacute;s barato en Internet !!</strong></p></div>
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

<%
    }catch(Exception ex){
        System.out.println("Error en freesnoits.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>