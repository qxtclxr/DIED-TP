package dal.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		}
	}

	@Override
	public void update(Producto obj) throws SQLException {
		String statement = "UPDATE Producto SET  nombre = ?, descripcion = ?, preciounitario = ?, pesokg = ? WHERE idproducto = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {
			pstm.setInt(1, obj.getID());
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

	public List<Producto> searchByAttributes(Producto obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
