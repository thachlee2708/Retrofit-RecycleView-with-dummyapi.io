package com.example.hocretrofit.model;

public class Location {
    String street;
    String city;
    String state;
    String country;
    String timezone;

    public Location() {
    }

    public Location(String street, String city, String state, String country, String timezone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.timezone = timezone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", timezone='" + timezone;
    }
}
