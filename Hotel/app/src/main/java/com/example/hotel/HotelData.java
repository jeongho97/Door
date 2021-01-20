package com.example.hotel;

public class HotelData {
    private String hotelName;
    private String hotelAddress;
    private String hotelNumber;
    private String hotelCapacity;
    private String hotelCheckIn;
    private String hotelCheckOut;
    private String hotelPrice;
    private String hotelUserID;

    public HotelData(String hotelName, String hotelAddress, String hotelNumber, String hotelCapacity, String hotelCheckIn, String hotelCheckOut, String hotelPrice, String hotelUserID) {
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelNumber = hotelNumber;
        this.hotelCapacity = hotelCapacity;
        this.hotelCheckIn = hotelCheckIn;
        this.hotelCheckOut = hotelCheckOut;
        this.hotelPrice = hotelPrice;
        this.hotelUserID = hotelUserID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelNumber() {
        return hotelNumber;
    }

    public void setHotelNumber(String hotelNumber) {
        this.hotelNumber = hotelNumber;
    }

    public String getHotelCapacity() {
        return hotelCapacity;
    }

    public void setHotelCapacity(String hotelCapacity) {
        this.hotelCapacity = hotelCapacity;
    }

    public String getHotelCheckIn() {
        return hotelCheckIn;
    }

    public void setHotelCheckIn(String hotelCheckIn) {
        this.hotelCheckIn = hotelCheckIn;
    }

    public String getHotelCheckOut() {
        return hotelCheckOut;
    }

    public void setHotelCheckOut(String hotelCheckOut) {
        this.hotelCheckOut = hotelCheckOut;
    }

    public String getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(String hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public String getHotelUserID() {
        return hotelUserID;
    }

    public void setHotelUserID(String hotelUserID) {
        this.hotelUserID = hotelUserID;
    }

}
