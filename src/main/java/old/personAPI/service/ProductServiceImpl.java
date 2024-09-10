package old.personAPI.service;

import old.personAPI.exception.IncorrectDataException;
import lombok.RequiredArgsConstructor;
import old.personAPI.model.Product;
import old.personAPI.repository.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public boolean addProduct(Product product) {
        checkProductId(product.getId());
        return repository.saveProduct(product);
    }

    @Override
    public boolean deleteProduct(Product product) {
        checkProductId(product.getId());
        return repository.deleteProduct(product);
    }

    @Override
    public String getName(String id) {
        checkProductId(id);
        return repository.getName(id);
    }

    @Override
    public List<String> findByName(String name) {
        return repository.findByName(name);
    }

    private void checkProductId(String id) {
        if (Integer.parseInt(id) < 0) {
            throw new IncorrectDataException("Person.Id < 0");
        }
    }
}
