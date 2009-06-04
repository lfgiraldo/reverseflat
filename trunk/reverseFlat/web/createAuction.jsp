<%@page import="com.snoofing.gwt.client.common.valueobjects.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Snoits | be smart :)</title>
    <link href="css/css2.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="css/prettyForms/prettyForms.js"></script>
    <link href="css/prettyForms/prettyForms.css" rel="stylesheet" type="text/css" />
    <script src='js/tiny_mce/tiny_mce_gzip.js' type='text/javascript'></script>
    <script type="text/javascript">

	// This is where the compressor will load all components, include all components used on the page here
		tinyMCE_GZ.init({
			plugins : 'style,layer,table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras',
			themes : 'advanced',
			languages : 'en',
			disk_cache : true,
			debug : false
		});
	
	</script>
	<script language="javascript" type="text/javascript">
        tinyMCE.init({
            theme : "advanced",
            mode: "textareas",
            theme_advanced_toolbar_location : "top",
            theme_advanced_buttons1 : "bold,italic,underline,|,justifyleft,justifycenter,justifyright,justifyfull,|,outdent,indent,|,undo,redo,|,bullist,numlist,|",
            theme_advanced_buttons2 : "",
            theme_advanced_buttons3 : ""
        });

    </script>
    
</head>
	

<body onload="prettyForms()">
<div id="header">

<div id="logo"></div>
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
<form method="post" action="createAuctionServlet" enctype="multipart/form-data" id="myForm" onsubmit="">
		
        <p>
	    <label><strong>Title(es): </strong></label>
			<input name="title_es" type="text" id="title_es" size="50" />
            <br class="clearAll" />
        </p>
        <p>
	    <label><strong>Title(en): </strong></label>
			<input name="title_en" type="text" id="title_en" size="50" />
            <br class="clearAll" />
        </p>
        <p>
            <label>Product Picture: </label>
            <input type="file" name="productPic" id="productPic" />
			<br class="clearAll" />
    </p>
      <p>
            <label>Circled Picture: </label>
            <input type="file" name="circledPic" id="circledPic" />
            <br class="clearAll" />
    </p>
      <p>
            <label><strong>Collage Picture: </strong></label>
            <input type="file" name="collagePic" id="collagePic" />
            <br class="clearAll" />
    </p>
			<br />
<p>
		  	<label>Short Description (es):</label>
       	  <textarea name="shortDescription_es" cols="60" rows="10" id="shortDescription_es"></textarea>
			<br class="clearAll" />
    </p>
    <p>
		  	<label>Short Description (en):</label>
          	<textarea name="shortDescription_en" cols="60" rows="10" id="shortDescription_en"></textarea>
			<br class="clearAll" />
    </p>
            <br />
<p>
            <label>Long Description(es): </label>
            <textarea name="longDescription_es" cols="60" rows="10" id="longDescription_es"></textarea>
			<br class="clearAll" />
    </p>
    <p>
            <label>Long Description(en): </label>
            <textarea name="longDescription_en" cols="60" rows="10" id="longDescription_en"></textarea>
			<br class="clearAll" />
    </p>
            <br/>
      <p>
            <label><strong>COGS: </strong></label>
			<input name="cogs" type="text" id="cogs" />
            <br class="clearAll" />
    </p>
      <p>
            <label><strong>Gross Margin: </strong></label>
			<input name="grossMargin" type="text" id="grossMargin" />
            <br class="clearAll" />
    </p>
      <p>
            <label><strong>Chip Cost: </strong></label>
			<input name="chipCost" type="text" id="chipCost" value="0.25" />
            <br class="clearAll" />
    </p>
      <p>
            <label><strong>Necessary Bids: </strong></label>
			<input name="minBids" type="text" id="minBids" />
            <br class="clearAll" />
    </p>
        <p>
        <label><strong>Time After Close: </strong></label>
			<input name="timeQ" type="text" id="timeQ" />
            <br class="clearAll" />
        </p>
        <p>
			<label>Activate Auction: </label>
			<input type="checkbox" name="activate" />
			<br class="clearAll" />
        </p>
        <p>
            <label><strong>Type:</strong> </label>
			<select name="type" id="type">
			  	<option value="normal" selected="selected">Normal</option>
				<option value="club">Club</option>
		  	  <option value="free">Free</option>
		  </select>
        </p>
        <br />
  <p>
            <label><strong>Category:</strong> </label>
			<select name="category" id="category">
	  	    <option value="1" selected="selected">Category 1</option>
				<option value="2">Category 2</option>
			  	<option value="3">Category 3</option>
			</select>

	</p>
		<p><br class="clearAll" />
			<br />
		</p>
		
		
		<p><input type="submit" value="check inputs" /></p>
		
  </form>


</div>
</body>
</html>

