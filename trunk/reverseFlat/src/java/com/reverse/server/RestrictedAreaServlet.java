
package com.reverse.server;

import com.reverse.ejb.session.AuctionFacadeLocal;
import com.reverse.ejb.session.UserFacadeLocal;
import com.reverse.ejb.exception.ClosedAuctionException;
import com.reverse.ejb.exception.NoBidsException;
import com.reverse.ejb.exception.NoChipsException;
import com.reverse.ejb.exception.NoUniqueBidsException;
import com.reverse.ejb.exception.NoWinnerException;
import com.reverse.ejb.exception.RangeException;
import com.reverse.server.commons.CommonQueries;
import com.reverse.ejb.persistence.Auction;
import com.reverse.ejb.persistence.Auctioncategory;
import com.reverse.ejb.persistence.Bid;
import com.reverse.ejb.persistence.Chipsexpenditure;
import com.reverse.ejb.persistence.User;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 * Servlet encargado de controlar las peticiones del área de usuario registrado.
 * @author Luis Felipe Giraldo
 */
public class RestrictedAreaServlet extends HttpServlet {
   
    @EJB
    private UserFacadeLocal userFacade;
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
        String type = request.getParameter("type");
        try{
            User user = (User) request.getSession().getAttribute("user");
            if (user == null){
                request.getRequestDispatcher("Public").forward(request, response);
                return;
            }
            String option = request.getParameter("option");
            
            messages = (ResourceBundle) request.getSession().getAttribute("messages");
            
            String idAuction = request.getParameter("idAuction");
            if ("singleBid".equals(option)){
                try {

                    String bidAmount = request.getParameter("singleBid");
                    Bid bid = new Bid();
                    bid.setAuctionidAuction(new Auction(Integer.parseInt(idAuction)));
                    bid.setBidAmount(Float.parseFloat(bidAmount));
                    bid.setUseridUser(user);
                    auctionsFacade.singleBid(bid);
                    Auction currentAuction = (Auction) request.getSession().getAttribute("lastAuction");

                    Map map = refreshPanel(request, user, currentAuction, type);

                    JSONObject jsonObject = JSONObject.fromObject( map );
                    System.out.println( jsonObject );

                    response.getWriter().println(jsonObject);
                } catch (NoChipsException ex) {
                    Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("error", messages.getString("NoChipsException"));
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
                } catch (ClosedAuctionException ex) {
                    Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("error", messages.getString("ClosedAuctionException"));
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
                }
                //request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
            }else if ("rangeBid".equals(option)){
                try {
                    String startingNumber = request.getParameter("bidFrom");
                    String finishingNumber = request.getParameter("bidTo");
                    Bid bidFrom = new Bid();
                    Bid bidTo = new Bid();

                    bidFrom.setAuctionidAuction(new Auction(Integer.parseInt(idAuction)));
                    bidFrom.setBidAmount(Float.parseFloat(startingNumber));
                    bidFrom.setUseridUser(user);

                    bidTo.setAuctionidAuction(new Auction(Integer.parseInt(idAuction)));
                    bidTo.setBidAmount(Float.parseFloat(finishingNumber));
                    bidTo.setUseridUser(user);

                    auctionsFacade.rangeBid(bidFrom, bidTo);
                    Auction currentAuction = (Auction) request.getSession().getAttribute("lastAuction");
                    refreshPanel(request, user, currentAuction, type);
                    request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
                } catch (RangeException ex) {
                    Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    Auction currentAuction = (Auction) request.getSession().getAttribute("lastAuction");
                    refreshPanel(request, user, currentAuction, type);
                    request.setAttribute("error", messages.getString("RangeException"));
                    request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
                } catch (NoChipsException ex) {
                    Auction currentAuction = (Auction) request.getSession().getAttribute("lastAuction");
                    refreshPanel(request, user, currentAuction, type);
                    Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("error", messages.getString("NoChipsException"));
                    request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
                } catch (ClosedAuctionException ex) {
                    Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("error", messages.getString("ClosedAuctionException"));
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
                }
            }else if ("refresh".equals(option)){
                Auction auction = auctionsFacade.find(new Integer(idAuction));
                refreshPanel(request, user, auction, type);
                request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
            }else if ("enrolled".equals(option)){
                CommonQueries common = new CommonQueries();
                Auction currentAuction = common.getLastAuctionByUser(auctionsFacade, user.getIdUser());
                refreshPanel(request, user, currentAuction, type);
                request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
            }else if ("category".equals(option)){
                String idCategory = request.getParameter("idCategory");
                List<Auction> auction = auctionsFacade.findAuctionsByCategory(new Integer(idCategory));

                List<Auctioncategory> categories = auctionsFacade.getCategories();
                for(Auctioncategory cat: categories){
                    if ( cat.getIdCategory().toString().equals(idCategory.toString()) ){
                        String locale = (String) request.getSession().getAttribute("locale");
                        if ("en".equals(locale)){
                            request.setAttribute("lateralTitle", cat.getCategoryNameEn());
                        }else{
                            request.setAttribute("lateralTitle", cat.getCategoryNameEs());
                        }

                    }
                }

                Auction currentAuction = auction.get(0);
                refreshPanelByCategory(request, currentAuction, user, new Integer(idCategory));
                request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
            }else if ("logout".equals(option)){
                request.getSession().invalidate();
                request.setAttribute("type", type);
                request.getRequestDispatcher("Public").forward(request, response);
            }
            
        }catch(Exception ex){
            request.setAttribute("error", ex.getLocalizedMessage());
            request.setAttribute("type", type);
            request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
        }
        
    }

    /**
     * Consulta los datos relacionados con una subasta y un usuario para actualizar la información presentada en pantalla.
     * @param request
     * @param user Usuario autenticado
     * @param currentAuction Subasta Actual
     * @param type Tipo de Visualización (Active, Closed, Enrolled)
     * @return Mapa con duplas llave-valor para ser convertidas en formato JSON
     */
    private Map refreshPanel(HttpServletRequest request, User user, Auction currentAuction, String type ){
        CommonQueries common = new CommonQueries();

        Map map = new HashMap();


        String realPath = getServletContext().getRealPath("/");
        currentAuction = auctionsFacade.find(currentAuction.getIdAuction());
        //Barra de Actividad
        request.getSession().setAttribute("lastActivity", common.getAuctionList(auctionsFacade, request, realPath, "lastActivity"));
        //Costo de la puja en este momento.
        int bidCost = common.getBidCost(auctionsFacade, currentAuction.getIdAuction(), new Integer(1));
        map.put( "bidCost", bidCost );
        request.getSession().setAttribute("bidCost", bidCost);
        List<Auction> auctionsList = common.getAuctionList(auctionsFacade, request, getServletContext().getRealPath("/"), type);
        //Muestra la cantidad de subastas en las que está participando.
        request.getSession().setAttribute("enrolledAuctions", common.getAuctionList(auctionsFacade,request,realPath,"enrolledAuctions"));

        if("enrolledAuctions".equals(type)){
            request.setAttribute("lateralTitle", "Tus Subastas Activas");
        }else if("active".equals(type)){
            request.setAttribute("lateralTitle", "Subastas Abiertas");
        }else if("closed".equals(type) ){
            request.setAttribute("lateralTitle", "Subastas Cerradas");
        }

        if(auctionsList == null){
            request.setAttribute("message", messages.getString("NoEnrolledAuctions"));
            auctionsList = common.getAuctionList(auctionsFacade, request, getServletContext().getRealPath("/"), "active");
            request.setAttribute("lateralTitle", "Subastas Abiertas");
        }

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

        request.getSession().setAttribute("activeAuctions", auctionsList );
        request.getSession().setAttribute("chipsExpenditure", common.getActiveAuctionsByUser(auctionsFacade, user.getIdUser()));
        
        request.setAttribute("type", type);
        
        
        
        try {
            Bid lastBid = common.getLastBidByUser(auctionsFacade, currentAuction.getIdAuction(), user.getIdUser());
            request.getSession().setAttribute("lastBid", lastBid);
            Chipsexpenditure lastExpenditure = common.getLastChipsExpenditureByUser(auctionsFacade, currentAuction.getIdAuction(), user.getIdUser());
            NumberFormat formatter = new DecimalFormat("#0.0");
            if(lastExpenditure.getBidFrom() == lastExpenditure.getBidTo().floatValue()){
                map.put( "lastBid", formatter.format(lastExpenditure.getBidFrom())+" " );
            }else{
                map.put( "lastBid", "[ "+formatter.format(lastExpenditure.getBidFrom())+" - "+formatter.format(lastExpenditure.getBidTo())+" ]" );
            }
            request.getSession().setAttribute("lastExpenditure", lastExpenditure);
        } catch (NoBidsException ex) {
            request.getSession().setAttribute("lastBid", null);
            Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.INFO, ex.getMessage());
        }

        try {
            String uniqueBids = common.getUniqueBidsByUser(auctionsFacade, currentAuction.getIdAuction(), user.getIdUser());
            map.put( "uniqueBids", uniqueBids);
            request.getSession().setAttribute("uniqueBids", uniqueBids );
        } catch (NoUniqueBidsException ex) {
            request.getSession().setAttribute("uniqueBids", messages.getString("NoUniqueBidsException"));
            Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.INFO, ex.getMessage());
        }

        try {
            request.getSession().setAttribute("winnerBid", common.getWinnerBid(auctionsFacade, currentAuction.getIdAuction()));
        } catch (NoWinnerException ex) {
            request.getSession().setAttribute("winnerBid",null);
            Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.INFO, ex.getMessage());
        }

        request.getSession().setAttribute("lastAuction", currentAuction);
        try {
            request.getSession().setAttribute("bids", common.getBidsByUserAndAuction(auctionsFacade, user.getIdUser(), currentAuction.getIdAuction()));
        } catch (NoBidsException ex) {
            request.getSession().setAttribute("bids", null);
            Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.INFO, ex.getMessage());
        }
        user = userFacade.find(user.getIdUser());
        request.getSession().setAttribute("user", user);

        return map;
    }

    /**
     * Consulta los datos relacionados con una subasta, una categoría y un usuario para actualizar la información presentada en pantalla.
     * @param request
     * @param currentAuction Subasta Actual
     * @param user Usuario Autenticado
     * @param idCategory ID de la categoría
     */
    private void refreshPanelByCategory(HttpServletRequest request, Auction currentAuction, User user, Integer idCategory){
        CommonQueries common = new CommonQueries();

        List<Auction> auctionsList = common.getAuctionsByCategory(auctionsFacade, idCategory);
        request.getSession().setAttribute("activeAuctions", auctionsList );

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
        //request.getSession().setAttribute("chipsExpenditure", common.getActiveAuctionsByUser(auctionsFacade, user.getIdUser()));
        request.getSession().setAttribute("lastAuction", currentAuction);
        request.setAttribute("lateralTitle", "Relacionados");
        //Costo de la puja en este momento.
        request.getSession().setAttribute("bidCost", common.getBidCost(auctionsFacade, currentAuction.getIdAuction(), new Integer(1)));
        request.setAttribute("type", request.getParameter("type"));
        try {
            request.getSession().setAttribute("winnerBid", common.getWinnerBid(auctionsFacade, currentAuction.getIdAuction()));
        } catch (NoWinnerException ex) {
            request.getSession().setAttribute("winnerBid",null);
            Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.INFO, ex.getMessage());
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
