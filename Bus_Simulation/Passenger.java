//written by markm014 and arora141

import java.util.Random;

public class Passenger {

    private int onStop; //Stop at which passenger boards
    private int offStop; //Stop at which passenger gets off
    private double waitTime;
    private double boardTime;
    private double deboardTime;

    public Passenger(int getOnStop){ //Tested with print statements, this constructor is AOK
        onStop = getOnStop;
        Random rand = new Random();
        offStop = rand.nextInt(13); //This and the following 2 lines of code make it twice as likely that offStop = a downtown stop
        if (offStop > 9){
            offStop = offStop - 3;
        }
        while (onStop == offStop){ //To ensure the stop on and stop off are different
            offStop = rand.nextInt(13);
            if (offStop > 9){
                offStop = offStop - 3;
            }
        }
    }

    public double getDeboardTime(){
        return deboardTime;
    }

    public void setDeboardTime(double time){
        deboardTime = time;
    }

    public double getWaitTime(){
        return waitTime;
    }

    public double getBoardTime(){
        return boardTime;
    }

    public void setWaitTime(double time){
        waitTime = time;
    }

    public void setBoardTime(double time){
        boardTime = time;
    }

    public int getOnStop(){
        return onStop;
    }

    public int getOffStop(){
        return offStop;
    }

    public static void main(String[] args){
        int total = 0;
        int counter = 0;
        int average;
        while (counter < 10000){
            Random rand = new Random();
            int holder = rand.nextInt(240) +1;
            total += holder;
            counter++;
        }
        average = total / 10000;
        System.out.println(average);
    }
}
