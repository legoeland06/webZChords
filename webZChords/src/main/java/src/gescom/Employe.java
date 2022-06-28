package src.gescom;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Employe implements Client {

	private int no_employe;
	private String nom;
	private String prenom;

	public Employe(int n) throws ClassNotFoundException, SQLException {
		// récupération des données de l'employé Numéro n dans la base de données
		ResultSet rs = DB.getDB().select("select * from employes where no_employe="+n);
		if (rs.next()) {
			no_employe = n;
			nom = rs.getString("NOM");
			prenom = rs.getString("PRENOM");
		}
	}
	public static int login(String n, String p) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		// recherche de l'employé de nom n et password p dans la base de donnée
		ResultSet rs = DB.getDB().select("select no_employe from employes where nom='"+n+"' and password='"+Md5.md5(p)+"'");
		if (rs.next()) {
			return rs.getInt("no_employe");  // il existe et on retourne son numéro
		} else {
			return 0;  // il n'existe pas
		}
		
	}
	public  ArrayList<String> chercher(String client){
		ArrayList<String> l =new ArrayList<String>();
		return l;
	}
	public void validerCommande(String no_employe) {
		
	}
}
