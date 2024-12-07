package es.ull.passengers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import es.ull.flights.Flight;

public class PassengerTest {

    @Test
    public void testCreatePassengerValidCountry() {
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        assertNotNull(passenger);
        assertEquals("US", passenger.getCountryCode());
    }

    @Test
    public void testCreatePassengerInvalidCountry() {
        assertThrows(RuntimeException.class, () -> new Passenger("P002", "Jane Doe", "XYZ")); // Invalid country code
    }

    @Test
    public void testJoinFlight() {
        Flight flight = new Flight("AA123", 100);
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testJoinAnotherFlight() {
        Flight flight1 = new Flight("AA123", 100);
        Flight flight2 = new Flight("BB456", 100);
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        passenger.joinFlight(flight1);
        assertEquals(flight1, passenger.getFlight());
        assertEquals(1, flight1.getNumberOfPassengers());

        // Now, passenger joins another flight
        passenger.joinFlight(flight2);
        assertEquals(flight2, passenger.getFlight());
        assertEquals(1, flight2.getNumberOfPassengers());
        assertEquals(0, flight1.getNumberOfPassengers());
    }

    @Test
    public void testJoinFlightFullSeats() {
        Flight flight = new Flight("AA123", 1);
        Passenger passenger1 = new Passenger("P001", "John Doe", "US");
        Passenger passenger2 = new Passenger("P002", "Jane Doe", "US");

        passenger1.joinFlight(flight);
        assertEquals(1, flight.getNumberOfPassengers());

        assertThrows(RuntimeException.class, () -> passenger2.joinFlight(flight)); // Flight is full
    }
}
