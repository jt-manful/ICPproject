package src;

/**
 * The src.Airport class is a Java class that contains all the attributes of an airport
 */
public class Airport {

    public String Airport_ID;
    public String Name;
    public String City;
    public String Country;
    public String IATA ;
    public String ICAO;
    public String Latitude;
    public String  Longitude;
    public String Altitude;
    public String Timezone;
    public String DST;
    public String Tz;
    public String Type;
    public String Source;

    public Airport(String airport_ID, String name,
                   String city, String country,
                   String IATA, String ICAO,
                   String latitude, String longitude,
                   String altitude, String timezone,
                   String DST, String tz, String type, String source) {

        Airport_ID = airport_ID;
        Name = name; City = city; Country = country;
        this.IATA = IATA; this.ICAO = ICAO; Latitude = latitude;
        Longitude = longitude; Altitude = altitude; Timezone = timezone;
        this.DST = DST; Tz = tz; Type = type; Source = source;
    }

    public String getAirport_ID() {
        return Airport_ID;
    }

    public String getName() {
        return Name;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getIATA() {
        return IATA;
    }

    public String getICAO() {
        return ICAO;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getAltitude() {
        return Altitude;
    }

    public String getTimezone() {
        return Timezone;
    }

    public String getDST() {
        return DST;
    }

    public String getTz() {
        return Tz;
    }

    public String getType() {
        return Type;
    }

    public String getSource() {
        return Source;
    }


}
