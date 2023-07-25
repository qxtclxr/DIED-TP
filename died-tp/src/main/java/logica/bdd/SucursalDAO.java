package logica.bdd;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import javax.swing.JOptionPane;
import datos.Operatividad;
import datos.Sucursal;
import datos.TipoSucursal;


public class SucursalDAO {

	private Conexion c;
    private Connection connection;
    private Sucursal s;

    public SucursalDAO(Sucursal s) {
        this.connection = c.conectar();
        this.s = s;
    }
    
    public void insert(Sucursal s) {
    	
    	try {
            String consulta = "INSERT INTO Sucursal (idsucursal, nombre, horarioapertura, horariocierre, estado,tipo) " +
                              "VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = this.connection.prepareStatement(consulta);
            
            pstmt.setString(1, s.getIdSucursal());
            pstmt.setString(2, s.getNombre());
            pstmt.setTime(3, s.getHorarioApertura());
            pstmt.setTime(4, s.getHorarioCierre());
            pstmt.setString(5, s.getEstado().toString());
            pstmt.setString(6, s.getTipo().toString());
            
            pstmt.executeUpdate();
           
            JOptionPane.showMessageDialog(null, "La sucursal se inserto exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            
            this.c.desconectar();
            
            
        } catch (SQLException e) {

        	if (e.getMessage().contains("llave duplicada viola restricción de unicidad")) {
            	JOptionPane.showMessageDialog(null, "Ya existe una sucursal con este identificador", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                e.printStackTrace();
            }
        	
        	
        }
    	
    	
    }
    
    public void delete(Sucursal s) {
    	
    	try {
    		
            String consulta = "DELETE FROM Sucursal WHERE idsucursal = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(consulta);
            pstmt.setString(1, s.getIdSucursal() );
            int filasBorradas = pstmt.executeUpdate();
            
            if(filasBorradas > 0) {
            	JOptionPane.showMessageDialog(null, "La sucursal se ha borrado con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
            	JOptionPane.showMessageDialog(null, "No se ha podido borrar, la sucursal no existe", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
    		
    		this.c.desconectar();
    		
    	}
    	catch(SQLException e) {
    		JOptionPane.showMessageDialog(null, "Error inesperado: "+e.getMessage().toString() ,"Error", JOptionPane.ERROR_MESSAGE );
    	}
    	
    }
    
    public void update(Sucursal s) {
    	
        try {
            String consulta = "UPDATE Sucursal SET nombre = ?, horarioapertura = ?, horariocierre = ?, " +
                              "estado = ?, tiposucursal = ? WHERE idsucursal = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(consulta);
            pstmt.setString(1, s.getNombre());
            pstmt.setTime(2, s.getHorarioApertura());
            pstmt.setTime(3, s.getHorarioCierre());
            pstmt.setString(4, s.getEstado().toString());
            pstmt.setString(5, s.getTipo().toString());
            pstmt.setString(6, s.getIdSucursal());

            int filasActualizadas = pstmt.executeUpdate();
            
            if(filasActualizadas > 0) {
            	JOptionPane.showMessageDialog(null, "Los datos de las sucursal fueron actualizados con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
            	JOptionPane.showMessageDialog(null, "No se ha podido actualizar, la sucursal no existe, revisa el identificador", "Error", JOptionPane.ERROR_MESSAGE);
            }
    		
    		
    		
    		
    		this.c.desconectar();
    	}
    	catch(SQLException e) {
    		JOptionPane.showMessageDialog(null, "Error insesperado: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	
    }
    
    public Sucursal getById(Sucursal s) {
   
            try {
            	Sucursal sucursal = null;
                String consulta = "SELECT idsucursal, nombre, horarioapertura, horariocierre, estado " +
                                  "FROM Sucursal WHERE idsucursal = ?";
                PreparedStatement pstmt = this.connection.prepareStatement(consulta);
                pstmt.setString(1, s.getIdSucursal());

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    sucursal = new Sucursal();
                    sucursal.setIdSucursal(rs.getString("idsucursal"));
                    sucursal.setNombre(rs.getString("nombre"));
                    sucursal.setHorarioApertura(rs.getTime("horarioapertura"));
                    sucursal.setHorarioCierre(rs.getTime("horariocierre"));
                    Operatividad o = Operatividad.valueOf(rs.getString("estado"));
                    TipoSucursal t = TipoSucursal.valueOf(rs.getString("tipo"));
                    sucursal.setTipo(t);
                    sucursal.setEstado(o);
                    
                }
    	
    		this.c.desconectar();
    		
    		return sucursal;
    	}
    	catch(SQLException e) {
    		JOptionPane.showMessageDialog(null,"Error inesperado: "+e.getMessage() , "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	
    	return null;
   }
   
    public List<Sucursal> getByAtributtes(Sucursal s) {

    	List<Sucursal> sucursalesEncontradas = new ArrayList<>();
    	
        try {
        	
        
            String consulta = "SELECT idsucursal, nombre, horarioapertura, horariocierre, estado, tipo " +
                              "FROM Sucursal WHERE " +
                              "(idsucursal = ? OR ? IS NULL) AND " +
                              "(nombre = ? OR ? IS NULL) AND " +
                              "(horarioapertura = ? OR ? IS NULL) AND " +
                              "(horariocierre = ? OR ? IS NULL) AND " +
                              "(estado = ? OR ? IS NULL) AND " +
                              "(tipo = ? OR ? IS NULL)";
            PreparedStatement pstmt = this.connection.prepareStatement(consulta);
            pstmt.setString(1, s.getIdSucursal());
            pstmt.setString(2, s.getIdSucursal());
            pstmt.setString(3, s.getNombre());
            pstmt.setString(4, s.getNombre());
            pstmt.setTime(5, s.getHorarioApertura());
            pstmt.setTime(6, s.getHorarioApertura());
            pstmt.setTime(7, s.getHorarioCierre());
            pstmt.setTime(8, s.getHorarioCierre());
            pstmt.setString(9, s.getEstado() != null ? s.getEstado().toString() : null);
            pstmt.setString(10, s.getEstado() != null ? s.getEstado().toString() : null);
            pstmt.setString(11, s.getTipo() != null ? s.getTipo().toString() : null);
            pstmt.setString(12, s.getTipo() != null ? s.getTipo().toString() : null);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getString("idsucursal"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setHorarioApertura(rs.getTime("horarioapertura"));
                sucursal.setHorarioCierre(rs.getTime("horariocierre"));
               
                Operatividad estadoSucursal = Operatividad.valueOf(rs.getString("estado"));
                sucursal.setEstado(estadoSucursal);
                TipoSucursal t = TipoSucursal.valueOf(rs.getString("tipo"));
                sucursal.setTipo(t);
                
                sucursalesEncontradas.add(sucursal);
            }
            
            
            
            this.c.desconectar();
            
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
        
      return sucursalesEncontradas;
 
    }
}

