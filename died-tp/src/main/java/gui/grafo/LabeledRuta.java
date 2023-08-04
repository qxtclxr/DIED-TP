package gui.grafo;

import java.util.Objects;

import datos.Ruta;

public class LabeledRuta{
	
	private Ruta ruta;
	
	public LabeledRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	
	public Ruta getRuta() {
		return this.ruta;
	}
	
	public String toString() {
		return ruta.getDuracion() + "mins";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ruta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabeledRuta other = (LabeledRuta) obj;
		return Objects.equals(ruta, other.ruta);
	}	
	
}
