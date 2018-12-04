package controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojos.Libro;

@WebServlet("/AltaLibro")
public class AltaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AltaLibro() {
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String editorial = request.getParameter("editorial");
		String precio = request.getParameter("precio");
		
		Libro libro;
		try {
			libro = new Libro(Long.parseLong(id),isbn,titulo,autor,editorial,Double.parseDouble(precio));
			
		} catch(RuntimeException e) {
			//response.sendRedirect("login.jsp");
			request.setAttribute("error", "Error en la lectura de proyectos");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		ArrayList<Libro> proyectos = new ArrayList<Libro>();
		
		
		request.setAttribute("proyectos", proyectos);
		
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
