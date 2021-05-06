package main.java.analyzer;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dan
 * Satatic class containing some helpful utilities
 */
public class FlightUtils {

    public static Flight getInvertedFlight(Flight flight) {

        List<Segment> invertedSegments = new ArrayList<>();
        Iterator<Segment> iterator = flight.getSegments().iterator();
        LocalDateTime landTime;

        if (flight.getSegments().isEmpty())
            return new Flight(invertedSegments);

        landTime = iterator.next().getArrivalDate();

        while (iterator.hasNext()) {
            Segment next = iterator.next();

            invertedSegments.add(
                    new Segment(landTime, next.getDepartureDate())
            );

            landTime = next.getArrivalDate();
        }

        return new Flight(invertedSegments);
    }

    public static long getDiffInSeconds(LocalDateTime from, LocalDateTime until) {

        long y = until.getYear() - from.getYear();
        long d = until.getDayOfYear() - from.getDayOfYear();

        long h = until.getHour() - from.getHour();
        long m = until.getMinute() - from.getMinute();
        long s = until.getSecond() - from.getSecond();

        return y * (long) 3.154e+7 + d * 86400 + h * 3600 + m * 60 + s;
    }

    public static long getSecondsBetween(LocalDateTime begin, LocalDateTime end) {

        return Math.abs( getDiffInSeconds(begin, end) );
    }

    public static long getOverallFlightTime(Flight flight) {

        long result = 0;

        for (Segment seg : flight.getSegments()) {
            result += Math.abs(getSecondsBetween(
                    seg.getDepartureDate(),
                    seg.getArrivalDate()
            ));
        }

        return result;
    }

    public static long getOverallGroundTime(Flight flight) {

        return getOverallFlightTime(
                getInvertedFlight(flight)
        );
    }
}
