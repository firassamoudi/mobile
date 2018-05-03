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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entites.Utilisateur;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author Monia
 */
public class InscriptionVisiteurForm {
    Form f;
	TextField nomVisiteur;
	TextField prenomVisiteur;
	TextField ville;
	//TextField Desc;
	   Button ajouter,AjouterImage;
	   Container registerContainer;
	   ImageViewer iv;
	public InscriptionVisiteurForm(Resources theme){
		Utilisateur u = new Utilisateur();
		nomVisiteur=new TextField();
		nomVisiteur.setHint("nom Visiteur");
		ville=new TextField();
		ville.setHint("ville");
	//	Tel=new TextField();
	//	Tel.setHint("Téléphone");
		prenomVisiteur=new TextField();
		prenomVisiteur.setHint("Prenom Visiteur");
		AjouterImage= new Button("Photo de Profil");
		iv = new ImageViewer();
		ajouter=new Button("Ajouter");
		ajouter.addActionListener(e->{
			
		});
		
		AjouterImage.addActionListener(e -> {
Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
    if (ev != null && ev.getSource() != null) {
        String path = (String) ev.getSource();
        iv.setHeight(100);
        iv.setWidth(100);
        setImage(path, iv ,u);
        System.out.println("hethi  l p  bel image "+u );
        System.out.println("hetha l pathhhhhhhh "+ path );
        try {
            Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
        } catch (IOException e1) {
            System.out.println(e1.getMessage());
        }
    }
}, Display.GALLERY_IMAGE);
});
		registerContainer=new Container(BoxLayout.y());
		AjouterImage.setUIID("SkipButton");
		registerContainer.add(nomVisiteur).add(prenomVisiteur).add(ville).add(AjouterImage).add(ajouter);
		f=new Form("Inscription Visiteur");
                f.getStyle().setBgColor(1583656);
		f.add(registerContainer);
	}
	public void show(){
		f.show();
    }
	
	private void setImage(String filePath,ImageViewer iv , Utilisateur doc) {
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
                       doc.setPhotodeprofil(s);
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

}
