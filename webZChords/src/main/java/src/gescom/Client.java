package src.gescom;

import java.util.ArrayList;

public interface Client {

	public  ArrayList<String> chercher(String client);
	public void validerCommande(String client);
}
