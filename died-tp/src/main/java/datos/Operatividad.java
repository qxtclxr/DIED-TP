package datos;

public enum Operatividad {
	OPERATIVA("Operativa"),
	NO_OPERATIVA("No operativa");
	
	private String name;
	
	private Operatividad(String name) {
		this.name = name;
	}
	
	public String getValueAsString() {return super.toString();}
	
	public String toString() {return name;}
}
