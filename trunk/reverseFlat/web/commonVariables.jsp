<%@page import="com.reverseFlat.ejb.persistence.*, java.util.*" %>
<%-- 
    Document   : commonVariables
    Created on : 17-abr-2009, 13:52:33
    Author     : Luis Felipe Giraldo
--%>
<%
    User user = (User) request.getSession().getAttribute("user");
    List<Auction> enrolledAuctions = (List<Auction>) request.getSession().getAttribute("enrolledAuctions");
    List<Auction> auctionsByActivity = (List<Auction>) request.getSession().getAttribute("lastActivity");
    Auction lastAuction = (Auction) request.getSession().getAttribute("lastAuction");
    Bid winnerBid = (Bid) request.getSession().getAttribute("winnerBid");
    Bid lastBid = (Bid) request.getSession().getAttribute("lastBid");
    Chipsexpenditure lastExpenditure = (Chipsexpenditure) request.getSession().getAttribute("lastExpenditure");
    String uniqueBids = (String) request.getSession().getAttribute("uniqueBids");
    List<Bid> yourBids = (List<Bid>) request.getSession().getAttribute("bids");
    String userLocale = (String) request.getSession().getAttribute("locale");
    if (userLocale == null)
        userLocale = "es";
	String error = (String)request.getAttribute("error");
	String message = (String)request.getAttribute("message");
    ResourceBundle messages = (ResourceBundle) request.getSession().getAttribute("messages");

%>