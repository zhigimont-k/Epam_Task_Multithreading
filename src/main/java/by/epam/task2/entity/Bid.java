package by.epam.task2.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Bid extends Thread {
    private static Logger logger = LogManager.getLogger();
    private int bidId;
    private int price;
    private CyclicBarrier barrier;

    public Bid(int bidId, int price, CyclicBarrier barrier) {
        this.bidId = bidId;
        this.price = price;
        this.barrier = barrier;
    }

    public int getBidId() {
        return bidId;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void run() {
        try {
            logger.log(Level.INFO, "Participant " + this.bidId + "offers a price.");
            this.barrier.await();
        } catch (BrokenBarrierException | InterruptedException e){
            logger.log(Level.ERROR, e);
        }
    }
}
