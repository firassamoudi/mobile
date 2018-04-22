/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Promotion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServicePromotion {

    public void ajoutPromotion(Promotion ta) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/web-master/web/app_dev.php/api/promotions/new?descritpion=" + ta.getDescription()+ "&etat_promo=" + ta.getEtat_promo();
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

    public ArrayList<Promotion> getListPromotion(String json) {

        ArrayList<Promotion> listPromotions= new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> promotions = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(promotions);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) promotions.get("root");

            for (Map<String, Object> obj : list) {
                Promotion e = new Promotion();

                // System.out.println(obj.get("id"));
               
                 float reduction =Integer.parseInt(obj.get("etat").toString());

       
                
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setDescription(obj.get("description").toString());
                e.setReduction((int)reduction);
    
                
                System.out.println(e);
                listPromotions.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listPromotions);
        return listPromotions;

    }
    ArrayList<Promotion> listPromotions = new ArrayList<>();
    
    public  ArrayList<Promotion> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/web-master/web/app_dev.php/api/promotions/all");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServicePromotion ser = new ServicePromotion();
             listPromotions = ser.getListPromotion(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPromotions;
    }

}
