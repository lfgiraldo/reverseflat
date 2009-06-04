
package com.reverse.server;

import com.reverse.ejb.session.AuctionFacadeLocal;
import com.reverse.ejb.exception.NoWinnerException;
import com.reverse.server.commons.CommonQueries;
import com.reverse.ejb.persistence.Auction;
import com.reverse.ejb.persistence.Bid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de cargar la información que se presenta cuando un usuario
 * ingresa a la página principal.
 * Al mismo tiempo inicializa el mensaje de error en caso de que otro servlet lance una excepción. 
 * @author Luis Felipe Giraldo
 */
public class PublicIndexServlet extends HttpServlet {


    @EJB
    private AuctionFacadeLocal auctionsFacade;
    private ResourceBundle  messages;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try{
            CommonQueries common = new CommonQueries();
            messages = common.setLocale(request);

            String realPath = getServletContext().getRealPath("/");

            List<Auction> auctionsList = common.getAuctionList(auctionsFacade, request, realPath, "active");
            request.getSession().setAttribute("activeAuctions",auctionsList );

            HashMap winnerBids = new HashMap();
            for (Auction auc : auctionsList){
                try {
                    winnerBids.put(auc.getIdAuction(), common.getWinnerBid(auctionsFacade, auc.getIdAuction()));
                } catch (NoWinnerException ex) {
                    Bid bid = new Bid();
                    bid.setBidAmount(0);
                    winnerBids.put(auc.getIdAuction(), bid );
                }
            }
            request.getSession().setAttribute("winnerBids", winnerBids );

            request.getSession().setAttribute("closedAuctions", common.getAuctionList(auctionsFacade, request, realPath, "closed"));
            request.getSession().setAttribute("categories", common.getCategories(auctionsFacade));
            request.getSession().setAttribute("lastActivity", common.getAuctionList(auctionsFacade, request, realPath, "lastActivity"));
            request.getSession().setAttribute("enrolledAuctions", common.getAuctionList(auctionsFacade,request,realPath,"enrolledAuctions"));
            //Inicialmente se miran las subastas activas
            request.setAttribute("type", "active");
            String error = request.getParameter("error");
            if (error != null){
                if ("nonexistent".equalsIgnoreCase(error)){
                    request.setAttribute("message", messages.getString("InvalidUser"));
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);
                }else if ("password".equalsIgnoreCase(error)){
                    request.setAttribute("error", messages.getString("LoginException"));
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);
                }else if ("password".equalsIgnoreCase(error)){
                    request.setAttribute("error", messages.getString("LoginException"));
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);
                }else if ("general".equalsIgnoreCase(error)){
                    request.setAttribute("error", messages.getString("StartSession"));
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);
                }else if ("role".equalsIgnoreCase(error)){
                    request.setAttribute("error", messages.getString("NoRol"));
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);
                }
            }else{
                request.getRequestDispatcher("indexTest.jsp").forward(request, response);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
