package datos;

import java.util.*;

public class Sucursal implements Comparable<Sucursal>{
	private String idSucursal;
	private String nombre;
	private Operatividad estado;
	private TipoSucursal tipo;
	private Map<Producto,Integer> productos;
	//private Time horarioApertura;
	//private Time horarioCierre;
	
	
	public int compareTo(Sucursal suc) {return this.idSucursal.compareTo(suc.idSucursal);}

	@Override
	public int hashCode() {
		return Objects.hash(estado, idSucursal, nombre, productos, tipo);
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
				&& Objects.equals(productos, other.productos) && tipo == other.tipo;
	}
}
