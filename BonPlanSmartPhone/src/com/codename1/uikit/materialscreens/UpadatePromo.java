/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.uikit.Services.PromoService;
import com.codename1.uikit.entites.Promotion;
import com.codename1.uikit.entites.Utilisateur;
import java.util.ArrayList;

/**
 *
 * @author Monia
 */
public class UpadatePromo {
    TextField tnom; 
    TextField tsujet; 
    int i ; 
    Form f ; 
    Button btnajout; 
    Container cnt ; 
    Label lb ; 
    public UpadatePromo (){
        f = new Form("Nos Promotions ");
        cnt=new Container();
      //  cnt.setUIID("WalkthruTab1");
        lb = new Label();
        PromoService P=new PromoService();
        int id = Utilisateur.connected_id; 
        ArrayList<Promotion> l = P.rechercheRestau(7);
    PromoService s = new PromoService();
                f.getStyle().setBgImage(UIManager.initFirstTheme("/theme").getImage("backgrpund.jpg"));
               

        f.add(lb);
        
         
    //    for (int i =0; i<l.size();i++)
        {
        
 //       f = new Form("Modifier restaurant");
    //    tnom = new TextField(s.rechercheRestau(7).get(i).getDescription());
     //   tnom.setUIID("TextFieldBlack");
     //   ttitre = new TextField();
       // ttitre.setUIID("TextFieldBlack");
   //     tsujet = new TextField();
   //     tsujet.setUIID("TextFieldBlack");
        //temail = new TextField();
        //temail.setUIID("TextFieldBlack");
        //ttel = new TextField();
        //ttel.setUIID("TextFieldBlack");
        //timage = new TextField();
        //timage.setUIID("TextFieldBlack");
        btnajout = new Button("Passer");
        //btnaff=new Button("Afficher mes reclamations");
     //   f.add(l1);
    //    f.add(tnom);
       // f.add(l2);
      //  f.add(temail);
        //f.add(l3);
    //    f.add(ttitre);
      //  f.add(l4);
      //  f.add(tsujet);
      //  f.add(l5);
      //  f.add(ttel);
        //Button upload = new Button("ajouter image");
        //f.add(upload);
        //f.add(timage);
        //f.add(timage);
        f.add(btnajout);
        getF().show();
        
        
        btnajout.addActionListener((e) -> {
           
            PromoService ser = new PromoService();
            ser.UpdateRestau(7,tnom.getText());
            //System.out.println(r.toString());

            

        });

        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}