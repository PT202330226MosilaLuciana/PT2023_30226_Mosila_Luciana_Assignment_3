package dataAccess;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;


public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAll();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage(), e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    public String createSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    public String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Set the values of the prepared statement
            setStatementParameters(statement, t);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating record failed, no rows affected.");
            }

            // Get the generated ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                setIdValue(t, generatedId); // Set the generated ID value to the object
            } else {
                throw new SQLException("Creating record failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i].getName());
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }

        sb.append(") VALUES (");

        for (int i = 0; i < fields.length; i++) {
            sb.append("?");
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }

        sb.append(")");

        return sb.toString();
    }
    private void setStatementParameters(PreparedStatement statement, T t) throws SQLException {
        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(t);
                statement.setObject(i + 1, value);
            } catch (IllegalAccessException e) {
                throw new SQLException("Failed to set statement parameters.", e);
            }
        }
    }
    private void setIdValue(T t, int id) throws SQLException {
        try {
            Field field = type.getDeclaredField("id");
            field.setAccessible(true);
            field.set(t, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new SQLException("Failed to set ID value.", e);
        }
    }
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setStatementParameters2(statement, t);
            statement.executeUpdate();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        // Exclude the ID column from the update statement
        Field[] fields = type.getDeclaredFields();
        boolean isFirstField = true;
        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                if (!isFirstField) {
                    sb.append(", ");
                }
                sb.append(field.getName()).append(" = ?");
                isFirstField = false;
            }
        }

        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    private void setStatementParameters2(PreparedStatement statement, T t) throws SQLException {
        int parameterIndex = 1;

        // Set the field values of the object as parameters in the statement
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                field.setAccessible(true);
                try {
                    Object value = field.get(t);
                    statement.setObject(parameterIndex, value);
                    parameterIndex++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Set the ID value as the last parameter in the statement
        try {
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(t);
            statement.setObject(parameterIndex, idValue);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.*;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import connection.ConnectionFactory;
//
//public class AbstractDAO<T> {
//    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
//
//    private final Class<T> type;
//
//    @SuppressWarnings("unchecked")
//    public AbstractDAO() {
//        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//
//    }
//    public T insert(T t) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        String query = createInsertQuery();
//        try {
//            connection = ConnectionFactory.getConnection();
//            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//
//            // Set the values of the prepared statement
//            Field[] fields = type.getDeclaredFields();
//            for (int i = 0; i < fields.length; i++) {
//                try {
//                    Field field = fields[i];
//                    field.setAccessible(true);
//                    Object value = field.get(t);
//                    statement.setObject(i + 1, value);
//                } catch (IllegalAccessException e) {
//                    throw new SQLException("Failed to set statement parameters.", e);
//                }
//            }
//
//            int affectedRows = statement.executeUpdate();
//            if (affectedRows == 0) {
//                throw new SQLException("Creating record failed, no rows affected.");
//            }
//
//            // Get the generated ID
//            ResultSet generatedKeys = statement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                int generatedId = generatedKeys.getInt(1);
//                try {
//                    Field field = type.getDeclaredField("id");
//                    field.setAccessible(true);
//                    field.set(t,generatedId);
//                } catch (NoSuchFieldException | IllegalAccessException e) {
//                    throw new SQLException("Failed to set ID value.", e);
//                } // Set the generated ID value to the object
//            } else {
//                throw new SQLException("Creating record failed, no ID obtained.");
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
//        } finally {
//            ConnectionFactory.close(resultSet);
//            ConnectionFactory.close(statement);
//            ConnectionFactory.close(connection);
//        }
//        return t;
//    }
//    private String createInsertQuery() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("INSERT INTO ");
//        sb.append(type.getSimpleName());
//        sb.append(" (");
//
//        Field[] fields = type.getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            sb.append(fields[i].getName());
//            if (i < fields.length - 1) {
//                sb.append(", ");
//            }
//        }
//
//        sb.append(") VALUES (");
//
//        for (int i = 0; i < fields.length; i++) {
//            sb.append("?");
//            if (i < fields.length - 1) {
//                sb.append(", ");
//            }
//        }
//
//        sb.append(")");
//
//        return sb.toString();
//    }
//
//
//    private String createSelectQuery(String field) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT ");
//        sb.append(" * ");
//        sb.append(" FROM ");
//        sb.append(type.getSimpleName());
//        sb.append(" WHERE " + field + " =?");
//        return sb.toString();
//    }
//
//    public List<T> findAll() {
//        // TODO:
//        return null;
//    }
//
//    public T findById(int id) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        String query = createSelectQuery("id");
//        try {
//            connection = ConnectionFactory.getConnection();
//            statement = connection.prepareStatement(query);
//            statement.setInt(1, id);
//            resultSet = statement.executeQuery();
//
//            return createObjects(resultSet).get(0);
//        } catch (SQLException e) {
//            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
//        } finally {
//            ConnectionFactory.close(resultSet);
//            ConnectionFactory.close(statement);
//            ConnectionFactory.close(connection);
//        }
//        return null;
//    }
//    public List<T> createObjects(ResultSet resultSet) {
//        List<T> list = new ArrayList<T>();
//        Constructor[] ctors = type.getDeclaredConstructors();
//        Constructor ctor = null;
//        for (int i = 0; i < ctors.length; i++) {
//            ctor = ctors[i];
//            if (ctor.getGenericParameterTypes().length == 0)
//                break;
//        }
//        try {
//            while (resultSet.next()) {
//                ctor.setAccessible(true);
//                T instance = (T)ctor.newInstance();
//                for (Field field : type.getDeclaredFields()) {
//                    String fieldName = field.getName();
//                    Object value = resultSet.getObject(fieldName);
//                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
//                    Method method = propertyDescriptor.getWriteMethod();
//                    method.invoke(instance, value);
//                }
//                list.add(instance);
//            }
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IntrospectionException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
////    public T insert(T t) {
////        Connection connection = null;
////        PreparedStatement statement = null;
////        ResultSet resultSet = null;
////        String query = createInsertQuery();
////        try {
////            connection = ConnectionFactory.getConnection();
////            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
////            int i = 1;
////            for (Field field : type.getDeclaredFields()) {
////                field.setAccessible(true);
////                Object value = field.get(t);
////                statement.setObject(i, value);
////                i++;
////            }
////            int affectedRows = statement.executeUpdate();
////
////            if (affectedRows == 0) {
////                throw new SQLException("Creating " + type.getSimpleName() + " failed, no rows affected.");
////            }
////
////            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
////                if (generatedKeys.next()) {
////                    int id = generatedKeys.getInt(1);
////                    Field idField = type.getDeclaredField("id");
////                    idField.setAccessible(true);
////                    idField.set(t, id);
////                } else {
////                    throw new SQLException("Creating " + type.getSimpleName() + " failed, no ID obtained.");
////                }
////            }
////
////            return t;
////        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
////            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
////        } finally {
////            ConnectionFactory.close(resultSet);
////            ConnectionFactory.close(statement);
////            ConnectionFactory.close(connection);
////        }
////        return null;
////    }
//
//
//    public T update(T t) {
//        // TODO:
//        return t;
//    }
//}
