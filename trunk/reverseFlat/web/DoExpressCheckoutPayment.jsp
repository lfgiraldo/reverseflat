<html>
<head>
    <title>PayPal JSP SDK - DoExpressCheckoutPayment API</title>
</head>
<body>
	<center>
	    <b>Thank you for your payment!</b>
	    <br>
	    <br>
    	<table width="400">
			<tr>
            	<td>
                	Transaction ID:
	            </td>
	            <td>
    	            <%= request.getParameter("transactionId") %>
    	        </td>
        	</tr>
	        <tr>
            	<td>
                	Amount:
                </td>
	            <td>
	            	<%= request.getParameter("currency") + " " +  request.getParameter("amount") %> </td>
        </tr>
    </table>
    </center>
    <a id="CallsLink" class="home" href="PayPal.jsp?locale=<%= request.getSession().getAttribute("locale") %>">Home</a>
</body>
</html>
