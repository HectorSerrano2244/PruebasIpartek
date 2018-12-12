package controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		File archivo=new File(Libro.RUTA_LIBROS);
		if (!archivo.exists()) {
			PrintWriter escritura = new PrintWriter(new FileWriter(Libro.RUTA_LIBROS));
		}else {
			BufferedReader lectura = new BufferedReader(new FileReader(Libro.RUTA_LIBROS));
			String lineafichero,id, isbn, titulo, autor, editorial, precio;
			Integer pos;
			String[] partes;
			Libro libro;
			while ((lineafichero = lectura.readLine()) != null) {
				libro = new Libro();
				partes = lineafichero.split("-");
				pos = partes[0].indexOf("=");
				id = partes[0].substring(pos + 1);
				libro.setId(Long.parseLong(id));
				pos = partes[1].indexOf("=");
				titulo = partes[1].substring(pos + 1);
				libro.setTitulo(titulo);
				pos = partes[2].indexOf("=");
				editorial = partes[2].substring(pos + 1);
				libro.setEditorial(editorial);
				pos = partes[3].indexOf("=");
				isbn = partes[3].substring(pos + 1);
				libro.setIsbn(isbn);
				pos = partes[4].indexOf("=");
				autor = partes[4].substring(pos + 1);
				libro.setAutor(autor);
				pos = partes[5].indexOf("=");
				precio = partes[5].substring(pos + 1);
				libro.setPrecio(Double.parseDouble(precio));
				
				libros.add(libro);
			}
			lectura.close();

		request.setAttribute("libros",libros);
		request.setAttribute("accion", "nuevo");

		request.getRequestDispatcher("principal.jsp").forward(request,response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
