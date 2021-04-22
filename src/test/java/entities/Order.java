package entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable, Comparable<Order> {

    private long id;
    private String name;
    private Timestamp creationDate;
    private int price;

    public Order() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        return 0;
    }
}
