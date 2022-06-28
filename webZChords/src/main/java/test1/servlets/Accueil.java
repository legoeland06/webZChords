package test1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test1.forms.LoginForm;

/**
 * @author EricBruneau
 *
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Accueil() {
		super();
	}

protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("grike", Grille.maGrille);

		
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("grike", Grille.maGrille);
		request.setAttribute("longGrille", String.valueOf(Grille.maGrille.getLength()));

		LoginForm form = new LoginForm();
		form.login(request);
		request.setAttribute("form", form);
		
		doGet(request, response);
	}

}
