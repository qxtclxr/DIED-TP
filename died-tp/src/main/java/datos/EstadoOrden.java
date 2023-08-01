package datos;

public enum EstadoOrden {
	PENDIENTE("Pendiente"),
	EN_PROCESO("En proceso");
	
	private String name;
	
	private EstadoOrden(String name) {
		this.name = name;
	}
	
	public String getValueAsString() {return super.toString();}
	
	public String toString() {return name;}
}
