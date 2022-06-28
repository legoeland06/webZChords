package test1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Remove() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer hash = Integer.parseInt(request.getParameter("hash"));

		Grille.maGrille.delChord(hash);
		request.setAttribute("grike", Grille.maGrille);
		request.setAttribute("longGrille", Grille.maGrille.getLength());

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("longGrille", Grille.maGrille.getLength());
		doGet(request, response);
	}

}
