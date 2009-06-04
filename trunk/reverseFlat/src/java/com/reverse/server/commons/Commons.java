/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.server.commons;

import com.reverse.ejb.commons.valueobjects.CreditCardServer;
import com.reverse.common.valueobjects.AuctionVO;
import com.reverse.common.valueobjects.BidVO;
import com.reverse.common.valueobjects.ChipsincomeVO;
import com.reverse.common.valueobjects.CreditCardVO;
import com.reverse.common.valueobjects.FacebookinfoVO;
import com.reverse.common.valueobjects.UserVO;
import com.reverse.ejb.persistence.Auction;
import com.reverse.ejb.persistence.Auctiontexttranslation;
import com.reverse.ejb.persistence.Bid;
import com.reverse.ejb.persistence.Chipsincome;
import com.reverse.ejb.persistence.Facebookinfo;
import com.reverse.ejb.persistence.User;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;


/**
 *
 * @author Pipe
 */
@SuppressWarnings("sun")
public class Commons {

    public static User UserVOtoUser (UserVO userVO){
        User user = new User();
        user.setActive(userVO.getActive());
        user.setAddress(userVO.getAddress());
        //user.setBidCollection(userVO.getBidCollection());

        user.setBirthday(userVO.getBirthday());
        user.setCellPhone(userVO.getCellPhone());
        user.setEmail(userVO.getEmail());
        //user.setFacebookInfo(userVO.getFacebookInfo());

        user.setGender(userVO.getGender());
        user.setIdCity(userVO.getIdCity());
        user.setIdCountry(userVO.getIdCity());
        user.setIdUser(userVO.getIdUser());
        user.setLandPhone(userVO.getLandPhone());
        user.setLastName(userVO.getLastName());
        user.setName(userVO.getName());
        user.setNickname(userVO.getNickname());
        user.setPassword(userVO.getPassword());
        user.setPostalCode(userVO.getPostalCode());
        user.setRegisterDate(userVO.getRegisterDate());
        //user.setUserChipsCollection(userVO.getUserChipsCollection);
        //user.setUserRoleidRole(userVO.getIdRole());
        return user;
    }
    
    public static UserVO UsertoUserVO (User user){
        UserVO userVO = new UserVO();
        userVO.setAddress(user.getAddress());
        if (user.getBirthday() != null ){
            userVO.setBirthday(user.getBirthday());
        }
        
        userVO.setCellPhone(user.getCellPhone());
        userVO.setEmail(user.getEmail());
        userVO.setGender(user.getGender());
        userVO.setIdCity(user.getIdCity());
        userVO.setIdCountry(user.getIdCity());
        userVO.setIdUser(user.getIdUser());
        userVO.setLandPhone(user.getLandPhone());
        userVO.setLastName(user.getLastName());
        userVO.setName(user.getName());
        userVO.setNickname(user.getNickname());
        userVO.setPassword(user.getPassword());
        userVO.setPostalCode(user.getPostalCode());
        //userVO.setIdRole(user.getUserRoleidRole());
        userVO.setActive(user.getActive());
        return userVO;
    }

    public static User readRegisterForm (HttpServletRequest request){
        User user = new User();
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String password1 = request.getParameter("password1");
        String email = request.getParameter("email");

        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setNickname(userName);
        user.setPassword(password1);
        user.setActive(false);
        return user;
    }
    
    public static void convertBytesToFile(byte[] picture, String fullPath) {
        try {

            File uploadedFile = new File(fullPath);
            if (uploadedFile.exists()){
                //Logger.getLogger(Commons.class.getName()).log(Level.INFO, "Existing Pic...");
                return;
            }
            FileOutputStream fos;
            DataOutputStream dos;
            fos = new FileOutputStream(uploadedFile);
            dos = new DataOutputStream(fos);
            dos.write(picture);
            dos.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static Chipsincome ChipsincomeVOtoChipsincome (ChipsincomeVO chipsIncome){
        Chipsincome newIncome = new Chipsincome();
        newIncome.setChargeMethod(chipsIncome.getChargeMethod());
        newIncome.setChipsAmount(chipsIncome.getChipsAmmount());
        newIncome.setDate(chipsIncome.getDate());
        newIncome.setIdIncome(chipsIncome.getIdIncome());
        newIncome.setMoneyPaid(new BigDecimal(chipsIncome.getMoneyPaid()));
        newIncome.setTransactionNumber(chipsIncome.getTransactionNumber());
        //@TODO Cambiar idUsuario por Usuario completo
        newIncome.setUseridUser(new User(chipsIncome.getUserIduser()));
        return newIncome;
    }
    public static CreditCardServer CreditCardVOtoCreditCardServer (CreditCardVO creditCard){
        CreditCardServer ccs = new CreditCardServer();
        //ccs.setAmount(creditCard.getAmount());
        ccs.setCreditCardNumber(creditCard.getCreditCardNumber());
        ccs.setCreditCardType(creditCard.getCreditCardType());
        ccs.setCurrency(creditCard.getCurrency());
        ccs.setCvv2Number(creditCard.getCvv2Number());
        ccs.setExpdateMonth(creditCard.getExpdateMonth());
        ccs.setExpdateYear(creditCard.getExpdateYear());
        return ccs;
    }

    public static BidVO convertBidToBidVO(Bid bid) {
        BidVO bidVO = new BidVO();
        bidVO.setAuctionidAuction(bid.getAuctionidAuction().getIdAuction());
        bidVO.setBidAmount(bid.getBidAmount());
        bidVO.setBidDate(bid.getBidDate());
        bidVO.setIdBid(bid.getIdBid());
        bidVO.setUseridUser(Commons.UsertoUserVO(bid.getUseridUser()));
        return bidVO;
    }

    public static Bid convertBidVOToBid(BidVO bidVO) {
        Bid bid = new Bid();
        bid.setAuctionidAuction(convertAuctionVOToAuction(new AuctionVO(bidVO.getAuctionidAuction())));
        bid.setBidAmount(bidVO.getBidAmount());
        bid.setBidDate(bidVO.getBidDate());
        bid.setIdBid(bidVO.getIdBid());
        bid.setUseridUser(Commons.UserVOtoUser(bidVO.getUseridUser()));
        return bid;
    }

    public static AuctionVO convertAuctionToAuctionVO(Auction item, String locale) {
        AuctionVO valueObject = new AuctionVO();

        valueObject.setIdAuction(item.getIdAuction());
        valueObject.setAuctionBidEndDate(item.getEndDate());
        valueObject.setAuctionMinBids(item.getMinBids());
        valueObject.setAuctionPublishDate(item.getPublishDate());
        valueObject.setFileName(item.getPictureFileName());
        
        BidVO[] bidsList = null;
        if ( item.getBidCollection() != null ){
            bidsList = new BidVO[(item.getBidCollection().size())];
            Iterator iterator = item.getBidCollection().iterator();
            for(int i=0; iterator.hasNext(); i++){
                bidsList[i] = convertBidToBidVO( (Bid) iterator.next());
            }
        }

        //valueObject.setBidCollection(bidsList);
        for(Auctiontexttranslation language : item.getAuctiontexttranslationCollection()){
            if (locale.equalsIgnoreCase(language.getLanguageCode())){
                valueObject.setShortDescription(language.getShortDescription());
                valueObject.setLongDescription(language.getLongDescription());
                valueObject.setTitle(language.getTitle());
            }

        }
        valueObject.setUserNickname(item.getUserNickname());
        valueObject.setActive(item.getActive());

        return valueObject;
    }

    public static Auction convertAuctionVOToAuction(AuctionVO item) {
        Auction auction = new Auction();

        auction.setIdAuction(item.getIdAuction());
        auction.setEndDate(item.getAuctionBidEndDate());
        auction.setMinBids(item.getAuctionMinBids());
        auction.setPublishDate(item.getAuctionPublishDate());

        ArrayList bidsList = new ArrayList();

        /*
        BidVO[] bids = item.getBidCollection();
        if ( item.getBidCollection() != null ){
            for(int i=0; i < item.getBidCollection().length; i++){
                bidsList.add(convertBidVOToBid( bids[i] ));
            }
        }
         * */

        auction.setBidCollection(bidsList);
        //auction.setShortDescription(item.getShortDescription());
        //@TODO falta descripcion larga
        //auction.setLongDescription(item.getLongDescription());
        //auction.setTitle(item.getTitle());
        auction.setUserNickname(item.getUserNickname());
        auction.setActive(item.isActive());

        return auction;
    }

        /**
         * This method takes in an image as a byte array (currently supports GIF, JPG, PNG and possibly other formats) and
         * resizes it to have a width no greater than the pMaxWidth parameter in pixels. It converts the image to a standard
         * quality JPG and returns the byte array of that JPG image.
         *
         * @param pImageData
         *                the image data.
         * @param pMaxWidth
         *                the max width in pixels, 0 means do not scale.
         * @return the resized JPG image.
         * @throws IOException
         *                 if the iamge could not be manipulated correctly.
         */
        public static byte[] resizeImageAsJPG(byte[] pImageData, int pMaxWidth) throws IOException {
        // Create an ImageIcon from the image data
        ImageIcon imageIcon = new ImageIcon(pImageData);
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();
        // If the image is larger than the max width, we need to resize it
        if (pMaxWidth > 0 && width > pMaxWidth) {
            // Determine the shrink ratio
            double ratio = (double) pMaxWidth / imageIcon.getIconWidth();
            height = (int) (imageIcon.getIconHeight() * ratio);
            width = pMaxWidth;
            //System.out.println("imageIcon post scale width: #0  height: #1 "+ width + " " + height);

        }
        // Create a new empty image buffer to "draw" the resized image into
        BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Create a Graphics object to do the "drawing"
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw the resized image
        g2d.drawImage(imageIcon.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        // Now our buffered image is ready
        // Encode it as a JPEG
        ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(encoderOutputStream);
        encoder.encode(bufferedResizedImage);
        byte[] resizedImageByteArray = encoderOutputStream.toByteArray();
        return resizedImageByteArray;
    }

        public static Facebookinfo convertFacebookinfoVOToFacebookinfo(FacebookinfoVO vo) {
            Facebookinfo facebook = new Facebookinfo();
            facebook.setAboutMe(vo.getAboutMe());
            facebook.setActivities(vo.getActivities());
            facebook.setAffiliations(vo.getAffiliations());
            facebook.setBirthday(vo.getBirthday());
            facebook.setBooks(vo.getBooks());
            facebook.setCurrentLocation(vo.getCurrentLocation());
            facebook.setEducationHistory(vo.getEducationHistory());
            facebook.setFacebookId(vo.getFacebookId());
            facebook.setFirstName(vo.getFirstName());
            facebook.setHasAddedApp(vo.getHasAddedApp());
            facebook.setHometownLocation(vo.getHometownLocation());
            facebook.setHsInfo(vo.getHsInfo());
            facebook.setInterests(vo.getInterests());
            facebook.setIsAppUser(vo.getIsAppUser());
            facebook.setLastName(vo.getLastName());
            facebook.setMeetingFor(vo.getMeetingFor());
            facebook.setMeetingSex(vo.getMeetingSex());
            facebook.setMovies(vo.getMovies());
            facebook.setMusic(vo.getMusic());
            facebook.setName(vo.getName());
            facebook.setNotesCount(vo.getNotesCount());
            facebook.setPic(vo.getPic());
            facebook.setPicBig(vo.getPicBig());
            facebook.setPicSmall(vo.getPicSmall());
            facebook.setPicSquare(vo.getPicSquare());
            facebook.setPolitical(vo.getPolitical());
            facebook.setProfileUpdateTime(vo.getProfileUpdateTime());
            facebook.setQuotes(vo.getQuotes());
            facebook.setRelationshipsStatus(vo.getRelationshipsStatus());
            facebook.setReligion(vo.getReligion());
            facebook.setSex(vo.getSex());
            facebook.setSignificantOtherId(vo.getSignificantOtherId());
            facebook.setStatus(vo.getStatus());
            facebook.setTimezone(vo.getTimezone());
            facebook.setTv(vo.getTv());
            //facebook.setUser(Commons.UserVOtoUser(vo.getUser()));
            facebook.setUseridUser(vo.getUseridUser());
            facebook.setWallCount(vo.getWallCount());
            facebook.setWorkHistory(vo.getWorkHistory());

            return facebook;
        }

        public static byte[] mergeImages(byte[] imageBytesFront, byte[] imageBytesBack, int maxWidthSize){
            BufferedImage bufferedImageFront;
            BufferedImage bufferedImageBack;
            byte[] resultImageAsRawBytes = null;
            
            
            try {

                ImageIcon imageIconBack = new ImageIcon(imageBytesBack);
                //Image image2 = Toolkit.getDefaultToolkit().getImage("D:/Documents/NetBeansProjects/TestGWT/build/web/img/productCircle.gif");


                ImageIcon imageIconFront = new ImageIcon(imageBytesFront);
                int widthFront = imageIconFront.getIconWidth();
                int heightFront = imageIconFront.getIconHeight();

                int widthBack = imageIconBack.getIconWidth();
                int heightBack = imageIconBack.getIconHeight();



                int pMaxWidth = maxWidthSize;

                // If the image is larger than the max width, we need to resize it

                    // Determine the shrink ratio
                double ratio = (double) pMaxWidth / imageIconFront.getIconWidth();
                heightFront = (int) (imageIconFront.getIconHeight() * ratio);
                widthFront = pMaxWidth;
                //System.out.println("imageIcon post scale width: 0  height: 1 "+ width + " " + height);



                // Determine the shrink ratio
                ratio = (double) pMaxWidth / imageIconBack.getIconWidth();
                heightBack = (int) (imageIconBack.getIconHeight() * ratio);
                widthBack = pMaxWidth;
                //System.out.println("imageIcon post scale width: 0  height: 1 "+ width + " " + height);



                  //TRANSFORMAR CIERTO COLOR EN TRANSPARENTE
              //Graphics2D g = dimg.createGraphics();






                //image1 = bufferedResizedImage.getGraphics();
                bufferedImageFront = new BufferedImage(widthFront, heightFront, BufferedImage.TYPE_INT_ARGB);

                bufferedImageFront = blurImage(bufferedImageFront);

                Graphics2D graphic2D = bufferedImageFront.createGraphics();

                graphic2D = bufferedImageFront.createGraphics();

                graphic2D.setComposite(AlphaComposite.Src);

                graphic2D.drawImage(imageIconFront.getImage(),0, 0, widthFront, heightFront, null);

                graphic2D.dispose();

                 for(int i = 0; i < bufferedImageFront.getHeight(); i++) {
                     for(int j = 0; j < bufferedImageFront.getWidth(); j++) {
                         if(bufferedImageFront.getRGB(j, i) == Color.WHITE.getRGB() ) {
                            bufferedImageFront.setRGB(j, i, Color.TRANSLUCENT);
                         }
                     }
                 }
                 

                bufferedImageBack = new BufferedImage(widthBack, heightBack, BufferedImage.TYPE_INT_ARGB);

                bufferedImageBack = blurImage(bufferedImageBack);

                graphic2D = bufferedImageBack.createGraphics();
                graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                graphic2D.drawImage(imageIconBack.getImage(), 0, 0, widthBack, heightBack, null);

                graphic2D.dispose();

                 for(int i = 0; i < bufferedImageBack.getHeight(); i++) {
                     for(int j = 0; j < bufferedImageBack.getWidth(); j++) {
                         if(bufferedImageBack.getRGB(j, i) == Color.WHITE.getRGB() ) {
                            bufferedImageBack.setRGB(j, i, Color.TRANSLUCENT);
                         }
                     }
                 }

                 //g2d.drawImage(bufferedResizedImage, null, 0, 0);


                BufferedImage mixedImage = new BufferedImage(widthFront > widthBack? widthFront:widthBack, heightFront > heightBack? heightFront: heightBack, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = mixedImage.createGraphics();

                Point2D center =  new Point2D.Float(mixedImage.getWidth() / 2, mixedImage.getHeight() / 2);
                AffineTransform at = AffineTransform.getTranslateInstance(center.getX( ) - (widthBack / 2), center.getY( ) - (heightBack / 2));

                g2.transform(at);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawImage(bufferedImageBack, 0, 0, null);

                //Add transparency
                //Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .80f);
                //g2.setComposite(c);

                at = AffineTransform.getTranslateInstance(center.getX( ) - (widthFront / 2), center.getY( ) - (heightFront / 2));
                g2.setTransform(at);
                g2.drawImage(bufferedImageFront, 0, 0, null);


                
                
                try {
                    //ImageIO.write(mixedImage, "png", new File("img/prueba.png"));

                    ByteArrayOutputStream baos = new ByteArrayOutputStream( 1000 );

                    // W R I T E
                    ImageIO.write( mixedImage, "png" /* "png" "jpeg" ... format desired */,
                               baos );

                    // C L O S E
                    baos.flush();
                    resultImageAsRawBytes = baos.toByteArray();

                    baos.close();
                   
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }catch (ImageFormatException ex) {
                Logger.getLogger(Commons.class.getName()).log(Level.SEVERE, null, ex);
            }
             return resultImageAsRawBytes;
    }

      public static byte[] getBytesFromFile(String filePath) throws IOException {
            File file = new File(filePath);
            InputStream is = new FileInputStream(file);

            // Get the size of the file
            long length = file.length();

            // You cannot create an array using a long type.
            // It needs to be an int type.
            // Before converting to an int type, check
            // to ensure that file is not larger than Integer.MAX_VALUE.
            if (length > Integer.MAX_VALUE) {
                throw new IOException("File is too large "+file.getName());
            }

            // Create the byte array to hold the data
            byte[] bytes = new byte[(int)length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }

            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "+file.getName());
            }

            // Close the input stream and return bytes
            is.close();
            return bytes;
        }

        public static BufferedImage blurImage(BufferedImage image) {
            float ninth = 1.0f/9.0f;
            float[] blurKernel = {
            ninth, ninth, ninth,
            ninth, ninth, ninth,
            ninth, ninth, ninth
            };

            Map map = new HashMap();

            map.put(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            RenderingHints hints = new RenderingHints(map);
            BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
            return op.filter(image, null);
        }
}
