package com.drusp.myconnect.models;

public class Status {

    private String statusId;
    private String temperature;
    private String himidity;


    public Status() {
    }

    public Status(String statusId, String temperature, String himidity) {
        this.statusId = statusId;
        this.temperature = temperature;
        this.himidity = himidity;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHimidity() {
        return himidity;
    }

    public void setHimidity(String himidity) {
        this.himidity = himidity;
    }
}
