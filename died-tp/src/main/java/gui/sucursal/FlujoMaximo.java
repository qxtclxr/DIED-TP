package gui.sucursal;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;
import datos.*;
import gui.*;
import gui.grafo.*;
import logica.*;
import logica.grafo.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class FlujoMaximo extends Pantalla {
	
	private OrdenDeProvision orden;
	private JComboBox<Sucursal> cmbFuentes;
	private JComboBox<Sucursal> cmbSumideros;
	private JLabel lblTituloCalculo;
	private JLabel lblCalculo;
	private JButton btnCalcular;
	
	public FlujoMaximo(JFrame frame, JPanel pantallaAnterior) {
		super(frame,pantallaAnterior);
	}
	
	@Override
	public void inicializarComponentes() {
		setLayout(null);
		JLabel lblTitulo = new JLabel("Flujo maximo");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitulo.setBounds(10, 11, 780, 30);
		add(lblTitulo);
		
		JLabel descTitulo = new JLabel("Calculo de flujo maximo entre una sucursal fuente y una sucursal sumidero.");
		descTitulo.setForeground(Color.GRAY);
		descTitulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descTitulo.setBounds(10, 50, 780, 14);
		add(descTitulo);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		btnCalcular = new JButton("Calcular flujo maximo");
		btnCalcular.setEnabled(false);
		btnCalcular.addActionListener(act -> calcularFlujoMaximo());
		btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCalcular.setBounds(623, 111, 167, 23);
		add(btnCalcular);
		
		cmbFuentes = new JComboBox<>();
		cmbFuentes.addActionListener(act -> updateButtonState(cmbFuentes, cmbSumideros, btnCalcular));
		cmbFuentes.setBounds(10, 111, 250, 22);
		add(cmbFuentes);
		
		cmbSumideros = new JComboBox<Sucursal>();
		cmbSumideros.addActionListener(act -> updateButtonState(cmbFuentes, cmbSumideros, btnCalcular));
		cmbSumideros.setBounds(270, 111, 250, 22);
		add(cmbSumideros);
		
		generarOpciones();
		
		JLabel lblSucursalDeOrigen = new JLabel("Sucursal fuente");
		lblSucursalDeOrigen.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalDeOrigen.setBounds(10, 92, 250, 16);
		lblSucursalDeOrigen.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(lblSucursalDeOrigen);
		
		JLabel lblSucursalSumidero = new JLabel("Sucursal sumidero");
		lblSucursalSumidero.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalSumidero.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSucursalSumidero.setBounds(270, 92, 250, 16);
		add(lblSucursalSumidero);
		
		JSeparator separadorBuscarCaminos = new JSeparator();
		separadorBuscarCaminos.setBounds(10, 150, 780, 2);
		add(separadorBuscarCaminos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 159, 162));
		btnCancelar.setBounds(690, 466, 100, 23);
		btnCancelar.addActionListener(act -> this.actionVolver());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
		
		lblTituloCalculo = new JLabel("");
		lblTituloCalculo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloCalculo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloCalculo.setBounds(10, 264, 780, 14);
		this.add(lblTituloCalculo);
		
		lblCalculo = new JLabel("");
		lblCalculo.setForeground(new Color(64, 128, 128));
		lblCalculo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalculo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCalculo.setBounds(10, 289, 780, 22);
		this.add(lblCalculo);
		
	}
	
	public void generarOpciones() {
		try {
			GestorSucursal gestor = GestorSucursal.getInstance();
			List<Sucursal> fuentes = gestor.getFuentes();
			List<Sucursal> sumideros = gestor.getSumideros();
			for(Sucursal suc : fuentes) {
				cmbFuentes.addItem(suc);
			}
			for(Sucursal suc : sumideros) {
				cmbSumideros.addItem(suc);
			}
		}catch(SQLException | ClassNotFoundException ex) {
			DatabaseErrorMessage.showMessageDialog(frame);
		}
		
	}
	
	public void actionVolver() {
		this.setVisible(false);
		pantallaAnterior.setVisible(true);
		frame.setContentPane(pantallaAnterior);
	}
	
	private void updateButtonState(JComboBox comboBox1, JComboBox comboBox2, JButton button) {
        boolean comboBox1HasSelection = comboBox1.getSelectedItem() != null;
        boolean comboBox2HasSelection = comboBox2.getSelectedItem() != null;
        button.setEnabled(comboBox1HasSelection && comboBox2HasSelection);
    }
	
	public void calcularFlujoMaximo() {
		try {
			Sucursal fuente = (Sucursal) cmbFuentes.getSelectedItem();
			Sucursal sumidero = (Sucursal) cmbSumideros.getSelectedItem();
			
			float flujoMaximo = GestorSucursal.getInstance().getFlujoMaximo(fuente, sumidero);
			
			lblTituloCalculo.setText("El flujo máximo entre fuente y sumidero es de:");
			lblCalculo.setText(flujoMaximo + " [kgs]");
		}catch (SQLException | ClassNotFoundException ex){
			DatabaseErrorMessage.showMessageDialog(frame);
		}
	}
}
