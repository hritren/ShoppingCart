package bg.sofia.uni.fmi.mjt.shopping;


import bg.sofia.uni.fmi.mjt.shopping.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Comparator;

public class ListShoppingCart implements ShoppingCart {

    List<Item> items;
    ProductCatalog catalog;

    public ListShoppingCart(ProductCatalog catalog) {
        this.catalog = catalog;
        this.items = new ArrayList<>();
    }

    public ListShoppingCart(List<Item> items) {
        this.items = items;
        catalog = null;
    }

    @Override
    public Collection<Item> getUniqueItems() {
        return new TreeSet<>(items);
    }

    @Override
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        items.add(item);
    }

    @Override
    public void removeItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        if (!items.contains(item)) {
            throw new ItemNotFoundException("Item was not found");
        }
        items.remove(item);
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += catalog.getProductInfo(item.getId()).price();
        }
        return total;
    }

    @Override
    public Collection<Item> getSortedItems() {
        Map<Item, Integer> itemToQuantity = create_map();
        Map<Item, Integer> sortedItems = new TreeMap<>(new Comparator<Item>() {
            public int compare(Item item1, Item item2) {
                return itemToQuantity.get(item1).compareTo(itemToQuantity.get(item2));
            }
        });
        sortedItems.putAll(itemToQuantity);
        return sortedItems.keySet();
    }

    private Map<Item, Integer> create_map() {
        HashMap<Item, Integer> itemToQuantity = new HashMap<Item, Integer>();
        for (Item item : items) {
            boolean condition = !itemToQuantity.containsKey(item);
            itemToQuantity.put(item, condition ? 1 : itemToQuantity.get(item) + 1);
        }
        return itemToQuantity;
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

}

