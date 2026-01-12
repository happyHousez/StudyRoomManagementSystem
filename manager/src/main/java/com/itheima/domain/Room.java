package com.itheima.domain;

import java.io.Serializable;

public class Room implements Serializable
{
    private Integer id;          // id
    private String name;        // 名称
    private String  location;  //  位置
    private int capacity;     //  可容纳人数
    private String phone;     //  电话
    private String status;   // 状态： 0：空闲，1：占用，2：维修
    private String openTime;  //开放时间
    private String closeTime;    // 关闭时间
    private String appointment;  // 预约人
    private String appointtime;  // 预约时间
    private String description;   // 描述
    private Integer isHot;     // 是否热门 0：否，1：是

    public Room() {
    }

    public Room(Integer id, String name, String location, int capacity, String phone, String status, String openTime, String closeTime, String appointment, String appointtime, String description, Integer isHot) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.phone = phone;
        this.status = status;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.appointment = appointment;
        this.appointtime = appointtime;
        this.description = description;
        this.isHot = isHot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getAppointTime() {
        return appointtime;
    }

    public void setAppointTime(String appointtime) {
        this.appointtime = appointtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", openTime='" + openTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", appointment='" + appointment + '\'' +
                ", appointtime='" + appointtime + '\'' +
                ", description='" + description + '\'' +
                ", isHot=" + isHot +
                '}';
    }
}
