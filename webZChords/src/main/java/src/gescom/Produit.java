package src.gescom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produit {
	
	// les attributs de la classe Produits correspondent aux informations d'un produits qui nous sont utiles
	private int ref_produit;
	private double prix_unitaire;
	private String nom_produit;
	
	// le constructeur permet d'initialiser les données de l'objet avec les informations stockées dans la base de données
	public Produit(String ref) throws ClassNotFoundException, SQLException {
		int nref;
		// l'argument passé correspond à la référence du produit que l'on souhaite instancier
		// il faut le convertir en integer
		try {
			nref = Integer.parseInt(ref);
		} catch (Exception e) {
			nref = 0;
		}
		// on lance une requete select
		ResultSet rs = DB.getDB().select("select * from produits where ref_produit="+nref);
		if (rs.next()) {
			// et on initialise les attributs de l'objet avec le resultat
			this.ref_produit = rs.getInt("ref_produit");
			this.prix_unitaire = rs.getDouble("prix_unitaire");
			this.nom_produit = rs.getString("nom_produit");
		} 
		// si aucun produit ne correspond dans la base, l'objet est instancié quand même mais avec des valeurs à zéro
		else {
			this.ref_produit = 0;
			this.prix_unitaire = 0.0;
			this.nom_produit = "";
		}
	}
	
	// setters et getters générés par Eclipse
	public int getRef_produit() {
		return ref_produit;
	}
	public void setRef_produit(int ref_produit) {
		this.ref_produit = ref_produit;
	}
	public double getPrix_unitaire() {
		return prix_unitaire;
	}
	public void setPrix_unitaire(double prix_unitaire) {
		this.prix_unitaire = prix_unitaire;
	}
	public String getNom_produit() {
		return nom_produit;
	}
	public void setNom_produit(String nom_produit) {
		this.nom_produit = nom_produit;
	}
	
	// cette fonction statique peut être appelée sans avoir à instancier un objet te renvoi une liste affichable de tous les
	// produits dont le nom contient la chaine de caractères passée en argument
	public static ArrayList<String> lister(String filtre) throws ClassNotFoundException, SQLException {
		// la fonction upper() dans la requête permet de rendre la recherche insensible à la casse
		ResultSet rs = DB.getDB().select("select * from produits where upper(nom_produit) like upper('%"+filtre+"%')");
		ArrayList<String> liste = new ArrayList<String>() ;
		while (rs.next()) {
			liste.add(rs.getInt("ref_produit")+" / "+rs.getString("nom_produit"));
		}
		return liste;
	}
}
