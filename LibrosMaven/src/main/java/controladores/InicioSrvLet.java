package controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.Libro;

@WebServlet("/inicio")
public class InicioSrvLet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InicioSrvLet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Libro> libros = new ArrayList<Libro>();
		BufferedReader lectura = new BufferedReader(new FileReader("libros.txt"));
		String lineafichero,id, isbn, titulo, autor, editorial, precio;
		Integer pos;
		while ((lineafichero = lectura.readLine()) != null) {
			String[] partes = lineafichero.split(",");
			pos = partes[0].indexOf("=");
			id = partes[0].substring(pos + 1);
			pos = partes[1].indexOf("=");
			titulo = partes[1].substring(pos + 1);
			pos = partes[2].indexOf("=");
			editorial = partes[2].substring(pos + 1);
			pos = partes[3].indexOf("=");
			isbn = partes[3].substring(pos + 1);
			pos = partes[4].indexOf("=");
			autor = partes[4].substring(pos + 1);
			pos = partes[5].indexOf("=");
			precio = partes[5].substring(pos + 1);
			Libro libro = new Libro(Long.parseLong(id), isbn, titulo, autor, editorial, Double.parseDouble(precio));
			libros.add(libro);
		}
		lectura.close();

	request.getServletContext().setAttribute("libros",libros);

	request.getRequestDispatcher("Principal.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
