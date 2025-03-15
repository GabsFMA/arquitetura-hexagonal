import core.usecases.SaleService;
import infrastructure.fileloader.FileProductRepository;
import application.SupermarketCLI;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
            FileProductRepository productRepo = null;
			try {
				productRepo = new FileProductRepository("ArqEspecProds.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            
            SaleService saleService = new SaleService(productRepo);
            SupermarketCLI cli = new SupermarketCLI(saleService);
            cli.start();
       
    }
}
