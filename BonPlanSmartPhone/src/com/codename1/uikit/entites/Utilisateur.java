/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.entites;

import java.util.Date;

/**
 *
 * @author Ali
 */
public class Utilisateur {
	private Integer id;
	private String username;
    private String usernameCanonical;
    private String email;
    private String emailCanonical;
    private String salt;
    private String password;
    private Date lastLogin;
    private String confirmationToken;
    private Date passwordRequestedAt;
    private String roles;
	private String description;
	private String nomPlan;
	private String adresse;
	private String nomVisiteur;
	private String prenomVisiteur;
	private double latitude;
	private double longitude;
	private String telephone;
	private int categorie;
	private String ville;
	private String photodeprofil;
	private int codePostal;
	private int validite;
	public static int connected_id;
    

	public Utilisateur() {
	}

	public Utilisateur(Integer id, String username, String usernameCanonical, String email, String emailCanonical, String salt, String password, Date lastLogin, String confirmationToken, Date passwordRequestedAt, String roles, String description, String nomPlan, String adresse, String nomVisiteur, String prenomVisiteur, double latitude, double longitude, String telephone, int categorie, String ville, String photodeprofil, int codePostal, int validite) {
		this.id = id;
		this.username = username;
		this.usernameCanonical = usernameCanonical;
		this.email = email;
		this.emailCanonical = emailCanonical;
		this.salt = salt;
		this.password = password;
		this.lastLogin = lastLogin;
		this.confirmationToken = confirmationToken;
		this.passwordRequestedAt = passwordRequestedAt;
		this.roles = roles;
		this.description = description;
		this.nomPlan = nomPlan;
		this.adresse = adresse;
		this.nomVisiteur = nomVisiteur;
		this.prenomVisiteur = prenomVisiteur;
		this.latitude = latitude;
		this.longitude = longitude;
		this.telephone = telephone;
		this.categorie = categorie;
		this.ville = ville;
		this.photodeprofil = photodeprofil;
		this.codePostal = codePostal;
		this.validite = validite;
	}
public Utilisateur( String username, String email, String password, String roles) {
		
		this.username = username;
		
		this.email = email;
		
		this.password = password;
		
		this.roles = roles;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameCanonical() {
		return usernameCanonical;
	}

	public void setUsernameCanonical(String usernameCanonical) {
		this.usernameCanonical = usernameCanonical;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailCanonical() {
		return emailCanonical;
	}

	public void setEmailCanonical(String emailCanonical) {
		this.emailCanonical = emailCanonical;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getPasswordRequestedAt() {
		return passwordRequestedAt;
	}

	public void setPasswordRequestedAt(Date passwordRequestedAt) {
		this.passwordRequestedAt = passwordRequestedAt;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNomPlan() {
		return nomPlan;
	}

	public void setNomPlan(String nomPlan) {
		this.nomPlan = nomPlan;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNomVisiteur() {
		return nomVisiteur;
	}

	public void setNomVisiteur(String nomVisiteur) {
		this.nomVisiteur = nomVisiteur;
	}

	public String getPrenomVisiteur() {
		return prenomVisiteur;
	}

	public void setPrenomVisiteur(String prenomVisiteur) {
		this.prenomVisiteur = prenomVisiteur;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPhotodeprofil() {
		return photodeprofil;
	}

	public void setPhotodeprofil(String photodeprofil) {
		this.photodeprofil = photodeprofil;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public int getValidite() {
		return validite;
	}

	public void setValidite(int validite) {
		this.validite = validite;
	}

	
	
	
	
	
	
	
}
