/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.entites;


import java.util.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author Meyssa
 */
public class Promotion {

    private int idPromo;
    private String description;
    private String urlPromo;
    private String datedebutp;
    private String datefinp;
    private int reduction;
    private int etat_promo;
    private int likes;
    private int proprietaire_id;

    public Promotion(String description, String urlPromo, String datedebutp, String datefinp, int reduction , int proprietaire_id) {
        this.description = description;
        this.urlPromo = urlPromo;
        this.datedebutp = datedebutp;
        this.datefinp = datefinp;
        this.reduction = reduction;
        this.proprietaire_id = proprietaire_id;
    }

   

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public void setEtat_promo(int etat_promo) {
        this.etat_promo = etat_promo;
    }

    public Promotion(String description, int etat_promo) {
        this.description = description;
        this.etat_promo = etat_promo;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReduction() {
        return reduction;
    }

    public int getEtat_promo() {
        return etat_promo;
    }

    public int getLikes() {
        return likes;
    }
 
    public Promotion(int idPromo, String description, String urlPromo, String dateDebut, String dateFin, int reduction, int etat_promo, int likes, int proprietaire_id) {
        this.idPromo = idPromo;
        this.description = description;
        this.urlPromo = urlPromo;
        this.datedebutp = dateDebut;
        this.datefinp = dateFin;
        this.reduction = reduction;
        this.etat_promo = etat_promo;
        this.likes = likes;
        this.proprietaire_id = proprietaire_id;
    }
  

    public Promotion() {
    }

 

 

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlPromo() {
        return urlPromo;
    }

    public void setUrlPromo(String urlPromo) {
        this.urlPromo = urlPromo;
    }

    public String getDateDebut() {
        return datedebutp;
    }

    public void setDateDebut(String dateDébut) {
        this.datedebutp = dateDébut;
    }

    public String getDateFin() {
        return datefinp;
    }

    public void setDateFin(String dateFin) {
        this.datefinp = dateFin;
    }

    public int getProprietaire_id() {
        return proprietaire_id;
    }

    public void setProprietaire_id(int proprietaire_id) {
        this.proprietaire_id = proprietaire_id;
    }

    @Override
    public String toString() {
        return "Promotion{" + "description=" + description + ", urlPromo=" + urlPromo + ", dateDebutP=" + datedebutp + ", dateFinP=" + datefinp + ", proprietaire_id=" + proprietaire_id + '}';
    }

 

    

}
