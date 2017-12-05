package com.seglab5.android.segandroidproject;

/**
 * Created by owotu on 2017-11-26.
 */

public class Emergency {
    public Emergency(){

    }

    String description;
    String location;
    int rank;
    public Emergency(String description, String location, int rank){
        this.description = description;
        this.location = location;
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
