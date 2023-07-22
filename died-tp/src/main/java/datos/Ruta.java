package datos;

public class Ruta {
	private Sucursal origen;
	private Sucursal destino;
	private Operatividad estado;
	private Integer duracion; //Unidad: minutos
	
	public Sucursal getOrigen() {return this.origen;}
	public Sucursal getDestino() {return this.destino;}
	public Integer getDuracion() {return this.duracion;}
}
