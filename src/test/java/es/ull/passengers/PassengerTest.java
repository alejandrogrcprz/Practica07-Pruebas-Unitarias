/**
 * @package es.ull.passengers
 *
 * Este paquete contiene las clases relacionadas con la gestión de pasajeros y su interacción con los vuelos.
 */

package es.ull.passengers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import es.ull.flights.Flight;

/**
 * @brief Clase de pruebas unitarias para la clase Passenger.
 *
 * Esta clase contiene métodos de prueba para verificar el comportamiento de la clase Passenger, incluyendo la validación de países y la asociación con vuelos.
 */
public class PassengerTest {

    /**
     * @brief Prueba para crear un pasajero con un código de país válido.
     *
     * Este test verifica que un pasajero con un código de país válido sea creado correctamente.
     */
    @Test
    public void testCreatePassengerValidCountry() {
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        assertNotNull(passenger);
        assertEquals("US", passenger.getCountryCode());
    }

    /**
     * @brief Prueba para crear un pasajero con un código de país inválido.
     *
     * Este test verifica que al intentar crear un pasajero con un código de país inválido, se lance una excepción.
     */
    @Test
    public void testCreatePassengerInvalidCountry() {
        assertThrows(RuntimeException.class, () -> new Passenger("P002", "Jane Doe", "XYZ")); // Invalid country code
    }

    /**
     * @brief Prueba para un pasajero que se une a un vuelo.
     *
     * Este test verifica que un pasajero pueda unirse a un vuelo y que el número de pasajeros en el vuelo se actualice adecuadamente.
     */
    @Test
    public void testJoinFlight() {
        Flight flight = new Flight("AA123", 100);
        Passenger passenger = new Passenger("P001", "John Doe", "US");
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para que un pasajero se una a un vuelo diferente.
     *
     * Este test verifica que un pasajero pueda unirse a otro vuelo y que los números de pasajeros en los vuelos se actualicen correctamente.
     */
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

    /**
     * @brief Prueba cuando un pasajero intenta unirse a un vuelo sin asientos disponibles.
     *
     * Este test verifica que al intentar que un pasajero se una a un vuelo lleno, se lance una excepción.
     */
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