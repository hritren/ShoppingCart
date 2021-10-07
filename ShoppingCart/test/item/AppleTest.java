package item;

import bg.sofia.uni.fmi.mjt.shopping.item.Apple;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AppleTest {

    String id;
    Item item;

    @Test
    public void testGetAppleId1() {
        id = "testId";
        item = new Apple(id);
        assertEquals(id, item.getId());
    }

    @Test
    public void testGetAppleId2() {
        id = "";
        item = new Apple(id);
        assertEquals(id, item.getId());
    }

    @Test
    public void testEqualsSame() {
        id = "a1";
        item = new Apple(id);
        assertTrue(item.equals(item));
    }

    @Test
    public void testEqualsNull() {
        id = "a1";
        item = new Apple(id);
        Item other = null;
        assertFalse(item.equals(other));
    }

    @Test
    public void testEqualsOther() {
        id = "a1";
        item = new Apple(id);
        Item other = new Apple(id);
        assertTrue(item.equals(other));
    }
}
