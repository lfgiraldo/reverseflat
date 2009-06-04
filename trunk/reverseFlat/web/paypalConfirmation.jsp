<% 
	try {
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Snoits | be smart :)</title>
	<%@ include file="cssSelector.jsp" %>
</head>


<body>


<div id="pop">
  <h1>COMPRA DE CR&Eacute;DITOS</h1>
    <h2>CON TARJETA DE CR&Eacute;DITO</h2>
    <h2><%=request.getAttribute("message")%></h2>
    <br/>
    <div align="center"><a href="RestrictedArea?option=refresh&idAuction=<%=request.getParameter("idAuction")%>&type=<%=request.getParameter("type")%>" class="button" target="_parent" onclick="self.close()">Aceptar</a></div>
</div>

<script type="text/javascript" src="js/ga.js"></script> 

</body>
</html>

<%
    }catch(Exception ex){
        System.out.println("Error en paypalConfirmation.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>