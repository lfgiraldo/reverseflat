<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Snoits | be smart :)</title>
	<%@ include file="cssSelector.jsp" %>
    <link href="js/formcheck/theme/classic/formcheck.css" rel="stylesheet" type="text/css" />    
</head>


<body bgcolor="#FFB206" style="background-color:#FFB206;">
<div id="loading" style="visibility:hidden">
      <div class="loading-indicator"> <img src="img/loading.gif" alt=""
                         style="margin-right:8px;float:left;vertical-align:top;"/>Snoits<br/>
        <span id="loading-msg">Transacci&oacute;n en proceso...</span></div>
    </div>

<div id="pop">

  <h1>COMPRA DE CR&Eacute;DITOS</h1>
    <h2>CON TARJETA DE CR&Eacute;DITO</h2>
      <form action="PayPalArea?option=creditCard" method="post" id="buyForm">
	<label>Tipo de Tarjeta:</label>
	<select name="creditCardType" id="creditCardType" class="validate['required']" >
	  <option>Seleccione una opci&oacute;n</option>
	  <option value="Visa">Visa</option>
	  <option value="MasterCard">Master Card</option>
	  <option value="Discover">Discover</option>
	  <option value="Amex">American Express</option>
    </select>
  	
	<label>N&uacute;m. de Tarjeta:</label>
        <input disabled="disabled" name="creditCardNumber" type="text" class="textm validate['required','length[16,16]']" id="creditCardNumber" value="4842839479139227">
        
        <label>Fecha de vencimiento:</label>
		<input disabled="disabled" name="expdateMonth" type="text" class="texts validate['required','digit[1,12]']" id="expdateMonth" style="display:inline" value="1">
        <input disabled="disabled" name="expdateYear" type="text" class="texts validate['required','digit[2009,-1]']" id="expdateYear" style="display:inline" value="2019">
        
        
        <label>N&uacute;m. de verificaci&oacute;n:</label>
        <input disabled="disabled" name="cvv2Number" type="text" class="texts validate['required']" id="cvv2Number">
        
        <label>Moneda:</label>
        <select disabled="disabled" name="currency" id="currency" class="validate['required']">
          <option>Seleccione un tipo de moneda</option>
          <option value="EUR">Euro</option>
        </select>

        
        <label>Precio:</label>
        <select disabled="disabled" name="comboChips" id="comboChips" class="validate['required']" >
          <option>Seleccione el paquete de fichas</option>
          <option value="1">40 fichas por 10 Euros</option>
          <option value="2">60 fichas por 15 Euros</option>
          <option value="3">80 fichas por 20 Euros</option>
          <option value="4">100 fichas por 25 Euros</option>
        	
        </select>
        <input type="hidden" name="idAuction" id="idAuction" value="<%=request.getParameter("idAuction")%>" style="visibility:hidden">
        <input type="hidden" name="type" id="type" value="<%=request.getParameter("type")%>" style="visibility:hidden">
        <input disabled="disabled" type="submit" name="Comprar" value="Comprar" class="button" />
      </form>
    <div id="pop_line">
    	    <h2>CON PAYPAL</h2>
	    <center><img src="img/paypal.gif"></center>
        <p style="font-size:9px; color:white;">Durante la fase beta no podr&aacute;s conseguir m&aacute;s cr&eacute;dito. A ver qui&eacute;n es el mejor estratega !! :)</p>
    </div>

    
</div>


</body>
</html>