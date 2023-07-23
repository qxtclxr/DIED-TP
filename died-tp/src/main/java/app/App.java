package app;

import gui.*;

public class App {
	public static void main(String[] args) {
		try {
			MenuPrincipal frame = new MenuPrincipal();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
