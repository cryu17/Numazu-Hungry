package com.example.numazu;

public class DataHolder {
    private static String type;
    private static String type_locale;
    private static String location;
    private static String location_locale;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        DataHolder.type = type;
    }

    public static String getTypeLocale() {
        return type_locale;
    }

    public static void setTypeLocale(String type_locale) {
        DataHolder.type_locale = type_locale;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        DataHolder.location = location;
    }

    public static String getLocationLocale() {
        return location_locale;
    }

    public static void setLocationLocale(String location_locale) {
        DataHolder.location_locale = location_locale;
    }
}