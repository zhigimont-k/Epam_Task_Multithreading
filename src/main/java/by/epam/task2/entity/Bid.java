package by.epam.task2.entity;

import java.math.BigDecimal;
import java.util.Random;

public class Bid{
    private static final double MIN_BID_PERCENTAGE = 10;
    private AuctionLot lot;
    private BigDecimal deltaPrice = new BigDecimal(0);

    public Bid(){}

    public Bid(AuctionLot lot) {
        this.lot = lot;
    }

    public BigDecimal getDeltaPrice() {
        return deltaPrice;
    }

    public void setDeltaPrice(BigDecimal deltaPrice){
        this.deltaPrice = deltaPrice;
    }

    public AuctionLot getLot() {
        return lot;
    }

    public BigDecimal calculateDeltaPrice() {
        return BigDecimal.valueOf(lot.getPrice().doubleValue() * new Random().nextDouble() + MIN_BID_PERCENTAGE);
    }



    @Override
    public String toString() {
        return "Bid from " +
                "for a lot " + lot +
                ", deltaPrice = " + deltaPrice;
    }
}
