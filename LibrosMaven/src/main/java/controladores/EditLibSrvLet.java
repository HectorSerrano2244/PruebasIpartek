package controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.Libro;


@WebServlet("/editarlibro")
public class EditLibSrvLet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditLibSrvLet() {
        super();
 
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idrecibido=request.getParameter("id");
		Libro libro;
		String[] partes;
		Integer pos;
		String lineafichero,id, isbn, titulo, autor, editorial, precio;
		BufferedReader lectura = new BufferedReader(new FileReader(Libro.RUTA_LIBROS));
		while ((lineafichero = lectura.readLine()) != null) {
			libro = new Libro();
			partes = lineafichero.split("-");
			pos = partes[0].indexOf("=");
			id = partes[0].substring(pos + 1);
			if(id.equals(idrecibido)) {
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
				
				String accion="editar";
				request.setAttribute("libro", libro);
				request.setAttribute("accion", accion);
					
				break;
			}
		}
		request.getRequestDispatcher("formulario.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
