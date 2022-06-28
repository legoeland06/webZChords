package src.gescom;

public class Commande extends Panier {
	
	protected int ref_client;
	
	public Commande(int ref_client) {
		super();
		this.ref_client = ref_client;
	}

	public int getRef_client() {
		return ref_client;
	}

	public void setRef_client(int ref_client) {
		this.ref_client = ref_client;
	}

	public boolean updateDb() {
		return true;
	}
}
