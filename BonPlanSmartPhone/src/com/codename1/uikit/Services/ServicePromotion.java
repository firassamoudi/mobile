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
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.uikit.entites.Promotion;
import com.codename1.uikit.entites.Utilisateur;

/**
 *
 * @author sana
 */
public class ServicePromotion {

    public void ajoutPromotion(Promotion ta) {
       int  id =Utilisateur.connected_id;
        ConnectionRequest con = new ConnectionRequest();
ta.setProprietaire_id(id);
        String Url = "http://localhost/web-master/web/app_dev.php/api/promotions/new?descritpion=" + ta.getDescription() + "&etat_promo=" + ta.getEtat_promo() + "&datedebutp=" + ta.getDateDebut()+"&id=" +id;
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

        ArrayList<Promotion> listPromotions = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> promotions = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(promotions);

            List<Map<String, Object>> list = (List<Map<String, Object>>) promotions.get("root");

            for (Map<String, Object> obj : list) {
                Promotion e = new Promotion();
             
//                Date datedebut = new Date(obj.get("datedebutp").toString());
//                Timestamp stamp = new Timestamp(Long.parseLong(datedebut.toString()));
//                Date date = new Date(stamp.getTime());
//                System.out.println(date);


// timestamp to Date

//Example -> in ms

                float id = Float.parseFloat(obj.get("idpromotion").toString());
              String image = obj.get("urlPromo").toString();
                 e.setUrlPromo(image);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setDescription(obj.get("description").toString());
                

                e.setIdPromo((int) id);

                System.out.println(e);
                listPromotions.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listPromotions);
        return listPromotions;

    }
    ArrayList<Promotion> listPromotions = new ArrayList<>();

    public ArrayList<Promotion> getList2() {
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
