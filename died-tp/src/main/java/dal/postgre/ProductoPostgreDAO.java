package dal.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import dal.general.ProductoDAO;
import datos.Operatividad;
import datos.Producto;
import datos.Sucursal;
import datos.TipoSucursal;

public class ProductoPostgreDAO implements ProductoDAO{

	private Connection conn;
	private static int BATCH_LIMIT = 1000;
	
	public ProductoPostgreDAO() throws ClassNotFoundException, SQLException {
		super();
		this.conn = Conexion.getInstance().getConn();
	}
	
	
	@Override
	public void insert(Producto obj) throws SQLException {
		String statement = "INSERT INTO Producto (nombre, descripcion, preciounitario, pesokg) VALUES (?,?,?,?)";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getDescripcion());
			pstm.setDouble(3, obj.getPrecioUnitario());
			pstm.setDouble(4, obj.getPesoKg());
			pstm.executeUpdate();
		}
	}

	@Override
	public void update(Producto obj) throws SQLException {
		String statement = "UPDATE Producto SET nombre = ?, descripcion = ?, preciounitario = ?, pesokg = ? WHERE idproducto = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getDescripcion());
			pstm.setDouble(3, obj.getPrecioUnitario());
			pstm.setDouble(4, obj.getPesoKg());
			pstm.setInt(5, obj.getID());
			pstm.executeUpdate();
		}		
	}

	@Override
	public void delete(Producto obj) throws SQLException {
		String statement = "DELETE FROM Producto WHERE idproducto = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,obj.getID());
			pstm.executeUpdate();
		}
		
	}

	@Override
	public Producto getByID(Integer id) throws SQLException {
	
		String statement = "SELECT idproducto, nombre, descripcion, preciounitario, pesokg " +
				   "FROM Producto " +
				   "WHERE idproducto = ?";
		Producto p =  null;
			try(PreparedStatement pstm = conn.prepareStatement(statement);){
						pstm.setInt(1,id);
								try(ResultSet rs = pstm.executeQuery();){
										while(rs.next()) {
												p = new Producto(
														rs.getInt(1),
														rs.getString(2),
														rs.getString(3),
														rs.getFloat(4),
														rs.getFloat(5)
													);	

			/*
			 * Para los ENUM, por restricciones en las columnas de la tabla
			 * se asegura un resultado correcto, es decir, valueOf() se ejecutara
			 * correctamente.
			 */
										}
								}
			}
			return p;

	}

	public List<Producto> searchByAttributes(String idProducto, String nombre, Float precioUDesdeF, Float precioUHastaF,
			Float pesoDesdeF, Float pesoHastaF) throws SQLException {
		List<Producto> result = new ArrayList<>();
		try(PreparedStatement pstm = searchStatement(idProducto,nombre,precioUDesdeF,precioUHastaF,pesoDesdeF,pesoHastaF);
				ResultSet rs=pstm.executeQuery()){
				while(rs.next()) {
					Producto aux=new Producto();
					aux.setID(rs.getInt(1));
					aux.setNombre(rs.getString(2));
					aux.setDescripcion(rs.getString(3));
					aux.setPrecioUnitario(rs.getFloat(4));
					aux.setPesoKg(rs.getFloat(5));
					
					result.add(aux);
				}
		return result;
	}
}
		private PreparedStatement searchStatement(String idProducto, String nombre, Float precioUDesdeF, Float precioUHastaF,
				Float pesoDesdeF, Float pesoHastaF) throws SQLException {
			String statement =
			"SELECT idproducto,nombre,descripcion,preciounitario,pesokg FROM producto " +
			"WHERE 1=1";
			if(idProducto != null) statement += " AND LOWER(idproducto::TEXT) LIKE LOWER(CONCAT('%',?,'%'))";
			if(nombre != null) statement += " AND LOWER(nombre) LIKE LOWER(CONCAT('%',?,'%'))";
			if(precioUDesdeF != null || precioUHastaF != null) statement += " AND (preciounitario BETWEEN ? AND ?)";
			if(pesoDesdeF != null || pesoHastaF != null) statement += " AND (pesokg BETWEEN ? AND ?)";
			//getValueasString
			statement += " ORDER BY idproducto";
			
			PreparedStatement pstm = conn.prepareStatement(statement);
			
			int paramIndex = 1;
			if(idProducto != null) pstm.setString(paramIndex++,idProducto);
			if(nombre != null) pstm.setString(paramIndex++,nombre);
			if(precioUDesdeF != null || precioUHastaF != null) {
				if(precioUDesdeF != null) pstm.setFloat(paramIndex++,precioUDesdeF);
				else pstm.setFloat(paramIndex++,-1);
				if(precioUHastaF != null) pstm.setFloat(paramIndex++,precioUHastaF);
				else pstm.setString(paramIndex++,"inf");
			}
			if(pesoDesdeF != null || pesoHastaF != null) {
				if(pesoDesdeF != null) pstm.setFloat(paramIndex++,pesoDesdeF);
				else pstm.setFloat(paramIndex++,-1);
				if(pesoHastaF != null) pstm.setFloat(paramIndex++,pesoHastaF);
				else pstm.setString(paramIndex++,"inf");
			}
			
			
			return pstm;
	}
		

}
