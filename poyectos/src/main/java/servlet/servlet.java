package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String rutaimagen = request.getParameter("rutaimagen");
		String rutaenlace = request.getParameter("rutaenlace");
		
		Proyecto proyecto;
		try {
			proyecto = new Proyecto(nombre,descripcion, rutaimagen, rutaenlace);
			
		} catch(RuntimeException e) {
			//response.sendRedirect("login.jsp");
			request.setAttribute("error", "Error en la lectura de proyectos");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
		
		proyectos.add(new Proyecto(1,"Proyecto 1"," efoj feoj 3fo weo fweo fewof wofej weoj few ", "C://", "C://"));
		proyectos.add(new Proyecto(2,"Proyecto 2"," efoj feoj 3fo dsgdsfdsgfdsfew ", "C://", "C://"));
		proyectos.add(new Proyecto(3,"Proyecto 3"," efoj fdsfgdsfg egjh sohe rojh greoih greewof wofej weoj few ", "C://", "C://"));
		proyectos.add(new Proyecto(4,"Proyecto 4"," dgfdfgdfgdfgddf e reg rtfewof wofej weoj few ", "C://", "C://"));
		
		request.setAttribute("proyectos", proyectos);
		
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
