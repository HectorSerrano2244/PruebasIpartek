package controladores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
		File fichero = new File("libros.dat");
		@SuppressWarnings("unused")
		Libro libro;
		
		try {
			libro = new Libro(Long.parseLong(id),isbn,titulo,autor,editorial,Double.parseDouble(precio));
			
		} catch(RuntimeException e) {
			//response.sendRedirect("login.jsp");
			request.setAttribute("error", "Error en la lectura de proyectos");
			request.getRequestDispatcher("Principal.jsp").forward(request, response);
			return;
		}
		//ArrayList<Libro> libros = (ArrayList<Libro>)request.getServletContext().getAttribute("libros");
		//String formato =(String)request.getAttribute("formato");
		
		BufferedWriter escritura = new BufferedWriter(new FileWriter(fichero));
		escritura.write(libro.toString());
		escritura.close();
		
		//libros.add(libro);
		
		//request.setAttribute("formato", libro);
		
		
		request.getRequestDispatcher("Principal.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
