package old.personAPI;

import lombok.extern.slf4j.Slf4j;
import old.personAPI.model.Product;
import org.junit.jupiter.api.Test;
import old.personAPI.repository.ProductRepository;
import old.personAPI.repository.ProductRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ProductRepositoryTest {

    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @Test
    void addNewProductShouldAddAndNotNull() {
        String productId = "1";
        String productName = "Airpods";
        Product product = new Product(productId, productName);
        log.info("Созданный продукт: {}", product);
        assertTrue(productRepository.saveProduct(product), "Продукт не сохранен");
        assertEquals(productRepository.getName(productId), productName, "Имя не совпадает продукта");
    }

    @Test
    void getListIdsByNameShouldBeFilledWith3Ids() throws ExecutionException, InterruptedException {
        Product product1 = new Product("1", "Airpods 1");
        Product product2 = new Product("2", "Airpods 2");
        Product product3 = new Product("3", "Airpods Pro");
        Product product4 = new Product("4", "Airpods Pro");
        Product product5 = new Product("5", "Airpods Pro");

        productRepository.saveProduct(product1);
        productRepository.saveProduct(product2);
        productRepository.saveProduct(product3);
        productRepository.saveProduct(product4);
        productRepository.saveProduct(product5);

        var result = productRepository.findByName("Airpods Pro");
        log.info("Полученный List: {}", result);
        assertEquals(result.size(), 3, "Количество продуктов не сошлось");
        assertEquals(result, List.of("3", "4", "5"), "Листы не равны");

        Optional<String> max = result.stream().max(String::compareTo);

        log.info("Максимальное значение: {}", max.orElse("null"));

        CompletableFuture<String> result2 = CompletableFuture.supplyAsync(() -> productRepository.getName("2"));
        //assertTrue(result2.isDone());
        assertEquals(result2.get(), "Airpods 2");

    }
}
