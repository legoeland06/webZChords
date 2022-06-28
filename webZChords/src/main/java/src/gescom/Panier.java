package src.gescom;

import java.sql.ResultSet;
import java.util.*;

public class Panier {

	private static Panier instance = null;
	private int ref_commande;
	private int ref_employe;
	private Date date_commde;
	private List<Detail_commande> lignes_commande = new ArrayList<Detail_commande>();
	
	// fonction statique pour récupérer l'instance du panier qui doit être unique
	public static Panier getPanier() {
		if (instance == null) {
			instance = new Panier();  // s'il n'existe pas encore on l'instancie
		}
		return instance;
	}
	
	// constructeurs
	public Panier() {
		super();
	}
	public Panier(int ref_commande, int ref_employe, Date date_commde) {
		super();
		this.ref_commande = ref_commande;
		this.ref_employe = ref_employe;
		this.date_commde = date_commde;
	}
	
	// setters et getters
	public List<Detail_commande> getLignes_commande() {
		return lignes_commande;
	}

	public void setLignes_commande(List<Detail_commande> lignes_commande) {
		this.lignes_commande = lignes_commande;
	}

	public void ajouter_produit(int ref, int q, double prix) {
		
		lignes_commande.add(new Detail_commande(0, ref, q, prix));
		
	}
	public void supprimer_produit(int ref_produit) {
		
	}

	// retourne une liste des éléments du panier pour affichage
	public ArrayList<String> consulter() {
		
		ArrayList<String> paniers = new ArrayList<String>() ;
		for (Detail_commande d : this.lignes_commande) {
			paniers.add("Ref produit : " + d.getRef_produit() + " / Quantite : " + d.getQuantite() + " / Prix unitaire : " + d.getPrix_unitaire());
		}
		return paniers;

	}
}
