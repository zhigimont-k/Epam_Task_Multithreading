package by.epam.task2.entity;


import java.math.BigDecimal;

public class AuctionLot {
    private String name;
    private BigDecimal price;

    public AuctionLot() {
    }

    public AuctionLot(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public AuctionLot(String name, double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(double price){
        this.price = BigDecimal.valueOf(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuctionLot that = (AuctionLot) o;
        return price.equals(that.price) &&
                name.equalsIgnoreCase(that.name);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash += hash * 31 + name.hashCode() + price.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return name + ", $" + price;
    }
}
