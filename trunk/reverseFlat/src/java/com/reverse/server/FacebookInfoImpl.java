/*
 * FacebookInfoImpl.java
 *
 * Created on November 25, 2008, 5:12 PM
*/

package com.reverse.server;
import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookXmlRestClient;
import com.google.code.facebookapi.ProfileField;
import com.reverse.ejb.session.AuctionFacadeLocal;
import com.reverse.ejb.session.UserFacadeLocal;
import com.reverse.ejb.exception.NonExistentUserException;
import com.reverse.common.valueobjects.FacebookinfoVO;
import com.reverse.server.commons.CommonQueries;
import com.reverse.server.commons.Commons;
import com.reverse.ejb.persistence.Auction;
import com.reverse.ejb.persistence.Facebookinfo;
import com.reverse.ejb.persistence.User;
import com.sun.appserv.security.ProgrammaticLogin;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;

/**
 * Servlet encargado de conectarse a facebook, autenticarse contra el Realm de Glassfish y llenar la tabla facebookUser
 * @author Luis Felipe Giraldo
 */
public class FacebookInfoImpl extends HttpServlet {

    private String apiKey;
    private String secretKey;
    @EJB
    private UserFacadeLocal userFacade;
    @EJB
    private AuctionFacadeLocal auctionsFacade;



    @Override
    public void init() throws ServletException {
        super.init();
        apiKey = getInitParameter("apiKey");
        secretKey = getInitParameter("secretKey");

    }



     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {


         ProgrammaticLogin pl = new ProgrammaticLogin();
        try {
            request.getSession();
            String auth_token = request.getParameter("auth_token");
            FacebookinfoVO facebookInfoVO = getFacebookUser(auth_token, request, response);
            Facebookinfo facebookInfo = new Facebookinfo();
            String userPassword;
            if (facebookInfoVO != null){
                try{
                    facebookInfo = userFacade.findByFacebookId(facebookInfoVO.getFacebookId());
                    userPassword = userFacade.updatePassword4FacebookLogin(facebookInfo.getFacebookId());
                }catch(NonExistentUserException ex){
                    //Si el usuario no existe, se crea uno tomando como base la informaci'on de facebook.
                    userPassword = userFacade.createUserWithFacebookAccount(Commons.convertFacebookinfoVOToFacebookinfo(facebookInfoVO));
                }
                pl.login(facebookInfoVO.getFacebookId(), userPassword, "SnoitsRealm", request, response, true);

                if(request.isUserInRole("user")){
                    User user = new User();
                    user = (User) userFacade.findByUserNickname(facebookInfo.getFacebookId());
                    request.getSession().setAttribute("user", user);

                    CommonQueries common = new CommonQueries();
                    Auction currentAuction = common.getLastAuctionByUser(auctionsFacade, user.getIdUser());
                    String realPath = getServletContext().getRealPath("/");
                    request.getSession().setAttribute("lastActivity", common.getAuctionList(auctionsFacade, request, realPath, "lastActivity"));
                    List<Auction> enrolledAuction = common.getAuctionList(auctionsFacade,request,realPath,"enrolledAuctions");
                    if(enrolledAuction == null){
                        enrolledAuction = common.getAuctionList(auctionsFacade,request,realPath,"active");
                    }
                    request.getSession().setAttribute("activeAuctions", enrolledAuction );
                    request.getRequestDispatcher("RestrictedArea?option=refresh&idAuction="+currentAuction.getIdAuction()+"&type=enrolledAuctions").forward(request, response);

                }
                else{
                    if(request.isUserInRole("admin")){
                        response.sendRedirect("AdminArea?option=login");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("indexTest.jsp?error=invalid");
        }
     }
    public FacebookinfoVO getFacebookUser(String auth_token, HttpServletRequest request, HttpServletResponse response) {
        // Do something interesting with 's' here on the server.
        FacebookinfoVO facebookUser = new FacebookinfoVO();
        //@TODO Arreglar información de Facebook
        
        //facebookUser.setLogged(false);
        FacebookXmlRestClient facebookRestClient = null;
        String sessionKey = (String) request.getSession().getAttribute("facebookSessionKey");
        //HttpServletRequest request = this.getThreadLocalRequest();
        //String token = auth_token;
        
        //HttpSession session = getSession();
        if (sessionKey != null && sessionKey.length() > 0 ){
            facebookRestClient = new FacebookXmlRestClient(apiKey, secretKey, sessionKey);
        }else if ( auth_token != null ){
            facebookRestClient = new FacebookXmlRestClient(apiKey, secretKey);
           facebookRestClient.setIsDesktop(false);
           try{
               sessionKey = facebookRestClient.auth_getSession(auth_token);
               request.getSession().setAttribute("facebookSessionKey", sessionKey);
               
            
            } catch (FacebookException ex) {
                Logger.getLogger(FacebookInfoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                response.sendRedirect("http://www.facebook.com/login.php?api_key=" + apiKey + "&v=1.0");
                return null;
            } catch (IOException ex) {
                //Logger.getLogger(FacebookInfoImpl.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        try{

            long myId = facebookRestClient.users_getLoggedInUser();
            /*
           EnumSet<ProfileField> fields = EnumSet.of(
                   ProfileField.CURRENT_LOCATION,   ProfileField.FIRST_NAME, ProfileField.EDUCATION_HISTORY,
                   ProfileField.HAS_ADDED_APP, ProfileField.HOMETOWN_LOCATION, ProfileField.AFFILIATIONS,
                   ProfileField.BOOKS, ProfileField.BIRTHDAY, ProfileField.ACTIVITIES,
                   ProfileField.ABOUT_ME, ProfileField.PIC_SMALL, ProfileField.TV,
                   ProfileField.WORK_HISTORY, ProfileField.WALL_COUNT, ProfileField.MEETING_FOR,
                   ProfileField.QUOTES, ProfileField.PIC_BIG, ProfileField.NOTES_COUNT,
                   ProfileField.MUSIC, ProfileField.LAST_NAME, ProfileField.RELATIONSHIP_STATUS,
                   ProfileField.PIC, ProfileField.STATUS, ProfileField.PROFILE_UPDATE_TIME,
                   ProfileField.INTERESTS, ProfileField.TIMEZONE, ProfileField.PIC_SQUARE,
                   ProfileField.POLITICAL, ProfileField.IS_APP_USER, ProfileField.MOVIES,
                   ProfileField.NAME, ProfileField.MEETING_SEX, ProfileField.RELIGION,
                   ProfileField.SEX, ProfileField.SIGNIFICANT_OTHER_ID, ProfileField.HS_INFO

                   ); */



            try {
            // TODO code application logic here
            CRC32 crc = new CRC32();
            crc.update("lfgiraldo@gmail.com".getBytes());
            System.out.println(crc.getValue());


            MessageDigest md;
            md = MessageDigest.getInstance("MD5");
            byte[] md5hash = new byte[32];
            md.update("lfgiraldo@gmail.com".getBytes("iso-8859-1"), 0, "lfgiraldo@gmail.com".length());
            md5hash = md.digest();

            StringBuffer md5 = new StringBuffer();
                for (int i = 0; i < md5hash.length; i++) {
                    int halfbyte = (md5hash[i] >>> 4) & 0x0F;
                    int two_halfs = 0;
                    do {
                        if ((0 <= halfbyte) && (halfbyte <= 9))
                            md5.append((char) ('0' + halfbyte));
                        else
                            md5.append((char) ('a' + (halfbyte - 10)));
                        halfbyte = md5hash[i] & 0x0F;
                    } while(two_halfs++ < 1);
                }
                System.out.println(md5.toString());

                TreeMap accountData = new TreeMap();
                accountData.put("email_hash", crc.getValue()+"_"+md5.toString());
                accountData.put("connect_registerUsers", myId);

                //accounts
                List accounts = new ArrayList();
                accounts.add(accountData);
                
                System.out.println( facebookRestClient.connect_registerUsers(accounts) );

            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FacebookInfoImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FacebookInfoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            





            Collection<Long> users = new ArrayList();
           List<ProfileField> fields = Arrays.asList(ProfileField.values());

           users.add(myId);
           Document d = facebookRestClient.users_getInfo(users, fields);

           StringBuffer ret = new StringBuffer();
           for (ProfileField pfield : fields){
            ret.append(pfield.fieldName()).append(d.getElementsByTagName(pfield.fieldName()).item(0).getTextContent()).append(" ");
            ret.append("\n");
           }
           System.out.println(ret.toString());

           facebookUser.setFacebookId(""+myId);
           facebookUser.setCurrentLocation(d.getElementsByTagName("current_location").item(0).getTextContent());
           facebookUser.setFirstName(d.getElementsByTagName("first_name").item(0).getTextContent());
           facebookUser.setEducationHistory(d.getElementsByTagName("education_history").item(0).getTextContent());
           facebookUser.setHasAddedApp(Boolean.getBoolean(d.getElementsByTagName("has_added_app").item(0).getTextContent()));
           facebookUser.setHometownLocation(d.getElementsByTagName("hometown_location").item(0).getTextContent());
           facebookUser.setAffiliations(d.getElementsByTagName("affiliations").item(0).getTextContent());
           facebookUser.setBooks(d.getElementsByTagName("books").item(0).getTextContent());
           //facebookUser.setBirthday(d.getElementsByTagName("birthday").item(0).getTextContent());
           facebookUser.setActivities(d.getElementsByTagName("activities").item(0).getTextContent());
           facebookUser.setAboutMe(d.getElementsByTagName("about_me").item(0).getTextContent());
           facebookUser.setPicSmall(d.getElementsByTagName("pic_small").item(0).getTextContent());
           facebookUser.setWorkHistory(d.getElementsByTagName("work_history").item(0).getTextContent());
           facebookUser.setWallCount(d.getElementsByTagName("wall_count").item(0).getTextContent());
           facebookUser.setMeetingFor(d.getElementsByTagName("meeting_for").item(0).getTextContent());
           facebookUser.setQuotes(d.getElementsByTagName("quotes").item(0).getTextContent());
           facebookUser.setPicBig(d.getElementsByTagName("pic_big").item(0).getTextContent());
           facebookUser.setNotesCount(d.getElementsByTagName("notes_count").item(0).getTextContent());
           facebookUser.setMusic(d.getElementsByTagName("music").item(0).getTextContent());
           facebookUser.setLastName(d.getElementsByTagName("last_name").item(0).getTextContent());
           facebookUser.setRelationshipsStatus(d.getElementsByTagName("relationship_status").item(0).getTextContent());
           facebookUser.setPic(d.getElementsByTagName("pic").item(0).getTextContent());
           facebookUser.setStatus(d.getElementsByTagName("status").item(0).getTextContent());
           //facebookUser.setProfileUpdateTime(d.getElementsByTagName("profile_update_time").item(0).getTextContent());
           facebookUser.setInterests(d.getElementsByTagName("interests").item(0).getTextContent());
           facebookUser.setTimezone(d.getElementsByTagName("timezone").item(0).getTextContent());
           facebookUser.setPicSquare(d.getElementsByTagName("pic_square").item(0).getTextContent());
           facebookUser.setPolitical(d.getElementsByTagName("political").item(0).getTextContent());
           facebookUser.setIsAppUser(Boolean.getBoolean(d.getElementsByTagName("is_app_user").item(0).getTextContent()));
           facebookUser.setMovies(d.getElementsByTagName("movies").item(0).getTextContent());
           facebookUser.setName(d.getElementsByTagName("name").item(0).getTextContent());
           facebookUser.setSignificantOtherId(d.getElementsByTagName("significant_other_id").item(0).getTextContent());
           facebookUser.setHsInfo(d.getElementsByTagName("hs_info").item(0).getTextContent());

           request.getSession().setAttribute("facebookUser", facebookUser);
           //TemplatizedAction action = new TemplatizedAction("{actor} recommends {site}");                      //the user has recommended a book


           HashMap map = new HashMap();
           map.put("site","<a href='http://www.snoits.com'>Snoits</a>");
           map.put("auction", "Playstation 3");
           //map.put("url2",url2);

            

           //Este número representa la autorización por parte de facebook para publicar en los feeds. 
            //@TODO MENSAJES FACEBOOK facebookRestClient.feed_publishUserAction(new Long("54051078300"),map,null,null);
           //System.out.println("Documento XML: "+d.toString());
           /*
           // Get my friends id
            Document d2 = facebookRestClient.friends_get();
            NodeList userIDNodes = d2.getElementsByTagName("uid");
            int fcount = userIDNodes.getLength();


            Collection<Long> friends = new ArrayList<Long>();
            for (int i = 0; i < fcount; i++) {
                Node node = userIDNodes.item(i);
                String idText = node.getTextContent();
                Long id = Long.valueOf(idText);
                friends.add(id);
            }

            ArrayList l = new ArrayList();
            UserVO m = new UserVO();
            Document d3 = facebookRestClient.users_getInfo(friends, fields);

            fields = EnumSet.of(
                   ProfileField.NAME,       ProfileField.PIC,           ProfileField.PIC_SMALL,
                   ProfileField.PIC_BIG
                   );
            // Get my friends information
            for (int j = 0; j < fcount; j++) {
                String name2 = d3.getElementsByTagName("name").item(j).getTextContent();
                String picture2 = d3.getElementsByTagName("pic").item(j).getTextContent();
                //String picture3 = d3.getElementsByTagName("pic_small").item(j).getTextContent();
                //String picture4 = d3.getElementsByTagName("pic_big").item(j).getTextContent();

                m.setName(name2);
                m.setPicture(picture2);
                //m.put("picture_small", picture3);
                //m.put("picture_big", picture4);

                l .add(m);
                m = new UserVO();

            }
           facebookUser.setFriends(l);

           facebookUser.setLogged(true);
           d = facebookRestClient.friends_get();
           */
        
        }catch (FacebookException ex){
            ex.printStackTrace();
        }
        return facebookUser;
        
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
