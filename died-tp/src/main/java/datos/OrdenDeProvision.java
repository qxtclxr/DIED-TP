package datos;

import java.util.*;

public class OrdenDeProvision {
	//private Date fecha;
	private Sucursal sucursalDestino;
	private Integer tiempoMaximo; //Unidad: minutos
	private EstadoOrden estado;
	private Map<Producto,Integer> productos;
}
