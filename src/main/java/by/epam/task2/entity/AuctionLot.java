package by.epam.task2.entity;


import by.epam.task2.main.Main;
import by.epam.task2.store.AuctionParticipantStore;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.concurrent.CyclicBarrier;

public class AuctionLot {
    private static Logger logger = LogManager.getLogger();
    private CyclicBarrier barrier;
    private String name;
    private BigDecimal price;
    private boolean isSold = false;

    private AuctionLot() {
        this.barrier = new CyclicBarrier(Main.PARTICIPANT_NUMBER, () -> {
            AuctionParticipant winner = defineWinner();
            if (winner.getBid(AuctionLot.this).getDeltaPrice().equals(BigDecimal.ZERO)) {
                logger.log(Level.INFO, "The lot was ignored by the participants!");
            } else {
                addPrice(winner.getBid(AuctionLot.this).getDeltaPrice());
                logger.log(Level.INFO, "Bid from participant " + winner.getId() + " won!");
            }
            isSold = true;
        });
    }

    public AuctionLot(String name, BigDecimal price) {
        this();
        this.name = name;
        this.price = price;
    }

    public AuctionLot(String name, double price) {
        this();
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    private AuctionParticipant defineWinner() {
        AuctionParticipant winner = AuctionParticipantStore.INSTANCE.get(0);
        for (AuctionParticipant participant : AuctionParticipantStore.INSTANCE) {
            if (participant.getBid(this).getDeltaPrice().compareTo(winner.getBid(this).getDeltaPrice()) > 0) {
                winner = participant;
            }
        }
        return winner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSold() {
        return isSold;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void addPrice(BigDecimal delta) {
        price = price.add(delta);
    }

    public void setPrice(double price) {
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
