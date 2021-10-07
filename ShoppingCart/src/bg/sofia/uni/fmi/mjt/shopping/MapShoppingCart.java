package bg.sofia.uni.fmi.mjt.shopping;


import bg.sofia.uni.fmi.mjt.shopping.item.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MapShoppingCart implements ShoppingCart {

    public Map<Item, Integer> items;
    public ProductCatalog catalog;

    public MapShoppingCart(ProductCatalog catalog) {
        this.catalog = catalog;
        items = new HashMap<>();
    }

    public MapShoppingCart(Map<Item, Integer> items) {
        this.items = items;
        catalog = null;
    }

    public boolean contains(Item item) {
        return items.containsKey(item);
    }

    public Collection<Item> getUniqueItems() {
        return new TreeSet<>(items.keySet());
    }

    @Override
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + 1);
        } else {
            items.put(item, 1);
        }
    }

    @Override
    public void removeItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }

        if (!items.containsKey(item)) {
            throw new ItemNotFoundException("Item not found");
        }

        Integer occurrences = items.get(item);
        if (occurrences == 1) {
            items.remove(item);
        } else {
            items.put(item, occurrences - 1);
        }
    }

    @Override
    public double getTotal() {
        int total = 0;
        for (Item item : items.keySet()) {
            total += catalog.getProductInfo(item.getId()).price() * items.get(item);
        }
        return total;
    }

    @Override
    public Collection<Item> getSortedItems() {
        List<Item> sortedItems = new ArrayList<>(items.keySet());
        Collections.sort(sortedItems, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                ProductInfo info1 = catalog.getProductInfo(item1.getId());
                ProductInfo info2 = catalog.getProductInfo(item2.getId());
                if (info1.price() > info2.price()) {
                    return 1;
                } else if (info1.price() < info2.price()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return sortedItems;
    }

    public int getQuantity(Item item) {
        if (!contains(item)) {
            return 0;
        }
        return items.get(item);
    }
}
