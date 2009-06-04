<html>
<head>
    <title>PayPal JSP SDK - ExpressCheckout API</title>
</head>
<body>
    <center>
        <table width="400">
            <tr>
                <td>
                    <b>Order Total:</b>
                </td>
                <td>
                    <%= request.getParameter("currencyCodeType") + " " +  request.getParameter("paymentAmount") %>
                </td>
            </tr>
            <tr>
            	<td colspan="2">
                    <b>Shipping Address:</b>
                </td>
            </tr>
            <tr>
                <td>
                    Street 1:
                </td>
                <td>
                    <%= request.getParameter("shipToStreet") %>
                </td>
            </tr>
            <tr>
                <td>
                    Street 2:
                </td>
                <td>
                	<%= request.getParameter("shipToStreet2") %>
                </td>
            </tr>
            <tr>
                <td>
                    City:
                </td>
                <td>
                  <%= request.getParameter("chipToCity") %>
                </td>
            </tr>
            <tr>
                <td>
                    State:
                </td>
                <td>
                    <%= request.getParameter("chipToState") %>
                </td>
            </tr>
            <tr>
                <td>
                    Postal code:
                </td>
                <td>
                    <%= request.getParameter("chipToZip") %>
                </td>
            </tr>
            <tr>
                <td>
                    Country:
                </td>
                <td>
                    <%= request.getParameter("chipToCountry") %>
                </td>
            </tr>
            <tr>
                <td colspan="2">
	                <form method="POST" action="ExpressCheckoutServlet?action=doPayment">
						<input type="submit" value="Pay" />
						<input type="hidden" name="token" value="<%= request.getParameter("token") %>"/>
						<input type="hidden" name="PayerID" value="<%= request.getParameter("payerId") %>"/>
                        <input type="hidden" name="paymentAmount" value="<%= request.getParameter("paymentAmount") %>"/>
                        <input type="hidden" name="currencyCodeType" value="<%= request.getParameter("currencyCodeType") %>"/>
					</form>
                </td>
            </tr>
        </table>
    </center>
    <a id="CallsLink" class="home" href="PayPal.jsp?locale=<%= request.getParameter("locale") %>">Home</a>
</body>
</html>
