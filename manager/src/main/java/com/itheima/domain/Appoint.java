package com.itheima.domain;
import java.io.Serializable;

public class Appoint implements Serializable {
    private Integer id;        //记录id
    private String roomname;   //预约的自习室名称
    private String location;   //预约的自习室位置
    private String appointment;   //预约人
    private String appointTime; //预约时间

    public Appoint() {
    }

    public Appoint(Object o, String name, String location, String appointment, String appointTime) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }
}
