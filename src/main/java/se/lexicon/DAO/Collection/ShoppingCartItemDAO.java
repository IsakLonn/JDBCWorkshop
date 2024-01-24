package se.lexicon.DAO.Collection;

import se.lexicon.DAO.Interface.IShoppingCartItemDAO;
import se.lexicon.model.Product;
import se.lexicon.model.ShoppingCart;
import se.lexicon.model.ShoppingCartItem;
import se.lexicon.util.Connector;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartItemDAO implements IShoppingCartItemDAO {

    @Override
    public ShoppingCartItem save(ShoppingCartItem item) {
        ShoppingCartItem shoppingCartItem = null;
        String insertItem = "insert into shopping_cart_item(amount, total_price, product_id, shopping_cart_id) values(?,?,?,?)";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertItem, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, item.getAmount());
            preparedStatement.setDouble(2, item.getTotalPrice());
            preparedStatement.setInt(3, item.getItem().getId());
            preparedStatement.setInt(4, item.getCart().getId());
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
        return shoppingCartItem;
    }



    @Override
    public Optional<ShoppingCartItem> findByID(Integer cartItemId) {
        String cartItemByID = "select * from shopping_cart_item where id = ?";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(cartItemByID)) {
            preparedStatement.setInt(1, cartItemId);
            try(ResultSet result = preparedStatement.executeQuery()){
                if(result.next()){
                    int amount = result.getInt(2);
                    double totalPrice = result.getDouble(3);
                    int productID = result.getInt(4);
                    ProductDAO productDAO = new ProductDAO();
                    Product product = productDAO.findByID(productID).orElse(null);
                    int shoppingCartID = result.getInt(5);
                    ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
                    ShoppingCart  shoppingCart = shoppingCartDAO.findByID(shoppingCartID).orElse(null);
                    ShoppingCartItem cartItem = new ShoppingCartItem(amount, totalPrice, product, shoppingCart);
                    return Optional.of(cartItem);
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<ShoppingCartItem> findAll() {
        String all_shopping_cart_items = "select * from shopping_cart_item";
        List<ShoppingCartItem> cartsItems = new ArrayList<>();
        try(Connection connection = Connector.getConnection();
            Statement statement = connection.createStatement()) {
            try(ResultSet result = statement.executeQuery(all_shopping_cart_items)){
                while (result.next()){
                    int id = result.getInt(1);
                    int amount = result.getInt(2);
                    double totalPrice = result.getDouble(3);
                    int productID = result.getInt(4);
                    ProductDAO productDAO = new ProductDAO();
                    Product product = productDAO.findByID(productID).orElse(null);
                    int shoppingCartID = result.getInt(5);
                    ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
                    ShoppingCart  shoppingCart = shoppingCartDAO.findByID(shoppingCartID).orElse(null);

                    ShoppingCartItem cartItem = new ShoppingCartItem(id, amount, totalPrice, product, shoppingCart );
                    cartsItems.add(cartItem);
                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cartsItems;
    }

    @Override
    public void delete(Integer id) {
        String delete_shopping_cart_item = "delete from shopping_cart_item where id = ?";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(delete_shopping_cart_item)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Cart Item with ID : " + id + " deleted.");
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }

    }

    @Override
    public List<ShoppingCartItem> findByCartId(int cartId) {
        List<ShoppingCartItem> list = new ArrayList<>();
        String cartItemByID = "select * from shopping_cart_item where shopping_cart_id = ?";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(cartItemByID)) {
            preparedStatement.setInt(1, cartId);
            try(ResultSet result = preparedStatement.executeQuery()){
                while(result.next()){
                    int amount = result.getInt(2);
                    double totalPrice = result.getDouble(3);
                    int productID = result.getInt(4);
                    ProductDAO productDAO = new ProductDAO();
                    Product product = productDAO.findByID(productID).orElse(null);
                    int shoppingCartID = result.getInt(5);
                    ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
                    ShoppingCart  shoppingCart = shoppingCartDAO.findByID(shoppingCartID).orElse(null);
                    ShoppingCartItem cartItem = new ShoppingCartItem(amount, totalPrice, product, shoppingCart);
                    list.add(cartItem);
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return list;

    }

    @Override
    public List<ShoppingCartItem> findByProductId(int productId) {

        List<ShoppingCartItem> list = new ArrayList<>();
        String cartItemByID = "select * from shopping_cart_item where product_id = ?";
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(cartItemByID)) {
            preparedStatement.setInt(1, productId);
            try(ResultSet result = preparedStatement.executeQuery()){
                while(result.next()){
                    int amount = result.getInt(2);
                    double totalPrice = result.getDouble(3);
                    int productID = result.getInt(4);
                    ProductDAO productDAO = new ProductDAO();
                    Product product = productDAO.findByID(productID).orElse(null);
                    int shoppingCartID = result.getInt(5);
                    ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
                    ShoppingCart  shoppingCart = shoppingCartDAO.findByID(shoppingCartID).orElse(null);
                    ShoppingCartItem cartItem = new ShoppingCartItem(amount, totalPrice, product, shoppingCart);
                    list.add(cartItem);
                }
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return list;
    }
}
