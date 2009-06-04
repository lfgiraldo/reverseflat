/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.server;

import com.reverse.ejb.session.AuctionFacadeLocal;
import com.reverse.ejb.session.UserFacadeLocal;
import com.reverse.ejb.persistence.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Felipe Giraldo
 */
public class TestSwf extends HttpServlet {


    @EJB
    private AuctionFacadeLocal auctionsFacade;
    @EJB
    private UserFacadeLocal userFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Vector winnerBid;
        String idWinnerBid = "";
        String winnerBidText = "";
        String uniqueBids = "";
        User winnerUser;
        Vector lastBidVector = null;
        String auction = request.getParameter("idAuction");
        String user = request.getParameter("idUser");

        Integer idAuction = new Integer(auction);
        Integer idUser = new Integer(user);

        /*
        try{
            winnerBid = auctionsFacade.getWinnerBid(idAuction);
            idWinnerBid = winnerBid.get(3).toString();
            winnerBidText = winnerBid.get(2).toString();
            winnerUser = userFacade.find(new Integer(idWinnerBid));
        }catch (NoWinnerException ex){
            winnerUser = new User();
            winnerUser.setNickname("NoWinnerException");
            winnerBidText = "0.0";
        }


        try{
            lastBidVector = auctionsFacade.getLastBidByUser(idAuction, idUser);
        }catch(NoBidsException ex){
            lastBidVector = new Vector();
            lastBidVector.add(0);
        }catch (NullPointerException ex){
            lastBidVector = new Vector();
            lastBidVector.add(0);
            //@TODO ocurre cuando es un usuario no registrado
        }
        */
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<data>");
            out.println("<winner>");
            out.println(winnerBidText);
            out.println("</winner>");
            out.println("<yourBid>");
            out.println(lastBidVector.get(2));
            out.println("</yourBid>");
            out.println("</data>");

        } finally {
            out.close();
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
