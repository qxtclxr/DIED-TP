package gui.sucursal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import datos.Producto;
import datos.Sucursal;
import excepciones.IDNotFoundException;
import gui.tabla.TablaDeDatos;
import logica.GestorProducto;
import logica.GestorSucursal;
import gui.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;


public class ModificarStockSucursal extends Pantalla {
	
	private static final String[] COL_NAMES = {"ID del producto","Nombre","Descripcion","Precio unitario","Peso (en kg.)","Stock",""};
	private JLabel lblTitulo;
	private JLabel descTitulo;
	private JTextField txtIDProducto;
	private JTextField txtNombre;
	private JTextField txtPrecioDesde;
	private JTextField txtPrecioHasta;
	private JTextField txtPesoDesde;
	private JTextField txtPesoHasta;
	private TablaDeDatos tabla;
	private JScrollPane panelContenedorTabla;
	private JButton btnBuscar;
	private Sucursal suc;

	public ModificarStockSucursal(JFrame frame, JPanel pantallaAnterior, Sucursal suc) {
		super(frame, pantallaAnterior);
		this.suc = suc;
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		lblTitulo = new JLabel("Modificar stock en sucursal");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitulo.setBounds(10, 11, 780, 30);
		add(lblTitulo);
		
		descTitulo = new JLabel("Completa los campos para filtrar los productos. Una vez realizada la busqueda podras modificar el stock disponible de cada uno.");
		descTitulo.setForeground(Color.GRAY);
		descTitulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descTitulo.setBounds(10, 50, 780, 14);
		add(descTitulo);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		txtIDProducto = new JTextField();
		txtIDProducto.setBounds(10, 113, 121, 20);
		add(txtIDProducto);
		txtIDProducto.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(141, 113, 121, 20);
		add(txtNombre);
		
		txtPrecioDesde = new JTextField();
		txtPrecioDesde.setBounds(272, 113, 121, 20);
		add(txtPrecioDesde);
		
		txtPrecioHasta = new JTextField();
		txtPrecioHasta.setBounds(403, 113, 121, 20);
		add(txtPrecioHasta);
		
		JLabel lblIDProducto = new JLabel("ID del producto");
		lblIDProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIDProducto.setVerticalAlignment(SwingConstants.TOP);
		lblIDProducto.setBounds(10, 95, 121, 14);
		add(lblIDProducto);
		
		JLabel lblSucursalOrigen = new JLabel("Nombre del producto");
		lblSucursalOrigen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSucursalOrigen.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalOrigen.setBounds(141, 95, 121, 14);
		add(lblSucursalOrigen);
		
		JLabel lblSucursalDestino = new JLabel("Precio unitario");
		lblSucursalDestino.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSucursalDestino.setVerticalAlignment(SwingConstants.TOP);
		lblSucursalDestino.setBounds(272, 95, 121, 14);
		add(lblSucursalDestino);
		
		JLabel lblDuracion = new JLabel("Peso (en kg.)");
		lblDuracion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDuracion.setVerticalAlignment(SwingConstants.TOP);
		lblDuracion.setBounds(534, 95, 121, 14);
		add(lblDuracion);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(act -> actionBuscar());
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setBounds(10, 144, 121, 20);
		add(btnBuscar);
		
		JButton btnCancelar = new JButton("Volver");
		btnCancelar.setBounds(690, 466, 100, 23);
		btnCancelar.addActionListener(act -> this.actionVolver());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
		
		txtPesoDesde = new JTextField();
		txtPesoDesde.setColumns(10);
		txtPesoDesde.setBounds(534, 113, 121, 20);
		add(txtPesoDesde);
		
		txtPesoHasta = new JTextField();
		txtPesoHasta.setColumns(10);
		txtPesoHasta.setBounds(665, 113, 121, 20);
		add(txtPesoHasta);
		
		JLabel descPrecioDesde = new JLabel("desde");
		descPrecioDesde.setForeground(Color.GRAY);
		descPrecioDesde.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descPrecioDesde.setBounds(272, 133, 121, 14);
		add(descPrecioDesde);
		
		JLabel descPesoDesde = new JLabel("desde");
		descPesoDesde.setForeground(Color.GRAY);
		descPesoDesde.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descPesoDesde.setBounds(534, 133, 121, 14);
		add(descPesoDesde);
		
		JLabel descPrecioHasta = new JLabel("hasta");
		descPrecioHasta.setForeground(Color.GRAY);
		descPrecioHasta.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descPrecioHasta.setBounds(403, 133, 121, 14);
		add(descPrecioHasta);
		
		JLabel descPesoHasta = new JLabel("hasta");
		descPesoHasta.setForeground(Color.GRAY);
		descPesoHasta.setFont(new Font("Tahoma", Font.ITALIC, 11));
		descPesoHasta.setBounds(665, 133, 121, 14);
		add(descPesoHasta);
		
		fieldsDefaultColor();
		generarTabla();
	}
	
	private void fieldsDefaultColor() {
		txtIDProducto.setBackground(Color.WHITE);
		txtNombre.setBackground(Color.WHITE);
		txtPrecioDesde.setBackground(Color.WHITE);
		txtPrecioHasta.setBackground(Color.WHITE);
		txtPesoDesde.setBackground(Color.WHITE);
		txtPesoHasta.setBackground(Color.WHITE);
	}
	
	private boolean validateInput() {
		fieldsDefaultColor();
		Color colorInvalid = Color.decode("#ff8080");
		boolean validInput = true;
		if(!SyntaxValidator.validID()) {
			txtIDProducto.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validTextLength(txtNombre,0,256)) {
			txtNombre.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validFloatingPointOrEmpty(txtPrecioDesde)) {
			txtPrecioDesde.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validFloatingPointOrEmpty(txtPrecioHasta)) {
			txtPrecioHasta.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validFloatingPointOrEmpty(txtPesoDesde)) {
			txtPesoDesde.setBackground(colorInvalid);
			validInput = false;
		}
		if(!SyntaxValidator.validFloatingPointOrEmpty(txtPesoHasta)) {
			txtPesoHasta.setBackground(colorInvalid);
			validInput = false;
		}
		return validInput;
	}
	
	private void generarTabla() {
		tabla = new TablaDeDatos(COL_NAMES);
		tabla.onPressingButton(act -> actionModificar());
		tabla.setButtonLabel("Modif. stock");
		panelContenedorTabla = new JScrollPane(tabla);
		panelContenedorTabla.setBounds(10, 174, 780, 277);
		add(panelContenedorTabla);
	}
	
	public void actionBuscar() {
		List<Producto> dataList = new ArrayList<>();
		if(this.validateInput()) {
			try {				
				dataList = GestorProducto.getInstance().consultaPorAtributos(
						txtIDProducto.getText(),
						txtNombre.getText(),
						txtPrecioDesde.getText(),
						txtPrecioHasta.getText(),
						txtPesoDesde.getText(),
						txtPesoHasta.getText());
				DefaultTableModel model = (DefaultTableModel) tabla.getModel();
				model.setRowCount(0); //Resetea la tabla
				for(Producto prod : dataList) {
					Integer stock = suc.getStock().get(prod);
					stock = stock == null ? 0 : stock;
					Object[] fila = {
							prod.getID(),
							prod.getNombre(),
							prod.getDescripcion(),
							prod.getPrecioUnitario(),
							prod.getPesoKg(),
							stock,
							prod.getID()
					};
					model.addRow(fila);
				}
			}catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				DatabaseErrorMessage.showMessageDialog(frame);
			}
		} else {
			InvalidInputMessage.showMessageDialog(frame);
		}		
	}
	
	public void actionModificar() {
		try {
			int row = tabla.convertRowIndexToModel(tabla.getEditingRow());
			Integer prodID = (Integer) tabla.getModel().getValueAt(row,0);
			Producto target = GestorProducto.getInstance().getByID(prodID);
			this.modificarStockDialog(target);
		}catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			DatabaseErrorMessage.showMessageDialog(frame);
		} catch (IDNotFoundException ex) {
			ex.getMessage();
		}finally {
			this.actionBuscar(); //Refrescar la tabla
		}
		
	}
	
	public void modificarStockDialog(Producto target) throws ClassNotFoundException, SQLException {
		Integer stockActual = GestorSucursal.getInstance().getStockOfProduct(suc, target);
		String input = JOptionPane.showInputDialog(frame,"Ingrese el valor del stock:",stockActual);
		if(input!=null) {
			if(SyntaxValidator.validInteger(input)) {
				Integer inputInt = Integer.parseInt(input);
				GestorSucursal.getInstance().setStock(suc, target, inputInt);
				JOptionPane.showMessageDialog(
						frame,
						"El stock ha sido modificado correctamente.",
						"Datos guardados",
						JOptionPane.INFORMATION_MESSAGE);
				suc.setStock(GestorSucursal.getInstance().getStock(suc));
			}else{
				JOptionPane.showMessageDialog(
						frame,
						"El stock ingresado es invalido. Intente de nuevo.",
						"Datos ingresados invalidos",
						JOptionPane.ERROR_MESSAGE);
			}
		}else {
			/*Si el input es null es porque el usuario apreto "Cancelar"
			 * Basicamente no se hace nada y se vuelve a como estaba.*/
		}
	}
	
	public void actionVolver(){
		this.setVisible(false);
		pantallaAnterior.setVisible(true);
		frame.setContentPane(pantallaAnterior);
	}

}
