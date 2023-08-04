package datos;

import java.util.Objects;

public class Ruta implements Entidad{

	private Integer idRuta;
	private Sucursal origen;
	private Sucursal destino;
	private Operatividad estado;
	private Integer duracion; //Unidad: minutos
	private Float capacidadMaxima; //Unidad: kg
	
	public Ruta() {
		super();
	};
	
	public Ruta(Integer idRuta, Sucursal origen, Sucursal destino, Operatividad estado, Integer duracion,
			Float capacidadMaxima) {
		super();
		this.idRuta = idRuta;
		if(origen.getTipo().equals(TipoSucursal.SUMIDERO)) {throw new IllegalArgumentException();}
		else {this.origen = origen;}
		if(destino.getTipo().equals(TipoSucursal.FUENTE)) {throw new IllegalArgumentException();}
		else {this.destino = destino;}
		this.destino = destino;
		this.estado = estado;
		this.duracion = duracion;
		this.capacidadMaxima = capacidadMaxima;
		
	}

	public Ruta( Sucursal origen, Sucursal destino, Operatividad estado, Integer duracion,
			Float capacidadMaxima) {
		super();
		if(origen.getTipo().equals(TipoSucursal.SUMIDERO)) {throw new IllegalArgumentException();}
		else {this.origen = origen;}
		if(destino.getTipo().equals(TipoSucursal.FUENTE)) {throw new IllegalArgumentException();}
		else {this.destino = destino;}
		this.estado = estado;
		this.duracion = duracion;
		this.capacidadMaxima = capacidadMaxima;
	}
	
	
	public Integer getID(){
		return idRuta;
	}

	public Sucursal getOrigen() {
		return origen;
	}

	public Sucursal getDestino() {
		return destino;
	}

	public Operatividad getEstado() {
		return estado;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public Float getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setID(Integer idRuta) {
		this.idRuta = idRuta;
	}

	public void setOrigen(Sucursal origen) {
		if(origen.getTipo().equals(TipoSucursal.SUMIDERO))
			throw new IllegalArgumentException();
		else
			this.origen = origen;
	}

	public void setDestino(Sucursal destino) {
		if(destino.getTipo().equals(TipoSucursal.FUENTE))
			throw new IllegalArgumentException();
		else
			this.destino = destino;
	}

	public void setEstado(Operatividad estado) {
		this.estado = estado;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public void setCapacidadMaxima(Float capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	@Override
	public String toString() {
		return this.origen.getNombre() + " -> " + this.destino.getNombre() + " (" + this.getID() + ", " + this.getDuracion() + "mins)";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idRuta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ruta other = (Ruta) obj;
		return Objects.equals(idRuta, other.idRuta);
	}
	
}
