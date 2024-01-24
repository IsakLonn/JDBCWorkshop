package se.lexicon.DAO.Interface;

import se.lexicon.model.ShoppingCart;

import java.util.List;

public interface IShoppingCartDAO extends IDAOBase<ShoppingCart, Integer> {
    abstract List<ShoppingCart> findByOrderStatus(String status);
    abstract List<ShoppingCart> findByReference(String customer);
}
