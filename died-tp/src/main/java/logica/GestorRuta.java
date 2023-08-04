package logica;


import java.sql.SQLException;
import java.util.List;

import dal.general.FactoryDAO;
import dal.general.SucursalDAO;
import datos.Ruta;
import datos.Sucursal;
import excepciones.IDNotFoundException;
import datos.Operatividad;

public final class GestorRuta {
	/* falta gestionar responsabilidades para esta clase
	 * 
	 */

	private static GestorRuta gestor;
	
	public synchronized static GestorRuta getInstance() {
		if(gestor==null) {
			gestor=new GestorRuta();
		}
		return gestor;
	}
	
	private GestorRuta() {
		super();
	}
	
	public void altaRuta(Sucursal sucursalOrigen, Sucursal sucursalDestino ,Operatividad estado, String duracion, String capacidad) throws SQLException, ClassNotFoundException{
		
		FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
		Integer duracionParse = Integer.parseInt(duracion);
		Float capacidadParse = Float.parseFloat(capacidad);
	/* Coloco un null en el constructor de ruta en la posicion del id, porque eso me lo va a 
	 * generar como serial la bdd 
	 */
		Ruta aux= new Ruta(sucursalOrigen,sucursalDestino,estado,duracionParse,capacidadParse);
		fact.getRutaDAO().insert(aux);
	}
	
	public void modificarRuta(Integer idRuta, Sucursal sucursalOrigen, Sucursal sucursalDestino ,Operatividad estado, String duracion, String capacidad) throws ClassNotFoundException, SQLException {
		FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
		Integer duracionParse = Integer.parseInt(duracion);
		Float capacidadParse = Float.parseFloat(capacidad);
	/* Coloco un null en el constructor de ruta en la posicion del id, porque eso me lo va a 
	 * generar como serial la bdd 
	 */
		Ruta aux= new Ruta(idRuta,sucursalOrigen,sucursalDestino,estado,duracionParse,capacidadParse);
		fact.getRutaDAO().update(aux);
	}
	
	public List<Ruta> consultaPorAtributos(String idRuta, Sucursal origen, Sucursal destino,
			 Operatividad estado, String duracionDesde, String duracionHasta,
			 String capacMaxDesde, String capacMaxHasta) throws ClassNotFoundException, SQLException{
			
			FactoryDAO fact=FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
			Integer idRutaInt;
			if(idRuta.isBlank()) {
				idRutaInt=null;
			}
			Integer duracionDesdeInt;
			Integer duracionHastaInt;
			if(duracionDesde.isBlank()) {
				duracionDesdeInt=null;
			}
			else {
				duracionDesdeInt=Integer.parseInt(duracionDesde);
				
			}
			if(duracionHasta.isBlank()) {
				duracionHastaInt=null;
			}
			else {
				duracionHastaInt=Integer.parseInt(duracionHasta);
			}
			
			Float capMaxDesdeFloat;
			Float capMaxHastaFloat;
			if(capacMaxDesde.isBlank()) {
				capMaxDesdeFloat=null;
				
			}
			else {
				capMaxDesdeFloat=Float.parseFloat(capacMaxDesde);
			}
			if(capacMaxHasta.isBlank()) {
				capMaxHastaFloat=null;
			}
			else {
				capMaxHastaFloat=Float.parseFloat(capacMaxHasta);
			}
			
			
			return fact.getRutaDAO().searchByAttributes(idRuta, origen, destino, estado, duracionDesdeInt, duracionHastaInt, capMaxDesdeFloat, capMaxHastaFloat);
		
	}
	public Ruta getByID(Integer idruta) throws ClassNotFoundException, SQLException, IDNotFoundException {
		return FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getRutaDAO().getByID(idruta);
	}
	public void eliminar(Ruta r) throws ClassNotFoundException, SQLException {
		FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY).getRutaDAO().delete(r);
	}
	
	
	

}
