package se.lexicon.DAO.Collection;

import se.lexicon.DAO.Interface.IShoppingCartDAO;
import se.lexicon.model.Product;
import se.lexicon.model.ShoppingCart;
import se.lexicon.util.Connector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartDAO implements IShoppingCartDAO {
    @Override
    public ShoppingCart save(ShoppingCart item) {
        ShoppingCart savedItem = null;
        String insertItem = "insert into shopping_cart(last_update,order_status, delivery_address, customer_reference) values(LOCALTIME(),?,?,?)";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertItem, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getOrderStatus());
            preparedStatement.setString(2, item.getDeliveryAdress());
            preparedStatement.setString(3, item.getCustomerReference());
            int rowsAdded = preparedStatement.executeUpdate();
            if(rowsAdded > 0) {
                try(ResultSet generatedId = preparedStatement.getGeneratedKeys()) {
                    if(generatedId.next()) {
                        item.setId(generatedId.getInt(1));
                        savedItem = item;
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
        return savedItem;
    }

    @Override
    public Optional<ShoppingCart> findByID(Integer cartID) {
        String productByID = "select * from shopping_cart where id = ?";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(productByID)) {
            preparedStatement.setInt(1, cartID);
            try(ResultSet result = preparedStatement.executeQuery()){
                if(result.next()){
                    int id = result.getInt(1);
                    LocalDateTime lastUpdate = result.getTimestamp(2).toLocalDateTime();
                    String orderStatus = result.getString(3);
                    String deliveryAddress = result.getString(4);
                    String customerReference = result.getString(5);
                    ShoppingCart cart = new ShoppingCart(id, lastUpdate, orderStatus, deliveryAddress, customerReference);
                    return Optional.of(cart);
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ShoppingCart> findAll() {
        String all_product = "select * from shopping_cart";
        List<ShoppingCart> carts = new ArrayList<>();
        try(Connection connection = Connector.getConnection();
            Statement statement = connection.createStatement()) {
            try(ResultSet result = statement.executeQuery(all_product)){
                while (result.next()){
                    int id = result.getInt(1);
                    LocalDateTime lastUpdate = result.getTimestamp(2).toLocalDateTime();
                    String orderStatus = result.getString(3);
                    String deliveryAddress = result.getString(4);
                    String customerReference = result.getString(5);
                    ShoppingCart cart = new ShoppingCart(id, lastUpdate, orderStatus, deliveryAddress, customerReference);
                    carts.add(cart);
                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return carts;
    }

    @Override
    public void delete(Integer cartID) {
        String delete_product = "delete from shopping_cart where id = ?";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(delete_product)) {
            preparedStatement.setInt(1, cartID);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Product with ID : " + cartID + " deleted.");
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }

    }

    @Override
    public List<ShoppingCart> findByOrderStatus(String status) {
        String productByName = "select * from shopping_cart where order_status = ?";
        List<ShoppingCart> carts = new ArrayList<>();
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(productByName)) {
            preparedStatement.setString(1, status);
            try(ResultSet result = preparedStatement.executeQuery()){
                while (result.next()){
                    int id = result.getInt(1);
                    LocalDateTime lastUpdate = result.getTimestamp(2).toLocalDateTime();
                    String orderStatus = result.getString(3);
                    String deliveryAddress = result.getString(4);
                    String customerReference = result.getString(5);
                    ShoppingCart cart = new ShoppingCart(id, lastUpdate, orderStatus, deliveryAddress, customerReference);
                    carts.add(cart);
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return carts;
    }

    @Override
    public List<ShoppingCart> findByReference(String customerReference) {
        String productByName = "select * from shopping_cart where customer_reference = ?";
        List<ShoppingCart> carts = new ArrayList<>();
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(productByName)) {
            preparedStatement.setString(1, customerReference);
            try(ResultSet result = preparedStatement.executeQuery()){
                while (result.next()){
                    int id = result.getInt(1);
                    LocalDateTime lastUpdate = result.getTimestamp(2).toLocalDateTime();
                    String orderStatus = result.getString(3);
                    String deliveryAddress = result.getString(4);
                    String _customerReference = result.getString(5);
                    ShoppingCart cart = new ShoppingCart(id, lastUpdate, orderStatus, deliveryAddress, _customerReference);
                    carts.add(cart);
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return carts;
    }
}
