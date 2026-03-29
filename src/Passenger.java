import java.util.Random;
/** A class to represent a passenger. */
public class Passenger {
// Data Fields
    /**
     * The ID number for this passenger.
     */
    private int passengerId;
    /**
     * The time needed to process this passenger.
     */
    private int processingTime;
    /**
     * The time this passenger arrives.
     */
    private int arrivalTime;
    /**
     * The maximum time to process a passenger.
     */
    private static int maxProcessingTime;
    /**
     * The sequence number for passengers.
     */
    private static int idNum = 0;
    /**
     * Randomizer
     */
    private Random randGen;

    /**
     * Create a new passenger.
     *
     * @param arrivalTime The time this passenger arrives
     */
    public Passenger(int arrivalTime) {
        randGen = new Random();
        this.arrivalTime = arrivalTime;
        setMaxProcessingTime(AirlineCheckinSim.maxProcessingTime);
        //processingTime = 1 + randGen.nextInt(maxProcessingTime);
        passengerId = idNum++;
    }

    /**
     * Get the arrival time.
     *
     * @return The arrival time
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Get the processing time.
     *
     * @return The processing time
     */
    public int getProcessingTime() {
        return processingTime;
    }

    /**
     * Get the passenger ID.
     *
     * @return The passenger ID
     */
    public int getId() {
        return passengerId;
    }

    /**
     * Set the maximum processing time
     *
     * @param maxProcessTime The new value
     */
    public void setMaxProcessingTime(int maxProcessTime) {
        maxProcessingTime = maxProcessTime;
        processingTime = 1 + randGen.nextInt(maxProcessingTime);
    }
}
