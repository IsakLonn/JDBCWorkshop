package se.lexicon.DAO.Interface;

import se.lexicon.model.Product;
import java.util.List;

public interface IProductDAO extends IDAOBase<Product, Integer> {
     abstract List<Product> findByName(String name);
     abstract List<Product> findByPriceBetween(int low, int high);
}
