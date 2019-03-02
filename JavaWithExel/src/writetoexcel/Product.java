package writetoexcel;

import java.util.Date;

public class Product {
    private String id;
    private String name;
    private long price;
    private int quantity;
    private Date creationDate;

    public Product(String id, String name, long price, int quantity, Date creationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
