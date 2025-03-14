import core.usecases.SaleService;
import infrastructure.fileloader.FileProductRepository;
import infrastructure.persistence.SQLiteSaleRepository;
import application.SupermarketCLI;
import java.sql.SQLException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            FileProductRepository productRepo = new FileProductRepository("ArqEspecProds.txt");
            SQLiteSaleRepository saleRepo = new SQLiteSaleRepository("sales.db");
            
            SaleService saleService = new SaleService(productRepo);
            SupermarketCLI cli = new SupermarketCLI(saleService);
            cli.start();
            
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}