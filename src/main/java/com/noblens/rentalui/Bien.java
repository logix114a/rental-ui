package com.noblens.rentalui;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bien {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nom;
	private int numero;
	private String adresse;
	private String ville;
	private int postal_code;
	private String type_bien;
	private String etage;
	private String pays;
	private String Created_source;
	private Date Created_dttm;
    private Date Last_updated_dttm;
    private String Last_updated_source;  
	private Double superficie;
	private int nbr_piece;
	private int nbr_chambre;
	private String description;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int i) {
		this.numero = i;
	}

	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(int i) {
		this.postal_code = i;
	}
	public String getType_bien() {
		return type_bien;
	}
	public void setType_bien(String type_bien) {
		this.type_bien = type_bien;
	}
	public String getEtage() {
		return etage;
	}
	public void setEtage(String etage) {
		this.etage = etage;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public Double getSuperficie() {
		return superficie;
	}
	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}
	public int getNbr_piece() {
		return nbr_piece;
	}
	public void setNbr_piece(int nbr_piece) {
		this.nbr_piece = nbr_piece;
	}
	public int getNbr_chambre() {
		return nbr_chambre;
	}
	public void setNbr_chambre(int nbr_chambre) {
		this.nbr_chambre = nbr_chambre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
    public String getCreated_source() {
		return Created_source;
	}
	public void setCreated_source(String created_source) {
		Created_source = created_source;
	}
	public Date getCreated_dttm() {
		return Created_dttm;
	}
	public void setCreated_dttm(Date created_dttm) {
		Created_dttm = created_dttm;
	}
	public Date getLast_updated_dttm() {
		return Last_updated_dttm;
	}
	public void setLast_updated_dttm(Date last_updated_dttm) {
		Last_updated_dttm = last_updated_dttm;
	}
	public String getLast_updated_source() {
		return Last_updated_source;
	}
	public void setLast_updated_source(String last_updated_source) {
		Last_updated_source = last_updated_source;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
