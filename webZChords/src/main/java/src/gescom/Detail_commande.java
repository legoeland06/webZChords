package src.gescom;

public class Detail_commande {
	private int ref_commande;
	private int ref_produit;
	private int quantite;
	private double prix_unitaire;
	
	public Detail_commande(int ref_commande, int ref_produit, int quantite, double prix_unitaire) {
		super();
		this.ref_commande = ref_commande;
		this.ref_produit = ref_produit;
		this.quantite = quantite;
		this.prix_unitaire = prix_unitaire;
	}

	public int getRef_commande() {
		return ref_commande;
	}

	public void setRef_commande(int ref_commande) {
		this.ref_commande = ref_commande;
	}

	public int getRef_produit() {
		return ref_produit;
	}

	public void setRef_produit(int ref_produit) {
		this.ref_produit = ref_produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix_unitaire() {
		return prix_unitaire;
	}

	public void setPrix_unitaire(double prix_unitaire) {
		this.prix_unitaire = prix_unitaire;
	}
	
}
