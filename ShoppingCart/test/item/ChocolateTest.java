package item;

import bg.sofia.uni.fmi.mjt.shopping.item.Chocolate;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ChocolateTest {
    String id;
    Item item;

    @Test
    public void testGetChocolateId1() {
        id = "testId";
        item = new Chocolate(id);
        assertEquals(id, item.getId());
    }

    @Test
    public void testGetChocolateId2() {
        id = "";
        item = new Chocolate(id);
        assertEquals(id, item.getId());
    }

    @Test
    public void testEqualsSame() {
        id = "c1";
        item = new Chocolate(id);
        assertTrue(item.equals(item));
    }

    @Test
    public void testEqualsNull() {
        id = "c1";
        item = new Chocolate(id);
        Item other = null;
        assertFalse(item.equals(other));
    }

    @Test
    public void testEqualsOther() {
        id = "c1";
        item = new Chocolate(id);
        Item other = new Chocolate(id);
        assertTrue(item.equals(other));
    }
}
