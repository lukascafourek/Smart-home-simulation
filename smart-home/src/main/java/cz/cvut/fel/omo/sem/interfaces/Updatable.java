package cz.cvut.fel.omo.sem.interfaces;

/**
 * Interface for classes that can be updated every tick of the simulation.
 */
public interface Updatable {

    /**
     * Method that updates the class each tick of the simulation.
     * Each class using this method updates in its own way.
     */
    void update();
}
