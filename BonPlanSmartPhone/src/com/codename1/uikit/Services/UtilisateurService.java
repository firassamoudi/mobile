/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.Services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.entites.Utilisateur;
import com.codename1.uikit.materialscreens.LoginForm;
import com.codename1.uikit.materialscreens.WalkthruForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ali
 */
public class UtilisateurService {
	private ConnectionRequest con,connectionRequest;
              
    
    /*public Utilisateur findUser(String login)
    {
        Utilisateur user = new  Utilisateur();
        user.setId(null);
        con = new ConnectionRequest();
        con.setUrl("http://localhost/MobileCupCakes/ScriptPHP/Utilisateur/info_id.php?uid='" +login+"'"); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
                public void actionPerformed(NetworkEvent evt) {
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> Users = j.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                        System.out.println(Users);
                        List<Map<String, Object>> list = (List<Map<String, Object>>) Users.get("info");
                        System.out.println("info : " + list);
                        if( list!=null)
                        {
                            for (Map<String, Object> obj : list) { 
                                user.setId(Integer.parseInt(obj.get("id").toString()));
                                user.setUsername(obj.get("username").toString());
                                user.setPassword(obj.get("password").toString());
                                user.setEmail(obj.get("email").toString());
                                user.setPhotodeprofil(obj.get("imageProfil").toString());
                                user.setAdresse(obj.get("adresse").toString());
                                user.setRoles(obj.get("roles").toString());
                                user.setTelephone(obj.get("telephone").toString());
                                user.setNomVisiteur(obj.get("nomVisiteur").toString());
                                user.setPrenomVisiteur(obj.get("prenomVisiteur").toString());
                            }
                        }
                    } catch (IOException ex) {
                    }
                }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return user;
    }*/
	
	public boolean verifFailed(String s,String toVerif){
		System.out.println(s.substring(1,s.length()-1).equals("Failed"));
		return s.substring(1,s.length()-1).equals(toVerif);
	}
	public Utilisateur login(String json)  {
		ArrayList<Utilisateur> user = new ArrayList<>();
		if(!verifFailed(json,"Failed")){
			try {
				System.out.println(json);
				JSONParser j = new JSONParser();
				System.out.println("	uuuuuuuu");
				Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
				System.out.println(obj);
				System.out.println("");
				//List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("root");
				Utilisateur u = new Utilisateur();
				System.out.println(obj.get("id"));
				float id = Float.parseFloat(obj.get("id").toString());
				System.out.println(id);
				u.setId((int) id);
				u.setNomVisiteur(""+obj.get("nomVisiteur"));
				u.setPrenomVisiteur(""+obj.get("prenomVisiteur"));
				u.setEmail(""+obj.get("email"));
				u.setAdresse(""+obj.get("adresse"));
				
				u.setTelephone(""+obj.get("telephone"));
				u.setVille(""+obj.get("ville"));
				System.out.println(u);
				user.add(u);
				System.out.println(user);
				return user.get(0);
			} catch (IOException ex) {
			}
		}
		Utilisateur u=new Utilisateur();
		u.setId(-1);
		return u;
	}
	
	 public ArrayList<Utilisateur> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Web/web/app_dev.php/api/tasks/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                UtilisateurService ser = new UtilisateurService();
                listTasks = ser.getListUser(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
	 
	 public ArrayList<Utilisateur> getListUser(String json) {

        ArrayList<Utilisateur> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
           // System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Utilisateur u = new Utilisateur();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                u.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                //u.setAdresse(obj.get("adresse").toString());
				String nomPlan = ""+obj.get("nomPlan");
				
					u.setNomPlan(nomPlan);
				
				String adresse = ""+obj.get("adresse");
				if (adresse != null) {
					u.setAdresse(adresse);
				}
				String nomVisiteur = ""+obj.get("nomVisiteur");
				if (nomVisiteur != null) {
					u.setNomVisiteur(nomVisiteur);
				}
				String prenomVisiteur = ""+obj.get("prenomVisiteur");
				if (prenomVisiteur != null) {
					u.setPrenomVisiteur(prenomVisiteur);
				}
                
				//u.setNomVisiteur(obj.get("nomVisiteur").toString());
               // System.out.println(u);
                listEtudiants.add(u);
            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
         public void register(Utilisateur user){
		connectionRequest=new ConnectionRequest();
        String Url = "http://localhost/web-master/web/app_dev.php/api/registermobile";
		connectionRequest.setPost(false);
		connectionRequest.addArgument("username",user.getUsername());
		connectionRequest.addArgument("password",user.getPassword());
		connectionRequest.addArgument("email",user.getEmail());
		connectionRequest.addArgument("roles",user.getRoles());
	
		
        connectionRequest.setUrl(Url);

        System.out.println("tt");

        connectionRequest.addResponseListener((e) -> {
            String str = new String(connectionRequest.getResponseData());
            if(verifFailed(str,"Success")){
				            Toolbar.setGlobalToolbar(false);
					
					Toolbar.setGlobalToolbar(true);
			}
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
	}
    ArrayList<Utilisateur> listTasks = new ArrayList<>();
}
