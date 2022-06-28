package src.gescom;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

public class Md5 {

	// fonction de hachage d'une chaine de caractères
	public static String md5(String pwd) throws NoSuchAlgorithmException {
		byte[] bc = pwd.getBytes(StandardCharsets.UTF_8);
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(bc);
		StringBuilder s = new StringBuilder();
		for (byte b : hash) {
			s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
		return s.toString();

	}
	
	// initialisation des mots de passe de la table employés
	// utilisée une seule fois pour notre exemple
	public static void pwdEmployes() {
		ResultSet rs = null;
		try {
			rs = DB.getDB().select("select NO_EMPLOYE, PRENOM FROM EMPLOYES");
			while(rs.next()) {
				String noEmploye = rs.getString("NO_EMPLOYE");
				String prenom = rs.getString("PRENOM");
				String h = Md5.md5(prenom);
				DB db2 = new DB();
				db2.update("UPDATE EMPLOYES SET PASSWORD='"+h+"' WHERE NO_EMPLOYE='"+noEmploye+"'");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
