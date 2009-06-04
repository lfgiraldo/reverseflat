/*
 * LoginServiceImpl.java
 *
 * Created on November 10, 2008, 4:56 PM
 *
 */

package com.reverse.server;
import com.reverse.ejb.session.AuctionFacadeLocal;
import com.reverse.ejb.persistence.Auction;
import com.reverse.ejb.persistence.Auctioncategory;
import com.reverse.ejb.persistence.Auctiontexttranslation;
import com.reverse.ejb.persistence.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet encargado de crear subastas en el área de administración
 * @author Luis Felipe Giraldo
 */
public class CreateAuctionServlet extends HttpServlet {
    @EJB
    private AuctionFacadeLocal auctionFacade;
    private int yourMaxMemorySize;
    private File yourTempDirectory;
    private long yourMaxRequestSize = 10000000;
    private Locale locale;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
        try {

            //@TODO Activarlo cuando sea necesario
            /*
            //Create a progress listener
            ProgressListener progressListener = new ProgressListener(){
               private long megaBytes = -1;
               public void update(long pBytesRead, long pContentLength, int pItems) {
                   long mBytes = pBytesRead / 100000;
                   if (megaBytes == mBytes) {
                       return;
                   }
                   megaBytes = mBytes;
                   if (pContentLength == -1) {
                       System.out.println("Cargando... "+megaBytes+" bytes");
                   } else {
                       System.out.println("Cargando... "+megaBytes+" bytes");
                   }
               }
            };
            */

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Set factory constraints
            factory.setSizeThreshold(yourMaxMemorySize);
            factory.setRepository(yourTempDirectory);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Set overall request size constraint
            upload.setSizeMax(yourMaxRequestSize);
            
            //@TODO Activarlo cuando sea necesario
            //upload.setProgressListener(progressListener);

            // Parse the request
            List items = upload.parseRequest(req); /* FileItem */

            // Process the uploaded items
            Iterator iter = items.iterator();
            Auction auction = new Auction();
            User user = (User) req.getSession().getAttribute("user");
            if (user != null){
                auction.setUserNickname(user.getNickname());
            }
            auction.setActive(false);
            auction.setType("Normal");
            //@TODO Definir qué hay que hacer con esto
            auction.setChipsPerBid1stQ(Short.parseShort("1"));
            auction.setChipsPerBid2ndQ(Short.parseShort("2"));
            auction.setChipsPerBid3rdQ(Short.parseShort("3"));
            auction.setChipsPerBid4thQ(Short.parseShort("4"));


            Auctiontexttranslation english = new Auctiontexttranslation();
            english.setLanguageCode("en");
            Auctiontexttranslation spanish = new Auctiontexttranslation();
            spanish.setLanguageCode("es");

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                System.out.println("Field Name... "+item.getFieldName());
                String fieldName = item.getFieldName();
                if (item.isFormField()) {
                    if ( "title_es".equals(fieldName) ){
                        spanish.setTitle(item.getString());
                    }else if ( "title_en".equals(fieldName) ){
                        english.setTitle(item.getString());
                    }else if ( "cogs".equals(fieldName)){
                        auction.setCogs(Float.parseFloat(item.getString()));
                    }else if ( "grossMargin".equals(fieldName)){
                        auction.setGrossMargin(Float.parseFloat(item.getString()));
                    }else if ( "chipCost".equals(fieldName)){
                        auction.setChipCost(Float.parseFloat(item.getString()));
                    }else if ( "minBids".equals(fieldName)){
                        auction.setMinBids(Integer.parseInt(item.getString()));
                    }else if ( "shortDescription_es".equals(fieldName)){
                        spanish.setShortDescription(item.getString());
                    }else if ( "shortDescription_en".equals(fieldName)){
                        english.setShortDescription(item.getString());
                    }else if ( "longDescription_es".equals(fieldName)){
                        spanish.setLongDescription(item.getString());
                    }else if ( "longDescription_en".equals(fieldName)){
                        english.setLongDescription(item.getString());
                    }else if ( "timeQ".equals(fieldName)){
                        auction.setMinutesAfterClose(Short.parseShort(item.getString()));
                    }else if ( "activate".equals(fieldName)){
                        auction.setActive(true);
                    }else if ( "type".equals(fieldName)){
                        auction.setType(item.getString());
                    }else if ( "category".equals(fieldName)){
                        auction.setCategoryidCategory(new Auctioncategory( Short.parseShort(item.getString()) ));
                    }
                    
                } else {

                    String fileName = item.getName();
                    String extension = fileName.substring(fileName.lastIndexOf("."));
                    //String contentType = item.getContentType();
                    //boolean isInMemory = item.isInMemory();
                    //long sizeInBytes = item.getSize();
                    long idPicture = GregorianCalendar.getInstance().getTimeInMillis();
                    //String ruta = super.getServletContext().getRealPath("/") + "uploadedItems/"+idPicture+extension;
                    String rutaRelativa = "uploadedItems/"+idPicture+extension;

                    if ( "productPic".equals(fieldName) ){
                        auction.setPictureFileName(rutaRelativa);
                        auction.setPicture(item.get());
                    }else if ( "circledPic".equals(fieldName) ){
                        auction.setCircledFileName(rutaRelativa);
                        auction.setCircledPicture(item.get());
                    }else if ( "collagePic".equals(fieldName) ){
                        auction.setMosaicFileName(rutaRelativa);
                        auction.setMosaicPicture(item.get());
                    }
                    /*
                    File uploadedFile = new File(ruta);
                    FileOutputStream fos; 
                    DataOutputStream dos;
                    
                    fos = new FileOutputStream(uploadedFile);
                    dos=new DataOutputStream(fos);
                    dos.write(Commons.resizeImageAsJPG(item.get(), 150));
                    dos.close();
                    fos.close();
                    */
                }
            }
            List languages = new ArrayList();
            languages.add(spanish);
            languages.add(english);
            auction.setAuctiontexttranslationCollection(languages);
            auction.setUserNickname(req.getUserPrincipal().getName());
            auctionFacade.create(auction);
            List<Auction> auctionsList = auctionFacade.findAll();
            req.getSession().setAttribute("auctionsList", auctionsList);
            resp.sendRedirect("adminArea.jsp");
        } catch (FileUploadException ex) {
            ex.printStackTrace();
            Logger.getLogger(CreateAuctionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e){
            e.printStackTrace();
            Logger.getLogger(CreateAuctionServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
}
