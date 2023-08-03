package dal.general;

import java.sql.SQLException;
import java.util.List;

import datos.Operatividad;
import datos.Ruta;
import datos.Sucursal;

public interface RutaDAO extends DAO<Ruta>{
	public List<Ruta> searchByAttributes(String idRuta, Sucursal origen, Sucursal destino,
			 Operatividad estado, Integer duracionDesde, Integer duracionHasta,
			 Float capacMaxDesde, Float capacMaxHasta) throws SQLException;
	public List<Ruta> getAll()throws SQLException;
}
