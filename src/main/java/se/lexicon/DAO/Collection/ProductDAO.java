package se.lexicon.DAO.Collection;

import se.lexicon.DAO.Interface.IProductDAO;
import se.lexicon.model.Product;
import se.lexicon.util.Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO implements IProductDAO {

    @Override
    public Product save(Product item) {
        Product product = null;
        String insertItem = "insert into product(name,price) values(?,?)";
        try(Connection connection = Connector.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(insertItem, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            int rowsAdded = preparedStatement.executeUpdate();
            if(rowsAdded > 0) {
                try(ResultSet generatedId = preparedStatement.getGeneratedKeys()) {
                    if(generatedId.next()) {
                        item.setId(generatedId.getInt(1));
                        return item;
                    }
                }
                catch(SQLException exception){
                    exception.printStackTrace();
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return product;
    }

    @Override
    public Optional<Product> findByID(Integer productID) {
        String productByID = "select * from product where id = ?";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(productByID)) {
            preparedStatement.setInt(1, productID);
            try(ResultSet result = preparedStatement.executeQuery()){
                if(result.next()){
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    double price = result.getDouble(3);
                    Product product = new Product(id, name, price);
                    return Optional.of(product);
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        String all_product = "select * from product";
        List<Product> products = new ArrayList<>();
        try(Connection connection = Connector.getConnection();
            Statement statement = connection.createStatement()) {
            try(ResultSet result = statement.executeQuery(all_product)){
                while (result.next()){
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    double price = result.getDouble(3);
                    Product product = new Product(id, name, price);
                    products.add(product);
                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return products;
    }

    @Override
    public void delete(Integer productID) {
        String delete_product = "delete from product where id = ?";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(delete_product)) {
            preparedStatement.setInt(1, productID);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Product with ID : " + productID + " deleted.");
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }

    }

    @Override
    public List<Product> findByName(String name) {
        String productByName = "select * from product where name = ?";
        List<Product> products = new ArrayList<>();
         try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(productByName)) {
            preparedStatement.setString(1, name);
            try(ResultSet result = preparedStatement.executeQuery()){
                while (result.next()){
                    int id = result.getInt(1);
                    String productName = result.getString(2);
                    double price = result.getDouble(3);
                    Product product = new Product(id, productName, price);
                    products.add(product);
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findByPriceBetween(int low, int high) {
        String productByPrice = "select * from product where price between ? and ?";
        List<Product> products = new ArrayList<>();
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(productByPrice)) {
            preparedStatement.setInt(1, low);
            preparedStatement.setInt(2, high);
            try(ResultSet result = preparedStatement.executeQuery()){
                while (result.next()){
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    double price = result.getDouble(3);
                    Product product = new Product(id, name, price);
                    products.add(product);
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return products;
    }
}
