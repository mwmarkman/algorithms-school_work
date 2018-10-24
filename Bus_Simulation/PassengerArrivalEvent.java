import java.util.Random;

public class PassengerArrivalEvent implements IEvent {

    private int stop;

    public PassengerArrivalEvent(int stop) {
        this.stop = stop;
    }

    public int getStop() {
        return stop;
    }

    public void run() {
        Passenger a = new Passenger(stop);
        a.setWaitTime(BusSim.agenda.getCurrentTime()); //Line currently being messed around with
        if (a.getOnStop() < a.getOffStop() || a.getOnStop() == 9) { //This block is to add passenger to appropriate westbound vs eastbound line
            BusSim.lines[a.getOnStop()].add(a);
        } else if (a.getOnStop() > a.getOffStop()) {
            if (a.getOnStop() == 1) {
                BusSim.lines[17].add(a);
            } else if (a.getOnStop() == 2) {
                BusSim.lines[16].add(a);
            } else if (a.getOnStop() == 3) {
                BusSim.lines[15].add(a);
            } else if (a.getOnStop() == 4) {
                BusSim.lines[14].add(a);
            } else if (a.getOnStop() == 5) {
                BusSim.lines[13].add(a);
            } else if (a.getOnStop() == 6) {
                BusSim.lines[12].add(a);
            } else if (a.getOnStop() == 7) {
                BusSim.lines[11].add(a);
            } else if (a.getOnStop() == 8) {
                BusSim.lines[10].add(a);
            }
        }
        Random rand = new Random();
        double randTimeNormal = rand.nextInt(240 + ((int) BusSim.randFactorNorm * 2)) + 1; //A simple way to average passenger arrival at 120 seconds
        double randTimeDowntown = rand.nextInt(160 + ((int) BusSim.randFactorDown *2))+1;
        if (stop < 7){
            BusSim.agenda.add(new PassengerArrivalEvent(stop), randTimeNormal);
        }
        else{
            BusSim.agenda.add(new PassengerArrivalEvent(stop), randTimeDowntown);
        }
    }
}
