import bg.sofia.uni.fmi.mjt.shopping.ProductInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductInfoTest {

    @Test
    public void test() {
        String name = "name";
        String description = "description";
        double price = 1.0;
        ProductInfo info = new ProductInfo("name", "description", price);
        assertEquals(name, info.name());
        assertEquals(description, info.description());
        assertEquals(price, info.price(), 0.01);
    }
}
