package com.reverse.server;

//import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.reverse.ejb.commons.valueobjects.CreditCardServer;
import com.reverse.ejb.session.UserFacadeLocal;
import com.reverse.ejb.exception.PayPalServiceException;
import com.reverse.ejb.persistence.Auction;
import com.reverse.ejb.persistence.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de hacer el pago con tarjeta de crédito a través de Paypal.
 * @author Luis Felipe Giraldo
 */
public class PayPalAreaServlet extends HttpServlet { // extends RemoteServiceServlet implements PayPalService {

    
	private static final long serialVersionUID = 1L;
    @EJB
    private UserFacadeLocal userFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        try{
            String option = request.getParameter("option");

            if ("creditCard".equals(option)){
                try {
                    String transactionID = buyChipsWithCreditCard(request);
                    String message = "Transacci&oacute;n exitosa. <br/>ID de la transacci&oacute;n: <br/>"+transactionID;
                    request.setAttribute("message", message);
                    Auction lastAuction = (Auction) request.getSession().getAttribute("lastAuction");
                    request.setAttribute("idAuction", lastAuction.getIdAuction() );
                    request.getRequestDispatcher("paypalConfirmation.jsp").forward(request, response);
                } catch (PayPalServiceException ex) {
                    Logger.getLogger(PayPalAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("paypalConfirmation.jsp").forward(request, response);
                }
            }
        }catch(Exception ex ){
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("paypalConfirmation.jsp").forward(request, response);
        }
        

    }

    /**
     * Toma los datos del formulario y hace el pago a través de Paypal.
     * @param request
     * @return ID de la transacción
     * @throws com.reverse.ejb.exception.PayPalServiceException Excepción generada por Paypal
     */
	public String buyChipsWithCreditCard(HttpServletRequest request) throws PayPalServiceException {
        try {
            
            String creditCardType = request.getParameter("creditCardType");
            String creditCardNumber = request.getParameter("creditCardNumber");
            String expdateMonth = request.getParameter("expdateMonth");
            String expdateYear = request.getParameter("expdateYear");
            String cvv2Number = request.getParameter("cvv2Number");
            String currency = request.getParameter("currency");
            String comboChips = request.getParameter("comboChips");


            CreditCardServer creditCard = new CreditCardServer();
            creditCard.setIdCombo(comboChips);
            creditCard.setCreditCardNumber(creditCardNumber);
            creditCard.setCreditCardType(creditCardType);
            creditCard.setCurrency(currency);
            creditCard.setCvv2Number(cvv2Number);
            creditCard.setExpdateMonth(expdateMonth);
            creditCard.setExpdateYear(expdateYear);

            User user = (User) request.getSession().getAttribute("user");
            //userDto.setChipsBalance(chips);
            String transactionId = userFacade.addPaypalChips(user, creditCard);
            return transactionId;
        } catch (com.reverse.ejb.exception.PayPalServiceException ex) {
            throw new PayPalServiceException(ex.getMessage());
        }

	}

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
