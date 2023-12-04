package controller;

import com.google.gson.Gson;
import database.DBConnection;
import model.Producto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class ProductosController {

    public void importarProductos() {

        BufferedReader bufferedReader = null;
        HttpURLConnection connection = null;
        Connection sqlConnecion = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            URL url = new URL("https://dummyjson.com/products");
            connection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject response = new JSONObject(bufferedReader.readLine());
            JSONArray products = response.getJSONArray("products");
            for (int i = 0; i < products.length(); i++) {
                JSONObject product = products.getJSONObject(i);
                Producto producto = new Gson().fromJson(product.toString(), Producto.class);

                /*
                SERÏA LO MISMO QUE SACARLO CON GSON
                int id = product.getInt("id");
                String name = product.getString("name");
                String description = product.getString("descripction");;
                double price = product.getDouble("price");;
                int stock = product.getInt("stock");;
                Producto producto1 = new Producto(id,name,description,stock,price);*/


                preparedStatement = sqlConnecion.prepareStatement(String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                        "productos",
                        "nombre", "descripcion", "cantidad", "precio"
                ));

                preparedStatement.setString(1, producto.getTitle());
                preparedStatement.setString(2, producto.getDescription());
                preparedStatement.setInt(3, producto.getStock());
                preparedStatement.setDouble(4, producto.getPrice());
                preparedStatement.execute();


                //statement.execute("INSERT INTO almacen.productos (nombre, descripcion, cantidad, precio) VALUES('Ejemplo', 'Ejemplo', 23, 23.9);");
                preparedStatement.close();

            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Producto getProducto(int id) {

        Connection connection = DBConnection.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM productos WHERE id = " + id);
            if (resultSet != null){
                while (resultSet.next()){
                    int idProducto = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String descripcion = resultSet.getString("descripcion");
                    Double precio = resultSet.getDouble("precio") ;
                    int stock = resultSet.getInt("cantidad");
                    return new Producto(idProducto,nombre,descripcion,stock,precio);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    public void getAllProductos(){
        Connection connection = DBConnection.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM productos");
            if (resultSet != null){
                while (resultSet.next()){
                    int idProducto = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String descripcion = resultSet.getString("descripcion");
                    Double precio = resultSet.getDouble("precio") ;
                    int stock = resultSet.getInt("cantidad");
                    Producto producto = new Producto(idProducto,nombre,descripcion,stock,precio);
                    System.out.println(producto);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
