package logica;


import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import dal.general.FactoryDAO;
import datos.Operatividad;
import datos.Producto;
import datos.Ruta;
import datos.Sucursal;
import datos.TipoSucursal;
import excepciones.IDNotFoundException;

import java.util.stream.Collectors;

import logica.grafo.Grafo;

public final class GestorSucursal {
	/* Responsabilidades que tendria esta clas:
	 * ALERTA: Una vez que la inicialices, no vas a poder cambiarle la conexion
	 * Realizar altas y bajas mediante la conexion a un DAO
	 */
	
	private static GestorSucursal gestor;
	
	public synchronized static GestorSucursal getInstance() {
		if(gestor==null) {
			gestor= new GestorSucursal();
		}
		return gestor;
	}

	private GestorSucursal(){
		super();
	}

	
	public void altaSucursal(String nombreSuc,TipoSucursal tipo, Operatividad operatividad,
							 String horarioApertura,String horarioCierre) throws SQLException, ClassNotFoundException{
		Time parseHorarioApertura = Time.valueOf(horarioApertura+":00");
		Time parseHorarioCierre = Time.valueOf(horarioCierre+":00");
		Sucursal aux= new Sucursal(nombreSuc,parseHorarioApertura,parseHorarioCierre,operatividad,tipo);
		FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
		fact.getSucursalDAO().insert(aux);
	}
	public void modificarSucursal(Integer idSucursal,String nombreSuc,TipoSucursal tipo,
								  Operatividad operatividad,String horarioApertura,String horarioCierre) throws ClassNotFoundException, SQLException {
		Time parseHorarioApertura = Time.valueOf(horarioApertura+":00");
		Time parseHorarioCierre = Time.valueOf(horarioCierre+":00");
		Sucursal aux= new Sucursal(idSucursal,nombreSuc,parseHorarioApertura,parseHorarioCierre,operatividad,tipo);
		FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
		fact.getSucursalDAO().update(aux);
	}
	
	public List<Sucursal> getPosiblesOrigenes() throws SQLException,ClassNotFoundException{
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().getPosiblesOrigenes();
	}
	public List<Sucursal> getPosiblesDestinos() throws ClassNotFoundException, SQLException{
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().getPosiblesDestinos();
	}
	
	public List<Sucursal> consultaPorAtributos(String idSucursal, String nombre, TipoSucursal tipo,
			 Operatividad estado, String horarioApertura, String horarioCierre) throws ClassNotFoundException, SQLException{
			
			FactoryDAO fact=FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
			
			if(idSucursal.isBlank()) {
				idSucursal=null;
			}
			
			
			Time parseHorarioApertura;
			
			if(horarioApertura.equals("--:--")) {
				parseHorarioApertura=null;
				
			}
			else {
				parseHorarioApertura= Time.valueOf(horarioApertura+":00");
				
			}
			Time parseHorarioCierre;
			if(horarioCierre.equals("--:--")) {
				parseHorarioCierre=null;
			}
			else {
				parseHorarioCierre=Time.valueOf(horarioCierre+":00");
			}
			
			
			
			return fact.getSucursalDAO().searchByAttributes(idSucursal, nombre,tipo, estado, parseHorarioApertura, parseHorarioCierre);
		
	}
	
	public Sucursal getByID(Integer id) throws ClassNotFoundException, SQLException, IDNotFoundException {
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().getByID(id);
	}
	public void eliminar(Sucursal s) throws ClassNotFoundException, SQLException {
		FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().delete(s);
	}
	
	public List<Sucursal> getAll() throws ClassNotFoundException, SQLException{
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().getAll();
	}
	
	public Map<Producto, Integer> getStock(Sucursal s) throws ClassNotFoundException, SQLException {
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().getStock(s);
	}
	
	public Integer getStockOfProduct(Sucursal s, Producto p) throws ClassNotFoundException, SQLException {
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().getStockOfProduct(s,p);
	}
	
	public void setStock(Sucursal s, Producto p, Integer stock) throws ClassNotFoundException, SQLException {
		FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().setStock(s,p,stock);
	}
	
	public List<Sucursal> hasStock(Map<Integer,Integer> stockRequired) throws SQLException, ClassNotFoundException{
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().hasStock(stockRequired);
	}
	
	public Float getFlujoMaximo(Sucursal origen,Sucursal destino, List<List<Ruta>> res) throws ClassNotFoundException, SQLException {
		Grafo g=new Grafo();
		g.addArista(FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getRutaDAO().getAll());
		g.addVertice(FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().getAll());
		return g.flujoMaximo(origen, destino, res);
	}
	public Map<Sucursal,Double> pageRank() throws ClassNotFoundException, SQLException{
		Grafo g=new Grafo();
		g.addArista(FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getRutaDAO().getAll());
		g.addVertice(FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getSucursalDAO().getAll());
		//return g.pageRank().entrySet().stream().sorted((t1,t2)->t1.getValue().compareTo(t2.getValue())).map(t->t.getKey()).collect(Collectors.toList());
		return g.pageRank();
	}
}
