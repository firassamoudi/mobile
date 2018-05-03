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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.uikit.Services.UtilisateurService;
import com.codename1.uikit.entites.Utilisateur;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author Monia
 */
	public class Register {
	TextField usernameField;
	TextField mdpField;
	TextField repeatMdpField;
	Resources theme;

	TextField emailField;
	
	
	
    RadioButton visiteurRadio;
	RadioButton proprietaireRadio;
	Form RegisterForm; 
	Container registerContainer;
	Form registerForm;
	Button registerBtn;
        TextField nomVisiteur;
	TextField prenomVisiteur;
	TextField ville;
	//TextField Desc;
	   Button ajouter,AjouterImage;
	   
	   ImageViewer iv;
int test ; 
Utilisateur u ; 
	public Register() {
            System.out.println("loalalalalala");
            RegisterForm=new Form();
            registerContainer=new Container(BoxLayout.y());
		usernameField=new TextField();
                   
		usernameField.setHint("Username");
		mdpField=new TextField();
		mdpField.setHint("Password");
		repeatMdpField=new TextField();
		repeatMdpField.setHint("Repeat");
		emailField=new TextField();
		emailField.setHint("Email");
                             nomVisiteur=new TextField();
		nomVisiteur.setHint("nom Visiteur");
		ville=new TextField();
		ville.setHint("ville");
		prenomVisiteur=new TextField();
		prenomVisiteur.setHint("Prenom Visiteur");
		AjouterImage= new Button("Photo de Profil");
                ajouter = new Button("Inscription");
		iv = new ImageViewer();
                              registerForm=new Form("Register");
                             ajouter.setUIID("SkipButton");
		AjouterImage.setUIID("SkipButton");
		registerForm.add(usernameField);
                        registerForm.add(mdpField);
                        registerForm.add(repeatMdpField);
                        registerForm.add(emailField);
                        registerForm.add(nomVisiteur);
                        registerForm.add(prenomVisiteur);
                        registerForm.add(ville);
                        registerForm.add(AjouterImage);
                        registerForm.add(ajouter);
                        registerForm.add(iv);
                        
                        
	 registerForm.getStyle().setBgColor(1583656);
		 
              System.out.println("hahahahahhaha");
               //registerForm.getStyle().setBgColor(1583656);
		//f.add(registerContainer);
                   Utilisateur u  = new Utilisateur();
                AjouterImage.addActionListener(e -> {
                    System.out.println("henenenenenenenenenennenenenenenenenenenene");
Display.getInstance().openGallery(new ActionListener() {
@Override
public void actionPerformed(ActionEvent ev) {
    System.out.println("lrrnrnrnrnrnrnrnrn");
if (ev != null && ev.getSource() != null) {
String path = (String) ev.getSource();
iv.setHeight(100);
         iv.setWidth(100);
         System.out.println("haw hneeeeeeeeeeeeee");
      
 setImage(path, iv ,u);
    System.out.println("hethi  l p  bel image "+u );
    System.out.println("henenenenenennenene");
    
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
		
                String	role="a:1:{i:0;s:13:\"ROLE_VISITEUR\";}";
             
                
		ajouter.addActionListener(e->{
                    UtilisateurService us = new UtilisateurService();
                     
                    u.setEmail(this.emailField.getText());
                    u.setNomVisiteur(this.nomVisiteur.getText());
                    u.setRoles(role);
                    u.setUsername(this.usernameField.getText());
                    u.setPassword(this.mdpField.getText());
                    System.out.println("Password"+u.getPassword());
                    u.setPrenomVisiteur(this.prenomVisiteur.getText());
                     
                    System.out.println("haw hneeeeeeeeee");
                    System.out.println("uuuuuuuu"+u);
			us.register(u);
                        });
		//Container typeCompteContainer=new Container();
		//Label proprietaire =new Label("Proprietaire");
	//	Label visiteur =new Label("Visiteur");
		
               //               visiteurRadio =new RadioButton();
	//	proprietaireRadio=new RadioButton();
	//	
	//	typeCompteContainer.add(visiteur).add(visiteurRadio).add(proprietaire).add(proprietaireRadio);
		
	//	registerContainer=new Container(BoxLayout.y());
	//	registerBtn=new Button("Register");
	//	registerBtn.addActionListener(e->{
	//		String type="";
	//		String role="";
	//		if(this.visiteurRadio.isSelected()){
			
                            
                   //             InscriptionVisiteurForm b= new InscriptionVisiteurForm(theme);
                     //                          b.show();}
		//	else role="a:1:{i:0;s:9:\"ROLE_PROP\";}";{
		//	test =1 ; 
                }
			
			
			
		
		
	
		
	//	Tel=new TextField();
	//	
		
		 
	public void show(){
		registerForm.show();
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