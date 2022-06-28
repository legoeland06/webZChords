package test1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @author patriciabruneau
 * Affichage de la Grille et gestion des ajouts et suppressions d'accords
 */
@WebServlet("/Grille")
public class Grille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String titre;
	private Integer tempo;
	public ArrayList<Chord> chords = new ArrayList<>();
	public ArrayList<Integer> hChords = new ArrayList<>();
	public static Grille maGrille = new Grille();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("grike", maGrille);
		request.setAttribute("longGrille", Grille.maGrille.getLength());

		this.getServletContext().getRequestDispatcher("/WEB-INF/grille.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("longGrille", Grille.maGrille.getLength());
		doGet(request, response);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public ArrayList<Chord> getChords() {
		return this.chords;
	}

	public void setChords(ArrayList<Chord> chords) {
		this.chords = chords;
	}

	public Grille() {
		super();
		this.titre = "_titre";
		this.tempo = 120;
	}

	public Integer getLength() {
		return this.chords.size();
	}

	public Grille(String titre, Integer tempo, ArrayList<Chord> chords) {
		super();
		this.titre = titre;
		this.tempo = tempo;
		this.chords = chords;
	}

	public void addChord(Chord e) {
		this.chords.add(e);
		this.hChords.add(e.hashCode());
	}

	public void delChord(Integer i) {
		this.chords.remove(this.hChords.indexOf(i));
		this.hChords.remove(this.hChords.indexOf(i));

	}

	public Chord getChord(Integer i) {
		return this.chords.get(Grille.maGrille.hChords.indexOf(i));
	}

	public void delGrille() {
		this.chords.clear();
	}

	public void insertChord(Integer index, Chord element) {
		this.chords.add(index, element);
	}
}
