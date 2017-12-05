package com.seglab5.android.segandroidproject;

/**
 * Created by owotu on 2017-12-04.
 */

public class FlightSchedule {
    String flightID;
    String boarddingTime;
    String arrivalTime;
    String Destination;

    public FlightSchedule(String flightID, String boarddingTime, String arrivalTime,String Destination) {
        this.flightID = flightID;
        this.boarddingTime = boarddingTime;
        this.arrivalTime = arrivalTime;
        this.Destination = Destination;
    }

    public FlightSchedule(){

    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getBoarddingTime() {
        return boarddingTime;
    }

    public void setBoarddingTime(String boarddingTime) {
        this.boarddingTime = boarddingTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDestination(){
        return this.Destination;
    }

    public void setDestination(String destination){
        this.Destination = destination;
    }
}
