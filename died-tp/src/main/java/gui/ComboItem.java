package gui;

public class ComboItem<T> {
	private String display;
	private T value;
	
	public ComboItem(String display, T value) {
		this.display = display;
		this.value = value;
	}

	public String getDisplay() {return this.display;}

	public T getValue() {return this.value;}
	
	@Override
	public String toString() {return this.display;}
}
