<div id="menu1">
	<div id="cierres_tit">Subastas Activas</div>
	<div id="SlideItMoo_info_outer">
		<div id="SlideItMoo_info_inner">			
			<div id="SlideItMoo_info_items">
        <%
        if ( auctionsByActivity != null)
        {
            int i = 0;
            for(Auction currentAuction : auctionsByActivity ){
                List<Auctiontexttranslation> translatedTexts = currentAuction.getAuctiontexttranslationCollection();
                Auctiontexttranslation currentText = new Auctiontexttranslation();
        
                for(Auctiontexttranslation text : translatedTexts){
                    if (text.getLanguageCode().equalsIgnoreCase(userLocale)){
                        currentText = text;
                        break;
                    }
                }
                String link = "";
                if (user != null){
                    link = "RestrictedArea?option=refresh&idAuction="+currentAuction.getIdAuction()+"&type=active";
                }else{
                    link = "PublicArea?option=info&idAuction="+currentAuction.getIdAuction();
                }
                List<Bid> bids = (List<Bid>)currentAuction.getBidCollection();
                long percentage = 0;
                String quarter = "";
                if ( bids != null){
                    int minBids = currentAuction.getMinBids();
                    int totalBids = bids.size()*2;
                    percentage = (totalBids*100/minBids);
                    if (percentage == 0 ){
                        quarter = "<img src=\"images/bullet_circle_white.png\" />" +
                        "<img src=\"images/bullet_circle_white.png\" /> " +
                        "<img src=\"images/bullet_circle_white.png\" /> " ;
                    }else if (percentage > 0 && percentage < 33){
                        quarter = "<img src=\"images/bullet_circle_orange.png\" />" +
                        "<img src=\"images/bullet_circle_white.png\" /> " +
                        "<img src=\"images/bullet_circle_white.png\" /> " ;
                    }else if (percentage >= 33 && percentage < 66){
                        quarter = "<img src=\"images/bullet_circle_orange.png\" />" +
                        "<img src=\"images/bullet_circle_orange.png\" /> " +
                        "<img src=\"images/bullet_circle_white.png\" /> " ;
                    }else if (percentage >= 66 && percentage <= 100){
                        quarter = "<img src=\"images/bullet_circle_orange.png\" />" +
                        "<img src=\"images/bullet_circle_orange.png\" /> " +
                        "<img src=\"images/bullet_circle_orange.png\" /> " ;
                    }
                    
                }
        %>
            <div class="info_item" align="center">
                
    <%
                if (currentAuction.getActive() && currentAuction.getEndDate() == null){
    %>
                    <%=quarter%>
                    <%=currentText.getTitle()%>
                    <img src="images/separador_01.png" />

    <%
                }else if (currentAuction.getActive() && currentAuction.getEndDate() != null){
                    Date today = new Date();
                    long remainingTime = currentAuction.getEndDate().getTime() - today.getTime();
                    long anHour = 60000; //in miliseconds
                    long percentageTime = remainingTime / anHour;

                    percentageTime *= 100;
                    percentageTime = 100 - percentageTime;
                    if (percentageTime < 100)
                        percentageTime = Math.round(percentageTime / 8.3);
                    else
                        percentageTime = 12;
                    //Convierte de milisegundos a segundos
                    remainingTime /= 1000;
    %>
                    <p><a href="<%=link%>"><%=currentText.getTitle().length() >=20? currentText.getTitle().substring(0,20)+"...":currentText.getTitle()%></a></p>
                    <p><a href="<%=link%>"><img src="img/swt0<%=percentageTime%>.gif" class="cierres_img" title="Avance de la Subasta" id="imgbar<%=currentAuction.getIdAuction()%>" /></a></p>
                    <p id="bar<%=currentAuction.getIdAuction()%>" style="font-weight:bold;">
                        <a href="<%=link%>">
                            <script>
                                    var name = 'aucBar<%=currentAuction.getIdAuction()%>';
                                    window[name] = <%=remainingTime%>;
                                    window.addEvent('domready',function(){window['timeraucBar<%=currentAuction.getIdAuction()%>']=setInterval("timer('bar<%=currentAuction.getIdAuction()%>', 'aucBar<%=currentAuction.getIdAuction()%>')",1000)});
                            </script>
                        </a>
                    </p>
    <%
                }
    %>
                    <!-- <p><a href="<%=link%>">PUJA</a></p> -->
                </div>
        <%
            }
        }
            %>
                
		</div>  <!-- SlideItMoo_info_items -->
    </div> <!-- SlideItMoo_info_inner -->
    </div>	<!-- SlideItMoo_info_outter -->
</div> <!-- Cierres -->