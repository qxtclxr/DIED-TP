package dal.general;

import java.sql.SQLException;
import java.util.List;

import datos.Producto;

public interface ProductoDAO extends DAO<Producto>{

	List<Producto> searchByAttributes(String idProducto, String nombre, Float precioUDesdeF, Float precioUHastaF,
			Float pesoDesdeF, Float pesoHastaF)throws SQLException;

}
