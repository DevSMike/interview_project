package interview.nonton.service;

import interview.nonton.exception.IncorrectDataException;
import interview.nonton.model.Product;
import interview.nonton.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

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
