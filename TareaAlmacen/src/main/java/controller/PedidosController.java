package controller;

import database.DBConnection;
import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PedidosController {

    public void addPedido(Producto producto){
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                    "pedidios",
                    "id_producto","descripcion","precio",
                    producto.getId(),producto.getDescription(),(int)producto.getPrice()));
            preparedStatement.setInt(1,producto.getId());
            preparedStatement.setString(2,producto.getDescription());
            preparedStatement.setDouble(3,producto.getPrice());
            preparedStatement.execute();

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
