package personAPI.service;

import personAPI.model.Product;

import java.util.List;

public interface ProductService {

    boolean addProduct(Product product);
    boolean deleteProduct(Product product);
    String getName(String id);
    List<String> findByName(String name);
}
