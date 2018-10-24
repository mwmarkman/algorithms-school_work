//Written by markm014 and arora141
//Main method here runs the whole program

import java.util.Random;

public class BusSim {

    public static double numTimesLooped; //Counter for the number of times the simulation is being run
    public static PQ agenda = new PQ();
    public static Q<Passenger>[] lines = new Q[18];
    public static double randFactorNorm;
    public static double randFactorDown;
    public static double totalPassengers = 0;
    public static double totalWaitTime = 0;
    public static double maxWaitTime = 0;
    public static double totalRideTime = 0;
    public static double totalRidesCompleted = 0;
    public static double meanWaitTime = 0;
    public static double meanRideTime = 0;
    public static double meanTravelTime = 0;
    public static double totalPMPG;
    public static double PMPGEvents;
    public static double meanPMPG;

    //Stat compilers for when route is normal (within 25% of average interval between arrival times)

    public static double meanPMPGNorm; //At the end of the loop do meanPMPGNorm = meanPMPGNorm/Events
    public static double meanTravelTimeNorm;
    public static double meanWaitTimeNorm;
    public static double meanMaxWaitTimeNorm;
    public static double normEvents;

    //Stat compilers for when route is busy (50% or greater decrease in average interval between passenger arrival)

    public static double meanPMPGBusy;
    public static double meanTravelTimeBusy;
    public static double meanWaitTimeBusy;
    public static double meanMaxWaitTimeBusy;
    public static double busyEvents;

    //Stat compilers for when route is slow(50% or greater increase in average interval between passenger arrival)

    public static double meanPMPGSlow;
    public static double meanTravelTimeSlow;
    public static double meanWaitTimeSlow;
    public static double meanMaxWaitTimeSlow;
    public static double slowEvents;

    public static void main(String[] args) {
        while (numTimesLooped < 1000) { //while loop to modify how many times the simulaiton is run
            Random rand = new Random();
            int randNum = rand.nextInt(100) + 1; //Determination for whether the interval between passengers is the same, less, or greater
            if (randNum < 11) {
                randFactorNorm = 90;
                randFactorDown = 60;
            } else if (randNum >= 11 && randNum < 26) {
                randFactorNorm = 60;
                randFactorDown = 40;
            } else if (randNum >= 26 && randNum < 46) {
                randFactorNorm = 24;
                randFactorDown = 16;
            } else if (randNum >= 46 && randNum < 56) {
                randFactorNorm = 0;
                randFactorDown = 0;
            } else if (randNum >= 56 && randNum < 76) {
                randFactorNorm = -24;
                randFactorDown = -16;
            } else if (randNum >= 76 && randNum < 91) {
                randFactorNorm = -60;
                randFactorDown = -40;
            } else if (randNum >= 91 && randNum < 101) {
                randFactorNorm = -90;
                randFactorDown = -60;
            }
            for (int i = 0; i < 18; i++) {
                lines[i] = new Q(1000);
            }
            for (int i = 0; i < 10; i++) { //Create a new Passenger arrival event at every island
                PassengerArrivalEvent theEvent = new PassengerArrivalEvent(i);
                agenda.add(theEvent, 0);
            }

            Bus bus1 = new Bus(false);
            Bus bus2 = new Bus(false);
            Bus bus3 = new Bus(false);
            Bus bus4 = new Bus(false);
            Bus bus5 = new Bus(false);
            Bus bus6 = new Bus(false);
            Bus bus7 = new Bus(false);
            Bus bus8 = new Bus(false);
            Bus bus9 = new Bus(false);
            Bus bus10 = new Bus(false);
            BusEvent busEvent0 = new BusEvent(0, bus1);
            BusEvent busEvent9 = new BusEvent(2, bus2);
            BusEvent busEvent4 = new BusEvent(4, bus3);
            BusEvent busEvent14 = new BusEvent(6, bus4);
            BusEvent busEvent18 = new BusEvent(8, bus5);
            BusEvent busEvent19 = new BusEvent(10, bus6);
            BusEvent busEvent20 = new BusEvent(12,bus7);
            BusEvent busEvent21 = new BusEvent(14, bus8);
            BusEvent busEvent22 = new BusEvent(16, bus9);
            BusEvent busEvent23 = new BusEvent(17, bus10);
            agenda.add(busEvent0, 0);
            agenda.add(busEvent4, 0);
            agenda.add(busEvent9, 0);
            agenda.add(busEvent14, 0);
            agenda.add(busEvent18, 0);
            agenda.add(busEvent19,0);
            agenda.add(busEvent20,0);
            agenda.add(busEvent21,0);
            agenda.add(busEvent22,0);
            agenda.add(busEvent23,0);

            while (agenda.getCurrentTime() <= 10000) {
                agenda.remove().run();
            }

            meanWaitTime = (totalWaitTime / totalPassengers); //statistical calculations
            meanRideTime = (totalRideTime / totalRidesCompleted);
            meanTravelTime = ((totalRideTime / totalRidesCompleted) + (totalWaitTime / totalPassengers));
            totalPMPG += ((bus1.getTotalOccupants() / bus1.getOccupancyEvents() * bus1.getMPG()));
            PMPGEvents++;
            totalPMPG += ((bus2.getTotalOccupants() / bus2.getOccupancyEvents() * bus2.getMPG()));
            PMPGEvents++;
            totalPMPG += ((bus3.getTotalOccupants() / bus3.getOccupancyEvents() * bus3.getMPG()));
            PMPGEvents++;
            totalPMPG += ((bus4.getTotalOccupants() / bus4.getOccupancyEvents() * bus4.getMPG()));
            PMPGEvents++;
            totalPMPG += ((bus5.getTotalOccupants() / bus5.getOccupancyEvents() * bus5.getMPG()));
            PMPGEvents++;
            totalPMPG += ((bus6.getTotalOccupants() / bus6.getOccupancyEvents() * bus6.getMPG()));
            PMPGEvents++;
            totalPMPG += ((bus7.getTotalOccupants() / bus7.getOccupancyEvents() * bus7.getMPG()));
            PMPGEvents++;
            totalPMPG += ((bus8.getTotalOccupants() / bus8.getOccupancyEvents() * bus8.getMPG()));
            PMPGEvents++;
            totalPMPG += ((bus9.getTotalOccupants() / bus9.getOccupancyEvents() * bus9.getMPG()));
            PMPGEvents++;
            totalPMPG += ((bus10.getTotalOccupants() / bus10.getOccupancyEvents() * bus10.getMPG()));
            PMPGEvents++;
            meanPMPG = (totalPMPG / PMPGEvents);

            if (randNum < 26){
                meanPMPGSlow += meanPMPG;
                meanTravelTimeSlow += meanTravelTime;
                meanWaitTimeSlow += meanWaitTime;
                meanMaxWaitTimeSlow += maxWaitTime;
                slowEvents++;
            }
            else if (randNum < 76){
                meanPMPGNorm += meanPMPG;
                meanTravelTimeNorm += meanTravelTime;
                meanWaitTimeNorm += meanWaitTime;
                meanMaxWaitTimeNorm += maxWaitTime;
                normEvents++;
            }
            else {
                meanPMPGBusy += meanPMPG;
                meanTravelTimeBusy += meanTravelTime;
                meanWaitTimeBusy += meanWaitTime;
                meanMaxWaitTimeBusy += maxWaitTime;
                busyEvents++;
            }

            lines = new Q[18]; //These lines reset each variable that calculate stats for each individual loop
            agenda = new PQ();
            randFactorNorm = 0;
            randFactorDown = 0;
            totalPassengers = 0;
            totalWaitTime = 0;
            maxWaitTime = 0;
            totalRideTime = 0;
            totalRidesCompleted = 0;
            meanWaitTime = 0;
            meanRideTime = 0;
            meanTravelTime = 0;
            totalPMPG = 0;
            PMPGEvents = 0;
            meanPMPG = 0;

            numTimesLooped++;
        }
        meanPMPGSlow = meanPMPGSlow/slowEvents; //final statistic calculation
        meanTravelTimeSlow = meanTravelTimeSlow/slowEvents;
        meanMaxWaitTimeSlow = meanMaxWaitTimeSlow/slowEvents;
        meanWaitTimeSlow = meanWaitTimeSlow/slowEvents;

        meanPMPGBusy = meanPMPGBusy/busyEvents;
        meanTravelTimeBusy = meanTravelTimeBusy/busyEvents;
        meanMaxWaitTimeBusy = meanMaxWaitTimeBusy/busyEvents;
        meanWaitTimeBusy = meanWaitTimeBusy/busyEvents;

        meanPMPGNorm = meanPMPGNorm/normEvents;
        meanTravelTimeNorm = meanTravelTimeNorm/normEvents;
        meanMaxWaitTimeNorm = meanMaxWaitTimeNorm/normEvents;
        meanWaitTimeNorm = meanWaitTimeNorm/normEvents;

        System.out.println("*************************************************"); //System output for statistics
        System.out.println("Bus Simulator After " + numTimesLooped + " Runs");
        System.out.println("*************************************************");
        System.out.println("");
        System.out.println("Stats For When the Route is Busy (" + busyEvents + " runs)");
        System.out.println("-------------------------------------------------");
        System.out.println("Average Bus Passenger Miles Per Gallon (PMPG): " + meanPMPGBusy);
        System.out.println("Average Passenger Wait Time (seconds): " + meanWaitTimeBusy);
        System.out.println("Average Maximum Passenger Wait Time (seconds): " + meanMaxWaitTimeBusy);
        System.out.println("Average Travel Time Per Passenger (seconds): " + meanTravelTimeBusy);
        System.out.println("");
        System.out.println("Stats For When the Route is Normal (" + normEvents + " runs)");
        System.out.println("-------------------------------------------------");
        System.out.println("Average Bus Passenger Miles Per Gallon (PMPG): " + meanPMPGNorm);
        System.out.println("Average Passenger Wait Time (seconds): " + meanWaitTimeNorm);
        System.out.println("Average Maximum Passenger Wait Time (seconds): " + meanMaxWaitTimeNorm);
        System.out.println("Average Travel Time Per Passenger (seconds): " + meanTravelTimeNorm);
        System.out.println("");
        System.out.println("Stats For When the Route is Slow (" + slowEvents + " runs)");
        System.out.println("-------------------------------------------------");
        System.out.println("Average Bus Passenger Miles Per Gallon (PMPG): " + meanPMPGSlow);
        System.out.println("Average Passenger Wait Time (seconds): " + meanWaitTimeSlow);
        System.out.println("Average Maximum Passenger Wait Time (seconds): " + meanMaxWaitTimeSlow);
        System.out.println("Average Travel Time Per Passenger (seconds): " + meanTravelTimeSlow);
    }

}
