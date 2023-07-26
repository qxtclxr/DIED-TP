package datos;

import java.util.*;
import java.sql.Time;

public class Sucursal implements Comparable<Sucursal>{
	private String idSucursal;
	private String nombre;
	private Operatividad estado;
	private TipoSucursal tipo;
	private Map<Producto,Integer> stock;
	private Time horarioApertura;
	private Time horarioCierre;
	
	
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
		return Objects.hash(estado, idSucursal, nombre, stock, tipo);
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
		return estado == other.estado && Objects.equals(idSucursal, other.idSucursal) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(stock, other.stock) && tipo == other.tipo;
	}
	
}
