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
/**
 * @file Passenger.java
 * @brief Clase que representa a un pasajero con identificador, nombre, código de país y vuelo asociado.
 */

package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.flights.Flight;

/**
 * @class Passenger
 * @brief Clase que modela un pasajero con información personal y su relación con un vuelo.
 */
public class Passenger {

    /**
     * @brief Identificador único del pasajero.
     *
     * Este atributo almacena un valor que sirve para identificar de manera única al pasajero en el sistema.
     */
    private String identifier; /**< Identificador único del pasajero. */

    /**
     * @brief Nombre del pasajero.
     *
     * Este atributo contiene el nombre completo del pasajero.
     */
    private String name; /**< Nombre del pasajero. */

    /**
     * @brief Código de país del pasajero en formato ISO.
     *
     * Este atributo almacena el código del país del pasajero, en formato de dos letras según el estándar ISO 3166-1.
     */
    private String countryCode; /**< Código de país del pasajero en formato ISO. */

    /**
     * @brief Vuelo al que está asociado el pasajero.
     *
     * Este atributo hace referencia al objeto de la clase Flight que representa el vuelo al cual el pasajero está vinculado.
     */
    private Flight flight; /**< Vuelo al que está asociado el pasajero. */

    /**
     * @brief Constructor para inicializar un pasajero.
     * @param identifier Identificador único del pasajero.
     * @param name Nombre del pasajero.
     * @param countryCode Código de país del pasajero en formato ISO.
     * @throws RuntimeException Si el código de país no es válido.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Obtiene el identificador único del pasajero.
     * @return Identificador del pasajero.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Obtiene el nombre del pasajero.
     * @return Nombre del pasajero.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Obtiene el código de país del pasajero.
     * @return Código de país del pasajero en formato ISO.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Obtiene el vuelo al que está asociado el pasajero.
     * @return Vuelo del pasajero.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Asocia al pasajero con un vuelo.
     * Si el pasajero ya está asociado a otro vuelo, primero lo elimina de ese vuelo.
     * @param flight Vuelo al que se desea asociar al pasajero.
     * @throws RuntimeException Si no se puede eliminar al pasajero del vuelo anterior
     *                          o añadirlo al nuevo vuelo.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Asigna un vuelo al pasajero.
     * @param flight Vuelo que se asignará al pasajero.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Representa al pasajero como una cadena de texto.
     * @return Una cadena con la información del pasajero: nombre, identificador y código de país.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}