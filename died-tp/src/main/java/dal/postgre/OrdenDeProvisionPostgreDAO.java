package dal.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import dal.general.Conexion;
import dal.general.OrdenDeProvisionDAO;
import dal.general.SucursalDAO;
import datos.*;
import excepciones.IDNotFoundException;

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
	
	public List<OrdenDeProvision> getPendientes() throws SQLException {
		List<OrdenDeProvision> ordenes = new ArrayList<>();
		String statement =
				"SELECT s.idsucursal,s.nombre,s.horarioapertura,s.horariocierre,s.estado,s.tipo,"+
				"o.idorden,o.fecha,o.tiempomaximo,o.estadoorden "+
				"FROM ordendeprovision o, Sucursal s "+ 
				"WHERE o.sucursaldestino = s.idsucursal AND o.estadoorden = 'PENDIENTE'";
		try(PreparedStatement pstm = conn.prepareStatement(statement);
			ResultSet rs = pstm.executeQuery();){
			while(rs.next()) {
				Sucursal suc = new Sucursal();
				suc.setID(rs.getInt(1));
				suc.setNombre(rs.getString(2));
				suc.setHorarioApertura(rs.getTime(3));
				suc.setHorarioCierre(rs.getTime(4));
				suc.setEstado(Operatividad.valueOf(rs.getString(5)));
				suc.setTipo(TipoSucursal.valueOf(rs.getString(6)));
				OrdenDeProvision orden = new OrdenDeProvision();
				orden.setID(rs.getInt(7));
				orden.setFecha(rs.getDate(8));
				orden.setTiempoMaximo(rs.getInt(9));
				orden.setEstado(EstadoOrden.valueOf(rs.getString(10)));
				orden.setSucursalDestino(suc);
				ordenes.add(orden);
			}
		}
		for(OrdenDeProvision orden : ordenes) {
			orden.setProductos(this.getProductos(orden));
		}
		return ordenes;
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
	
	public Map<Integer,Integer> getProductos(OrdenDeProvision ord) throws SQLException{
		Map<Integer,Integer> productos = new HashMap<>();
		String statement = "SELECT idproducto,cantidad FROM detalleorden WHERE idorden = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);){
			pstm.setInt(1,ord.getID());
			try(ResultSet rs = pstm.executeQuery();){
				while(rs.next()) {
					productos.put(rs.getInt(1), rs.getInt(2));
				}
			}
		}
		return productos;
	}

	@Override
	public void update(OrdenDeProvision obj) throws SQLException {
		//No implementa.
	}
	
	public void setEnProceso(OrdenDeProvision ord) throws SQLException{
		String statement = "UPDATE OrdenDeProvision SET estadoorden = 'EN_PROCESO' WHERE idorden = ?";
		try(PreparedStatement pstm = conn.prepareStatement(statement);) {
			pstm.setInt(1,ord.getID());
			pstm.executeUpdate();
		}
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
	public OrdenDeProvision getByID(Integer id) throws SQLException,IDNotFoundException {
		OrdenDeProvision orden = null;
		String statement =
				"SELECT s.idsucursal,s.nombre,s.horarioapertura,s.horariocierre,s.estado,s.tipo,"+
				"o.idorden,o.fecha,o.tiempomaximo,o.estadoorden "+
				"FROM ordendeprovision o, Sucursal s "+ 
				"WHERE o.sucursaldestino = s.idsucursal";
		try(PreparedStatement pstm = conn.prepareStatement(statement);
				ResultSet rs = pstm.executeQuery();){
				while(rs.next()) {
					Sucursal suc = new Sucursal();
					suc.setID(rs.getInt(1));
					suc.setNombre(rs.getString(2));
					suc.setHorarioApertura(rs.getTime(3));
					suc.setHorarioCierre(rs.getTime(4));
					suc.setEstado(Operatividad.valueOf(rs.getString(5)));
					suc.setTipo(TipoSucursal.valueOf(rs.getString(6)));
					orden = new OrdenDeProvision();
					orden.setID(rs.getInt(7));
					orden.setFecha(rs.getDate(8));
					orden.setTiempoMaximo(rs.getInt(9));
					orden.setEstado(EstadoOrden.valueOf(rs.getString(10)));
					orden.setSucursalDestino(suc);
				}
			}
			if(orden!=null) {
				orden.setProductos(this.getProductos(orden));
				return orden;
			}
			else{
				throw new IDNotFoundException();
			}
	}
	
	
	public List<OrdenDeProvision> searchByAttributes(OrdenDeProvision obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
