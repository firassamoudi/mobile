/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.Services.PromoService;
import com.codename1.uikit.Services.ServicePromotion;
import com.codename1.uikit.entites.Promotion;
import java.io.IOException;
import java.util.ArrayList;
 
import java.util.Date;
import java.util.List;

/**
 *
 * @author sana
 */
public class Affichage extends SideMenuBaseForm{

     Form f;
    SpanLabel lb;
    Container cnt;
   
    Label datedebut, datefinp,description;
  
    public Affichage()  {
       // cnt.setUIID("Container");
         
       f = new Form("Nos Promotions ");
        cnt=new Container();
      //  cnt.setUIID("WalkthruTab1");
        lb = new SpanLabel();
                f.getStyle().setBgImage(UIManager.initFirstTheme("/theme").getImage("backgrpund.jpg"));

        f.add(lb);
        PromoService ProduitService=new PromoService();
        ArrayList<Promotion> l = ProduitService.getList2();
        for (int i = 0; i < l.size(); i++) {

            cnt.add(addItem(l.get(i)));
            

        }
        f.add(cnt); 
        
     //   lb.setText(ProduitService.getList2().toString());
        /*/
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://41.226.11.243:10004/tasks/");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                List<Task> list = ser.getListTask(new String(con.getResponseData()));
                System.out.println("sana");
                System.out.println(list);
                lb.setText(list.toString());
               
                System.out.println(lb.getText());
                f.refreshTheme();
            }
        });
        //*/
         
          
    }
      public void addContainer(Promotion p ) {
       /*
          Label idd = new Label(id);
        idd.setUIID(id);

        Label desc = new Label(description);
        desc.setUIID(description);
        Button image = new Button(theme.getImage("round.png").fill(50, 50));
        Container cnt1 = BorderLayout.west(image);
        cnt1.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        idd,
                        BoxLayout.encloseX(desc)
                ));

        image.addActionListener(e -> {
            makeForm(id).show();
        });

        f2.add(cnt1);
    }

    **/}
      public Form makeForm(int id ,Promotion p ) {
          Form f2 =new Form() ; 
        ConnectionRequest con = new ConnectionRequest();
        Form f = new Form("Detail de promo ", BoxLayout.y());
        f.getStyle().setBgImage(UIManager.initFirstTheme("/theme").getImage("backgrpund.jpg"));
        f.getStyle().setBgTransparency(5);
          
        Container cnt = new Container(BoxLayout.y());
        EncodedImage encImg  = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("round-mask.png"),false);
      System.err.println(p.getUrlPromo());
     URLImage imgUrl = URLImage.createToStorage(encImg, p.getUrlPromo(), "http://localhost/Promotion/"+p.getUrlPromo());
 imgUrl.fetch();
 ImageViewer image = new ImageViewer(imgUrl);
      
        cnt.add(image);
       
        String Url = "http://localhost/web-master/web/app_dev.php/api/promotions/find/"+ id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(" STRRR " + p);
            System.out.println("date DEDHEDHE DHHDE" +p.getDateDebut());
            String date = p.getDateDebut();
            System.out.println("LOOOOOOOOOOONG"+date.length());
            String s = date.substring(8,10);
         //   System.out.println("sssssssssssssssssssssssss "+s);
            s= date.substring(11, 13)+"-"+s ; 
          //  System.out.println("mmmmmmmmmmmmmmmmm"+s);
            s= date.substring(24, 28)+"-"+s ;
         //   System.out.println("DATEEEEEEEEEEEEEEEEEE +"+s);
             //String date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(p.getDateDebut());
          //System.out.println("ssssssssssssssssssssssssssssssssssss"+date);
           String datefi = p.getDateFin();
            System.out.println("SATEEEEEEEEEEEEEE DIN "+datefi);
            
          //  System.out.println("LOOOOOOOOOOONG"+date.length());
            String s1 = datefi.substring(8,10);
           // System.out.println("sssssssssssssssssssssssss "+s);
            s1= datefi.substring(11, 13)+"-"+s1 ; 
        //    System.out.println("mmmmmmmmmmmmmmmmm"+s);
            s1= datefi.substring(24, 28)+"-"+s1 ;
            System.out.println("DATEEEEEEEEEEEEEEEEEE +"+s1);
          datedebut = new Label ();
             Label dated = new Label ("Date debut :"+s) ; 
             Label datef = new Label("Date fin : " + s1);
              String reduction1 = Integer.toString(p.getReduction());
             Label reductionl = new Label ("Reduction " +reduction1);
             String like = Integer.toString(p.getLikes());
             Label Likes = new Label ("Likes : "  + like);
            Label description1 = new Label ("Description : "+p.getDescription());
            cnt.add(description1);
            cnt.add(dated);
            cnt.add(datef); 
            cnt.add(reductionl);
            cnt.add(Likes);
            PromoService ser = new PromoService(); 
            String sl = ser.getListUser(p.getIdPromo());
            Label moleha = new Label ("Proprietaire " +sl);
            cnt.add(moleha);
        }); NetworkManager.getInstance().addToQueueAndWait(con);

        f.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_WEB, e -> {
            f.show();
        });

     
        f.getToolbar().addCommandToRightBar("back", null, (ev)-> {
             Affichage h=new Affichage();
          h.getF().show();
          });
   
        f.getToolbar().addMaterialCommandToOverflowMenu("Afficher", FontImage.MATERIAL_WEB, e -> {
           Affichage a = new Affichage();
           a.show();
        });
        f.add(cnt);
        return f;
    }
    public Container addItem(Promotion p) {
       
        
    //   prix = new Label(p.getPrix().toString());
          //     prix.setText(p.getPrix().toString());
        description = new Label(p.getDescription());
        
       datefinp = new Label(p.getDateFin());
        Container cnt1 = new Container(BoxLayout.y());
        
        Container cnt2 = new Container(BoxLayout.x());
         
        cnt1.add(description);
         PromoService ser = new PromoService(); 
            String s = ser.getListUser(p.getIdPromo());
            Label moleha = new Label (s);
            cnt1.add(moleha);
          //  cnt1.add(datefinp);
       // cnt1.add(datedebut);
//           String date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(p.getDateDebut());
  //         System.out.println("ssssssssssssssssssssssssssssssssssss"+date);
//     datedebut = new Label (date);
        EncodedImage encImg  = EncodedImage.createFromImage(UIManager.initFirstTheme("/theme").getImage("round-mask.png"),false);
      System.err.println(p.getUrlPromo());
     URLImage imgUrl = URLImage.createToStorage(encImg, p.getUrlPromo(), "http://localhost/Promotion/"+p.getUrlPromo());
 imgUrl.fetch();
 ImageViewer image = new ImageViewer(imgUrl);
        System.out.println("PROMOOOOOOOOOO"+ p.getIdPromo());
        image.addPointerPressedListener((e)->{
        makeForm(p.getIdPromo() , p).show();
        
    });
description.addPointerPressedListener((e)->{
        makeForm(p.getIdPromo() , p).show();
        
    });
 cnt1.getStyle().setBorder(Border.createRidgeBorder(1));
 cnt1.add(image);

       // cnt2.add(cnt1);
        //cnt2.getStyle().setBgColor(0x99CCCC);
       //image.addPointerPressedListener(e -> {
         //   makeForm(p.getIdPromo()).show();
        //});

        return cnt1;
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    @Override
    protected void showOtherForm(Resources res) {
       //To change body of generated methods, choose Tools | Templates.
    }
    


}
