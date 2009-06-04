package com.reverse.server;

import com.reverse.ejb.session.UserFacadeLocal;
import com.reverse.ejb.exception.NonExistentUserException;
import com.reverse.ejb.exception.WrongActivationNumberException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de activar la cuenta de usuario. Se envía un correo y se muestra un mensaje de confirmación.
 * @author Luis Felipe Giraldo
 */
public class ActivationServlet extends HttpServlet {

    @EJB
    private UserFacadeLocal userFacade;
    private Locale locale;
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
        String nickname = request.getParameter("nickname");
        String activationKey = request.getParameter("activationKey");
        
        try{
            userFacade.activateUser(nickname, activationKey);
            request.setAttribute("message", "<p>Su cuenta se ha activado correctamente.</p><p>Ahora ingrese con su nombre de usuario y contrase&ntilde;a</p>");
            request.getRequestDispatcher("indexTest.jsp").forward(request, response);
        }catch(WrongActivationNumberException ex){
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("indexTest.jsp").forward(request, response);
        }catch(NonExistentUserException ex){
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("indexTest.jsp").forward(request, response);
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
