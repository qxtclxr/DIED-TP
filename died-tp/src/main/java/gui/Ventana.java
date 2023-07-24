package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ventana extends JFrame {

	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(800, 500));
		pack();
		
		MenuPrincipal menu = new MenuPrincipal(this);
		this.setContentPane(menu);
		
		setTitle("SGL (Sistema de Gestion Logistico)");
		setLocationRelativeTo(null);
		setResizable(false);		
	}
	
}
