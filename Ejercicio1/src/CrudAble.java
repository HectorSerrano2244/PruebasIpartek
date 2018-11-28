

public interface CrudAble<Clase> {
//	java <=7 (lo imso sin default)
//	public List<Pojo> getAll();
//	public Pojo getById(Long id);
//	public void insert(Pojo pojo);
//	public void update(Pojo pojo);
//	public void delete(Pojo pojo);
	public default void getAll() {
		throw new RuntimeException("No implementado");
	}
	public default Clase getById(Long id) {
		throw new RuntimeException("No implementado");
	}
	public default void insert(Clase clase) {
		throw new RuntimeException("No implementado");
	}
	public default void update(Clase clase) {
		throw new RuntimeException("No implementado");
	}
	public default void delete(Long id) {
		throw new RuntimeException("No implementado");
	}
}
