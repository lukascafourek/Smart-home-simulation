package cz.cvut.fel.omo.sem.interfaces;

import java.util.List;

/**
 * Interface for aggregate classes.
 * It is implemented in SpaceAggregateImpl class.
 * It is used in space classes House, Floor, Room.
 */
public interface SpaceAggregate {

    /**
     * Adds item to the aggregate
     * @param type type of the item
     * @param item item to be added
     * @param <T> type of the item
     */
    <T>void addItem(Class<T> type, T item);

    /**
     * Removes item from the aggregate
     * @param type type of the item
     * @param item item to be removed
     * @param <T> type of the item
     */
    <T>void removeItem(Class<T> type, T item);

    /**
     * Gets all items of the given type from the aggregate
     * @param type type of the item
     * @param <T> type of the item
     * @return list of items
     */
    <T> List<T> getItems(Class<T> type);
}
