package application;

import core.exceptions.ProductNotFoundException;
import core.exceptions.InsufficientPaymentException;
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
            try {
                saleHandler.addItemToSale(productId, 1);
                System.out.print("Quantidade: ");
                int quantity = scanner.nextInt();
                saleHandler.addItemToSale(productId, quantity - 1);
            } catch (ProductNotFoundException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            addingItems = false;
            concludeSale();
        }
    }
}

private void concludeSale() {
    double total = saleHandler.concludeSale();
    System.out.println("Total: R$ " + total);
    
    double paid;
    while (true) {
        try {
            System.out.print("Valor pago: R$ ");
            paid = scanner.nextDouble();
            double change = saleHandler.calculateChange(paid);
            System.out.println("Troco: R$ " + change);
            break; 
        } catch (InsufficientPaymentException e) {
            System.out.println("Erro: " + e.getMessage() + " Tente novamente.");
        }
    }
}
}