package dal.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import dal.general.OrdenDeProvisionDAO;
import datos.OrdenDeProvision;
import datos.Producto;

public class OrdenDeProvisionPostgreDAO implements OrdenDeProvisionDAO  {

	private Connection conn;
	private static int BATCH_LIMIT = 1000;
	
	public OrdenDeProvisionPostgreDAO() throws ClassNotFoundException, SQLException {
		super();
		this.conn = Conexion.getInstance().getConn();
	}
	
	
	@Override
	public void insert(OrdenDeProvision ord) throws SQLException {
		boolean successful = true;
		String statement = "INSERT INTO ordendeprovision (sucursaldestino,fecha,tiempomaximo,estadoorden) VALUES (?,?,?,?) RETURNING idorden";
		try {
			conn.setAutoCommit(false);
			try(PreparedStatement pstm = conn.prepareStatement(statement);) {
				pstm.setInt(1, ord.getSucursalDestino().getID());
				pstm.setDate(2, ord.getFecha());
				pstm.setInt(3, ord.getTiempoMaximo());
				pstm.setString(4, ord.getEstado().getValueAsString());
		        try(ResultSet rs =  pstm.executeQuery();){
		        	if(rs.next())
		        		ord.setID(rs.getInt(1));
		        }
			}
			insertProductos(ord);
			
		}catch(Exception ex) {
			successful = false;
			throw ex;
		}finally {
			if(successful) conn.commit();
			else conn.rollback();
			conn.setAutoCommit(true);
		}
	}
	
	public void insertProductos(OrdenDeProvision ord) throws SQLException {
		Set<Entry<Integer, Integer>> productos = ord.getProductos().entrySet();
		String statement = "INSERT INTO detalleorden(idorden, idproducto, cantidad) VALUES (?,?,?)";
		try (PreparedStatement pstm = conn.prepareStatement(statement);){
			int i = 0;
			/*Se hace un conteo de el numero de fila insertada porque
			 *las BD tienen un limite de filas por operacion batch.*/
			for(Entry<Integer, Integer> item : productos) {
				Integer prodID = item.getKey();
				Integer cantidad = item.getValue();
				pstm.setInt(1,ord.getID());
				pstm.setInt(2, prodID);
				pstm.setInt(3, cantidad);
				pstm.addBatch();
				i++;
				if(i%BATCH_LIMIT==0)
					pstm.executeBatch();
			}
			pstm.executeBatch();
		}
	}

	@Override
	public void update(OrdenDeProvision obj) throws SQLException {
		/*String statement = "UPDATE ordendeprovision SET idorden = ?, sucursaldestino = ?, fecha= ? , tiempomaximo = ? WHERE idorden = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){

			pstm.setInt(1, obj.getID());
			pstm.setInt(2, obj.getSucursalDestino().getID());
			pstm.setDate(3, obj.getFecha());
			pstm.setInt(4, obj.getTiempoMaximo());
			pstm.setInt(5, obj.getID());
		}
		
		obj.getProductos().forEach((prod, cant) -> {
			String statment = "UPDATE detalleorden SET idorden = ?, idproducto = ?, cantidad = ? WHERE idorden = ? AND idproducto = ?";
			try(PreparedStatement pstm = conn.prepareStatement(statement);){
				pstm.setInt(1, obj.getID());
				pstm.setInt(2, prod.getID());
				pstm.setInt(3, cant);
				pstm.setInt(4, obj.getID());
				pstm.setInt(5, prod.getID());
			}
		});*/
	}

	@Override
	public void delete(OrdenDeProvision obj) throws SQLException {
		String statement = "DELETE FROM ordendeprovision WHERE idorden = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,obj.getID());
			pstm.executeUpdate();
		}
	}

	@Override
	public OrdenDeProvision getByID(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<OrdenDeProvision> searchByAttributes(OrdenDeProvision obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
