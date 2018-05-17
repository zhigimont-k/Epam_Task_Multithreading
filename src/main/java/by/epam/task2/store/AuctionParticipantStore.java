package by.epam.task2.store;

import by.epam.task2.entity.AuctionLot;
import by.epam.task2.entity.AuctionParticipant;
import by.epam.task2.entity.Bid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public enum AuctionParticipantStore implements Iterable<AuctionParticipant> {
    INSTANCE;
    private static Logger logger = LogManager.getLogger();
    private List<AuctionParticipant> store = new ArrayList<>();


    public List<AuctionParticipant> getList() {
        return Collections.unmodifiableList(store);
    }

    public List<Bid> getBidList(AuctionLot lot) {
        List bidList = new ArrayList<>();
        for (AuctionParticipant participant : store) {
            if (participant.getBid(lot).getLot().equals(lot)){
                bidList.add(participant.getBid(lot));
            }
        }
        return bidList;
    }

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
        logger.log(Level.INFO, "New participant added: " + auctionParticipant);
        return store.add(auctionParticipant);
    }

    public boolean remove(Object o) {
        return store.remove(o);
    }

    public AuctionParticipant get(int index) {
        return store.get(index);
    }
}
