public class Tester
{
    public static void main(String[] args)
    {
        boolean foundValid = false;
        while (!foundValid)
        {
            foundValid = AirlineCheckinSim.findPassengersLeft();
            //Max processing time is 5,
            //Total time is 20
            if (foundValid)
            {
                System.out.println("All passengers processed");
            }
            else
            {
                System.out.println("Passengers still leftover");
            }
            //Given the worst case, where every passenger has service time of 5,
            //only 4 passengers are handled in the 20 minutes
            //Given the best case, where every passenger has service time of 1,
            //20 total passengers can be handled, for every minute
        }
    }
}
