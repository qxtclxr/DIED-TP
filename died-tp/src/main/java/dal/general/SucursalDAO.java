package dal.general;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import datos.Operatividad;
import datos.OrdenDeProvision;
import datos.Producto;
import datos.Sucursal;
import datos.TipoSucursal;

public interface SucursalDAO extends DAO<Sucursal> {
	public abstract void setStock(Sucursal suc, Producto p, Integer stock) throws SQLException;
	public abstract void setStock(Sucursal suc) throws SQLException;
	public abstract Map<Producto,Integer> getStock(Sucursal suc) throws SQLException;
	public List<Sucursal> getPosiblesOrigenes() throws SQLException;
	public List<Sucursal> getPosiblesDestinos() throws SQLException;
	public List<Sucursal> searchByAttributes(String idSuc, String nombre,TipoSucursal tipo,Operatividad estado, Time parseHorarioApertura, Time parseHorarioCierre) throws SQLException;
	public abstract Integer getStockOfProduct(Sucursal s, Producto p) throws SQLException;
	public List<Sucursal> getAll() throws SQLException;
	public abstract List<Sucursal> hasStock(OrdenDeProvision ord) throws SQLException;
	public abstract boolean esFuente(Sucursal suc) throws SQLException;
	public abstract boolean esSumidero(Sucursal suc) throws SQLException;
}
