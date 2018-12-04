import java.util.List;

public interface CrudAble<Libro> {
	
	
	public default List<Libro> getAll() {
		throw new RuntimeException("No implementado");
	}
	public default Libro getById(Long id) {
		throw new RuntimeException("No implementado");
	}
	public default void insert(Libro libro) {
		throw new RuntimeException("No implementado");
	}
	public default void update(Libro libro) {
		throw new RuntimeException("No implementado");
	}
	public default void delete(Long id) {
		throw new RuntimeException("No implementado");
	}
}
