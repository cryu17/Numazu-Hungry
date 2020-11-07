package com.example.numazu;

public class ListData {

    private final String name;
    private final String menu;
    private final String location;
    private final String address_jp;
    private final String address;
    private final String day;
    private final String type;
    private final int ohour;
    private final int ominute;
    private final int chour;
    private final int cminute;
    private final int rshour;
    private final int rsminute;
    private final int rehour;
    private final int reminute;
    private final String longitude;
    private final String latitude;

    public ListData(String name, String type, String location, String menu, String address_jp, String address, String day, String ohour, String ominute, String chour, String cminute, String rshour, String rsminute, String rehour, String reminute, String longitude, String latitude) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.menu = menu;
        this.address_jp = address_jp;
        this.address = address;
        this.day = day;
        this.ohour = Integer.parseInt(ohour);
        this.ominute = Integer.parseInt(ominute);
        this.chour = Integer.parseInt(chour);
        this.cminute = Integer.parseInt(cminute);
        this.rshour = Integer.parseInt(rshour);
        this.rsminute = Integer.parseInt(rsminute);
        this.rehour = Integer.parseInt(rehour);
        this.reminute = Integer.parseInt(reminute);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public ListData() {
        this.name = "";
        this.type = "";
        this.location = "";
        this.menu = "";
        this.address_jp = "";
        this.address = "";
        this.day = "";
        this.ohour = 0;
        this.ominute = 0;
        this.chour = 0;
        this.cminute = 0;
        this.rshour = 0;
        this.rsminute = 0;
        this.rehour = 0;
        this.reminute = 0;
        this.longitude = "0.0";
        this.latitude = "0.0";
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getMenu() {
        return menu;
    }

    public String getAddress_jp() {
        return address_jp;
    }

    public String getAddress() {
        return address;
    }

    public String getDay() {
        return day;
    }

    public int getOhour() {
        return ohour;
    }

    public int getOminute() {
        return ominute;
    }

    public int getChour() {
        return chour;
    }

    public int getCminute() {
        return cminute;
    }

    public int getRshour() {
        return rshour;
    }

    public int getRsminute() {
        return rsminute;
    }

    public int getRehour() {
        return rehour;
    }

    public int getReminute() {
        return reminute;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}