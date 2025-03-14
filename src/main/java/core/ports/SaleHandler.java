package core.ports;

public interface SaleHandler {
    void startNewSale();
    void addItemToSale(int productId, int quantity);
    double concludeSale();
    double calculateChange(double amountPaid);
}