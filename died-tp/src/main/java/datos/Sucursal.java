;;package datos;

import java.util.*;
import java.sql.Time;

public class Sucursal implements Comparable<Sucursal>{
	private String idSucursal;
	private String nombre;
	private Time horarioApertura;
	private Time horarioCierre;
	private Operatividad estado;
	private TipoSucursal tipo;
	private Map<Producto,Integer> stock;
	
	public Sucursal(String idSucursal, String nombre, Time horarioApertura, Time horarioCierre, Operatividad estado, TipoSucursal tipo) {
		super();
		this.idSucursal = idSucursal;
		this.nombre = nombre;
		this.estado = estado;
		this.tipo = tipo;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
	}
	
	
	public String getID() {
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

	public Map<Producto, Integer> getStock() {
		return stock;
	}
	
	public void setStock(Map<Producto, Integer> stock) {
		this.stock = stock;
	}

	public Time getHorarioApertura() {
		return horarioApertura;
	}

	public Time getHorarioCierre() {
		return horarioCierre;
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
	
}
