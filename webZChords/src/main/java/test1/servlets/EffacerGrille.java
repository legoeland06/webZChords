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
 * Effacement de la grille d'accords
 *
 */
@WebServlet("/EffacerGrille")
public class EffacerGrille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EffacerGrille() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Grille.maGrille.delGrille();
		request.setAttribute("grike", Grille.maGrille);
		request.setAttribute("longGrille", Grille.maGrille.getLength());

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("grike", Grille.maGrille);
		request.setAttribute("longGrille", Grille.maGrille.getLength());
		doGet(request, response);
	}

}
