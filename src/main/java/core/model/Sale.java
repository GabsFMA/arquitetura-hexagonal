package core.model;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private final List<SaleItem> items = new ArrayList<>();

    public void addItem(SaleItem item) {
        items.add(item);
    }

    public double getTotal() {
        return items.stream().mapToDouble(SaleItem::getSubtotal).sum();
    }
}