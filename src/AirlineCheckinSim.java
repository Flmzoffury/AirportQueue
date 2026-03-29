/** Simulate the check-in process of an airline.
 */
import java.util.Scanner;

public class AirlineCheckinSim {
// Data Fields
    /**
     * Queue of frequent flyers.
     */
    private static PassengerQueue frequentFlyerQueue =
            new PassengerQueue("Frequent Flyer");
    /**
     * Queue of regular passengers.
     */
    private static PassengerQueue regularPassengerQueue =
            new PassengerQueue("Regular Passenger");
    /**
     * Maximum number of frequent flyers to be served
     * before a regular passenger gets served.
     */
    private static int frequentFlyerMax;
    /**
     * Maximum time to service a passenger.
     */
    public static int maxProcessingTime;
    /**
     * Total simulated time.
     */
    private static int totalTime;
    /**
     * If set true, print additional output.
     */
    private static boolean showAll;
    /**
     * Simulated clock.
     */
    private int clock = 0;
    /**
     * Time that the agent will be done with the current passenger.
     */
    private int timeDone;
    /**
     * Number of frequent flyers served since the
     * last regular passenger was served.
     */
    private int frequentFlyersSinceRP;

    /**
     * Main method.
     *
     * @param args Not used
     */
    public static void main(String[] args) {
        AirlineCheckinSim sim = new AirlineCheckinSim();
        sim.enterData();
        sim.runSimulation();
        sim.showStats();
        System.exit(0);
    }

    private void runSimulation() {
        for (clock = 0; clock < totalTime; clock++) {
            frequentFlyerQueue.checkNewArrival(clock, showAll);
            regularPassengerQueue.checkNewArrival(clock, showAll);
            if (clock >= timeDone) {
                startServe();
            }
        }
    }

    private void startServe() {
        if (!frequentFlyerQueue.isEmpty()
                && ((frequentFlyersSinceRP <= frequentFlyerMax)
                || regularPassengerQueue.isEmpty())) {
// Serve the next frequent flyer.
            frequentFlyersSinceRP++;
            timeDone = frequentFlyerQueue.update(clock, showAll);
        } else if (!regularPassengerQueue.isEmpty()) {
// Serve the next regular passenger.
            frequentFlyersSinceRP = 0;
            timeDone = regularPassengerQueue.update(clock, showAll);
        } else if (showAll) {
            System.out.println("Time is " + clock
                    + " server is idle");
        }
    }

    /** Method to show the statistics. */
    private void showStats() {
        System.out.println
                ("\nThe number of regular passengers served was "
                        + regularPassengerQueue.getNumServed());
        double averageWaitingTime =
                (double) regularPassengerQueue.getTotalWait()
                        / (double) regularPassengerQueue.getNumServed();
        System.out.println(" with an average waiting time of "
                + averageWaitingTime
                + " seconds");
        System.out.println("The number of frequent flyers served was "
                + frequentFlyerQueue.getNumServed());
        averageWaitingTime =
                (double) frequentFlyerQueue.getTotalWait()
                        / (double) frequentFlyerQueue.getNumServed();
        System.out.println(" with an average waiting time of "
                + averageWaitingTime
                + " seconds");
        System.out.println("Passengers in frequent flyer queue: "
                + frequentFlyerQueue.size());
        System.out.println("Passengers in regular passenger queue: "
                + regularPassengerQueue.size());
    }

    private void enterData()
    {
        //Scanner inputReader = new Scanner(System.in);
        showAll = true;
        totalTime = 20 * 60; //Total time is now measured in seconds rather than minutes
        maxProcessingTime = 5 * 60; //Now measured in seconds
        frequentFlyerQueue =
                new PassengerQueue("Frequent Flyer");
        regularPassengerQueue =
                new PassengerQueue("Regular Passenger");
        frequentFlyerMax = 1;
        frequentFlyerQueue.setArrivalRate((double) 15 /3600); //The rate is now in arrival/per second
        regularPassengerQueue.setArrivalRate((double) 30 / 3600);

    }
}