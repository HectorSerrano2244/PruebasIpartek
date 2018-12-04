import java.util.ArrayList;
import java.util.List;

public class LibroDaoArrayList implements CrudAble<Libro> {

	private ArrayList<Libro> libros = new ArrayList<>();

	@Override
	public List<Libro> getAll() {
		return libros;
	}

	@Override
	public Libro getById(Long id) {
		for (Libro libro : libros) {
			if (libro.getId() == id) {
				return libro;
			}
		}

		return null;
	}

	@Override
	public void insert(Libro libro) {
		libros.add(libro);
	}

	@Override
	public void update(Libro libro) {
		int posicionId = -1;
		
		for(int i = 0; i < libros.size(); i++) {
			if(libros.get(i).getId() == libro.getId()) {
				posicionId = i;
			} 
		}
		if(posicionId == -1) {
			throw new RuntimeException("Libro no encontrado");
		}
		
		libros.set(posicionId, libro);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		CrudAble.super.delete(id);
	}

}
