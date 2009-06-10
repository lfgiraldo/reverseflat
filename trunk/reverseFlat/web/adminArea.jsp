<%@page import="com.reverseFlat.ejb.persistence.*, java.util.*" %>
<%
    if (request.getSession().isNew()){
        response.sendRedirect("Public");
    }
	
	try {
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
    <head>
        <title>Snoits - Leader on Reverse Auctions</title>
        <link href="css/admin/styles.css" rel="stylesheet" type="text/css">
    </head>
 
<body topmargin="0" leftmargin="0">
<div id="wrap">
		<div id="top-bg"></div>

		<div id="header">
			<h1 id="titleText">Snoits</h1>
			<h2 id="sloganText">Be Smart!</h2>
		</div>


		<div id="header-photo"></div>

        <div id="nav1">
        	<li><a href="createAuction.jsp">Create Auction</a></li>
            
        </div>
		<div id="content-wrap">
		  <div id="userMain">
            <table id="mytable" cellspacing="0" summary="Existing Auctions">
<caption>Existing Auctions </caption>
  <tr>
    <th scope="col" abbr="Status" class="nobg">Status</th>

    <th scope="col" abbr="Title">Title</th>
    <th scope="col" abbr="Description">Short Desc</th>
	</tr>
<%
List<Auction> auctionsList = (List<Auction>) request.getSession().getAttribute("auctionsList");

if ( auctionsList != null )
{
	String[] rowcolor = {"spec","specalt"};
    String[] color = {"","alt"};
	int i =0;

    for(Auction currentAuction : auctionsList ){
        List<Auctiontexttranslation> translatedTexts = currentAuction.getAuctiontexttranslationCollection();
        Auctiontexttranslation currentText = new Auctiontexttranslation();
        String userLocale = (String) request.getSession().getAttribute("locale");
        for(Auctiontexttranslation text : translatedTexts){
            if (text.getLanguageCode().equalsIgnoreCase(userLocale)){
                currentText = text;
                break;
            }
        }
        currentText.getTitle();
        currentText.getShortDescription();
%>    
    
  <tr>
    <th scope="row" abbr="Active" class="<%=rowcolor[i%2]%>"><%out.print(currentAuction.getActive() ? "Active" : "Inactive");%></th>
    <td class="<%=color[i%2]%>"><a href="updateAuction.jsp?idAuction=<%=currentAuction.getIdAuction()%>"><%=currentText.getTitle()%></a></td>

    <td class="<%=color[i%2]%>"><%=currentText.getShortDescription()%></td>
    </tr>
    
  <%
  	i++;
    }
}
%>        
        
</table>

          </div>
		</div>

		<div id="footer-wrap">
			<div id="footer-bottom">
				<p>

<!-- Keep these links or make a $10 donation  -->
Design by

<a href="http://www.styleshout.com">StyleShout</a> and  <a href="http://www.dotemplate.com">doTemplate</a>
&nbsp;|&nbsp;
Web template created with <a href="http://www.dotemplate.com">doTemplate</a>
				</p>
				<p>&nbsp;</p>
			</div>
		</div>
</div>


</body>

</html>

<%
    }catch(Exception ex){
        System.out.println("Error en adminArea.jsp. \n");
        ex.printStackTrace();
        request.setAttribute("error", ex.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>