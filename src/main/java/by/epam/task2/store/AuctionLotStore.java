package by.epam.task2.store;

import by.epam.task2.entity.AuctionLot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public enum AuctionLotStore implements Iterable<AuctionLot>{
    INSTANCE;
    private List<AuctionLot> store = new ArrayList<>();

    public int size() {
        return store.size();
    }

    public boolean isEmpty() {
        return store.isEmpty();
    }

    public Iterator<AuctionLot> iterator() {
        return store.iterator();
    }

    public boolean add(AuctionLot auctionLot) {
        return store.add(auctionLot);
    }

    public boolean remove(Object o) {
        return store.remove(o);
    }

    public AuctionLot get(int index) {
        return store.get(index);
    }
}
