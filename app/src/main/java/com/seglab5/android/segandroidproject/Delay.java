package com.seglab5.android.segandroidproject;

/**
 * Created by owotu on 2017-11-26.
 */

public class Delay {
    public Delay(){

    }

    String FlightNumber;
    String waitime;
    public Delay(String FlightNumber, String waittime){
        this.FlightNumber = FlightNumber;
        this.waitime = waittime;
    }

    public String getFlightNumber(){
        return this.FlightNumber;
    }

    public String getWaitime(){
        return this.waitime;
    }

    public void setFlightNumber(String fn){
        this.FlightNumber = fn;
    }

    public void setWaitime(String wt){
        this.waitime = wt;
    }
}
