package se.lexicon;

import se.lexicon.DAO.Collection.ProductDAO;
import se.lexicon.DAO.Collection.ShoppingCartDAO;
import se.lexicon.DAO.Collection.ShoppingCartItemDAO;
import se.lexicon.model.Product;
import se.lexicon.model.ShoppingCart;
import se.lexicon.model.ShoppingCartItem;
import se.lexicon.util.Connector;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Connector.set_url("jdbc:mysql://localhost:3306/shopping_practice");
        Connector.set_password("Undulat159!");
        Connector.set_username("root");
        Connector.getConnection();

        /*
        Product product = new Product("Egg", 65.50);
        ProductDAO productDAO = new ProductDAO();
        Product product1 = productDAO.save(product);

        Product productReturned = productDAO.findByID(product1.getId()).get();
        System.out.println(productReturned.toString());

        List<Product> productList = productDAO.findAll();
        for(Product p : productList)
            System.out.println(p.toString());
        productDAO.delete(12);

        List<Product> productList1 = productDAO.findAll();
        for(Product p : productList1)
            System.out.println(p.toString());

         productDAO.findByName("Chocolate").forEach(System.out::println);

        productDAO.findByPriceBetween(20, 40).forEach(System.out::println);
        */
        ShoppingCartDAO cartDAO = new ShoppingCartDAO();
        ShoppingCart cart = cartDAO.findByID(2).get();
        //System.out.println("cart " + cart != null);

        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findByID(1).get();
        //System.out.println("product " + product != null);

        ShoppingCartItemDAO cartItemDAO = new ShoppingCartItemDAO();
        //cartItemDAO.findByCartId(2).forEach(System.out::println);
        //ShoppingCartItem cartItem = new ShoppingCartItem(1, 10, product, cart);
        //cartItemDAO.save(cartItem);

        //ShoppingCartItem cartItem = cartItemDAO.findByID(5).get();
       // System.out.println(cartItem.toString());

        //cartItemDAO.findAll().forEach(System.out::println);

        new ShoppingCartItemDAO().findByProductId(1).forEach(System.out::println);


    }
}