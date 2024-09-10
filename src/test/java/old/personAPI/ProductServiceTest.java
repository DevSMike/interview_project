package old.personAPI;

import old.personAPI.exception.IncorrectDataException;
import old.personAPI.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import old.personAPI.repository.ProductRepository;
import old.personAPI.service.ProductServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void checkSaveMethodProductIdBiggerThanZeroShouldBeTrue() {
        Product product = new Product("123", "Test Product");
        when(repository.saveProduct(product)).thenReturn(true);
        boolean result = productService.addProduct(product);
        assertTrue(result);
    }

    @Test
    void checkSaveMethodProductIdLessThanZeroShouldBeIncorrectDataException() {
        Product product = new Product("-2", "Name");
        assertThrows(IncorrectDataException.class, () -> productService.addProduct(product));
        verify(repository,times(0)).saveProduct(product);

    }
}
