package main.java.analyzer;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import main.java.model.CustomTimeStructure;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @author dan
 * Flight filter module that generates flight lists according to designated rules
 */
public class FlightsFilter {
    List<Flight> flights;

    public FlightsFilter(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    /**
     * Don't miss your next flight!
     * @return list of flights after current machine timestamp
     */
    public List<Flight> getFlightsAfterNow() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Flight> resultFlights = new ArrayList<>();

        if (flights.isEmpty())
            return resultFlights;

        flights.forEach(flight -> {
            LocalDateTime flightStart = flight.getSegments().get(0).getDepartureDate();

            if (currentTime.compareTo(flightStart) < 0)
                resultFlights.add(new Flight(flight));
        });

        return resultFlights;
    }

    /**
     * Excludes incorrect flights
     * @return list of flights without flights, containing incorrect segments
     */
    public List<Flight> getFlightsWithCorrectSegments() {
        List<Flight> resultFlights = new ArrayList<>();

        flights.forEach(flight -> {
            for (Segment seg : flight.getSegments()) {
                if (seg.getArrivalDate().compareTo(seg.getDepartureDate()) < 0)
                    return;
            }

            resultFlights.add(new Flight(flight));
        });

        return resultFlights;
    }

    /**
     * Exclude flights containing annoying changes
     * @param capTime - waiting for the next segment time limit
     * @return list of flights which won't make you wait for long
     */
    public List<Flight> getGroundCappedFlights(CustomTimeStructure capTime) {
        List<Flight> resultFlights = new ArrayList<>();

        flights.forEach(flight -> {
            long overallGroundTime = FlightUtils.getOverallGroundTime(flight);

            if (overallGroundTime < capTime.getSeconds())
                resultFlights.add(new Flight(flight));
        });

        return resultFlights;
    }
}
