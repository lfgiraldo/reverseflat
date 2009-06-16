<%
    try{
%>

<%--IMPRESCINDIBLE INCLUIR commonVariables.jsp EN LAS PÁGINAS QUE INCLUYAN publicHeader.jsp --%>
    <%@ include file="commonVariables.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Snoits | be smart :)</title>
    <%@ include file="metatags.jsp" %>
    <%@ include file="cssSelector.jsp" %>
    <link href="js/formcheck/theme/classic/formcheck.css" rel="stylesheet" type="text/css" />
    <link href="css/SqueezeBox.css" rel="stylesheet" type="text/css" />
    <link rel="icon" type="image/png" href="img/favicon.png" />
<style type="text/css">
<!--
#prueba {
	border: 1px solid #ccc;
	position: relative;
	height: 350px;
	width: 250px;
}
-->
</style>
</head>
	
<body>
<!--
<a style="background-color:rgb(34, 34, 34);" id="fdbk_tab" href="feedback.jsp" class="boxed" rel="{handler:'iframe',size:{x:410,y:470}}" title="Feedback">FEEDBACK</a>
-->
<!-------------------------------------------HEADER-------------------------------------------> 
    <%@ include file="commonScripts.jsp" %>
	<% //@ include file="publicHeader.jsp" %> 

    
    
<!-------------------------------------------ACTUAL-------------------------------------------> 
<%//@ include file="publicClosing.jsp" %>

<!-------------------------------------------SUBMENU-------------------------------------------> 
<%//@ include file="publicSubmenu.jsp" %>

<div style="border:#000; color:#000">
  <span>
    <div>
        <div>Subastas Abiertas</div>
	</div>
<%
    List<Auction> auctionsList = (List<Auction>) request.getSession().getAttribute("activeAuctions");
	List<Auctioncategory> categories = (List<Auctioncategory>) request.getSession().getAttribute("categories");
    if (userLocale == null){
        userLocale = "es";
    }
if ( auctionsList != null)
{
	int i = 0;
    int half = Math.round(auctionsList.size()/2)-1; //Se resta 1 porque la i comienza en cero.
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

        String link = "";
		if (user != null){
			link = "RestrictedArea?option=refresh&idAuction="+currentAuction.getIdAuction()+"&type="+request.getAttribute("type");
		}else{
			link = "PublicArea?option=info&idAuction="+currentAuction.getIdAuction();
		}
%>
        <div id="prueba">
            <a href="<%=link%>">
                <img src="<%=currentAuction.getCircledFileName()%>" width="74" height="74" />
            </a>
                <div >
                <a href="<%=link%>"><%=currentText.getTitle()%></a>
                <p><%=currentText.getShortDescription()%></p>
					<%---------  TIMER & PROGRESS BAR ---------%>
                    <%@ include file="progressBarAndTimer.jsp" %>
                <div >
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
                    <a href="<%=link%>"><%=currentAuction.getActive()?"PUJA":"CERRADA"%></a>
                </div>
            </div>
        </div>
        
<%
  		
    }
}
%>        
  </span></div>
<!-------------------------------------------SUBMENU------------------------------------------> 
		<%//@ include file="publicKicker.jsp" %>
</div>

<!-------------------------------------------GA-----------------------------------------------> 

</body>
</html>

<%
    }catch(Exception ex){
        System.out.println("Error en indexTest.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>