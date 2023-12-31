package datos;

import java.util.*;
import java.sql.Time;

public class Sucursal implements Comparable<Sucursal>, Entidad{
	private Integer idSucursal;
	private String nombre;
	private Time horarioApertura;
	private Time horarioCierre;
	private Operatividad estado;
	private TipoSucursal tipo;
	private Map<Producto,Integer> stock;
	
	public Sucursal() {
		this.stock = new HashMap<>();
	}
	
	public Sucursal(Integer idSucursal, String nombre, Time horarioApertura, Time horarioCierre, Operatividad estado, TipoSucursal tipo) {
		super();
		this.idSucursal = idSucursal;
		this.nombre = nombre;
		this.estado = estado;
		this.tipo = tipo;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
	}
	
	
	public Sucursal( String nombre, Time horarioApertura, Time horarioCierre, Operatividad estado, TipoSucursal tipo) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.tipo = tipo;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
	}

	public Integer getID() {
		return idSucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public Operatividad getEstado() {
		return estado;
	}

	public TipoSucursal getTipo() {
		return tipo;
	}
	
	public Time getHorarioApertura() {
		return horarioApertura;
	}

	public Time getHorarioCierre() {
		return horarioCierre;
	}
	
	public Map<Producto, Integer> getStock() {
		return stock;
	}
	
	public void setID(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setHorarioApertura(Time horarioApertura) {
		this.horarioApertura = horarioApertura;
	}


	public void setHorarioCierre(Time horarioCierre) {
		this.horarioCierre = horarioCierre;
	}


	public void setEstado(Operatividad estado) {
		this.estado = estado;
	}


	public void setTipo(TipoSucursal tipo) {
		this.tipo = tipo;
	}
	
	public void setStock(Map<Producto, Integer> stock) {
		this.stock = stock;
	}
	
	public int compareTo(Sucursal suc) {
		return this.idSucursal.compareTo(suc.idSucursal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSucursal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sucursal other = (Sucursal) obj;
		return Objects.equals(idSucursal, other.idSucursal);
	}
	
	@Override
	public String toString() {
		return this.getNombre() + " (" + this.getID() + ")";
	}
	
}
