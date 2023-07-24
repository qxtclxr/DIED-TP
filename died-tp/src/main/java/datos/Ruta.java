package datos;

public class Ruta {
	private String idRuta;
	private Sucursal origen;
	private Sucursal destino;
	private Operatividad estado;
	private Integer duracion; //Unidad: minutos
	private Double capacidadMaxima; //Unidad: kg
	
	public Sucursal getOrigen() {return this.origen;}
	public Sucursal getDestino() {return this.destino;}
	public Integer getDuracion() {return this.duracion;}
	public String getIdRuta() {return this.idRuta;}
	public Double getCapacidadMaxima() {return this.capacidadMaxima;}
}
