package src.gescom;

import java.util.ArrayList;

public class Societe implements Client {

	String code_client;
	String societe;
	String adresse;
	String code_postal;
	String ville;
	String pays;
	String telephone;
	
	public Societe(String code_client) {
		super();
		this.code_client = code_client;
	}
	
	public String getCode_client() {
		return code_client;
	}

	public String getSociete() {
		return societe;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public String getVille() {
		return ville;
	}

	public String getPays() {
		return pays;
	}

	public String getTelephone() {
		return telephone;
	}

	public  ArrayList<String> chercher(String client){
		ArrayList<String> l =new ArrayList<String>();
		return l;
	}
	public void validerCommande(String client) {

	}

}
