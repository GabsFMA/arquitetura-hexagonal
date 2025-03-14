package core.usecases;

import core.exceptions.InsufficientPaymentException;
import core.exceptions.ProductNotFoundException;
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
        productRepository.findById(productId)
            .ifPresentOrElse(
                product -> currentSale.addItem(new SaleItem(product, quantity)),
                () -> { throw new ProductNotFoundException("Produto com ID " + productId + " não encontrado."); }
            );
    }

    @Override
    public double concludeSale() {
        return currentSale.getTotal();
    }


    @Override
    public double calculateChange(double amountPaid) {
        double total = currentSale.getTotal();
        if (amountPaid < total) {
            throw new InsufficientPaymentException("O valor pago é insuficiente. É preciso pagar ao menos: R$ " + total);
        }
        return Math.round((amountPaid - total) * 100.0) / 100.0; 
    }
}