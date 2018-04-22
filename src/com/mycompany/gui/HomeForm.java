/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompagny.Service.ServicePromotion;
import com.mycompany.Entite.Promotion;
import com.mycompany.Entite.Promotion;
import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
    TextField tdescription;
    TextField tetat;
    Button btnajout,btnaff;
   Picker datedebut;
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public HomeForm() {
        
        f = new Form("home",new BoxLayout(BoxLayout.Y_AXIS));
      
Picker datePicker = new Picker();
datePicker.setType(Display.PICKER_TYPE_DATE);


datePicker.setDate(new Date());


         
        tdescription = new TextField();
        tetat = new TextField();
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");
        f.add(tdescription);
        f.add(tetat);
        f.add(datePicker);
       Date datedebutp = datePicker.getDate();
       f.add(datePicker);
       Date datefinp=datePicker.getDate();
f.show();
 
        f.add(btnajout);
        f.add(btnaff);
        btnajout.addActionListener((e) -> {
            ServicePromotion ser = new ServicePromotion();
            int etat = Integer.parseInt(tetat.getText());
            Promotion t = new Promotion(tdescription.getText(),etat ,datedebutp , datefinp);
            ser.ajoutPromotion(t);
            

        });
        btnaff.addActionListener((e)->{
        Affichage a=new Affichage();
        a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tdescription;
    }

    public void setTnom(TextField tnom) {
        this.tdescription = tnom;
    }

}
