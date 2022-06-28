package test1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author EricBruneau
 * Classe servlet qui joue l'accord en sélection en le passant dans 
 * une nouvelle grille de 1 élément et en l'envoyant à la
 * classe PlayerMidi
 * 
 * @see PlayerMidi
 *
 */
@WebServlet("/Play")
public class Play extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Lit l'accord cliqué et renvois
	 * les attributs de l'accord vers le formulaire
	 * 
	 * dernierTime
	 * dernierFondamentale
	 * dernierQuality
	 * dernierBasse
	 * longGrille
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Chord acc = new Chord(request.getParameter("acc"));
		PlayerMidi SophiaMidi = new PlayerMidi();
		Grille newGrille = new Grille();
		newGrille.addChord(acc);
		SophiaMidi.injectSeq(newGrille);
		SophiaMidi.injectBasse(newGrille);
		SophiaMidi.play();
		
		request.setAttribute("dernierTime", acc.getTime());
		request.setAttribute("dernierFondamentale", acc.getFondamental());
		request.setAttribute("dernierQuality", acc.getQuality());
		request.setAttribute("dernierBasse", acc.getBasse());
		request.setAttribute("longGrille", Grille.maGrille.getLength());
		
		request.setAttribute("grike", Grille.maGrille);
		request.setAttribute("dernierAccord", acc.toString());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		doGet(request, response);
	}
	
	public Play() {
		super();
	}

}
