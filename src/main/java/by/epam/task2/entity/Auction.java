package by.epam.task2.entity;

import by.epam.task2.store.AuctionParticipantStore;

import java.util.concurrent.CyclicBarrier;

public class Auction {
    private CyclicBarrier barrier;

    public Auction(){
        this.barrier = new CyclicBarrier(AuctionParticipantStore.INSTANCE.size(), new Runnable(){
            public void run(){

            }
        });
    }
}
