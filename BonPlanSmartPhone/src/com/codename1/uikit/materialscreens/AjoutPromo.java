/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.uikit.Services.PromoService;
import com.codename1.uikit.Services.ServicePromotion;
import com.codename1.uikit.entites.Promotion;
import com.codename1.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import javafx.scene.paint.Color;
 


/**
 *
 * @author Monia
 */
public class AjoutPromo {
        Form f;
    TextField tdescription;
    TextField tetat;
    Button btnmodif, btnajout,btnaff,btnstat,AjouterImage;
    Picker datefinp ,datedebut;
    Label dated , datef , reduction , description ; 
    Calendar calendrier ;
    ImageViewer iv ; 
        
    
  public AjoutPromo(){
      
      f = new Form("Ajouter Promotion ");
      f.getStyle().setBgColor(1583656);
Picker datePicker = new Picker();
datePicker.setType(Display.PICKER_TYPE_DATE);
Picker datePickerfin = new Picker();
datePickerfin.setType(Display.PICKER_TYPE_DATE);

  Calendar calendrier = new Calendar ();
     Date datedebut = datePicker.getDate();
     SimpleDateFormat sdfDate = new SimpleDateFormat("yy-MM-dd");
			String strDate = sdfDate.format(datedebut);
                         
     Date datefinp = datePickerfin.getDate();
     SimpleDateFormat sdfDate1 = new SimpleDateFormat("yy-MM-dd");
			String strDate1 = sdfDate1.format(datefinp);
        tdescription = new TextField();
        tetat = new TextField();
        dated = new Label ("Date debut Promotion");
        datef = new Label ("Date fin Promotion ") ; 
        reduction = new Label ( "Reduction " ); 
        description = new Label ("Description ") ; 
        btnajout = new Button("ajouter");
        btnmodif = new Button("modifier");
          btnajout.setUIID("LoginButton");
        AjouterImage = new Button ("ajouter Image "); 
        AjouterImage.setUIID("LoginButton");
        btnstat= new Button ("statique ");
        btnstat.setUIID("LoginButton");
        
        f.add(description);
        f.add(tdescription);
        f.add(reduction);
        f.add(tetat);
        f.add(dated);
        f.add(datePicker);
        f.add(datef);
        f.add(datePickerfin);
         f.add(AjouterImage);
          Promotion p = new Promotion ();
        f.add(btnajout);
        iv = new ImageViewer();
        f.add(iv);
  f.add(btnmodif);
        f.add(btnstat);
         
           
 
   //     System.out.println(AjoutPromo.convertDate(datedebut) + "v  whehutututututu ");
        String datedeb = calendrier.getName();
        System.out.println(datedeb + "hayayayayahhayaha ");
      AjouterImage.addActionListener(e -> {
Display.getInstance().openGallery(new ActionListener() {
@Override
public void actionPerformed(ActionEvent ev) {
if (ev != null && ev.getSource() != null) {
String path = (String) ev.getSource();
iv.setHeight(100);
         iv.setWidth(100);
 setImage(path, iv ,p);
    System.out.println("hethi  l p  bel image "+p );
    
    System.out.println("hetha l pathhhhhhhh "+ path );

try {
    Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
} catch (IOException e) {
System.out.println(e.getMessage());
}
}
}
}, Display.GALLERY_IMAGE);
});
        /*AjouterImage.addActionListener((ev) -> {
            Display.getInstance().openGallery((e) -> {
                if(e == null || e.getSource() == null) {
                    System.out.println("Galerie annulée");
                }else{
//                    String file = "";
//    String extension;
                String filePath = (String)e.getSource();
                    System.out.println(filePath +"hethi FILE PATH ");
               Promotion p = new Promotion ();
               setImage(filePath, iv,p);
                    System.out.println(p +" hethiiiiiiiiiiii ");
                //System.out.println(Documents);
//if (e != null && e.getSource() != null) {
//                        file = (String) e.getSource();
//                        System.out.println(file);
//                        extension = null;
//                        if (file.lastIndexOf("/") > 0) {
//                            extension = file.substring(file.lastIndexOf("/") + 1);
//                            System.out.println("" + extension);
//                        }
//                  
//                    }
                }
            }, Display.GALLERY_IMAGE);

      });*/
        btnajout.addActionListener((ActionEvent e) -> {
    
         
              PromoService ser = new PromoService();
              int etat = Integer.parseInt(tetat.getText());
             
             
                p.setDescription(tdescription.getText()); 
                p.setReduction(etat);
               System.out.println("STRRRRRRRRRRRRRRr"+strDate);
                p.setDateDebut(strDate);
               p.setDateFin(strDate1);
               p.setEtat_promo(0);
     
            System.out.println(p +"hethiiiiiiiiiiiiiiiiiiiiiiii p ");
            ser.ajoutPromotion(p);
           Affichage af = new Affichage(); 
         af.show();
    
        });
        
        btnstat.addActionListener((e)->{
       statique a=new statique();
        a.createPieChartForm().show();
        });
        btnmodif.addActionListener((e)->{
       UpadatePromo a=new UpadatePromo();
        a.getF().show();
        });
    }
  private void setImage(String filePath,ImageViewer iv , Promotion doc) {
           try {
               Image i1 = Image.createImage(filePath);
               System.out.println("hethiiiiiiiiiiiiii iiiii "+ i1);
                iv.setWidth(100);
               iv.setHeight(100);
               iv.setImage(i1);
              
               if (i1 != null) {
                   //FileSystemStorage     
                   ImageIO imgIO = ImageIO.getImageIO();
                   ByteArrayOutputStream out = new ByteArrayOutputStream();
                   imgIO.save(i1, out, File.separator, 1);
                   byte[] ba = out.toByteArray();
                   String Imagecode = Base64.encode(ba);
                   ConnectionRequest request = new ConnectionRequest();
                   request.addResponseListener((NetworkEvent evt) -> {
                       byte[] data = (byte[]) evt.getMetaData();
                       String s = new String(data);
                       System.out.println(s);
                       doc.setUrlPromo(s);
                   });
                   request.setPost(true);
                   request.setHttpMethod("POST");
                   request.addArgument("Image", Imagecode);
                   request.setUrl("http://localhost:80/Upload.php");
                   NetworkManager.getInstance().addToQueueAndWait(request);
               } else {
                   System.out.println("Unable to upload");
               }
          

           } catch (Exception ex) {
               System.out.println(ex);
            
           }
            
       
    }
  
 private static String convertDate (String datePicker){
       Date date = new Date();
            String v=datePicker.replace('/','-');
            return v;
 }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  
 
}
