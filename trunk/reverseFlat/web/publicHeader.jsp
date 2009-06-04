<div id="header">
<%
    if (user!= null){
        
%>
<div id="zuser">
    	<div id="zuser_flag"><%-- <img src="img/bandera_FR.gif" width="16" height="12" />--%></div>
    	<div id="zuser_name"><strong>Hola <%=user.getName()%></strong> !</div>
    	<div id="zuser_info"><%=enrolledAuctions == null? "0": enrolledAuctions.size()%> subastas activas<br><%=user.getChipsBalance()%> snoits  - <%=user.getFreeChipsBalance()%> free snoits</div>
    	<div id="buy_link"><a href="buy.jsp?idAuction=<%=lastAuction.getIdAuction()%>&type=<%=request.getParameter("type")%>"" class="boxed" rel="{handler:'iframe',size:{x:410,y:470}}" title="Comprar">COMPRAR</a> | <a href="RestrictedArea?option=logout" class="header" title="Salir">Salir</a></div>
    </div>
<%
    }else{
%>
<div id="login">
  <p style="margin: 0px 0px 10px; text-align: center; line-height: 14px; display: block;">
	<%--
	<a href="facebook"><a href="register.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:460}}" title="Registro">
		<img src="img/Connect_dark_small_short.gif" alt="Connect With Facebook" width="16" height="16" border="0" style="position: relative; top: 3px;"/>
	</a> &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
	--%>
    <a href="register.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:460}}" title="Registro">Registro</a>
  </p>
  <form action="PublicArea" method="post" id="myForm">
    <label>Login:</label>
    <input name="userName" type="text" class="textm validate['required']" id="userName" value="Usuario" onfocus="this.value=''" />
    <input name="password" type="password" class="textm validate['required']" value="Contraseña" onfocus="this.value=''" />
    <input name="option" type="hidden" value="login" />
    <input name="locale" type="hidden" id="locale" value="" />
    <center>
      <input type="submit" value="Enviar" id="loginButton" />
    </center>
  </form>
</div>
<%
    }
%>
	<div id="menu">
    	<a href="Public">Inicio</a>
<%
    if (user!= null){

%>
        <a href="RestrictedArea?option=enrolled&type=enrolledAuctions">Tus Subastas</a>
		<a href="PublicArea?option=active&type=<%=request.getAttribute("type")%>">Subastas Abiertas</a>
		<a href="PublicArea?option=closed&type=<%=request.getAttribute("type")%>">Subastas Cerradas</a>
<%
    }else{
%>
        <a href="PublicArea?option=active">Subastas Abiertas</a>
        <a href="PublicArea?option=closed">Subastas Cerradas</a>
		<%-- <a href="#">Browse</a> --%>
		<%-- <a href="#">TheClub.</a> --%>
<%
     }
%>
     </div>
</div>