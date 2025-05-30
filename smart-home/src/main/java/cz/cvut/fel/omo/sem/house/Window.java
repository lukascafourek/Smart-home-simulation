package cz.cvut.fel.omo.sem.house;

import cz.cvut.fel.omo.sem.events.Event;
import cz.cvut.fel.omo.sem.events.EventObservableImpl;
import cz.cvut.fel.omo.sem.events.EventType;
import cz.cvut.fel.omo.sem.interfaces.EventObserver;

import static cz.cvut.fel.omo.sem.SimulationManager.logger;

/**
 * Class representing a window in the house.
 */
public class Window implements EventObserver {

    private boolean open = false;

    public Window() {
        EventObservableImpl.getInstance().addObserver(EventType.WIND, this);
    }

    @Override
    public void updateEvent(Event<?> event) {
        if (event.getEventType() == EventType.WIND) {
            logger.info("Window is opening/closing");
            open = !open;
        }
    }

    void buildHouseConfigurationReport(StringBuilder sb, int tab) {
        sb.append("\t".repeat(tab)).append(getClass().getSimpleName()).append("\n");
    }

    public boolean isOpen() {
        return open;
    }
}
