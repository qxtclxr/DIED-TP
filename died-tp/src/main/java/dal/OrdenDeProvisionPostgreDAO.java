package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import datos.OrdenDeProvision;

public class OrdenDeProvisionPostgreDAO implements OrdenDeProvisionDAO  {

	private Connection conn;
	private static int BATCH_LIMIT = 1000;
	
	public OrdenDeProvisionPostgreDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insert(OrdenDeProvision obj) throws SQLException {
		String statement = "INSERT INTO ordendeprovision (idorden,sucursaldestino,fecha,tiempomaximo) VALUES (?,?,?,?)";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {

			pstm.setString(1, obj.getIdOrden());
			pstm.setString(2, obj.getSucursalDestino().getID());
			pstm.setDate(3, obj.getFecha());
			pstm.setInt(4, obj.getTiempoMaximo());
			
	        pstm.executeUpdate();
		}

		
		obj.getProductos().forEach((prod, cant) -> {
			String statment = "INSERT INTO detalleorden(idorden, idproducto, cantidad) VALUES (?,?,?)";
			try(PreparedStatement pstm = conn.prepareStatement(statement);){
				pstm.setString(1, obj.getIdOrden());
				pstm.setString(2, prod.getID());
				pstm.setInt(3, cant);
			}

		});

		
		
	}

	@Override
	public void update(OrdenDeProvision obj) throws SQLException {
		String statement = "UPDATE ordendeprovision SET idorden = ?, sucursaldestino = ?, fecha= ? , tiempomaximo = ? WHERE idorden = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){

			pstm.setString(1, obj.getIdOrden());
			pstm.setString(2, obj.getSucursalDestino().getID());
			pstm.setDate(3, obj.getFecha());
			pstm.setInt(4, obj.getTiempoMaximo());
			pstm.setString(5, obj.getIdOrden());
		}
		
		obj.getProductos().forEach((prod, cant) -> {
			String statment = "UPDATE detalleorden SET idorden = ?, idproducto = ?, cantidad = ? WHERE idorden = ? AND idproducto = ?";
			try(PreparedStatement pstm = conn.prepareStatement(statement);){
				pstm.setString(1, obj.getIdOrden());
				pstm.setString(2, prod.getID());
				pstm.setInt(3, cant);
				pstm.setString(4, obj.getIdOrden());
				pstm.setString(5, prod.getID());
			}
		});
		
		
	}

	@Override
	public void delete(OrdenDeProvision obj) throws SQLException {
		String statement = "DELETE FROM ordendeprovision WHERE idorden = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setString(1,obj.getIdOrden());
		}
	
		obj.getProductos().forEach((prod, cant) -> {
			String statment = "DELETE FROM detalleorden WHERE idorden = ?";
			try(PreparedStatement pstm = conn.prepareStatement(statement);){
				pstm.setString(1, obj.getIdOrden());
			}

		});
		
	}	

	@Override
	public OrdenDeProvision getByID(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
