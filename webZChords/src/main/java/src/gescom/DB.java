package src.gescom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	final String driver = "oracle.jdbc.OracleDriver";
	final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	final String uid = "la300"; 
	final String passwd = "secret";
	static DB instance = null;
	private Connection cx = null;
	private Statement stmt = null;
	
	public DB() throws ClassNotFoundException, SQLException    {
	
			// création de la classe correspondant au pilote jdbc oracle
			Class.forName(driver);
			// connexion à la base de données
			cx = DriverManager.getConnection(url, uid, passwd);
			// Instanciation de l'objet Statement pour executer des requetes
			stmt = cx.createStatement();
	}
	
	// getDB() renvoie une instance de la clase DB si elle existe
	// ou la crée s'il elle n'existe pas
	// cela permet de ne se connecter su'une seule fois à la base de données
	public static DB getDB() throws ClassNotFoundException, SQLException {
		if (instance==null)
			instance = new DB();
		return instance;
	}
	
	// execution d'une requête update qui renvoie le nombre d'enregistrements impactés
	public int update(String requete) throws SQLException {
		return stmt.executeUpdate(requete);
	}
	
	// exécution d'une requête select qui renvoie un objet ResultSet contenant le résultat du select
	public ResultSet select(String requete) throws SQLException {
		return stmt.executeQuery(requete);
	}
	
	// exécution d'une requête insert qui renvoie la valeur de l'autoincrément s'il y en a un
	public int insert(String requete) throws SQLException {
		stmt.executeUpdate(requete);
		ResultSet rs = stmt.getGeneratedKeys();
		return rs.getInt(1);
	}
	
}
