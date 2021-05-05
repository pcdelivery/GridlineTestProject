package main.java.main;

import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Flight;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flightList = FlightBuilder.createFlights();

        for (Flight f : flightList) {
            System.out.println(f.toString());
        }
    }
}
