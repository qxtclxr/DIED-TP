package datos;

public enum EstadoOrden {
	PENDIENTE("Pendiente"),
	EN_PROCESO("En proceso");
	
	private String name;
	
	private EstadoOrden(String name) {
		this.name = name;
	}
	
	public String toString() {return name;}
}
