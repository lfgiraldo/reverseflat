<%@page import="com.snoofing.gwt.client.common.valueobjects.*" %>
<%
	try {
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Snoits | be smart :)</title>
    <link href="css/css.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="css/prettyForms/prettyForms.js"></script>
    <link href="css/prettyForms/prettyForms.css" rel="stylesheet" type="text/css" />
    
</head>
	

<body onload="prettyForms()">
<div id="header">

<div id="logo"><img src="images/snoits_logo.gif" width="165" height="80" /></div>
  <div id="menu">
    <div id="menu_h1">
      <div class="menu_unit"><a href="#" class="header">Inicio</a></div>
      <div class="menu_unit"><a href="#" class="header">Últimos artículos</a></div>
      <div class="menu_unit"><a href="#" class="header">Browser</a></div>
      <div class="menu_unit"><a href="#" class="header">Pr&oacute;ximos cierres</a></div>
      <div class="menu_unit"><a href="#" class="header">THE CLUB.</a></div>
    </div>
  </div>
</div>

<div id="cuerpo">
<form action="" enctype="multipart/form-data" id="myForm"	 onsubmit="return(showFormData(this))">
		<p>

			<label>textarea: </label><textarea name="textarea" cols="60" rows="10">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</textarea>
			<br class="clearAll" /><br />
		</p>
	
		<p>
			<label><strong>text box: </strong></label><input name="textbox" type="text" onfocus="doSomething()" value="Lorem ipsum" />
			<br class="clearAll" /><br />
		</p>

	
		<p>
			<label><strong>select box:</strong> </label>
			<select name="selectMenu" onchange="doSomething()">
				<option value="1">row 1 row 1 row 1 row 1</option>
				<option value="2">row 2 row 2 row 2 row 2</option>
				<option value="3" selected="selected">row 3 row 3 row 3 row 3</option>

				<option value="4">row 4 row 4 row 4 row 4</option>
			</select>
			<br class="clearAll" /><br />
		</p>
		
		<p>
			<label>checkboxes: </label>
			<input type="checkbox" name="checkbox1" /><label>check 1</label>

			<input type="checkbox" name="checkbox2" onchange="doSomething()" /><label><strong>check 2</strong></label>
			<input type="checkbox" name="checkbox3" /><label>check 3</label>
			<br class="clearAll" /><br />
		</p>
		
		<p>
			<label>radio buttons: </label>
			<input type="radio" name="radioButtons" value="1" /><label>radio 1</label>

			<input type="radio" name="radioButtons" value="2" /><label>radio 2</label>
			<input type="radio" name="radioButtons" value="3" checked="checked" onchange="doSomething()" /><label><strong>radio 3</strong></label>
			<br class="clearAll" />
			<label>
			<input type="file" name="fileField" id="fileField" />
			</label>
			<br />
		</p>
		
		
		<p><input type="submit" value="check inputs" /></p>
		
  </form>


</div>
</body>
</html>

<%
    }catch(Exception ex){
        System.out.println("Error en updateAuction.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>