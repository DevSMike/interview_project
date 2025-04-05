package interview.nonton.repository;

import interview.nonton.model.Product;

import java.util.List;

public interface ProductRepository {
    boolean saveProduct(Product product);
    boolean deleteProduct(Product product);
    String getName(String id);
    List<String> findByName(String name);
}
