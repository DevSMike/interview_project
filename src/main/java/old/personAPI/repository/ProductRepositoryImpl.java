package old.personAPI.repository;

import old.personAPI.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepository {
    private final Map<String, Product> productMap = new HashMap<>();

    @Override
    public boolean saveProduct(Product product) {
        if (productMap.containsKey(product.getId())) {
            return false;
        }
        productMap.put(product.getId(), product);
        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        if (!productMap.containsKey(product.getId())) {
            return false;
        }
        productMap.remove(product.getId());
        return true;
    }

    @Override
    public String getName(String id) {
        return productMap.get(id).getName();
    }

    /*
    // Возвращает массив (список) идентификаторов (id)
    // У которых наименование равно (=) name
    // Если таких нет, возвращает пустой массив (список)
     */
    // 1 - Товар1
    //  2 -Товар1 --
    // 3 - Товар3
    // O(N)
    //
    @Override
    public List<String> findByName(String name) {
        return productMap.values().stream()
                .filter(x -> x.getName().equals(name))
                .map(Product::getId)
                .collect(Collectors.toList());
    }
}
