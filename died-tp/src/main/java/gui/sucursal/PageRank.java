package gui.sucursal;

import java.awt.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.table.*;
import datos.*;
import gui.*;
import logica.*;

public class PageRank extends Pantalla {
	
	protected static final String[] COL_NAMES = {"Ranking","ID de sucursal","Nombre","Tipo de sucursal","Estado","Horario de apertura","Horario de cierre","Probab. aprox."};
	private JLabel lblTitulo;
	private JLabel descTitulo;
	private JTable tabla;
	private JScrollPane panelContenedorTabla;
	
	private class CustomColumnRenderer extends DefaultTableCellRenderer {
	    public CustomColumnRenderer() {
	        setOpaque(true);
	        setBackground(Color.decode("#1c4185"));
	        setForeground(Color.WHITE);
	        setFont(getFont().deriveFont(Font.BOLD));
	        setHorizontalAlignment(SwingConstants.CENTER);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    	 Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	         comp.setFont(table.getFont().deriveFont(Font.BOLD));
	         return comp;
	    }
	}
	
	public PageRank(JFrame frame, JPanel pantallaAnterior) {
		super(frame, pantallaAnterior);
		// TODO Auto-generated constructor stub
	}
	
	public void inicializarComponentes() {
		setLayout(null);
		
		lblTitulo = new JLabel("PageRank");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTitulo.setBounds(10, 11, 780, 30);
		add(lblTitulo);
		
		descTitulo = new JLabel("A continuacion se encuentran todas las sucursales rankeadas segun el algoritmo PageRank.");
		descTitulo.setForeground(Color.GRAY);
		descTitulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descTitulo.setBounds(10, 50, 780, 14);
		add(descTitulo);
		
		JSeparator separadorTituloContenido = new JSeparator();
		separadorTituloContenido.setBounds(10, 77, 780, 2);
		add(separadorTituloContenido);
		
		JButton btnCancelar = new JButton("Volver");
		btnCancelar.setBounds(690, 466, 100, 23);
		btnCancelar.addActionListener(act -> this.actionVolver());
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(btnCancelar);
	
		generarTabla();
	}
	
	protected void generarTabla() {
		tabla = new JTable();
		DefaultTableModel modeloTabla = new DefaultTableModel(new Object[0][COL_NAMES.length],COL_NAMES) {
			@Override
			public boolean isCellEditable(int row, int col) {return false;}
		};
		tabla.setModel(modeloTabla);
		
		generarData();
		
		panelContenedorTabla = new JScrollPane(tabla);
		panelContenedorTabla.setBounds(10, 90, 780, 361);
		add(panelContenedorTabla);
	}
	
	public void generarData() {
		try {
			Map<Sucursal, Double> sucursales = GestorSucursal.getInstance().pageRank();
			java.util.List<Sucursal> sucursalesRankeadas =
					sucursales.entrySet().stream().
					sorted((a,b) -> b.getValue().compareTo(a.getValue())).
					map(e -> e.getKey()).
					collect(Collectors.toList());
					
			DefaultTableModel model = (DefaultTableModel) tabla.getModel();
			model.setRowCount(0); //Resetea la tabla
			
			for(int i = 0 ; i < sucursalesRankeadas.size(); i++) {
				Sucursal suc = sucursalesRankeadas.get(i);
				Object[] fila = {
						i+1,
						suc.getID(),
						suc.getNombre(),
						suc.getTipo(),
						suc.getEstado(),
						suc.getHorarioApertura(),
						suc.getHorarioCierre(),
						sucursales.get(suc)
				};
				model.addRow(fila);
			}
			tabla.getColumnModel().getColumn(0).setCellRenderer(new CustomColumnRenderer());
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			DatabaseErrorMessage.showMessageDialog(frame);
		}
	}
	
	public void actionVolver(){
		this.setVisible(false);
		pantallaAnterior.setVisible(true);
		frame.setContentPane(pantallaAnterior);
	}

}
