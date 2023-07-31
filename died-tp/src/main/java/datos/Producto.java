package datos;

import java.util.Objects;

public class Producto {
	private Integer idProducto;
	private String nombre;
	private String descripcion;
	private Float precioUnitario;
	private Float pesoKg;
	
	public Producto(Integer idProducto, String nombre, String descripcion, Float precioUnitario, Float pesoKg) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.pesoKg = pesoKg;
	}
	
	public Integer getID() {
		return idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public Float getPrecioUnitario() {
		return precioUnitario;
	}
	public Float getPesoKg() {
		return pesoKg;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idProducto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(idProducto, other.idProducto);
	}
	
	
}
