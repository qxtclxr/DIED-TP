package datos;

import java.util.Objects;

public class Producto implements Entidad{
	private Integer idProducto;
	private String nombre;
	private String descripcion;
	private Float precioUnitario;
	private Float pesoKg;
	
	public Producto() {}
	
	public Producto(Integer id) {this.idProducto = id;}
	
	public Producto(Integer idProducto, String nombre, String descripcion, Float precioUnitario, Float pesoKg) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.pesoKg = pesoKg;
	}
	
	public Producto(String nombre2, String descripcion2, Float precioU, Float pesoKg2) {
		super();
		
		this.nombre = nombre2;
		this.descripcion = descripcion2;
		this.precioUnitario = precioU;
		this.pesoKg = pesoKg2;
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
	
	public void setID(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public void setPesoKg(Float pesoKg) {
		this.pesoKg = pesoKg;
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
