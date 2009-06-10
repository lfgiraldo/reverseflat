<%@page import="com.reverseFlat.ejb.persistence.*, java.util.*, java.math.BigDecimal" %>
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
</head>

<body>
<a style="background-color:rgb(34, 34, 34);" id="fdbk_tab" href="feedback.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:470}}" title="Feedback">FEEDBACK</a>

<!-------------------------------------------HEADER-------------------------------------------> 
	<%@ include file="commonScripts.jsp" %>
    <script>
        window.addEvent('domready', function(){
            var myVerticalSlide = new Fx.Slide('vertical_slide');
            var status = {
                'true': '[+] M&aacute;s Informaci&oacute;n',
                'false': '[-] Menos Informaci&oacute;n'
            };

            $('v_toggle').addEvent('click', function(e){
                    e.stop();
                    myVerticalSlide.toggle();
                    $('vertical_status').set('html', status[myVerticalSlide.open]);
                });
        });
    </script>
    <%@ include file="publicHeader.jsp" %>

<!-------------------------------------------CIERRES-------------------------------------------> 
	<%@ include file="publicClosing.jsp" %>

<!-------------------------------------------SUBMENU------------------------------------------> 
	<%@ include file="publicSubmenu.jsp" %>

<!-------------------------------------------CUERPO / COLUMNA-------------------------------------------> 	 
<div id="cuerpo">
<span class="column">
<div class="column_unit">
        <div class="column_h1"><%=request.getAttribute("lateralTitle")%></div>
</div>

  <%
    List<Auction> auctionsList = (List<Auction>) request.getSession().getAttribute("activeAuctions");

if ( auctionsList != null)
{
    for(Auction currentAuction : auctionsList ){
        List<Auctiontexttranslation> translatedTexts = currentAuction.getAuctiontexttranslationCollection();
        Auctiontexttranslation currentText = new Auctiontexttranslation();
        
        for(Auctiontexttranslation text : translatedTexts){
            if (text.getLanguageCode().equalsIgnoreCase(userLocale)){
                currentText = text;
                break;
            }
        }
        List<Bid> bids = (List<Bid>)currentAuction.getBidCollection();
        float percentage = 0;
        if ( bids != null){
            int minBids = currentAuction.getMinBids();
            int widthBar = 224;
            int totalBids = bids.size()*2;
            percentage = (float)totalBids/minBids;
            percentage = widthBar * percentage;
        }
%>
        <div class="column_unit">
            <a href="PublicArea?option=info&idAuction=<%=currentAuction.getIdAuction()%>">
                <img src="<%=currentAuction.getCircledFileName()%>" width="74" height="74" />
            </a>
                <div class="column_cell">
                <a href="PublicArea?option=info&idAuction=<%=currentAuction.getIdAuction()%>"><%=currentText.getTitle()%></a>
                <p><%=currentText.getShortDescription()%></p>
					<%------------TIMER & PROGRESS BAR --------%>
                    <%@ include file="progressBarAndTimer.jsp" %>
                <div class="column_puja">
<%
                    HashMap winnerBids = (HashMap)request.getSession().getAttribute("winnerBids");
                    Bid currentWinnerBid = (Bid) winnerBids.get(currentAuction.getIdAuction());
                    if(currentWinnerBid != null){
                        float winner = currentWinnerBid.getBidAmount();
                        int productCost = Math.round(winner/5);
                        productCost = (productCost + 1) * 5;
%>
                        <div style="float: left; text-align: left; color: black; font-weight: bolder; font-size: 12px; display: inline;">Puja Ganadora menor a <%=productCost%>&euro;</div>
<%
                    }
%>
                    <a href="PublicArea?option=info&idAuction=<%=currentAuction.getIdAuction()%>"><%=currentAuction.getActive()?"PUJA":"CERRADA"%></a>
                </div>
            </div>
        </div>
        
  <%
    }
}
%>        

</span>


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
        
        <p>
        <div class="twocols" style="float:left;">
            <strong>PVP</strong><br /><%=Math.round(lastAuction.getCogs())%>&euro;
        </div>
        <div class="twocols" style="float:right;">
	        <strong>Puja ganadora actual</strong><br />menor a <%=productCost%>&euro;
        </div>
        </p>
        
        <p align="center">Para poder pujar debes estar registrado. <a href="register.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:470}}" title="Registro">Hazlo aqu&iacute;</a></p>
        
      <div id="game_line"></div>
        
  <div id="game_form" style="height:40px;">
    <div id="game_form_sub" style="height:40px;">
		<img src="img/winner.gif" alt="Ganador Actual" title="Ganador Actual">
		<p id="winner" style="height:50px;">
            <%
                if (winnerBid == null){
                    out.print("No existe un ganador en este momento<");
                }else{
                    out.print("<strong>Ganador actual:</strong><br />" + winnerBid.getUseridUser().getName()+"<br/><br/>");
                }
            %></p>
      </div>
      
      <%
	  // premios intermedios de cada subasta....
	  			if (winnerBid != null){
	  				List<Pricebyauction> prices = lastAuction.getPricebyauctionCollection();
                    int tramo = 0;
                    String[] ordinal = {"Primer", "Segundo", "Tercer"};
                    if(prices != null){
                        for(Pricebyauction price : prices){
                            String name = price.getUseridUser()==null?"":price.getUseridUser().getName();
                            if (!"".equals(name)){
									out.print("<div id='game_form_sub' style='height:40px;'><p><strong>"+ordinal[tramo]+" premio</strong><br/>"+name+"</p></div>");
                            }
                            tramo++;
                        }
                    }
				}
		%>
 	 </div>
     
        <div id="game_line">&nbsp;</div>
        
        <div class="prod_img">
            <img src="<%=lastAuction.getMosaicFileName()%>" width="373" height="75" class="none"/>
        </div>
        <a id="v_toggle" href="#" name="v_toggle"><span id="vertical_status">[-] Menos Informaci&oacute;n</span></a>
        <div id="vertical_slide"><p><%=currentText.getLongDescription()%></p></div>
        
		</br>
    </div>
    <div id="gameKicker">
	<img src="img/game_c_ll.gif" class="c_l">
	<img src="img/game_c_lr.gif" class="c_r">
    </div>
</span>

<!-------------------------------------------CUERPO / COLUMNA-------------------------------------------> 

<%@ include file="publicKicker.jsp" %>
</div>


</body>
</html>


<%
    }catch(Exception ex){
        System.out.println("Error en publicBoard.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>