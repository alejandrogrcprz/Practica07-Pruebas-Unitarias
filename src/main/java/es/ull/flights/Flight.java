/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/**
 * @class Flight
 * @brief Clase que modela un vuelo con capacidad limitada de pasajeros.
 */
public class Flight {

    /**
     * @brief The flight number in the format AA1234.
     */
    private String flightNumber;

    /**
     * @brief The total number of seats available on the flight.
     */
    private int seats;

    /**
     * @brief A set of passengers currently on the flight.
     */
    private Set<Passenger> passengers = new HashSet<>();

    /**
     * @brief Regular expression for validating flight numbers.
     */
    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";

    /**
     * @brief Compiled pattern for flight number validation.
     */
    private static Pattern pattern = Pattern.compile(flightNumberRegex);

    /**
     * @brief Constructor para inicializar un vuelo.
     * @param flightNumber Número del vuelo. Debe cumplir con el formato definido por la expresión regular.
     * @param seats Número máximo de asientos disponibles en el vuelo.
     * @throws RuntimeException Si el número del vuelo no es válido.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Obtiene el número del vuelo.
     * @return Número del vuelo como cadena.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Obtiene el número actual de pasajeros en el vuelo.
     * @return Número de pasajeros en el vuelo.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Añade un pasajero al vuelo.
     * @param passenger Pasajero que se desea añadir al vuelo.
     * @return true si el pasajero se añadió con éxito, false en caso contrario.
     * @throws RuntimeException Si no hay suficientes asientos disponibles.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Elimina un pasajero del vuelo.
     * @param passenger Pasajero que se desea eliminar del vuelo.
     * @return true si el pasajero se eliminó con éxito, false en caso contrario.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}