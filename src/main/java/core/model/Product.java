package core.model;

public class Product {
    private final int id;
    private final String description;
    private final double price;

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    // Getters (sem setters para imutabilidade)
    public int getId() { 
    	return id; 
    }
    public String getDescription() { 
    	return description; 
    }
    public double getPrice() { 
    	return price; 
    }
}