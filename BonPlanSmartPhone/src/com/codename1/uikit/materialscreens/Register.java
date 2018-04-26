/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;


import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.uikit.Services.UtilisateurService;
import com.codename1.uikit.entites.Utilisateur;

/**
 *
 * @author Monia
 */
	public class Register {
	TextField usernameField;
	TextField mdpField;
	TextField repeatMdpField;
	
	
	TextField emailField;
	
	
	
    RadioButton visiteurRadio;
	RadioButton proprietaireRadio;
	
	Container registerContainer;
	Form registerForm;
	Button registerBtn;

	public Register() {
		usernameField=new TextField();
		usernameField.setHint("Username");
		mdpField=new TextField();
		mdpField.setHint("Password");
		repeatMdpField=new TextField();
		repeatMdpField.setHint("Repeat");
		emailField=new TextField();
		emailField.setHint("Email");
		
		Container typeCompteContainer=new Container();
		Label proprietaire =new Label("Proprietaire");
		Label visiteur =new Label("Visiteur");
		
                              visiteurRadio =new RadioButton();
		proprietaireRadio=new RadioButton();
		
		typeCompteContainer.add(visiteur).add(visiteurRadio).add(proprietaire).add(proprietaireRadio);
		
		registerContainer=new Container(BoxLayout.y());
		registerBtn=new Button("Register");
		registerBtn.addActionListener(e->{
			String type="";
			String role="";
			if(this.visiteurRadio.isSelected())
				role="a:1:{i:0;s:13:\"ROLE_VISITEUR\";}";
			else role="a:1:{i:0;s:9:\"ROLE_PROP\";}";
			
			
			
			Utilisateur u=new Utilisateur(this.usernameField.getText(),this.mdpField.getText(),this.emailField.getText(),role);
			UtilisateurService us=new UtilisateurService();
			us.register(u);
		});
		registerContainer.add(usernameField).add(mdpField).add(repeatMdpField).add(emailField).add(typeCompteContainer).add(registerBtn);
		registerForm=new Form("Register");
		registerForm.add(registerContainer);
	}
	
	public void show(){
		registerForm.show();
	}
	
}