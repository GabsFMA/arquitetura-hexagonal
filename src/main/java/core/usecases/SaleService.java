package core.usecases;

import core.ports.SaleHandler;
import core.ports.ProductRepository;
import core.model.*;

public class SaleService implements SaleHandler {
    private Sale currentSale;
    private final ProductRepository productRepository;

    public SaleService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void startNewSale() {
        currentSale = new Sale();
    }

    @Override
    public void addItemToSale(int productId, int quantity) {
        productRepository.findById(productId).ifPresent(product -> 
            currentSale.addItem(new SaleItem(product, quantity))
        );
    }

    @Override
    public double concludeSale() {
        return currentSale.getTotal();
    }

    @Override
    public double calculateChange(double amountPaid) {
        return amountPaid - currentSale.getTotal();
    }
}