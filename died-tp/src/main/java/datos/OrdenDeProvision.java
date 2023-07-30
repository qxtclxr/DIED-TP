package datos;

import java.util.*;
import java.sql.Date;

public class OrdenDeProvision {
	private Integer idOrden;
	private Date fecha;
	private Sucursal sucursalDestino;
	private Integer tiempoMaximo; //Unidad: minutos
	private EstadoOrden estado;
	private Map<Producto,Integer> productos;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Sucursal getSucursalDestino() {
		return sucursalDestino;
	}
	public void setSucursalDestino(Sucursal sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}
	public Integer getTiempoMaximo() {
		return tiempoMaximo;
	}
	public void setTiempoMaximo(Integer tiempoMaximo) {
		this.tiempoMaximo = tiempoMaximo;
	}
	public EstadoOrden getEstado() {
		return estado;
	}
	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}
	public Integer getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}
	public Map<Producto, Integer> getProductos() {
		return productos;
	}
	public void setProductos(Map<Producto, Integer> productos) {
		this.productos = productos;
	}
	
	
}
