package by.epam.task2.store;

import by.epam.task2.entity.AuctionParticipant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum AuctionParticipantStore {
    INSTANCE;
    private List<AuctionParticipant> store = new ArrayList<>();

    public int size() {
        return store.size();
    }

    public boolean isEmpty() {
        return store.isEmpty();
    }

    public Iterator<AuctionParticipant> iterator() {
        return store.iterator();
    }

    public boolean add(AuctionParticipant auctionParticipant) {
        return store.add(auctionParticipant);
    }

    public boolean remove(Object o) {
        return store.remove(o);
    }

    public AuctionParticipant get(int index) {
        return store.get(index);
    }
}
