package cz.cvut.fel.omo.sem.house;

import cz.cvut.fel.omo.sem.interfaces.SpaceAggregate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the SpaceAggregate interface.
 * It is used to store collections of items and provide methods for adding, removing and getting items.
 * It is used in space classes House, Floor, Room.
 */
public class SpaceAggregateImpl implements SpaceAggregate {

    /**
     * Map of collections of items.
     * Key is the type of the item, value is the list of items of that type.
     */
    protected final Map<Class<?>, List<?>> collections = new HashMap<>();

    @Override
    public <T> void addItem(Class<T> type, T item) {
        List<T> items = (List<T>) collections.get(type);
        if (items != null) {
            items.add(item);
        } else {
            throw new IllegalArgumentException("Unsupported type for adding item: " + type.getName());
        }
    }

    @Override
    public <T> void removeItem(Class<T> type, T item) {
        List<T> items = (List<T>) collections.get(type);
        if (items != null) {
            items.remove(item);
        } else {
            throw new IllegalArgumentException("Unsupported type for removing item: " + type.getName());
        }
    }

    @Override
    public <T> List<T> getItems(Class<T> type) {
        return (List<T>) collections.get(type);
    }
}
