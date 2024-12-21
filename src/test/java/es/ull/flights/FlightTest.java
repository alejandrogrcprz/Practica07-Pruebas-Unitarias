/**
 * @package es.ull.flights
 *
 * Este paquete contiene las clases relacionadas con la gestión de vuelos y pasajeros.
 */

package es.ull.flights;  // Declaración del paquete

// Importaciones
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @brief Clase de pruebas unitarias para la clase Flight.
 *
 * Esta clase contiene métodos de prueba para verificar el comportamiento de la clase Flight.
 */
public class FlightTest {

    /**
     * @brief Prueba para crear un vuelo válido.
     *
     * Este test verifica que un vuelo con un número de vuelo válido y número de asientos sea creado correctamente.
     */
    @Test
    public void testCreateValidFlight() {
        Flight flight = new Flight("AA123", 100);
        assertNotNull(flight);
        assertEquals("AA123", flight.getFlightNumber());
    }

    /**
     * @brief Prueba para crear un vuelo inválido.
     *
     * Este test verifica que al intentar crear un vuelo con un número de vuelo inválido, se lance una excepción.
     */
    @Test
    public void testCreateInvalidFlight() {
        assertThrows(RuntimeException.class, () -> new Flight("A123", 100)); // Invalid flight number
    }

    /**
     * @brief Prueba para añadir un pasajero a un vuelo.
     *
     * Este test verifica que un pasajero se pueda añadir correctamente a un vuelo y que el número de pasajeros se actualice adecuadamente.
     */
    @Test
    public void testAddPassenger() {
        Flight flight = new Flight("AA123", 100);
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        boolean added = flight.addPassenger(passenger);
        assertTrue(added);
        assertEquals(1, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para intentar añadir un pasajero cuando no hay asientos disponibles.
     *
     * Este test verifica que al intentar añadir un pasajero cuando no hay asientos disponibles en el vuelo, se lance una excepción.
     */
    @Test
    public void testAddPassengerNoSeats() {
        Flight flight = new Flight("AA123", 1);
        Passenger passenger1 = new Passenger("P001", "John Doe", "US");
        Passenger passenger2 = new Passenger("P002", "Jane Doe", "US");
        flight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2)); // No more seats
    }

    /**
     * @brief Prueba para eliminar un pasajero de un vuelo.
     *
     * Este test verifica que un pasajero se pueda eliminar correctamente de un vuelo y que el número de pasajeros se actualice adecuadamente.
     */
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