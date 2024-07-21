package bsu;

import bsu.exceptions.AppValidationException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Util {


    public static Integer isNumber(String str){
        try{
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static LocalTime calculateFlightTime(double distance, double speed) {
        double totalTime = distance / speed;
        int hours = (int) totalTime;
        int minutes = (int) ((totalTime - hours) * 60);

        return LocalTime.of(hours, minutes);
    }

    public static LocalDateTime tryParse(String time){
        LocalDateTime fromDate = null;
        try{
            fromDate = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        }catch (Exception e){
            throw new AppValidationException("The data is incorrect format!");
        }
        return fromDate;
    }


    public static double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0; // Radius of the Earth in km

        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Compute differences
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Haversine formula
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance
        return R * c;
    }
}
