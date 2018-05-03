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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.entites.Promotion;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.codename1.io.Log;
import com.codename1.uikit.entites.Utilisateur;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;



 
     
 

/**
 *
 * @author Monia
 */
public class PromoService {
     String username;
     public   static  int siize;
      List<String> st = new ArrayList<>(); 
    public void ajoutPromotion(Promotion ta)  {

        ConnectionRequest con = new ConnectionRequest();
System.out.println(ta.getDateDebut());
String datedebut = ta.getDateDebut();
int id = Utilisateur.connected_id;

        System.out.println("l url promo hetha  " + ta.getUrlPromo());

 
        System.out.println("hethi ta    " + ta );
        System.out.println("hethi l reduction  "+ ta.getReduction());
        System.out.println("hethi l etatt" +ta.getEtat_promo());
       
         String Url = "http://localhost/web-master/web/app_dev.php/api/promotions/new?descritpion=" + ta.getDescription() + "&reduction=" + ta.getReduction() + "&datedebutp=" +datedebut+"&urlPromo=" +ta.getUrlPromo()+"&datefinp=" +ta.getDateFin()+"&proprietaire_id=" +id; 
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    public void Convert (String string )
    {
    String s = string;
       

    }
public Date convertStringToDate(String dateString)
{
    Date date = null;
    Date formatteddate = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    try{
        date = df.parse(dateString);
       
    }
    catch ( Exception ex ){
        System.out.println(ex);
    }
    
    return formatteddate;
}
  private String convertDateFormat(String fromDate) {

    DateFormat df=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

    Date d= null;
    try {
        d = df.parse(fromDate);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    df=new SimpleDateFormat("yyyyMMdd");
    System.out.println(df.format(d));

    return df.format(d);
}
     public ArrayList<Promotion> getListPromo(String json) throws ParseException {
  List <Utilisateur> listUtilisatuer;
                        listUtilisatuer = new ArrayList<>();
        ArrayList<Promotion> listPromotions = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
          siize =  list.size(); 
           
            for (Map<String, Object> obj : list) {
                Promotion p = new Promotion();
                
                for (Map.Entry<String, Object> entry : obj.entrySet()) {
                    String key = entry.getKey();
                   
                    Object value = entry.getValue();
                   
                
                       // System.out.println("l keyyyyyyyyyyyyyyy "+key);
                  
                  // System.out.println("valueeeeeeeeeeeeeee " +value ); 
                    
                }
                // System.out.println(obj.get("id"));
                /*
       String f1 = ((LinkedHashMap)obj.get("datedebutp")).get("timestamp").toString().substring(0,1 );
                String f2 = ((LinkedHashMap)obj.get("datedebutp")).get("timestamp").toString().substring(2,9 );
                String f3 = f1+f2+"00";
                System.out.println(f3);
                System.out.println(f3);
                 Long s1 = Long.parseLong(f3);
                 Long s2 = Long.parseLong("86400");
                 Long s3 = s1+s2;
                 Long s4 = s3*1000;
                 System.out.println("****************"+s4 );*/
             //   System.out.println((Long)Long.parseLong(f3)*1000+24*3600);
    Map<String, Object> results = (Map<String, Object>) obj.get("datedebutp");
                Double a = (Double) results.get("timestamp");
                Date d = new Date((long) (a * 1000));
                p.setDateDebut(d.toString());
                Map<String, Object> results1 = (Map<String, Object>) obj.get("datefinp");
                Double a1 = (Double) results1.get("timestamp");
                Date d1 = new Date((long) (a1 * 1000));
                p.setDateFin(d1.toString());

                 String image = obj.get("urlpromo").toString();
                 p.setUrlPromo(image);
              float id = Float.parseFloat(obj.get("idpromotion").toString().trim());
                 
               p.setIdPromo((int)id);
              //  p.setDateDebut(obj.get("datedebutp").toString());
                 // p.setDateFin(obj.get("datefinp").toString());
                 
                 p.setDescription(obj.get("description").toString());
                  
                //System.out.println(p);
                listPromotions.add(p);
                
                       String  us ;
              us=getListUser(p.getIdPromo());
              List<String> st = new ArrayList<>(); 
              st.add(us);
                //System.out.println(" pppppppppppppppp "+p.getIdPromo());
                System.out.println("USSSSSSSSSSSSSSSSss " +us);
                
            }
 
    
            System.out.println("hahahahakdslmkdmqdkmqkdqs"+st);

        } catch (IOException ex) {
        }
        System.out.println(listPromotions);
        return listPromotions;

    }
      public ArrayList<Promotion> UpdateRestau(int id,String nom) {
      ArrayList<Promotion> listRestaurants = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      con.setUrl("http://localhost/pidev/PIDEV/web/app_dev.php/updateRest/"+id+"?nom="+nom);
      con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listOffres = getListOffre(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> restaurant = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(restaurant);
                    System.out.println(restaurant.keySet());
                    System.out.println(restaurant.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restaurant.get("root");
                    for (Map<String, Object> obj : list) {
                        Promotion rest = new Promotion();
//                        float id = Float.parseFloat(obj.get("id").toString());
                        //skill.setId((int) id);
                        
                        rest.setDescription(obj.get("description").toString());
                     //   rest.setEmailRestaurant(obj.get("email").toString());
                       // rest.setAdresseRestaurant(obj.get("adr").toString());
                        
                       /* 
                        int mm = Display.getInstance().convertToPixels(3);
                    rest.setImagePath("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString());
                    System.out.println(rest.getImagePath());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_PNG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    rest.setImage(im);
                        */
                      
                        
                        listRestaurants.add(rest);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRestaurants;
    }
    
     public ArrayList<Utilisateur> getListProduit(String json) {

        ArrayList<Utilisateur> listProduits = new ArrayList<>();

        try {
            System.out.println("0000000000" + json);
            JSONParser j = new JSONParser();

            Map<String, Object> panierproduit = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("1111");
            System.out.println(panierproduit);

            List<Map<String, Object>> list1 = (List<Map<String, Object>>) panierproduit.get("root");
            System.out.println("3333333");
            for (Map<String, Object> obj : list1) {
                 Utilisateur p= new Utilisateur();
                float idp = Float.parseFloat(obj.get("id").toString());
                p.setId((int) idp);
               
                String nomproduit = obj.get("username").toString();
                 //String image = obj.get("image").toString();
               //  p.setImage(image);
      //    p.setLibelle_produit(nomproduit);
//            e.setId_produit.LibelleProduit(Integer.parseInt(obj.get("produit.getLibelleProduit()").toString()));
                listProduits.add(p);

            }

        } catch (IOException ex) {
        }
        System.out.println(listProduits);
        return listProduits;

    }
     public ArrayList<Promotion> rechercheRestau(int id) {
      ArrayList<Promotion> listRestaurants = new ArrayList<>();
      ConnectionRequest con = new ConnectionRequest();
      id = Utilisateur.connected_id;
      con.setUrl("http://localhost/web-master/web/app_dev.php/api/promotions/find/"+7);
      con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listOffres = getListOffre(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> restaurant = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(restaurant);
                    System.out.println(restaurant.keySet());
                    System.out.println(restaurant.values());
                    List<Map<String, Object>> list = (List<Map<String, Object>>) restaurant.get("root");
                    for (Map<String, Object> obj : list) {
                        Promotion rest = new Promotion();
//                        float id = Float.parseFloat(obj.get("id").toString());
                        //skill.setId((int) id);
                        
                        rest.setDescription(obj.get("description").toString());
                        //rest.setEmailRestaurant(obj.get("email").toString());
                       // rest.setAdresseRestaurant(obj.get("adr").toString());
                        
                       /* 
                        int mm = Display.getInstance().convertToPixels(3);
                    rest.setImagePath("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString());
                    System.out.println(rest.getImagePath());
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm* 3, mm * 4, 0), false);

                    ImageIO imageio = ImageIO.getImageIO();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                        imageio.save(FileSystemStorage.getInstance().openInputStream("file://C:/wampNada/www/ProjetPi2/web/uploads/images/products/" + obj.get("image").toString()),
                                out,
                                ImageIO.FORMAT_PNG,
                                100, 100, 1);
                    } catch (IOException ex) {
                    }
                    Image im = Image.createImage(out.toByteArray(), 0, out.toByteArray().length);
                    rest.setImage(im);
                        */
                      
                        
                        listRestaurants.add(rest);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRestaurants;
    }
    ArrayList<Promotion> listPromotions = new ArrayList<>();
    public void lalala (int id ){  
      ConnectionRequest con = new ConnectionRequest();
      List<Utilisateur> lo = new ArrayList<>();
        con.setUrl("http://localhost/web-master/web/app_dev.php/api/promotions/findU/"+id);  
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  String s = (new String(con.getResponseData()));
                 JSONParser j = new JSONParser();
          
                    Map<String, Object> users;
                try {
                    users = j.parseJSON(new CharArrayReader(s.toCharArray()));
                   
                    List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");
            for (Map<String, Object> obj : list) {
                Utilisateur u = new Utilisateur();
                
                for (Map.Entry<String, Object> entry : obj.entrySet()) {
                    String key = entry.getKey();               
                    Object value = entry.getValue();    
                       System.out.println("l keyyyyyyyyyyyyyyy "+key);
                  
                  System.out.println("valueeeeeeeeeeeeeee " +value ); 
                    
                }
                // System.out.println(obj.get("id"));
       
          
                u.setUsername(obj.get("username").toString());
                System.out.println("uuuuuuuuuuuu " +u);
                }}
                catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });
       
         
    }
      
      
    public ArrayList<Promotion> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/web-master/web/app_dev.php/api/promotions/all");  
  //      con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PromoService pr = new PromoService();
                try {
                    listPromotions = pr.getListPromo(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPromotions;
    }
 public String  getListUser(int idUser){       
        ConnectionRequest con = new ConnectionRequest();
con.setUrl("http://localhost/web-master/web/app_dev.php/api/promotions/findU/" +idUser);
//      con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("hahahahahahahahhaah");
                PromoService pr = new PromoService();
                try {
                  username = new String(con.getResponseData(),"utf-8");
                
                    System.out.println("hahahhahahahahahaah"+username);
                } catch (UnsupportedEncodingException ex) {
                     System.out.println(ex);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return username;
    }
    
   ArrayList<Utilisateur> listProduit = new ArrayList<Utilisateur>();
   public String  getprix(int idUser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/web-master/web/app_dev.php/api/promotions/findU/" +idUser);
        System.out.println("gggggggggggggggggggggggggg"+con);
        System.out.println("hhhhhhhhhhhhhhhhhh"+idUser);
        con.addResponseListener((NetworkEvent evt) -> {
            System.out.println("hahahahahhahahaha");
            PromoService ser = new PromoService();
            String username1;
         
            try {
                username1 = new String(con.getResponseData(),"utf-8");
            } catch (UnsupportedEncodingException ex) {
                System.out.println( ex);
            }
                System.out.println("SERNFRLJKRJEZR" + username);
          
              
            System.out.println("hahahahahahhahah");
        });
        
      //  NetworkManager.getInstance().addToQueueAndWait(con);
            return username;
       
    
    }

}


