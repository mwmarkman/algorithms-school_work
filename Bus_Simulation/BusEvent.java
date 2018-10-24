//written by markm014 and arora141

public class BusEvent implements IEvent {

    private int currentStop; //Stop numbers range from 0-17, as each Eastbound/Westbound stop is counted as a distinct stop in a 18 stop loop that a bus travels
    private Bus bus;

    public BusEvent(int stop, Bus bus){ //Constructor for bus event must take in bus so that the simulation can operate multiple buses
        currentStop = stop;
        this.bus = bus;
    }

    public void run() { //the method that will be called for each bus event
        boolean passToUnload = false;
        int counter = 0;
        while ((!passToUnload) && (counter < bus.getSize())) { //this loop determines if there are any riders on the bus to be de-boarded at the current stop
            if (bus.getBusSeats()[counter] != null) {
                if (bus.getBusSeats()[counter].getOffStop() == currentStop) {
                    passToUnload = true;
                }
            }
            counter++;
        }
        if (passToUnload) { //this loop is run if it is true that there are passengers to be unloaded at the current stop
            Passenger[] passengersOff = bus.removePassengerAtStop(currentStop); //returns the passengers that were unloaded in an array
            for (int i = 0; i < passengersOff.length; i++){ //iterates through the returned array to calculate stats for each passenger and add them to static variables housed in BusSim
                passengersOff[i].setDeboardTime(BusSim.agenda.getCurrentTime());
                double rideTime = passengersOff[i].getDeboardTime() - passengersOff[i].getBoardTime();
                BusSim.totalRideTime += rideTime;
                BusSim.totalRidesCompleted++;
                }
            BusSim.agenda.add(new BusEvent(currentStop, bus), (double) passengersOff.length * 2); //creates a new BusEvent in the future depending on how many passengers got off
            bus.setTime1((double) passengersOff.length * 2); //updates times so that the bus can determine if it needs to wait longer at a stop (it always needs to wait a minimum of 15 seconds)
        }
        else if (BusSim.lines[currentStop].length() != 0 && !bus.isFull()) { //if there are people waiting to board the bus here and the bus is not full, this condition is run
            double numBoarded = 0;
            while (BusSim.lines[currentStop].length() != 0 && !bus.isFull()) {
                Passenger passToBoard;
                passToBoard = BusSim.lines[currentStop].remove();
                bus.addPassenger(passToBoard);
                passToBoard.setBoardTime(BusSim.agenda.getCurrentTime());
                double passWaitTime = passToBoard.getBoardTime() - passToBoard.getWaitTime();
                BusSim.totalPassengers++;
                BusSim.totalWaitTime += passWaitTime;
                if (passWaitTime > BusSim.maxWaitTime){
                    BusSim.maxWaitTime = passWaitTime;
                }
                numBoarded++;
            }
            BusSim.agenda.add(new BusEvent(currentStop, bus), numBoarded * 3);
            bus.setTime2(numBoarded * 3);
           // System.out.println("the bus just boarded " + numBoarded + " passengers");
        }
        else if (bus.getTime1() + bus.getTime2() < 15){ //If bus hasn't already been at stop 15 seconds, it will wait until it has been there for at least 15 seconds
            BusSim.agenda.add(new BusEvent(currentStop, bus), (15 - (bus.getTime1() + bus.getTime2())));
            bus.setTime2(100);
            bus.setTime1(100);
       //     System.out.println("The bus just waited additional time before departing for next stop");
        }
        else if (currentStop != 17){
            BusSim.agenda.add(new BusEvent(currentStop + 1, bus), 180);
           // System.out.println("Bus traveling between stop " + currentStop + "and" + (currentStop + 1));
            bus.setTime2(0);
            bus.setTime1(0);
            bus.addOccupancyEvents(1);
            bus.addTotalOccupants(bus.numOccupants());
        }
        else {
            BusSim.agenda.add(new BusEvent(0, bus), 180);
         //   System.out.println("bust traveling between stops 17 and 0");
            bus.setTime2(0);
            bus.setTime1(0);
            bus.addOccupancyEvents(1);
            bus.addTotalOccupants(bus.numOccupants());
        }
    }
}
