package application;

import core.ports.SaleHandler;
import java.util.Scanner;

public class SupermarketCLI {
    private final SaleHandler saleHandler;
    private final Scanner scanner = new Scanner(System.in);

    public SupermarketCLI(SaleHandler saleHandler) {
        this.saleHandler = saleHandler;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("1. Nova venda | 2. Sair");
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> handleNewSale();
                case 2 -> running = false;
            }
        }
    }

    private void handleNewSale() {
        saleHandler.startNewSale();
        boolean addingItems = true;
        while (addingItems) {
            System.out.println("1. Adicionar item | 2. Concluir venda");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("ID do produto: ");
                int productId = scanner.nextInt();
                System.out.print("Quantidade: ");
                int quantity = scanner.nextInt();
                saleHandler.addItemToSale(productId, quantity);
            } else {
                addingItems = false;
                concludeSale();
            }
        }
    }

    private void concludeSale() {
        double total = saleHandler.concludeSale();
        System.out.println("Total: R$ " + total);
        System.out.print("Valor pago: R$ ");
        double paid = scanner.nextDouble();
        System.out.println("Troco: R$ " + saleHandler.calculateChange(paid));
    }
}