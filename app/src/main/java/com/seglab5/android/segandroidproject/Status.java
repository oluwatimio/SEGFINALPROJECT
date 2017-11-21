package com.seglab5.android.segandroidproject;

/**
 * Created by owotu on 2017-11-20.
 */

public class Status {
    String uid;
    String email;
    String status;
    public Status(String uid, String email, String status){
        this.uid = uid;
        this.email = email;
        this.status = status;
    }

    public String getUid(){
        return this.uid;
    }

    public String getEmail(){
        return this.email;
    }

    public String getStatus(){
        return this.status;
    }
}
