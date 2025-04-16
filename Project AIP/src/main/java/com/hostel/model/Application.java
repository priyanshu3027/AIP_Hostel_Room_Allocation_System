package com.hostel.model;

public class Application {
    private int appId;
    private int studentId;
    private int roomId;
    private String status;

    public Application() {
    }

    public Application(int appId, int studentId, int roomId, String status) {
        this.appId = appId;
        this.studentId = studentId;
        this.roomId = roomId;
        this.status = status;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
} 