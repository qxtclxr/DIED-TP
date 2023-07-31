package datos;

public class Ruta {
	private Integer idRuta;
	private Sucursal origen;
	private Sucursal destino;
	private Operatividad estado;
	private Integer duracion; //Unidad: minutos
	private Float capacidadMaxima; //Unidad: kg
	
	
	public Ruta(Integer idRuta, Sucursal origen, Sucursal destino, Operatividad estado, Integer duracion,
			Float capacidadMaxima) {
		super();
		this.idRuta = idRuta;
		this.origen = origen;
		this.destino = destino;
		this.estado = estado;
		this.duracion = duracion;
		this.capacidadMaxima = capacidadMaxima;
		
	}

	public Sucursal getOrigen() {return this.origen;}
	public Sucursal getDestino() {return this.destino;}
	public Integer getDuracion() {return this.duracion;}
	public Integer getID() {return this.idRuta;}
	public Float getCapacidadMaxima() {return this.capacidadMaxima;}
	public Operatividad getEstado() {return this.estado;}
	
	@Override
	public String toString() {
		return this.origen.getNombre() + " -> " + this.destino.getNombre() + " (" + this.getID() + ", " + this.getDuracion() + "mins)";
	}
	
	
}
