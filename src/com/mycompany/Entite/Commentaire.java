/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Date;

/**
 *
 * @author firas
 */
public class Commentaire {

    private int idAvis;

    public Commentaire(int idAvis, String commentaire, Date dateComment) {
        this.idAvis = idAvis;
        this.commentaire = commentaire;
        this.dateComment = dateComment;
    }
    private String commentaire;
    private Date dateComment;

    public Commentaire(String commentaire, int idAvis) {

        this.commentaire = commentaire;
        this.idAvis = idAvis;

    }

    public Commentaire() {
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public Commentaire(String commentaire, Date dateComment) {
        this.commentaire = commentaire;
        this.dateComment = dateComment;
    }

    public Commentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
    }

}
