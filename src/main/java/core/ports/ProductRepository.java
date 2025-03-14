package core.ports;

import core.model.Product;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(int id);
}