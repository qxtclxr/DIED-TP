package app;

import gui.*;


public class App {

	public static void main(String[] args) {
		try {	
			Ventana frame = new Ventana();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
