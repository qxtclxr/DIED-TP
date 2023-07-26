package dal;

public interface DAO<T> {
	public void insert();
	
	public void update();
	
	public void delete();
	
	public T getByID();
}