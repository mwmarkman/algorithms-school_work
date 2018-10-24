//Written by markm014 and arora141.

public class Bus {

    private int size; //length of bus (40 or 60 seats). Also effects MPG and PMPG
    private Passenger[] busSeats;
    private double time1; //Time 1 and time 2 are used to determine if a bus has already waited 15 seconds at a stop after deboarding and boarding passengers
    private double time2;
    private double totalOccupants;
    private double occupancyEvents;
    private double MPG;

    public Bus(boolean length){
        time1 = 0;
        time2 = 0;
        if (length){ //use the parameter length to determine if the bus is long or short
            size = 60;
            MPG = 4;
        }
        else{
            size = 40;
            MPG = 6;
        }
        busSeats = new Passenger[size]; //initialize passenger array of correct size for each bus object
    }

    public int getSize(){
        return size;
    }

    public Passenger[] getBusSeats(){
        return busSeats;
    }

    public boolean addPassenger(Passenger p){ //Add a passenger to the bus. Returns true if passenger added, false if not
        boolean passAdded = false;
        int counter = 0;
        if (isFull()){
            return false;
        }
        else{
            while ((!passAdded) && counter < size){
                if (busSeats[counter] == null){
                    busSeats[counter] = p;
                    passAdded = true;
                }
                counter++;
            }
        }
        return passAdded;
    }

    public boolean isFull(){ //returns true if the bus is false, false if it is not
        int counter = 0;
        boolean full = true;
        while ((counter < size) && (full)){
            if (busSeats[counter] == null){
                full = false;
            }
            counter ++;
        }
        return full;
    }

    public Passenger[] removePassengerAtStop(int stop){ //returns an array of the passengers who get off at a stop and sets their seats on the bus to null
        Passenger[] firstArray = new Passenger[size];
        int arrayCounter = 0;
        for (int i = 0; i < size; i++){
            if (busSeats[i] != null){
                if (busSeats[i].getOffStop() == stop){
                    firstArray[arrayCounter] = busSeats[i];
                    arrayCounter++;
                    busSeats[i] = null;
                }
            }
        }
        Passenger[] returnArray = new Passenger[arrayCounter];
        for (int j = 0; j < arrayCounter; j++){
            returnArray[j] = firstArray[j];
        }
        return returnArray;
    }

    public double numOccupants(){
        int counter = 0;
        for (int i = 0; i < size; i++){
            if (busSeats[i] != null){
                counter++;
            }
        }
        return counter++;
    }

    public double getTime1(){
        return time1;
    }

    public double getTime2(){
        return time2;
    }

    public void setTime1(double time){
        time1 = time;
    }

    public void setTime2(double time){
        time2 = time;
    }

    public void addOccupancyEvents(double evs){
        occupancyEvents += evs;
    }

    public void addTotalOccupants(double ocs){
        totalOccupants += ocs;
    }

    public double getTotalOccupants(){
        return totalOccupants;
    }

    public double getOccupancyEvents(){
        return occupancyEvents;
    }

    public double getMPG(){
        return MPG;
    }
}
