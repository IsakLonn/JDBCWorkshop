package se.lexicon.DAO.Interface;

import se.lexicon.model.ShoppingCart;
import se.lexicon.model.ShoppingCartItem;
import java.util.List;

public interface IShoppingCartItemDAO extends IDAOBase<ShoppingCartItem,Integer> {
    abstract List<ShoppingCartItem> findByCartId(int cartId);
    abstract List<ShoppingCartItem> findByProductId(int productId);
}
