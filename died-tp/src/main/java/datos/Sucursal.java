package datos;
import java.sql.Time;
import java.util.*;

public class Sucursal implements Comparable<Sucursal>{
	private String idSucursal;
	private String nombre;
	private Operatividad estado;
	private TipoSucursal tipo;
	private Map<Producto,Integer> productos;
	private Time horarioApertura;
	private Time horarioCierre;
	
	
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

	public String getIdSucursal() {
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

	public Map<Producto, Integer> getProductos() {
		return productos;
	}

	public Time getHorarioApertura() {
		return horarioApertura;
	}

	public Time getHorarioCierre() {
		return horarioCierre;
	}

	public void setIdSucursal(String idSucursal) {
		this.idSucursal = idSucursal;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEstado(Operatividad string) {
		this.estado = string;
	}

	public void setTipo(TipoSucursal tipo) {
		this.tipo = tipo;
	}

	public void setProductos(Map<Producto, Integer> productos) {
		this.productos = productos;
	}

	public void setHorarioApertura(Time horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public void setHorarioCierre(Time horarioCierre) {
		this.horarioCierre = horarioCierre;
	}
	
	
	
	
}
