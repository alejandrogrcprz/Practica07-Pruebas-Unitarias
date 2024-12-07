package es.ull.flights;  // Declaración del paquete

// Importaciones
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightTest {

    @Test
    public void testCreateValidFlight() {
        Flight flight = new Flight("AA123", 100);
        assertNotNull(flight);
        assertEquals("AA123", flight.getFlightNumber());
    }

    @Test
    public void testCreateInvalidFlight() {
        assertThrows(RuntimeException.class, () -> new Flight("A123", 100)); // Invalid flight number
    }

    @Test
    public void testAddPassenger() {
        Flight flight = new Flight("AA123", 100);
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        boolean added = flight.addPassenger(passenger);
        assertTrue(added);
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testAddPassengerNoSeats() {
        Flight flight = new Flight("AA123", 1);
        Passenger passenger1 = new Passenger("P001", "John Doe", "US");
        Passenger passenger2 = new Passenger("P002", "Jane Doe", "US");
        flight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2)); // No more seats
    }

    @Test
    public void testRemovePassenger() {
        Flight flight = new Flight("AA123", 100);
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        flight.addPassenger(passenger);
        boolean removed = flight.removePassenger(passenger);
        assertTrue(removed);
        assertEquals(0, flight.getNumberOfPassengers());
    }
}
