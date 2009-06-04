<span class="column">
<div class="column_unit">
        <div class="column_h1">Ultimos art&iacute;culos</div>
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
                
<%-- <!-------------------------------------------TIMER & PROGRESS BAR -------------------------------------------> --%>
                    <%@ include file="progressBarAndTimer.jsp" %>
                
                <div class="column_puja">
                	<a href="PublicArea?option=info&idAuction=<%=currentAuction.getIdAuction()%>">PUJA</a>
                </div>
            </div>
        </div>
        
  <%
    }
}
%>        

</span>