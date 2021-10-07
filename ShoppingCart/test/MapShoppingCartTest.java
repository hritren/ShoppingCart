import bg.sofia.uni.fmi.mjt.shopping.ItemNotFoundException;
import bg.sofia.uni.fmi.mjt.shopping.MapShoppingCart;
import bg.sofia.uni.fmi.mjt.shopping.ProductCatalog;
import bg.sofia.uni.fmi.mjt.shopping.item.Apple;
import bg.sofia.uni.fmi.mjt.shopping.item.Chocolate;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MapShoppingCartTest {
    MapShoppingCart cart;
    Map<Item, Integer> testMap;
    Item item;

    @Test
    public void testContainsEmpty() {
        testMap = new HashMap<>();
        item = new Apple("a1");
        cart = new MapShoppingCart(testMap);
        assertFalse(cart.contains(item));
    }

    @Test
    public void testContainsApple() {
        testMap = new HashMap<>();
        item = new Apple("a1");
        testMap.put(item, 1);
        cart = new MapShoppingCart(testMap);
        assertTrue(cart.contains(item));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        item = null;
        testMap = new HashMap<>();
        cart = new MapShoppingCart(testMap);
        cart.addItem(item);
    }

    @Test
    public void testAddChocolate() {
        ProductCatalog catalog = null;
        cart = new MapShoppingCart(catalog);
        item = new Chocolate("c1");
        cart.addItem(item);
        assertTrue(cart.contains(item));
    }

    @Test
    public void testAddItemTwice() {
        item = new Apple("a1");
        testMap = new HashMap<>();
        cart = new MapShoppingCart(testMap);
        cart.addItem(item);
        cart.addItem(item);
        assertEquals(cart.getQuantity(item), 2);
    }

    @Test
    public void testGetUniqueItemsApple() {
        item = new Apple("a1");
        testMap = new HashMap<>();
        cart = new MapShoppingCart(testMap);
        cart.addItem(item);
        Collection<Item> set = cart.getUniqueItems();
        assertEquals(1, set.size());
    }

    @Test
    public void testGetUniqueItemsTwoChocolates() {
        item = new Chocolate("a1");
        testMap = new HashMap<>();
        cart = new MapShoppingCart(testMap);
        cart.addItem(item);
        cart.addItem(item);
        Collection<Item> set = cart.getUniqueItems();
        assertEquals(1, set.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        item = null;
        testMap = new HashMap<>();
        testMap.put(new Apple("a1"), 1);
        cart = new MapShoppingCart(testMap);
        cart.removeItem(item);
    }

    @Test(expected = ItemNotFoundException.class)
    public void testRemoveNotFound() {
        item = new Apple("a1");
        testMap = new HashMap<>();
        cart = new MapShoppingCart(testMap);
        cart.removeItem(item);
    }

    @Test
    public void testRemoveItem() {
        item = new Apple("a1");
        testMap = new HashMap<>();
        cart = new MapShoppingCart(testMap);
        cart.addItem(item);
        cart.addItem(item);
        cart.removeItem(item);
        assertEquals(1, cart.getQuantity(item));
        cart.removeItem(item);
        assertEquals(0, cart.getQuantity(item));
    }
}
