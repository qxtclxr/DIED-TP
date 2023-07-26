package dal;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

import datos.*;

public class SucursalDAO implements DAO<Sucursal>{
	
	private Connection conn;
	private static int BATCH_LIMIT = 1000;
	
	public SucursalDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void insert(Sucursal suc) throws SQLException {
		String statement = "INSERT INTO Sucursal (idsucursal,nombre,horarioapertura,horariocierre,estado,tipo) VALUES (?,?,?,?,?,?)";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {
	        pstm.setString(2, suc.getNombre());
	        pstm.setTime(3, suc.getHorarioApertura());
	        pstm.setTime(4, suc.getHorarioCierre());
	        pstm.setString(5, suc.getEstado().toString());
	        pstm.setString(6, suc.getTipo().toString());
	        pstm.executeUpdate();
		}
		setStock(suc);
	}
	
	public void update(Sucursal suc) throws SQLException {
		String statement = "UPDATE Sucursal SET nombre = ?, horarioapertura = ?, horariocierre = ?, estado = ?, tiposucursal = ? WHERE idsucursal = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setString(1, suc.getNombre());
			pstm.setTime(2, suc.getHorarioApertura());
			pstm.setTime(3, suc.getHorarioCierre());
			pstm.setString(4, suc.getEstado().toString());
			pstm.setString(5, suc.getTipo().toString());
			pstm.setString(6, suc.getID());
		}
	}
	
	public void setStock(Sucursal suc) throws SQLException{
		Set<Entry<Producto, Integer>> stock = suc.getStock().entrySet();
		String statement = "INSERT Stock SET idproducto = ?, idsucursal = ?, cantidad = ? " +
						   "ON CONFLICT DO UPDATE cantidad = ? WHERE idproducto = ? AND idsucursal = ?";
		/*La anterior sentencia es una sentencia "UPSERT", si la fila no
		 * existe se inserta y si existe se modifica.*/
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			conn.setAutoCommit(false);
			/*Se setea el autocommit en false, ya que se ejecutan
			 *multiples sentencias batch y ante un error se deberia
			 *hacer un rollback de todas*/
			int i = 0;
			/*Se hace un conteo de el numero de fila insertada porque
			 *las BD tienen un limite de filas por operacion batch.*/
			for(Entry<Producto, Integer> item : stock) {
				Producto prod = item.getKey();
				Integer cantidad = item.getValue();
				pstm.setString(1,prod.getID());
				pstm.setString(2,suc.getID());
				pstm.setInt(3, cantidad.intValue());
				pstm.setInt(4, cantidad.intValue());
				pstm.setString(5,prod.getID());
				pstm.setString(6,suc.getID());
				pstm.addBatch();
				if(i%BATCH_LIMIT==0 || i==stock.size()-1)
					pstm.executeBatch();
				i++;
			}
		}catch (SQLException ex) {
			conn.rollback();
			/*Si hubo un error, rollback y se relanza la excepcion para
			 *que la reciba la GUI.*/
			throw ex;
		}finally {
			conn.setAutoCommit(true);
			//Se vuelve a setear el autocommit a true como estaba originalmente.
		}
	}
	
	public Map<Producto,Integer> getStock(Sucursal suc) throws SQLException {//Puede cambiar para no tener que llamar a ProductoDAO
		ProductoDAO productoDAO = new ProductoDAO();
		HashMap<Producto,Integer> stock = new HashMap<Producto,Integer>();
		String statement = "SELECT * FROM Stock WHERE idsucursal = ?";
		try (PreparedStatement pstm = conn.prepareStatement(statement);
			 ResultSet rs = pstm.executeQuery();){
			while(rs.next()) {
				Producto prod = productoDAO.getByID(rs.getString("idproducto"));
				stock.put(prod,rs.getInt("cantidad"));
			}
		}
	}
}
