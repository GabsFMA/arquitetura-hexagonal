package infrastructure.fileloader;

import core.model.Product;
import core.ports.ProductRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileProductRepository implements ProductRepository {
    private final Map<Integer, Product> products = new HashMap<>();

    public FileProductRepository(String filePath) throws FileNotFoundException {
        loadProductsFromFile(filePath);
    }

    private void loadProductsFromFile(String filePath) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                int id = Integer.parseInt(parts[0].trim());
                String desc = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                products.put(id, new Product(id, desc, price));
            }
        }
    }

    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(products.get(id));
    }
}