package src.gescom;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class gescom {

	private static Employe employe = null;  // correspond à l'employé qui va se connecter
	private static Scanner clavier;			// pour les saisies au clavier
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// instanciation du clavier
		clavier = new Scanner(System.in);
		// pour sortir de la boucle infinie ci-dessous
		boolean quit = false;
		
		// boucle de saisie d'instructions au clavier
		// taper q! pour en sortir et terminer le programme
		while (!quit) {
			
			// si l'objet employe n'est pas encore instancié on appelle la fonction login pour identification
			if (employe==null) {
				
				try {
					quit = login();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			} else { 
				 // sinon on affiche le menu
				System.out.println("[1] Liste des produits - [2] Ajouter un produit dans le panier");
				// et on attend qu'une instruction soit entrée
				String menu = clavier.next();
				
				// on appelle la fonction correspondant à l'instruction
				switch (menu) {
				case "q!": 
					quit = true;
					break;
				case "1":
					afficherProduits();
					break;
				case "2":
					AjouterPanier();
					break;
				
				}
				
			}
			
			
			
		}


	}
	
	// cette fonction est appelée pour que l'employé qui utilisa l'application puisse s'identifier
	private static boolean login() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		// entrer le nom
		System.out.println("Entrer votre nom : ");
		String nom = clavier.next();
		if (nom.equals("q!")) return true;

		// entrer le mot de passe
		System.out.println("Entrer votre mot de passe : ");
		String pwd = clavier.next();
		if (pwd.equals("q!")) return true;
		
		// appel de la fonction static login() de la classe employé
		// elle renvoie le numéro de l'employé correspondant au nom et au mot de passe saisis
		// en cas d'echec d'identification elle retourne 0
		int no_employe = Employe.login(nom, pwd);
		if (no_employe > 0) {
			// si employé ok on crée l'objet employe (objet valide dans toutes les fonctions de la présente classe
			employe = new Employe(no_employe);
		}
		// dans les 2 cas, on continue la boucle de saisie
		return false;
	}

	// fonction appelée lorsque l'opérateur choisit le menu 1 
	private static void afficherProduits() throws ClassNotFoundException, SQLException {
		System.out.println("Nom produit :");
		String filtre = clavier.next();
		// on saisit une partie du nom du produit qui servira de filtre dans la fonction statique lister() de la classe produit
		ArrayList<String> liste = Produit.lister(filtre);
		// on affiche la liste retournée
		for (String l : liste) {
			System.out.println(l);
		}
		System.out.println("");
	}
	
	// fonction appelée lorsque l'opérateur choisit le menu 2
	private static void AjouterPanier() throws ClassNotFoundException, SQLException {
		System.out.println("Ref produit :");
		String ref = clavier.next();
		// on saisit une ref produit et on verifie que le produit existe
		Produit p = new Produit (ref);
		if (p.getRef_produit() > 0) {
			// s'il existe, on saisit une quantité
			System.out.println("Quantité :");
			int q = clavier.nextInt();
			// et on l'ajoute au panier qui est instancié lors du premier appel
			Panier.getPanier().ajouter_produit(p.getRef_produit(), q, p.getPrix_unitaire());
			// on récupère alors toutes les lignes du panier
			ArrayList<String> liste = Panier.getPanier().consulter();
			// et on les affiche
			System.out.println("Contenu du panier :");
			for (String l : liste) {
				System.out.println(l);
			}
			System.out.println("");
		}
	}

}

