package src;

public class Route {
    public String Airline_code;
    public String Airline_ID;
    public String Source_airport_code;
    public String Source_airport_ID;
    public String Destination_airport_code;
    public String Destination_airport_ID;
    public String Codeshare;
    public String Stops;
    public String Equipment  ;

    public Route(String airline_code, String airline_ID,
                 String source_airport_code,
                 String source_airport_ID,
                 String destination_airport_code,
                 String destination_airport_ID,
                 String codeshare, String stops, String equipment) {

        Airline_code = airline_code;
        Airline_ID = airline_ID;
        Source_airport_code = source_airport_code;
        Source_airport_ID = source_airport_ID;
        Destination_airport_code = destination_airport_code;
        Destination_airport_ID = destination_airport_ID;
        Codeshare = codeshare;
        Stops = stops;
        Equipment = equipment;
    }

    /**
     * This function returns the airline code of the flight
     *
     * @return The airline code is being returned.
     */
    public String getAirline_code() {
        return Airline_code;
    }

    public String getAirline_ID() {
        return Airline_ID;
    }

    public String getSource_airport_code() {
        return Source_airport_code;
    }

    public String getSource_airport_ID() {
        return Source_airport_ID;
    }

    public String getDestination_airport_code() {
        return Destination_airport_code;
    }

    public String getDestination_airport_ID() {
        return Destination_airport_ID;
    }

    public String getCodeshare() {
        return Codeshare;
    }

    public String getStops() {
        return Stops;
    }

    public String getEquipment() {
        return Equipment;
    }
}
