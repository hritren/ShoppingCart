package bg.sofia.uni.fmi.mjt.shopping.item;

import java.util.Objects;

public class Chocolate implements Item, Comparable<Item> {

    private String id;

    public Chocolate(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chocolate chocolate = (Chocolate) o;
        return id.equals(chocolate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Item o) {
        return this.getId().compareTo(o.getId());
    }
}
