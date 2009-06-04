/*
 * LoginServiceImpl.java
 *
 * Created on November 10, 2008, 4:56 PM
*/

package com.reverse.server;
import com.reverse.ejb.session.ProposeditemFacadeLocal;
import com.reverse.server.commons.Commons;
import com.reverse.ejb.persistence.Proposeditem;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet encargado de crear artículos propuestos por los usuarios.
 * @author Luis Felipe Giraldo
 */
public class ProposeItemServlet extends HttpServlet {
    @EJB
    private ProposeditemFacadeLocal proposeditemFacade;
    private int yourMaxMemorySize;
    private File yourTempDirectory;
    private long yourMaxRequestSize = 10000000;
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
        try {

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
                       //UploadFileWindow.progressBar.setText("Done!");
                   } else {
                       //UploadFileWindow.progressBar.setText(("Loading "  + pBytesRead + " of " + pContentLength ));
                   }
               }
            };

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Set factory constraints
            factory.setSizeThreshold(yourMaxMemorySize);
            factory.setRepository(yourTempDirectory);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Set overall request size constraint
            upload.setSizeMax(yourMaxRequestSize);
            upload.setProgressListener(progressListener);
            // Parse the request
            List items = upload.parseRequest(req); /* FileItem */

            // Process the uploaded items
            Iterator iter = items.iterator();
            Proposeditem proposedItem = new Proposeditem();
            
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                System.out.println("Item Name: "+item.getFieldName());
                if (item.isFormField()) {
                    if ( "productTitle".equals(item.getFieldName()) ){
                        proposedItem.setTitle(item.getString());    
                    }else{
                        if ( "productDescription".equals(item.getFieldName())){
                            proposedItem.setDescription(item.getString());
                        }else
                        if ( "endDate".equals(item.getFieldName())){
                            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy h:m:s");
                            Date lineDate = formatter.parse(item.getString());
                            proposedItem.setEndDate(lineDate);
                        }
                    }
                    
                } else {
                    /**@TODO Seleccionar el id del usuario */
                    proposedItem.setUserNickname(req.getUserPrincipal().getName());
                 
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    System.out.println("FileName: "+fileName);
                    System.out.println("Extension: "+fileName.substring(fileName.lastIndexOf(".")));
                    String extension = fileName.substring(fileName.lastIndexOf("."));
                    
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();
                    long idPicture = GregorianCalendar.getInstance().getTimeInMillis();
                    String ruta = super.getServletContext().getRealPath("/") + "uploadedItems/"+idPicture+extension;
                    
                    File uploadedFile = new File(ruta);
                    FileOutputStream fos; 
                    DataOutputStream dos;
                    
                    fos = new FileOutputStream(uploadedFile);
                    dos=new DataOutputStream(fos);
                    dos.write(Commons.resizeImageAsJPG(item.get(), 150));
                    dos.close();
                    fos.close();
                    

                    String rutaRelativa = idPicture+extension;
                    proposedItem.setFileName(rutaRelativa);
                    proposedItem.setPicture(item.get());
                    
                }
            }
            proposeditemFacade.create(proposedItem);
        } catch (FileUploadException ex) {
            ex.printStackTrace();
            Logger.getLogger(ProposeItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e){
            e.printStackTrace();
            Logger.getLogger(ProposeItemServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
}
