package src;

public class Airline {
   public String Airline_ID;
   public String Name;
   public String Alias;
   public String IATA;
   public String ICAO;
   public String Callsign;
   public String Country;
   public String Active;

    public Airline(String airline_ID, String name,
                   String alias, String IATA,
                   String ICAO, String callsign,
                   String country, String active) {

        Airline_ID = airline_ID;
        Name = name;
        Alias = alias;
        this.IATA = IATA;
        this.ICAO = ICAO;
        Callsign = callsign;
        Country = country;
        Active = active;
    }

    public String getAirline_ID() {
        return Airline_ID;
    }

    public String getName() {
        return Name;
    }

    public String getAlias() {
        return Alias;
    }

    public String getIATA() {
        return IATA;
    }

    public String getICAO() {
        return ICAO;
    }

    public String getCallsign() {
        return Callsign;
    }

    public String getCountry() {
        return Country;
    }

    public String getActive() {
        return Active;
    }
}
