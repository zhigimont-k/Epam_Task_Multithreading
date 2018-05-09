package by.epam.task2.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class AuctionParticipant {
    private long id;
    private BigDecimal cash;

    public AuctionParticipant(){
        id = Math.abs(UUID.randomUUID().getMostSignificantBits());
    }

    public AuctionParticipant(BigDecimal cash) {
        this();
        this.cash = cash;
    }

    public AuctionParticipant(double cash) {
        this();
        this.cash = BigDecimal.valueOf(cash);
    }

    public long getId() {
        return id;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public void setCash(double cash) {
        this.cash = BigDecimal.valueOf(cash);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuctionParticipant that = (AuctionParticipant) o;
        return id == that.id && cash.equals(that.cash);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash += hash * 31 + cash.hashCode() + id;
        return hash;
    }
}
