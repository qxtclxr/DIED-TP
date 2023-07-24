package logica.bdd;
import javax.swing.*;
import java.util.*;
import java.lang.reflect.Field;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class GenericDAO<T> {
    private Connection connection;
    private String tableName;
    private Class<T> entityClass;

    public GenericDAO(Connection connection, String tableName, Class<T> entityClass) {
        this.connection = connection;
        this.tableName = tableName;
        this.entityClass = entityClass;
    }

    public void insert(T entity) throws IllegalAccessException {
    	
    	try {
    		StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
            StringBuilder values = new StringBuilder(" VALUES (");

            Field[] fields = entityClass.getDeclaredFields();
            
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(entity);

                if (fieldValue != null) {
                    query.append(fieldName).append(", ");
                    values.append("?, ");
                }
            }

            query.delete(query.length() - 2, query.length()).append(")");
            values.delete(values.length() - 2, values.length()).append(")");

            PreparedStatement preparedStatement = connection.prepareStatement(query.toString() + values.toString());

            int parameterIndex = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue = field.get(entity);

                if (fieldValue != null) {
                    preparedStatement.setObject(parameterIndex, fieldValue);
                    parameterIndex++;
                }
            }

            preparedStatement.executeUpdate();
            
    	}catch(SQLException e) {
            // Aquí capturamos la excepción de clave primaria duplicada.
            if (e.getMessage().contains("llave duplicada viola restricción de unicidad")) {
            	JOptionPane.showMessageDialog(null, "Ya existe un objeto con este identificador", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Si ocurre otra excepción, la imprimimos en la consola.
                e.printStackTrace();
            }
    	}
    	
    	
        
    }

    public void update(T entity, String nombrecampopk) throws SQLException, IllegalAccessException {
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");

        Field[] fields = entityClass.getDeclaredFields();
        List<Object> fieldValues = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = field.get(entity);

            if (fieldValue != null && !fieldName.equals(nombrecampopk)) {
                query.append(fieldName).append(" = ?, ");
                fieldValues.add(fieldValue);
            }
        }

        query.delete(query.length() - 2, query.length()).append(" WHERE " + nombrecampopk + " = ?");
        Field idField = null;
        try {
            idField = entityClass.getDeclaredField(nombrecampopk);
            idField.setAccessible(true);
            Object idValue = idField.get(entity);
            fieldValues.add(idValue);
        } catch (NoSuchFieldException e) {
            throw new SQLException("Entity does not have a '" + nombrecampopk + "' field.");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

        for (int i = 0; i < fieldValues.size(); i++) {
            Object fieldValue = fieldValues.get(i);
            preparedStatement.setObject(i + 1, fieldValue);
        }

        int filasafectadas = preparedStatement.executeUpdate();
        
        if(filasafectadas > 0) {
        	JOptionPane.showMessageDialog(null, "La actualizacion se realizo con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
        	JOptionPane.showMessageDialog(null, "La fila a actualizar no ha sido encontrada, porfavor revisa los datos ingresados", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }

	public void delete(Object id, String nombrecampopk) throws SQLException {
	    	
	        String query = "DELETE FROM " + tableName + " WHERE "+ nombrecampopk +"= ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	
	        
	        // Especifica el tipo SQL correspondiente al objeto 'id'
	        if (id instanceof Integer) {
	            preparedStatement.setInt(1, (Integer) id);
	        } else if (id instanceof Long) {
	            preparedStatement.setLong(1, (Long) id);
	        } else if (id instanceof String) {
	            preparedStatement.setString(1, (String) id);
	        } else {
	            // Si el tipo de 'id' no es reconocido, usa Types.OTHER
	            preparedStatement.setObject(1, id, Types.OTHER);
	        }
	
	        int filasafectadas = preparedStatement.executeUpdate();
	        
	        if(filasafectadas > 0) {
	        	JOptionPane.showMessageDialog(null, "Se ha borrado con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
	        }
	        else {
	        	JOptionPane.showMessageDialog(null, "La fila a borrar no ha sido encontrada, porfavor revisa los datos ingresados", "Informacion", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
	        
	    }
	    
	public T getById(Object id, String nombrecampopk) throws Exception {
	    String query = "SELECT * FROM " + tableName + " WHERE "+ nombrecampopk + "= ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	
	    preparedStatement.setObject(1, id);
	
	    ResultSet resultSet = preparedStatement.executeQuery();
	
	    if (resultSet.next()) {
	        T entity = entityClass.getDeclaredConstructor().newInstance();
	        Field[] fields = entityClass.getDeclaredFields();
	        for (Field field : fields) {
	            field.setAccessible(true);
	            String fieldName = field.getName();
	            Object value = resultSet.getObject(fieldName);
	            field.set(entity, value);
	        }
	        
	        return entity;
	    }
	    JOptionPane.showMessageDialog(null, "No ha sido encontrada una entidad con este id", "Información", JOptionPane.INFORMATION_MESSAGE);
	    return null;
	}
	
	public List<T> searchByAttributes(Map<String, Object> attributes) throws Exception {
    StringBuilder query = new StringBuilder("SELECT * FROM " + tableName + " WHERE ");
    List<Object> fieldValues = new ArrayList<>();

    for (Map.Entry<String, Object> entry : attributes.entrySet()) {
        String attributeName = entry.getKey();
        Object attributeValue = entry.getValue();

        if (attributeValue != null) {
            query.append(attributeName).append(" = ? AND ");
            fieldValues.add(attributeValue);
        }
    }

    // Remove the last "AND" from the query
    query.delete(query.length() - 5, query.length());

    PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

    for (int i = 0; i < fieldValues.size(); i++) {
        Object fieldValue = fieldValues.get(i);
        preparedStatement.setObject(i + 1, fieldValue);
    }

    ResultSet resultSet = preparedStatement.executeQuery();
    List<T> resultEntities = new ArrayList<>();

    while (resultSet.next()) {
        T entity = entityClass.getDeclaredConstructor().newInstance();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = resultSet.getObject(fieldName);
            field.set(entity, value);
        }
        resultEntities.add(entity);
    }

    if(resultEntities.size() == 0) {
    	JOptionPane.showMessageDialog(null, "No se han encontrado entidades que cumplan los con los criterios de busqueda", "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    return resultEntities;
}

}

