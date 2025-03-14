package core.ports;

import core.model.Sale;

public interface SaleRepository {
    void save(Sale sale);
}