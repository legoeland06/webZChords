package test1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author EricBruneau
 * 
 * AddChords ajoute l'accord reçu en POST
 * à la table des accords (Grille.maGrille)
 *
 */
@WebServlet("/AddChords")
public class AddChords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("longGrille", Grille.maGrille.getLength());
		request.setAttribute("grike", Grille.maGrille);

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accReconstitue = (request.getParameter("times")) + ":" + (request.getParameter("fondamentales"))
				+ (request.getParameter("accords")) + "/" + (request.getParameter("basses"));
		Chord accord = new Chord(accReconstitue);
		int multiplicateur = request.getParameter("multiplicateur") != null
				? Integer.parseInt(request.getParameter("multiplicateur"))
				: 0;
		for (int i = 0; i < multiplicateur; i++) {
			Grille.maGrille.addChord(accord);
		}
		request.setAttribute("grike", Grille.maGrille);
		request.setAttribute("dernierTime", accord.getTime());
		request.setAttribute("dernierFondamentale", accord.getFondamental());
		request.setAttribute("dernierQuality", accord.getQuality());
		request.setAttribute("dernierBasse", accord.getBasse());
		
		request.setAttribute("longGrille", Grille.maGrille.getLength());

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

	}
	
	public AddChords() {
		super();
	}

}
