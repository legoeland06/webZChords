package test1.servlets;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author EricBruneau
 *
 */
public class Chord {

	public static String[] timers = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "16" };

	private int time;
	private Note fondamental;
	private Quality quality;
	private Note basse;
	private boolean played = true;
	private Integer duree = time;

	public int getTime() {
		return this.time;
	}

	public int getDuree() {
		return this.duree;
	}

	public void setDuree(Integer t) {
		this.duree = t;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Note getFondamental() {
		return this.fondamental;
	}

	public void setFondamental(Note fondamental) {
		this.fondamental = fondamental;
	}

	public Quality getQuality() {
		return this.quality;
	}

	public void setQuality(Quality quality) {
		this.quality = quality;
	}

	public Note getBasse() {
		return this.basse;
	}

	public void setBasse(Note basse) {
		this.basse = basse;
	}

	public void setPlayed(boolean p) {
		this.played = p;
	}


	public boolean getPlayed() {
		return this.played;
	}

	/**
	 * Constructeur ()
	 */
	public Chord() {
		super();
	}

	/**
	 * Constructeur (c)
	 * 
	 * @param c
	 */
	public Chord(String c) {
		super();
		Chord suzy = parseChord(c);
		this.time = suzy.time;
		this.fondamental = suzy.fondamental;
		this.quality = suzy.quality;
		this.basse = suzy.basse;
		this.duree = suzy.duree;
	}

	/**
	 * Constructeur (time,fondamental,quality,basse)
	 * 
	 * @param time
	 * @param fondamental
	 * @param quality
	 * @param basse
	 */
	public Chord(int time, Note fondamental, Quality quality, Note basse) {
		super();
		this.time = time;
		this.fondamental = fondamental;
		this.quality = quality;
		this.basse = basse;
	}

	/**
	 * Classique, renvois l'objet Chord sous la forme String
	 */
	public String toString() {
		if (this.getBasse() != null || this.getBasse() == this.getFondamental()) {
			return (this.getTime() + ":" + this.getFondamental() + this.quality + "/" + this.basse);
		} else
			return (this.getTime() + ":" + this.getFondamental() + this.quality);

	}

	/**
	 * @return renvois l'accord sous sa forme simple (sans la subdivision de temps)
	 */
	public String simpleToString() {
		if (this.getBasse() != null || this.getBasse() == this.getFondamental()) {
			return (this.getFondamental().toString() + this.quality.toString() + "/" + this.basse.toString());
		} else
			return (this.getTime() + ":" + this.getFondamental().toString() + this.quality.toString());
	}

	/**
	 * @param c est l'accord sous la forme 4:Cm7b5/G
	 * @return Methode de parsing de l'accord sous forme de texte renvois un objet
	 *         Chord
	 */
	public static Chord parseChord(String c) {
		String AccordSansTime = c.substring(2); // Ebm7b5/G
		String _quality;
		int positionDuSlash;
		int longBasse;

		if (c.indexOf(":") == -1) {
			System.out.println("Accord de la forme  4:Cm7b5/D ");
			System.exit(0);
		}

		if (AccordSansTime.indexOf("/") != -1) {
			positionDuSlash = AccordSansTime.indexOf("/"); // positionDuSlash = 6
		} else
			positionDuSlash = 0;

		String basse;
		String noteFondamentale;

		int longAccordSansTime = AccordSansTime.length(); // longAccordSansTime = 6

		String monTime = c.substring(0, 1);
		int _time = Integer.parseInt(monTime); // _time = 4

		if (AccordSansTime.length() > 1) {
			if (((AccordSansTime.substring(0, 2)).indexOf("#") != -1)
					|| ((AccordSansTime.substring(0, 2)).indexOf("b") != -1)) {
				noteFondamentale = AccordSansTime.substring(0, 2); // noteFondamentale = Eb ou F#
			} else {
				noteFondamentale = AccordSansTime.substring(0, 1); // noteFondamentale = E
			}

			if (!check_note(noteFondamentale)) {
				System.out.println("Probleme de notes: " + noteFondamentale);
				System.exit(0);
			}

			int longnoteFondamentale = noteFondamentale.length();

			if (positionDuSlash != 0) {
				basse = AccordSansTime.substring(positionDuSlash + 1, longAccordSansTime);
				longBasse = basse.length();
				_quality = AccordSansTime.substring(longnoteFondamentale, longAccordSansTime - longBasse - 1);
			} else { // pas de basse
				basse = "";
				longBasse = 0;
				if (longAccordSansTime >= 1) {
					_quality = AccordSansTime.substring(longnoteFondamentale);
				} else
					_quality = "";
			}

		} else { // accord simple 4:D
			noteFondamentale = AccordSansTime;
			basse = "";
			longBasse = 0;
			_quality = "";
		}

		Chord accParse = new Chord();
		accParse.setTime(_time);

		accParse.setFondamental(new Note(noteFondamentale));

		accParse.setQuality((_quality == "") ? new Quality(" ") : new Quality(_quality));

		accParse.setBasse((basse == "") ? new Note(noteFondamentale) : new Note(basse));

		return accParse;
	}

	static Boolean check_note(String note) {

		return Note.existeOrNo(note);
	}

	/**
	 * @return Liste des éléments de l'accord sous forme de liste d'objets de classe
	 *         Note
	 */
	public ArrayList<String> chordToComponents() {

		ArrayList<String> componentsOfChord = new ArrayList<>();
		String bonneListe = "";
		Note root = this.fondamental;
		Integer valRoot = Note.noteToVal(root.toString());

		for (String[] maliste : Qualities.listeQualities()) {
			if (maliste[0].contentEquals(this.quality.toString())) {
				bonneListe = maliste[1];
			}
		}

		// ***************************************************
		String[] listeTraitement = bonneListe.split(":");
		Integer i = 0;
		for (String valeurNoteString : listeTraitement) {
			Integer ValeurNote = Integer.parseInt(valeurNoteString) + valRoot;
			componentsOfChord.add(Note.valToNote(ValeurNote - 1));
			i++;
		}

		return componentsOfChord;
		// ***************************************************
	}

	/**
	 * @return Valeurs Midi de l'accord sous forme de liste d'Integer ces valeurs
	 *         seront utilisées dans le PlayerMidi
	 */
	public List<Integer> chordToValues() {

		ArrayList<Integer> componentsOfValues = new ArrayList<>();
		String bonneListe = "";
		String root = this.fondamental.toString();
		Integer valRoot = Note.noteToVal(root);

		for (String[] maliste : Qualities.listeQualities()) {
			if (maliste[0].contentEquals(this.quality.toString())) {
				bonneListe = maliste[1];
			}
		}
		String[] listeTraitement = bonneListe.split(":");
		Integer i = 0;
		for (String valeurNoteString : listeTraitement) {
			Integer ValeurNote = Integer.parseInt(valeurNoteString) + valRoot;
			if (ValeurNote < 5) {
				ValeurNote += 12;
			}
			// if (ValeurNote > 60) { ValeurNote -= 12; }
			componentsOfValues.add(ValeurNote - 1);
			i++;
		}

		return componentsOfValues;
	}

/**
 * Sortie vers le terminal de commandes des détails concernant l'accord en objet
 * Cette fonction est obsolète est sert de témoin dans le terminal de commandes
 */
	public void printDetails() {

		System.out.println();
		System.out.println("*******************************************");
		System.out.println("Accord complet: " + this.toString());
		System.out.println("Time: " + this.time);
		System.out.println("Fondamentale: " + this.fondamental);
		System.out.println("Quality: " + this.quality);
		System.out.println("Basse: " + this.basse);
		System.out.println("*******************************************");
		System.out.println("Composition des notes: " + this.chordToComponents());
		System.out.println("Valeur MIDI: " + this.chordToValues());
		System.out.println("*******************************************");
		System.out.println("Duree: " + this.getDuree());
		System.out.println("*******************************************");
		System.out.println("Played: " + this.getPlayed());
		System.out.println("*******************************************");
	}

}
