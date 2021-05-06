package main.java.main;

import com.gridnine.testing.FlightBuilder;
import main.java.analyzer.FlightsFilter;
import main.java.model.CustomTimeStructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        FlightsFilter flightsFilter = new FlightsFilter(
                FlightBuilder.createFlights()
        );

        System.out.println("\tInit flights:");

        flightsFilter.getFlights().forEach(System.out::println);

        System.out.println("\n- - -\n\tActual flights:");
        flightsFilter.getFlightsAfterNow().forEach(t -> System.out.println(
                "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) +
                        "](now) is before flight " +
                        t.toString()));

        System.out.println("\n- - -\n\tThese are correct flights:");
        flightsFilter.getFlightsWithCorrectSegments().forEach(System.out::println);

        System.out.println("\n- - -\n\tFlights limited by 2 hours waiting:");
        flightsFilter.getGroundCappedFlights(new CustomTimeStructure().addHours(2))
                .forEach(System.out::println);
    }
}
