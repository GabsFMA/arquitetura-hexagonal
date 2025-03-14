package core.usecases;

import infrastructure.fileloader.FileProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class SaleServiceTest {
    private SaleService saleService;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        // Configuração inicial para todos os testes
        FileProductRepository productRepo = new FileProductRepository("ArqEspecProds.txt");
        saleService = new SaleService(productRepo);
    }

    @Test
    void testTotalVendaComDoisProdutos() {
        saleService.startNewSale();
        saleService.addItemToSale(1, 2); // 2 unidades do produto ID 1 (Oreo: R$2.99)
        saleService.addItemToSale(2, 1); // 1 unidade do produto ID 2 (Serenata: R$2.99)
        double total = saleService.concludeSale();
        assertEquals(8.97, total); // (2*2.99) + (1*2.99) = 8.97
    }

    @Test
    void testTrocoValido() {
        saleService.startNewSale();
        saleService.addItemToSale(3, 1); // 1 unidade do produto ID 3 (Lindt: R$19.99)
        double troco = saleService.calculateChange(30.00);
        assertEquals(10.01, troco); // 30 - 19.99 = 10.01
    }
}