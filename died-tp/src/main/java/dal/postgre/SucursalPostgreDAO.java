package dal.postgre;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

import dal.general.SucursalDAO;
import datos.*;

public class SucursalPostgreDAO implements SucursalDAO{
	
	private Connection conn;
	private static int BATCH_LIMIT = 1000;
	
	public SucursalPostgreDAO() throws SQLException,ClassNotFoundException {
		super();
		this.conn = Conexion.getInstance().getConn();
	}
	
	public void insert(Sucursal suc) throws SQLException {
		String statement = "INSERT INTO Sucursal (nombre,horarioapertura,horariocierre,estado,tipo) VALUES (?,?,?,?,?)";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {
	        pstm.setString(1, suc.getNombre());
	        pstm.setTime(2, suc.getHorarioApertura());
	        pstm.setTime(3, suc.getHorarioCierre());
	        pstm.setString(4, suc.getEstado().getValueAsString());
	        pstm.setString(5, suc.getTipo().getValueAsString());
	        pstm.executeUpdate();
		}
	}
	
	public void update(Sucursal suc) throws SQLException {
		String statement = "UPDATE Sucursal SET nombre = ?, horarioapertura = ?, horariocierre = ?, estado = ?, tipo = ? WHERE idsucursal = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setString(1, suc.getNombre());
			pstm.setTime(2, suc.getHorarioApertura());
			pstm.setTime(3, suc.getHorarioCierre());
			pstm.setString(4, suc.getEstado().getValueAsString());
			pstm.setString(5, suc.getTipo().getValueAsString());
			pstm.setInt(6, suc.getID());
			pstm.executeUpdate();
		}
	}
	
	public void delete(Sucursal suc) throws SQLException {
		String statement = "DELETE FROM Sucursal WHERE idsucursal = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,suc.getID());
			pstm.executeUpdate();
		}
	}
	
	public Sucursal getByID(Integer id) throws SQLException { //Si no existe, se devuelve null. Podria cambiarse por una exception
		String statement = "SELECT idsucursal,nombre,horarioapertura,horariocierre,estado,tipo " +
						   "FROM Sucursal " +
						   "WHERE idsucursal = ?";
		Sucursal suc =  null;
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,id);
			try(ResultSet rs = pstm.executeQuery();){
				while(rs.next()) {
					suc = new Sucursal(
							rs.getInt(1),
							rs.getString(2),
							rs.getTime(3),
							rs.getTime(4),
							Operatividad.valueOf(rs.getString(5)), 
							TipoSucursal.valueOf(rs.getString(6)));
					/*
					 * Para los ENUM, por restricciones en las columnas de la tabla
					 * se asegura un resultado correcto, es decir, valueOf() se ejecutara
					 * correctamente.
					 */
				}
			}
		}
		return suc;
	}
	
	public List<Sucursal> getPosiblesOrigenes() throws SQLException {
		String statement = "SELECT idsucursal,nombre,horarioapertura,horariocierre,estado,tipo FROM Sucursal WHERE tipo<>'SUMIDERO' ORDER BY nombre";
		ArrayList<Sucursal> result = new ArrayList<Sucursal>();
		
		try(PreparedStatement pstm = conn.prepareStatement(statement);
			ResultSet rs = pstm.executeQuery();){
			while(rs.next()) {
				result.add(new Sucursal(
						rs.getInt(1),
						rs.getString(2),
						rs.getTime(3),
						rs.getTime(4),
						Operatividad.valueOf(rs.getString(5)), 
						TipoSucursal.valueOf(rs.getString(6))
						)
					);
			}
		}		
		return result;
	}
	
	public List<Sucursal> getPosiblesDestinos() throws SQLException {
		String statement = "SELECT idsucursal,nombre,horarioapertura,horariocierre,estado,tipo FROM Sucursal WHERE tipo<>'FUENTE' ORDER BY nombre";
		ArrayList<Sucursal> result = new ArrayList<Sucursal>();
		
		try(PreparedStatement pstm = conn.prepareStatement(statement);
			ResultSet rs = pstm.executeQuery();){
			while(rs.next()) {
				result.add(new Sucursal(
						rs.getInt(1),
						rs.getString(2),
						rs.getTime(3),
						rs.getTime(4),
						Operatividad.valueOf(rs.getString(5)), 
						TipoSucursal.valueOf(rs.getString(6))
						)
					);
			}
		}
		return result;
	}

	@Override
	public List<Sucursal> searchByAttributes(String idSuc, String nombre, TipoSucursal tipo, Operatividad estado,
		Time parseHorarioApertura, Time parseHorarioCierre) throws SQLException {
		List<Sucursal> result = new ArrayList<>();
		try(PreparedStatement pstm = searchStatement(idSuc,nombre,tipo,estado,parseHorarioApertura,parseHorarioCierre);
				ResultSet rs=pstm.executeQuery()){
				while(rs.next()) {
					Sucursal aux=new Sucursal();
					aux.setID(rs.getInt(1));
					aux.setNombre(rs.getString(2));
					aux.setHorarioApertura(rs.getTime(3));
					aux.setHorarioCierre(rs.getTime(4));
					aux.setEstado(Operatividad.valueOf(rs.getString(5)));
					aux.setTipo(TipoSucursal.valueOf(rs.getString(6)));
					result.add(aux);
				}
		}
				
		return result;
	}
	
	private PreparedStatement searchStatement(String idSucInt, String nombre, TipoSucursal tipo, Operatividad estado,
		Time parseHorarioApertura, Time parseHorarioCierre) throws SQLException {
		String statement =
		"SELECT idsucursal,nombre,horarioapertura,horariocierre,estado,tipo FROM sucursal " +
		"WHERE 1=1";
		if(idSucInt != null) statement += " AND LOWER(idsucursal::TEXT) LIKE LOWER(CONCAT('%',?,'%'))";
		if(nombre != null) statement += " AND LOWER(nombre) LIKE LOWER(CONCAT('%',?,'%'))";
		if(tipo != null) statement += " AND tipo = ?";
		if(estado != null) statement += " AND estado = ?";
		if(parseHorarioApertura != null ) statement += " AND horarioapertura <= ?";
		if(parseHorarioCierre != null ) statement += " AND horariocierre >= ?";
		//getValueasString
		statement += " ORDER BY idsucursal";
		
		PreparedStatement pstm = conn.prepareStatement(statement);
		
		int paramIndex = 1;
		if(idSucInt != null) pstm.setString(paramIndex++,idSucInt);
		if(nombre != null) pstm.setString(paramIndex++,nombre);
		if(tipo != null) pstm.setString(paramIndex++,tipo.getValueAsString());
		if(estado != null) pstm.setString(paramIndex++,estado.getValueAsString());
		if(parseHorarioApertura != null) pstm.setTime(paramIndex++, parseHorarioApertura);
		if(parseHorarioCierre != null) pstm.setTime(paramIndex++, parseHorarioCierre);
		return pstm;
	}
	
	public Integer getStock(Sucursal suc, Producto prod) throws SQLException {
		Integer stock = 0;
		String statement = "SELECT cantidad FROM Stock WHERE idsucursal = ? AND idproducto = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,suc.getID());
			pstm.setInt(2,prod.getID());
			try(ResultSet rs = pstm.executeQuery()){
				if(rs.next())
					stock = rs.getInt(1);
			}
		}
		return stock;
	}
	
	public void setStock(Sucursal suc, Producto prod, Integer stock) throws SQLException{
		if(stock > 0) {
			String statement =
					"INSERT INTO Stock (idsucursal,idproducto,cantidad) VALUES (?,?,?) " +
					"ON CONFLICT (idsucursal,idproducto) DO UPDATE SET cantidad = ? WHERE stock.idsucursal = ? AND stock.idproducto = ?";
			try(PreparedStatement pstm = conn.prepareStatement(statement);){
				pstm.setInt(1,suc.getID());
				pstm.setInt(2,prod.getID());
				pstm.setInt(3,stock);
				pstm.setInt(4,stock);
				pstm.setInt(5,suc.getID());
				pstm.setInt(6,prod.getID());
				pstm.executeUpdate();
			}
		}else {
			String statement = "DELETE FROM stock WHERE idsucursal = ? AND idproducto = ?";
			try(PreparedStatement pstm = conn.prepareStatement(statement);){
				pstm.setInt(1,suc.getID());
				pstm.setInt(2,prod.getID());
				pstm.executeUpdate();
			}
		}
	}
	
	public void setStock(Sucursal suc) throws SQLException{
		Set<Entry<Producto, Integer>> stock = suc.getStock().entrySet();
		String statement =
				"INSERT INTO Stock (idsucursal,idproducto,cantidad) VALUES (1,1,59) " +
				"ON CONFLICT (idsucursal,idproducto) DO UPDATE SET cantidad = 59 WHERE stock.idsucursal = 1 AND stock.idproducto = 1";
		/*La anterior sentencia es una sentencia "UPSERT", si la fila no
		 *existe se inserta y si existe se modifica.*/
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
				pstm.setInt(1,prod.getID());
				pstm.setInt(2,suc.getID());
				pstm.setInt(3, cantidad.intValue());
				pstm.setInt(4, cantidad.intValue());
				pstm.setInt(5,prod.getID());
				pstm.setInt(6,suc.getID());
				pstm.addBatch();
				i++;
				if(i%BATCH_LIMIT==0 || i==stock.size()-1)
					pstm.executeBatch();
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
	
	public Map<Producto,Integer> getStock(Sucursal suc) throws SQLException {
		HashMap<Producto,Integer> stock = new HashMap<Producto,Integer>();
		String statement = 
				"SELECT p.idproducto,p.nombre,p.descripcion,p.preciounitario,p.pesokg,s.cantidad " +
				"FROM Stock s, Producto p " +
				"WHERE s.idsucursal = ? AND s.idproducto = p.idproducto";
		try (PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,suc.getID());
			try(ResultSet rs = pstm.executeQuery();){
				while(rs.next()) {
					Producto prod = new Producto(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getFloat(4),
							rs.getFloat(5));
					stock.put(prod,rs.getInt(6));
				}
			}
		}
		return stock;
	}

	@Override
	public List<Sucursal> getAll() throws SQLException {
		List<Sucursal> result = new ArrayList<>();
		try(PreparedStatement pstm = getAllStatement();
				ResultSet rs=pstm.executeQuery()){
				while(rs.next()) {
					Sucursal aux=new Sucursal();
					aux.setID(rs.getInt(1));
					aux.setNombre(rs.getString(2));
					aux.setHorarioApertura(rs.getTime(3));
					aux.setHorarioCierre(rs.getTime(4));
					aux.setEstado(Operatividad.valueOf(rs.getString(5)));
					aux.setTipo(TipoSucursal.valueOf(rs.getString(6)));
					result.add(aux);
				}
		}
				
		return result;
	}

	private PreparedStatement getAllStatement() throws SQLException {

		String statement =
		"SELECT idsucursal,nombre,horarioapertura,horariocierre,estado,tipo FROM sucursal ";
		statement += " ORDER BY idsucursal";
		
		PreparedStatement pstm = conn.prepareStatement(statement);
		
		return pstm;
	}

}
