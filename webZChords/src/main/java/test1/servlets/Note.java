package test1.servlets;

/**
 * @author EricBruneau
 * 
 * Classe Note
 *
 */
public class Note {
	private String nom;
	private int duree;
	private int hauteur;

	public static String[][] NOTE_VAL_DICT = { { "A", "9" }, { "A#", "10" }, { "Bb", "10" }, { "Cb", "11" },
			{ "B", "11" }, { "C", "0" }, { "Db", "1" }, { "C#", "1" }, { "D", "2" }, { "D#", "3" }, { "Eb", "3" },
			{ "E", "4" }, { "F", "5" }, { "Gb", "6" }, { "F#", "6" }, { "G", "7" }, { "G#", "8" }, { "Ab", "8" },

	};

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public String toString() {
		return this.nom;
	}

	public Note(String nom) {
		super();
		this.nom = nom;
	}

	static Boolean existeOrNo(String note) {
		boolean existe = false;
		for (String[] strings : NOTE_VAL_DICT) {
			if (strings[0].indexOf(note) != -1) {
				existe = true;
			}
		}
		return existe;
	}

	public static Integer noteToVal(String note) {
		Qualities myquality = new Qualities();

		return myquality.noteToVal(note);
	}

	public static String valToNote(int v) {
		Integer moduloNote = (v + 1) % 12;
		String lanote = Integer.toString(moduloNote);
		String victor = "";
		for (String[] strings : NOTE_VAL_DICT) {
			if (strings[1].indexOf(lanote) != -1) {
				victor = strings[0];
				continue;
			}
		}
		return victor;
	}
}
