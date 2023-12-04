package controller;

import database.DBConnection;
import model.Empleado;
import model.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpleadosController {

    public void addEmpleado(Empleado empleado) {
        Connection connection = DBConnection.getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(String.format("INSERT INTO %s (%s, %s,%s) VALUES ('%s','%s','%s')", "empleados"
                    , "nombre", "apellido", "correo",
                    empleado.getName(), empleado.getSurname(), empleado.getMail()));

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void getEmpleados() {

        Connection connection = DBConnection.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM empleados");
            if (resultSet != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String correo = resultSet.getString("correo");

                    Empleado empleado = new Empleado(id, nombre, apellido, correo);
                    System.out.println(empleado);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
