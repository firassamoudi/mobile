/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.materialscreens;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.Services.UtilisateurService;
import com.codename1.uikit.entites.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {
	private ConnectionRequest connectionRequest;
	private String url;
        public static Utilisateur user ;
    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome, ", "WelcomeWhite"),
                new Label("HayaWin", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
        
        Image profilePic = theme.getImage("logo.png");
        
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        
        
        TextField login = new TextField() ;
		login.setHint("Username");
        TextField password = new TextField() ;
		password.setHint("Password");
                password.setConstraint(TextField.PASSWORD);
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        
        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
			url="http://localhost/web-master/web/app_dev.php/api/login";

		connectionRequest=new ConnectionRequest();
			connectionRequest.setPost(false);
                         String hashed =login.getText().substring(0, 2)+"a"+login.getText().substring(3);
                         System.out.println("HAAAAAAAAAAAAAAAAASH " +hashed);
			connectionRequest.addArgument("username",login.getText());
			connectionRequest.addArgument("password",password.getText());
			connectionRequest.setUrl(url);
			connectionRequest.addResponseListener(a->{
				String resultat=new String(connectionRequest.getResponseData());
				Utilisateur user=new UtilisateurService().login(resultat);
                                // String hashed =user.getPassword().substring(0, 2)+"a"+user.getPassword().substring(3);        
                               //String password1 =  BCrypt.checkpw(password.getText(),hashed);
				
				if(user.getId()!=-1){
					Toolbar.setGlobalToolbar(false);
					new WalkthruForm(theme).show();
					Toolbar.setGlobalToolbar(true);
					user.connected_id=user.getId();
                                                                System.out.println("COnnnnnnnnn"+Utilisateur.connected_id);   
                                                                     
					
				}
				else{
					Dialog.show("Failed", "Login failed , please verify your informations .", "Ok",null);
				}
			});
 			NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
            
        });
        
        Button createNewAccount = new Button("CREATE NEW ACCOUNT");
        createNewAccount.addActionListener((e) -> 
        { Register register = new Register ( ); 
        register.show();

            
        });
        createNewAccount.setUIID("CreateNewAccountButton");
          Button PromotionButton = new Button("PROMOTIONS");
        PromotionButton.addActionListener((e) -> 
        { Affichage affichage= new Affichage(); 
        affichage.getF().show();});
        
        Button ajoutPromo = new Button("Ajouter Promotion");
        ajoutPromo.addActionListener((e) -> 
        { AjoutPromo a = new AjoutPromo(); 
        a.getF().show();

            
        }); 
       
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount,
                PromotionButton,
        ajoutPromo
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}
