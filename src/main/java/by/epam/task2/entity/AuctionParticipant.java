package by.epam.task2.entity;

import by.epam.task2.store.AuctionLotStore;
import by.epam.task2.store.AuctionParticipantStore;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class AuctionParticipant extends Thread {
    private static Logger logger = LogManager.getLogger();
    private static final int MAX_WAITING_TIME = 100;
    private static AuctionLot currentLot;
    private int id;
    private CyclicBarrier barrier;
    private List<Bid> bidList = new ArrayList<>();

    public AuctionParticipant() {
        id = AuctionParticipantStore.INSTANCE.size() + 1;
        initBids();
    }

    public AuctionParticipant(CyclicBarrier barrier) {
        this();
        this.barrier = barrier;
    }

    @Override
    public void run() {
        if (new Random().nextBoolean()) {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(MAX_WAITING_TIME));
                getBid(currentLot).setDeltaPrice(getBid(currentLot).calculateDeltaPrice());
                logger.log(Level.INFO, "Participant " + id + " offers a bid: $" + getBid(currentLot).getDeltaPrice()
                        + " for lot " + currentLot);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, e);
                Thread.currentThread().interrupt();
            }
        } else {
            logger.log(Level.INFO, "Participant " + id + " is not making a bid.");
        }
        try {
            this.barrier.await();
        } catch (BrokenBarrierException | InterruptedException e) {
            logger.log(Level.ERROR, e);
            Thread.currentThread().interrupt();
        }
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public long getId() {
        return id;
    }

    public Bid getBid(AuctionLot lot) {
        Bid found = new Bid();
        for (Bid bid : bidList) {
            if (bid.getLot().equals(lot)) {
                found = bid;
            }
        }
        return found;
    }

    public void addBid(AuctionLot lot) {
        bidList.add(new Bid(lot));
    }

    public static void setCurrentLot(AuctionLot currentLot) {
        AuctionParticipant.currentLot = currentLot;
    }

    public static AuctionLot getCurrentLot() {
        return currentLot;
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
        return id == that.id;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash += hash * 31 + id;
        return hash;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    private void initBids() {
        for (AuctionLot lot : AuctionLotStore.INSTANCE) {
            addBid(lot);
        }
    }
}
