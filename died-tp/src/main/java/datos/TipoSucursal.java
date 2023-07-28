package datos;

public enum TipoSucursal {
	FUENTE("Fuente"),
	SUMIDERO("Sumidero"),
	COMERCIAL("Comercial");
	
	private String name;
	
	private TipoSucursal(String name) {
		this.name = name;
	}
	
	public String toString() {return name;}
}
