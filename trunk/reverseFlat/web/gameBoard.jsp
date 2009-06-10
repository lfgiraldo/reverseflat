<%@page import="com.reverseFlat.ejb.persistence.*, java.util.*, java.text.NumberFormat, java.text.DecimalFormat" %>
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
	<%@ include file="metatags.jsp" %>
	<%@ include file="cssSelector.jsp" %>
    <link href="js/formcheck/theme/classic/formcheck.css" rel="stylesheet" type="text/css" />
    <link href="css/SqueezeBox.css" rel="stylesheet" type="text/css" />
    <link rel="icon" type="image/png" href="img/favicon.png" />

<!---------------------------------------SCRIPTS------------------------------------------>
	<script type="text/javascript" src="js/formcheck/lang/<%=(String)request.getSession().getAttribute("locale")%>.js"></script>
	<script type="text/javascript" src="js/mootools-1.2.1-core-yc.js"></script>
    <script type="text/javascript" src="js/mootools-1.2-more.js"></script>
	<script type="text/javascript" src="js/SqueezeBox.js"></script>
	<script type="text/javascript" src="js/formcheck.js"></script>
    <script type="text/javascript" src="js/sexyalertbox.v1.2.moo.mini.js"></script>
    <script type="text/javascript" src="js/slideitmoo-1.1.js"></script>
    <script type="text/javascript" src="js/mootools-1.2.2.1-more.slide.js"></script>
    <script type="text/javascript" src="js/mootools-1.2.2.1-more.tips.js"></script>

    <script>
        function getXMLObject()  //XML OBJECT
        {
           var xmlHttp = false;
           try {
             xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
           }
           catch (e) {
             try {
               xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
             }
             catch (e2) {
               xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
             }
           }
           if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
             xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
           }
           return xmlHttp;  // Mandatory Statement returning the ajax object created
        }

        var xmlhttp = new getXMLObject();	//xmlhttp holds the ajax object

        function ajaxFunction() {
          var getdate = new Date();  //Used to prevent caching during ajax call
          if(xmlhttp) {
            xmlhttp.open("POST","RestrictedArea",true); //gettime will be the servlet name
            xmlhttp.onreadystatechange  = handleServerResponse;
            xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            var singleBid = document.getElementById("singleBid").value;
            var idAuction = document.getElementById("idAuction").value;
            var type = "active";
            var box = document.getElementById("progressSave");
            box.innerHTML = "Saving...";
            xmlhttp.send("option=singleBid&singleBid="+singleBid+"&idAuction="+idAuction+"&type=active");
            box.fancyShow();
          }
          return false;
        }

        function handleServerResponse() {
           if (xmlhttp.readyState == 4) {
             if(xmlhttp.status == 200) {
               //document.myForm.time.value=xmlhttp.responseText; //Update the HTML Form element

               var newInfo = JSON.decode(xmlhttp.responseText);

               document.getElementById("uniqueBids").innerHTML = newInfo.uniqueBids;
               document.getElementById("lastBid").innerHTML = newInfo.lastBid;
               document.getElementById("bidCost").innerHTML = newInfo.bidCost;
               var box = document.getElementById("progressSave");
               box.innerHTML = "Saved!";
               box.fancyHide()
             }
             else {
                alert("Error during AJAX call. Please try again");
             }
           }
        }
    </script>
</head>

<body>
<a style="background-color:rgb(34, 34, 34);" id="fdbk_tab" href="feedback.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:470}}" title="Feedback">FEEDBACK</a>


<!-------------------------------------------HEADER-------------------------------------------> 


    <link rel="stylesheet" type="text/css" media="all" href="css/sexyalertbox.css"/>
    <%-- ESTA VALIDACIÓN PERTENECE ÚNICAMENTE A ESTA PÁGINA --%>
    <%@ include file="bidsValidation.jsp" %>    
    <%@ include file="publicHeader.jsp" %>
    
<!-------------------------------------------ACTUAL-------------------------------------------> 
<%@ include file="publicClosing.jsp" %>

<!-------------------------------------------SUBMENU------------------------------------------> 
<%@ include file="publicSubmenu.jsp" %>

<!-------------------------------------------CUERPO / COLUMNA-------------------------------------------> 	 
<%
    List<Auction> auctionsList = (List<Auction>) request.getSession().getAttribute("activeAuctions");
%>
<div id="cuerpo">


<!-------------------------------------------CUERPO / GAME -------------------------------------------> 
<%
    List<Auctiontexttranslation> translatedTexts = lastAuction.getAuctiontexttranslationCollection();
    Auctiontexttranslation currentText = new Auctiontexttranslation();

    for(Auctiontexttranslation text : translatedTexts){
        if (text.getLanguageCode().equalsIgnoreCase(userLocale)){
            currentText = text;
            break;
        }
    }
    HashMap winnerBids = (HashMap)request.getSession().getAttribute("winnerBids");
    Bid currentWinnerBid = (Bid) winnerBids.get(lastAuction.getIdAuction());
    int productCost = 5;
    if(currentWinnerBid != null){
        float winner = currentWinnerBid.getBidAmount();
        productCost = Math.round(winner/5);
        productCost = (productCost + 1) * 5;
    }
%>
<span id="game">
    <div id="gameHead">
	<img src="img/game_c_ul.gif" class="c_l">
	<img src="img/game_c_ur.gif" class="c_r">
    </div>
    <div id="gameBody">
        <div id="art_bg">
		<img src="<%=lastAuction.getPictureFileName()%>">
        </div>
	
        <h1><%=currentText.getTitle()%></h1>
        
        <p><%=currentText.getShortDescription()%></p>
        
        <h2><%=lastAuction.getActive()? "<img src='img/sub_abierta.gif' alt='SUBASTA ABIERTA' title='Subasta abierta'>":"<img src='img/sub_cerrada.gif' alt='SUBASTA CERRADA' title='Subasta cerrada'>"%>
		<%="free".equalsIgnoreCase(lastAuction.getType())? "<img src='img/minifree.gif' alt='subasta gratuita' title='subasta free'>":""%></h2>
	<div id="game_line">&nbsp;</div>
        
        <div class="twocols">
        	<strong>PVP</strong><br /><%=Math.round(lastAuction.getCogs())%>&euro;
        </div>
        <div class="twocols">
	        <strong>Puja ganadora actual </strong><br />menor a <%=productCost%>&euro;
        </div>
        
      <div id="game_line"></div>
        
        <img src="img/maquina.gif" width="603" height="170">
        
        <div id="game_line">&nbsp;</div>
        
        <div id="game_form">
            <div id="game_form_cell">
                <label>Puja Individual</label>
                <form action="RestrictedArea?option=singleBid&type=<%=request.getAttribute("type")%>" method="POST" id="singleForm" onsubmit="return ajaxFunction();">
                	<input name="idAuction" type="hidden" value="<%=lastAuction.getIdAuction()%>">
                    <input name="singleBid" id="singleBid" type="text" class="texts validate['required','number','~validateSingle']" <%=lastAuction.getActive()==true? "":"disabled='disabled'"%> > <br />
                    <input type="button" value="Pujar" class="button" <%=lastAuction.getActive()==true?"":"disabled='disabled'"%> onclick="return ajaxFunction();" >
                </form>
            </div>
            <div id="game_form_cell">
                <label>Puja por rango</label>
                <form action="RestrictedArea?option=rangeBid&type=<%=request.getAttribute("type")%>" method="POST" id="multipleForm">
                	<input name="idAuction" id="idAuction" type="hidden" value="<%=lastAuction.getIdAuction()%>">
                    Desde:
                    <input name="bidFrom" type="text" class="texts validate['required','number','~validateRange']" <%=lastAuction.getActive()? "":"disabled='disabled'"%> > <br />
                    Hasta:&nbsp;
                    <input name="bidTo" type="text" class="texts validate['required','number','~validateRange']" <%=lastAuction.getActive()? "":"disabled='disabled'"%> > <br />
                    <input type="submit" value="Pujar" class="button" <%=lastAuction.getActive()? "":"disabled='disabled'"%> >
                </form>
            </div>
            <div id="game_form_cell">
                <label>Coste Actual de la puja</label>
                <p class="bid_cost"><span id="bidCost"><%=request.getSession().getAttribute("bidCost")%></span> <%=Integer.parseInt(""+request.getSession().getAttribute("bidCost"))==1?"snoit":"snoits"%> </p>
                <div id="progressSave"></div>
		&nbsp;
            </div>
            <div id="game_form_cell" class="help_box">
                <label>Informaci&oacute;n adicional</label>
                <span class="info_box">
                    <%
                        if ( lastBid == null){
                            out.print(messages.getString("NoBidsException"));
                        }else if (winnerBid == null){
                            out.print(messages.getString("NoWinnerException"));
                        }else {
                            float last = lastBid.getBidAmount();
                            float winner = winnerBid.getBidAmount();
                            if ( last > winner){
                                out.print(messages.getString("Your_last_bid_is_above_the_winner_bid"));
                            }else if(last < winner){
                                out.print(messages.getString("Your_last_bid_is_below_the_winner_bid"));
                            }else if (last == winner){
                                out.print(messages.getString("Your_last_bid_is_the_winner_bid"));
                            }
                        }
                    %>
                </span>
            </div>
	    </div>

        <div id="game_line">&nbsp;</div>
        
        <div id="game_form">

        <div id="game_form_sub">
		<img src="img/winner.gif" alt="Ganador Actual" class="tipz" title="Gandor Actual :: Usuario con la puja <br/>única de menor valor.<br/>Es el usuario ganador <br/>por el momento."  />
		<p id="winner">
        	
            <%

                if (winnerBid == null){
                    out.print(messages.getString("NoWinnerException"));
                }else{
                    out.print("<strong>Ganador actual:</strong><br />" + winnerBid.getUseridUser().getName()+"<br/><br/>");
                    List<Pricebyauction> prices = lastAuction.getPricebyauctionCollection();
                    int tramo = 0;
                    String[] ordinal = {"Primer", "Segundo", "Tercer"};
                    if(prices != null){
                        for(Pricebyauction price : prices){
                            String name = price.getUseridUser()==null?"":price.getUseridUser().getName();
                            if (!"".equals(name)){
                                out.print("<strong>"+ordinal[tramo]+" premio</strong><br/>"+name+"<br/>");
                            }
                            tramo++;
                        }
                    }

                }
    
            %></p>
            </div>
	    <div id="game_form_sub">
		<img src="img/unibids.gif" alt="Tus Pujas" class="tipz" title="Tus Pujas :: Todas las pujas que has <br/>realizado hasta el <br/>momento en este <br/>artículo."  />
		<p id="yourBids">
            <%
                if (yourBids == null){
                    out.print(messages.getString("NoBidsException"));
                }else{
					out.print("<strong>Tus pujas:</strong><br />");
                    for (Bid bid : yourBids){
                        out.print(bid.getBidAmount()+" ");
                    }
                }

            %>
        </p>
            </div>
	    <div id="game_form_sub">
		<img src="img/lastbid.gif" alt="Tu &uacute;ltima Puja" class="tipz" title="Tu &uacute;ltima Puja :: &Uacute;ltima puja con la <br/>que has jugado y <br/>sobre la cual se muestra <br/>la ayuda."  />
        
		<p>
                <strong>&Uacute;ltima puja:</strong><br />
                <span id="lastBid">
                <%
                    if(lastBid == null){
                        out.print(messages.getString("NoBidsException"));
                    }else{
                        NumberFormat formatter = new DecimalFormat("#0.0");
                        if(lastExpenditure.getBidFrom() == lastExpenditure.getBidTo().floatValue()){
                            out.print(formatter.format(lastExpenditure.getBidFrom())+" ");
                        }else{
                            out.print("[ "+formatter.format(lastExpenditure.getBidFrom())+" - "+formatter.format(lastExpenditure.getBidTo())+" ]");
                        }
                    }
                %></span></p>
            </div>
            <div id="game_form_sub">
                <img src="img/winner.gif" alt="Tus Pujas &Uacute;nicas" class="tipz" title="Tus Pujas &Uacute;nicas :: Pujas que has realizado <br/> solo t&uacute; y que pueden <br/>hacerte ganador"  />
                
                <p>
                    <strong>Pujas &Uacute;nicas: </strong> <br />
                    <span id="uniqueBids">
                    <%=uniqueBids %>
                    </span>
                </p>
            </div>
        </div>
        
        
        <div id="game_line">&nbsp;</div>
        
        <div class="prod_img">
            <img src="<%=lastAuction.getMosaicFileName()%>" width="373" height="75" class="none"/>
        </div>
        <a id="v_toggle" href="#" name="v_toggle"><span id="vertical_status">[+] M&aacute;s informaci&oacute;n</span></a>
        <div id="vertical_slide"><p><%=currentText.getLongDescription()%></p></div>
        
		</br>
    </div>
    <div id="gameKicker">
	<img src="img/game_c_ll.gif" class="c_l">
	<img src="img/game_c_lr.gif" class="c_r">
    </div>
</span>

<!-------------------------------------------SUBMENU------------------------------------------> 
<%@ include file="publicKicker.jsp" %>
</div>


</body>
</html>

<%
    }catch(Exception ex){
        System.out.println("Error en GameBoard.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>