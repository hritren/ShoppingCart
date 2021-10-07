import bg.sofia.uni.fmi.mjt.shopping.ItemNotFoundException;
import bg.sofia.uni.fmi.mjt.shopping.ListShoppingCart;
import bg.sofia.uni.fmi.mjt.shopping.ProductCatalog;
import bg.sofia.uni.fmi.mjt.shopping.item.Apple;
import bg.sofia.uni.fmi.mjt.shopping.item.Chocolate;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ListShoppingCartTest {

    ListShoppingCart cart;
    List<Item> testList;
    Item item;

    @Test
    public void testContainsEmpty() {
        testList = new ArrayList<>();
        item = new Apple("a1");
        cart = new ListShoppingCart(testList);
        assertFalse(cart.contains(item));
    }

    @Test
    public void testContainsApple() {
        testList = new ArrayList<>();
        item = new Apple("a1");
        testList.add(item);
        cart = new ListShoppingCart(testList);
        assertTrue(cart.contains(item));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        item = null;
        testList = new ArrayList<>();
        cart = new ListShoppingCart(testList);
        cart.addItem(item);
    }

    @Test
    public void testAddApple() {
        item = new Apple("a1");
        testList = new ArrayList<>();
        cart = new ListShoppingCart(testList);
        cart.addItem(item);
        assertTrue(cart.contains(item));
    }

    @Test
    public void testAddChocolate() {
        ProductCatalog catalog = null;
        cart = new ListShoppingCart(catalog);
        item = new Chocolate("c1");
        cart.addItem(item);
        assertTrue(cart.contains(item));
    }

    @Test
    public void testAddItemTwice() {
        item = new Apple("a1");
        testList = new ArrayList<>();
        cart = new ListShoppingCart(testList);
        cart.addItem(item);
        cart.addItem(item);
        assertTrue(cart.contains(item));
    }

    @Test
    public void testGetUniqueItemsApple() {
        item = new Apple("a1");
        testList = new ArrayList<>();
        cart = new ListShoppingCart(testList);
        cart.addItem(item);
        Collection<Item> set = cart.getUniqueItems();
        assertEquals(1, set.size());
    }

    @Test
    public void testGetUniqueItemsTwoChocolates() {
        item = new Chocolate("a1");
        testList = new ArrayList<>();
        cart = new ListShoppingCart(testList);
        cart.addItem(item);
        cart.addItem(item);
        Collection<Item> set = cart.getUniqueItems();
        assertEquals(1, set.size());
    }

    @Test
    public void testRemoveApple() {
        item = new Apple("a1");
        testList = new ArrayList<>();
        testList.add(item);
        cart = new ListShoppingCart(testList);
        cart.removeItem(item);
        assertFalse(cart.contains(item));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        item = null;
        testList = new ArrayList<>();
        testList.add(new Apple("a1"));
        cart = new ListShoppingCart(testList);
        cart.removeItem(item);
    }

    @Test(expected = ItemNotFoundException.class)
    public void testRemoveNotFound() {
        item = new Apple("a1");
        testList = new ArrayList<>();
        cart = new ListShoppingCart(testList);
        cart.removeItem(item);
    }

    @Test
    public void testGetSortedItems() {
        testList = new ArrayList<>();
        testList.add(new Chocolate("c1"));
        testList.add(new Chocolate("c1"));
        testList.add(new Apple("a1"));
        testList.add(new Apple("a2"));
        cart = new ListShoppingCart(testList);
        Collection<Item> sortedItems = cart.getSortedItems();
        assertEquals(2, sortedItems.size());
    }
}
