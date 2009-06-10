package com.reverseFlat.server;

import com.octo.captcha.service.CaptchaServiceException;
import com.reverseFlat.ejb.session.AuctionFacadeLocal;
import com.reverseFlat.ejb.session.UserFacadeLocal;
import com.reverseFlat.ejb.exception.ActivationMailException;
import com.reverseFlat.ejb.exception.LoginException;
import com.reverseFlat.ejb.exception.NoWinnerException;
import com.reverseFlat.ejb.exception.NonExistentUserException;
import com.reverseFlat.common.exceptions.InvalidCaptchaException;
import com.reverseFlat.common.exceptions.MailException;

import com.reverseFlat.server.commons.CaptchaServiceSingleton;
import com.reverseFlat.server.commons.CommonQueries;
import com.reverseFlat.server.commons.Commons;
import com.reverseFlat.ejb.persistence.Auction;
import com.reverseFlat.ejb.persistence.Auctioncategory;
import com.reverseFlat.ejb.persistence.Bid;
import com.reverseFlat.ejb.persistence.User;
import com.sun.appserv.security.ProgrammaticLogin;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de procesar las solicitudes del área pública.
 * Recibe como parámetro una opción y dependiendo de su valor redirecciona a la página específica.
 * @author Luis Felipe Giraldo
 */
public class PublicAreaServlet extends HttpServlet {

    
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
        try{


            String option = request.getParameter("option");
            CommonQueries common = new CommonQueries();
            messages = (ResourceBundle) request.getSession().getAttribute("messages");
            String type = request.getParameter("type");

            if("enrolledAuctions".equals(type)){
                request.setAttribute("lateralTitle", "Tus Subastas Activas");
            }else if("active".equals(type)){
                request.setAttribute("lateralTitle", "Subastas Abiertas");
            }else if("closed".equals(type) ){
                request.setAttribute("lateralTitle", "Subastas Cerradas");
            }

            if ("register".equals(option)){
                User user = Commons.readRegisterForm(request);
                try {
                    setUser(user, request, response);
                } catch (MailException ex) {
                    Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", messages.getString("MailException"));
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("registerConfirmation.jsp").forward(request, response);
                } catch (InvalidCaptchaException ex) {
                    Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.INFO, null, ex);
                    request.setAttribute("message", messages.getString("InvalidCaptchaException"));
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("registerConfirmation.jsp").forward(request, response);
                }
            }else if ("login".equals(option)){
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                doLogin(userName, password, request, response);
            }else if ("info".equals(option)){
                String idAuction = request.getParameter("idAuction");
                request.setAttribute("lateralTitle", "Relacionados");
                Auction auction = auctionsFacade.find(new Integer(idAuction));
                 List<Auction> list = (List<Auction>) request.getSession().getAttribute("activeAuctions");
                refreshPanel(request, list, auction);
                request.setAttribute("type", type);
                request.getRequestDispatcher("publicBoard.jsp").forward(request, response);
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
                refreshPanelByCategory(request, currentAuction, new Integer(idCategory));
                request.setAttribute("type", type);
                request.getRequestDispatcher("publicBoard.jsp").forward(request, response);
            }else if ("active".equals(option)){

                String realPath = getServletContext().getRealPath("/");
                List<Auction> activeAuctions = common.getAuctionList(auctionsFacade, request, realPath, "active");
                if (activeAuctions == null){
                    request.setAttribute("error", messages.getString("NoOpenAuctions"));
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);
                }else{
                    request.setAttribute("lateralTitle", "Subastas Abiertas");
                    refreshPanel(request, activeAuctions, activeAuctions.get(0));
                    if ( request.getSession().getAttribute("user") != null){
                        request.setAttribute("type", "active");
                        request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
                    }else{
                        request.setAttribute("type", type);
                        request.getRequestDispatcher("publicBoard.jsp").forward(request, response);
                    }
                }
            }else if ("closed".equals(option)){

                String realPath = getServletContext().getRealPath("/");
                List<Auction> closedAuctions = common.getAuctionList(auctionsFacade, request, realPath, "closed");
                if (closedAuctions == null){
                    request.setAttribute("error", messages.getString("NoClosedAuctions"));
                    if ( request.getSession().getAttribute("user") != null){
                        request.setAttribute("type", type);
                        request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
                    }else{
                        request.setAttribute("type", "active");
                        request.getRequestDispatcher("indexTest.jsp").forward(request, response);
                    }
                    
                }else{
                    request.setAttribute("lateralTitle", "Subastas Cerradas");
                    refreshPanel(request, closedAuctions, closedAuctions.get(0));
                    if ( request.getSession().getAttribute("user") != null){
                        request.setAttribute("type", "closed");
                        request.getRequestDispatcher("gameBoard.jsp").forward(request, response);
                    }else{
                        request.setAttribute("type", type);
                        request.getRequestDispatcher("publicBoard.jsp").forward(request, response);
                    }
                    
                }
            }else if ("feedback".equals(option)){
                String userName = request.getParameter("userName");
                String explorer = request.getParameter("explorer");
                String web = request.getParameter("web");
                String location = request.getParameter("location");
                String problem = request.getParameter("problem");
                try{
                    String email = messages.getString("feedbackMails");
                    String subject = messages.getString("feedbackSubject");
                    String problemDesc = "REPORTE DE ERROR <br/>" +
                            "Nombre de Usuario: "+userName + "<br/>" +
                            "Navegador: "+explorer+ "<br/>" +
                            "Web: "+web+ "<br/>" +
                            "Location: "+location+ "<br/>" +
                            "Problema: "+problem;
                    String confirmMessage = messages.getString("feedbackMessage");
                    request.setAttribute("message", confirmMessage);
                    userFacade.sendMail(email, subject, problemDesc);
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);

                }catch (ActivationMailException ex){
                    request.setAttribute("error", messages.getString("MailError"));
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);
                }

            }/*else if ("contact".equals(option)){
                try{
                    String email = messages.getString("contactMails");
                    String subject = messages.getString("contactSubject");

                    request.setAttribute("message", confirmMessage);
                    userFacade.sendMail(email, subject, problemDesc);
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);

                }catch (ActivationMailException ex){
                    request.setAttribute("error", messages.getString("MailError"));
                    request.setAttribute("type", type);
                    request.getRequestDispatcher("indexTest.jsp").forward(request, response);
                }

            }*/else{
                request.setAttribute("error", messages.getString("InvalidOption"));
                request.setAttribute("type", type);
                request.getRequestDispatcher("indexTest.jsp").forward(request, response);
            }
        }catch(Exception ex){
           response.sendRedirect("error.jsp");
        }
    } 


    /**
     * Método encargado de crear un nuevo usuario. <br/>
     * Primero confirma que el captcha corresponda con la imagen generada, si es correcta verifica que el nombre de
     * usuario no exista previamente en la base de datos. <br/>
     * Luego crea el usuario y envía un correo de confirmación.
     * @param user Datos del usuario que quiere registrar.
     * @param request
     * @param response
     * @throws com.reverse.ejb.gwt.client.common.exceptions.MailException Error al momento de enviar el correo de activación
     * @throws com.reverse.ejb.gwt.client.common.exceptions.InvalidCaptchaException Error generado si el código ingresado no corresponde con la imagen
     * @throws java.io.IOException
     */
    public void setUser(User user, HttpServletRequest request, HttpServletResponse response) throws MailException, InvalidCaptchaException, IOException {

        //setLocale(request);

        Boolean isResponseCorrect =Boolean.FALSE;
        //remenber that we need an id to validate!
        String captcha = request.getParameter("captcha");
        String captchaSalt = request.getParameter("captchaSalt");
        String captchaId = request.getSession().getId()+captchaSalt;
        //retrieve the response
        String captchaText = captcha;
        // Call the Service method

        String type = request.getParameter("type");

        try {
            isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId,
                    captchaText);
        } catch (CaptchaServiceException e) {
             //should not happen, may be thrown if the id is not valid
        }

        if (!isResponseCorrect){
            throw new InvalidCaptchaException(messages.getString("InvalidCaptchaException"));
        }
        Boolean nicknameExist = userFacade.existNickname(user.getNickname());

        //If nickname exists, do not save
        if ( nicknameExist ){
            
            try {
                request.setAttribute("message", messages.getString("The_nickname_already_exists"));
                request.getRequestDispatcher("registerConfirmation.jsp").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("error.jsp");
            }
        }else{
            
            try {
                userFacade.create(user);
                userFacade.sendRegisterMail(user);
                request.setAttribute("message", messages.getString("Welcome"));
                request.setAttribute("type", type);
                request.getRequestDispatcher("registerConfirmation.jsp").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("type", type);
                response.sendRedirect("error.jsp");
            } catch (IOException ex) {
                Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("type", type);
                response.sendRedirect("error.jsp");
            } catch (ActivationMailException ex) {
                userFacade.remove(user);
                Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.SEVERE, null, ex);
                throw new MailException(messages.getString("MailException"));
            }
        }
    }

    /**
     * Autentica el usuario contra el Realm de Glassfish<br/>
     * Primero verifica que el usuario esté activo y luego lo redirige dependiendo de su rol.
     * @param userName Nombre de Usuario
     * @param password Contraseña
     * @param request
     * @param response
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    private void doLogin(String userName, String password, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String type = request.getParameter("type");
        try {

            request.getSession();

            ProgrammaticLogin pl = new ProgrammaticLogin();
            User user = userFacade.findByUserNickname(userName);
            CommonQueries common = new CommonQueries();
            
            pl.login(userName, password.trim(), "SnoitsRealm", request, response, true);
            
            if ( user == null ){
                request.setAttribute("error", messages.getString("InvalidUser"));
                request.setAttribute("type", type);
                request.getRequestDispatcher("indexTest.jsp").forward(request, response);
            }else
            if (!user.getActive()){
                request.setAttribute("error", messages.getString("ActivateAccount"));
                request.setAttribute("type", type);
                request.getRequestDispatcher("indexTest.jsp").forward(request, response);
            }else
            if (request.isUserInRole("user")){
                request.getSession().setAttribute("user", user );

                Auction currentAuction = common.getLastAuctionByUser(auctionsFacade, user.getIdUser());
                String realPath = getServletContext().getRealPath("/");
                request.getSession().setAttribute("lastActivity", common.getAuctionList(auctionsFacade, request, realPath, "lastActivity"));
                List<Auction> enrolledAuction = common.getAuctionList(auctionsFacade,request,realPath,"enrolledAuctions");
                if(enrolledAuction == null){
                    enrolledAuction = common.getAuctionList(auctionsFacade,request,realPath,"active");
                }
                request.getSession().setAttribute("activeAuctions", enrolledAuction );
                request.setAttribute("type", "enrolledAuctions");
                request.getRequestDispatcher("RestrictedArea?option=refresh&idAuction="+currentAuction.getIdAuction()+"&type=enrolledAuctions").forward(request, response);
            }else if (request.isUserInRole("admin")){
                request.getSession().setAttribute("user", user );
                request.getSession().setAttribute("auctionsList", common.getAuctionList(auctionsFacade, request, getServletContext().getRealPath("/"), "active") );
                request.setAttribute("type", type);
                request.getRequestDispatcher("adminArea.jsp").forward(request, response);
            }else{
                request.setAttribute("error", messages.getString("NoRol"));
                request.setAttribute("type", type);
                request.getRequestDispatcher("indexTest.jsp").forward(request, response);
            }
        } catch (NonExistentUserException ex) {
            Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.INFO, null, ex);
            request.setAttribute("error", messages.getString("NonExistentUserException"));
            request.setAttribute("type", type);
            request.getRequestDispatcher("indexTest.jsp").forward(request, response);
        } catch (javax.security.auth.login.LoginException ex) {
            Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.INFO, ex.getMessage(), ex);
            request.setAttribute("error", messages.getString("LoginException"));
            request.setAttribute("type", type);
            request.getRequestDispatcher("indexTest.jsp").forward(request, response);
        }catch (java.lang.SecurityException ex) {
            Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.INFO, ex.getMessage(), ex);
            request.setAttribute("error", messages.getString("LoginException") );
            request.setAttribute("type", type);
            request.getRequestDispatcher("indexTest.jsp").forward(request, response);
        }catch (LoginException ex) {
            Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.INFO, ex.getMessage(), ex);
            request.setAttribute("error", messages.getString("LoginException"));
            request.setAttribute("type", type);
            request.getRequestDispatcher("indexTest.jsp").forward(request, response);
        }catch (Exception ex) {
            Logger.getLogger(PublicAreaServlet.class.getName()).log(Level.INFO, ex.getMessage(), ex);
            request.setAttribute("error", messages.getString("SessionExpired"));
            request.setAttribute("type", type);
            request.getRequestDispatcher("indexTest.jsp").forward(request, response);
        }
    }

    /**
     * Consulta los datos relacionados con una subasta para actualizar la información presentada en pantalla.
     * @param request
     * @param list Listado de subastas
     * @param currentAuction Subasta Actual
     */
    private void refreshPanel(HttpServletRequest request,List<Auction> list, Auction currentAuction){
        CommonQueries common = new CommonQueries();

        HashMap winnerBids = new HashMap();
        for (Auction auc : list){
            try {
                winnerBids.put(auc.getIdAuction(), common.getWinnerBid(auctionsFacade, auc.getIdAuction()));
            } catch (NoWinnerException ex) {
                Bid bid = new Bid();
                bid.setBidAmount(0);
                winnerBids.put(auc.getIdAuction(), bid );
            }
        }
        request.getSession().setAttribute("winnerBids", winnerBids );
        request.getSession().setAttribute("activeAuctions", list );
        //request.getSession().setAttribute("chipsExpenditure", common.getActiveAuctionsByUser(auctionsFacade, user.getIdUser()));
        request.getSession().setAttribute("lastAuction", currentAuction);
        try {
            request.getSession().setAttribute("winnerBid", common.getWinnerBid(auctionsFacade, currentAuction.getIdAuction()));
        } catch (NoWinnerException ex) {
            request.getSession().setAttribute("winnerBid",null);
            Logger.getLogger(RestrictedAreaServlet.class.getName()).log(Level.INFO, ex.getMessage());
        }
    }

    /**
     * Consulta los datos relacionados con una subasta y una categoría para actualizar la información presentada en pantalla.
     * @param request
     * @param currentAuction Subasta Actual
     * @param idCategory ID de la categoría
     */
    private void refreshPanelByCategory(HttpServletRequest request, Auction currentAuction, Integer idCategory){
        CommonQueries common = new CommonQueries();
        List<Auction> list = common.getAuctionsByCategory(auctionsFacade, idCategory);
        request.getSession().setAttribute("activeAuctions", list );
        //request.getSession().setAttribute("chipsExpenditure", common.getActiveAuctionsByUser(auctionsFacade, user.getIdUser()));
        HashMap winnerBids = new HashMap();
        for (Auction auc : list){
            try {
                winnerBids.put(auc.getIdAuction(), common.getWinnerBid(auctionsFacade, auc.getIdAuction()));
            } catch (NoWinnerException ex) {
                Bid bid = new Bid();
                bid.setBidAmount(0);
                winnerBids.put(auc.getIdAuction(), bid );
            }
        }
        request.getSession().setAttribute("winnerBids", winnerBids );
        request.getSession().setAttribute("lastAuction", currentAuction);
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
