package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.Operatividad;
import datos.Producto;
import datos.Sucursal;
import datos.TipoSucursal;

public class ProductoPostgreDAO implements ProductoDAO{

	private Connection conn;
	private static int BATCH_LIMIT = 1000;
	
	public ProductoPostgreDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insert(Producto obj) throws SQLException {
		String statement = "INSERT INTO Producto (idproducto, nombre, descripcion, preciounitario, pesokg) VALUES (?,?,?,?,?)";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {
			pstm.setInt(1, obj.getID());
			pstm.setString(2, obj.getNombre());
			pstm.setString(3, obj.getDescripcion());
			pstm.setDouble(4, obj.getPrecioUnitario());
			pstm.setDouble(5, obj.getPesoKg());
		}
		
		
		
		
	}

	@Override
	public void update(Producto obj) throws SQLException {
		String statement = "UPDATE Producto SET idproducto = ?, nombre = ?, descripcion = ?, preciounitario = ?, pesokg = ? WHERE idproducto = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {
			pstm.setInt(1, obj.getID());
			pstm.setString(2, obj.getNombre());
			pstm.setString(3, obj.getDescripcion());
			pstm.setDouble(4, obj.getPrecioUnitario());
			pstm.setDouble(5, obj.getPesoKg());
			
			pstm.setInt(1, obj.getID());
		}
		
		
	}

	@Override
	public void delete(Producto obj) throws SQLException {
		String statement = "DELETE FROM Producto WHERE idproducto = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,obj.getID());
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

}
