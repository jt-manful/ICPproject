package src;// distance functions from - https://dzone.com/articles/distance-calculation-using-3
import java.io.*;
import java.util.*;


public class Runner {
    static HashMap<String, List<Airport>> airports = new HashMap<>();
    static HashMap<String, List<Airline>> airlines = new HashMap<>();
    static HashMap<String, List<Route>> routes = new HashMap<>();
    static HashMap<String, List<String>> airport_adjacency_list = new HashMap<>();
    static HashMap<String, String> active_flights = new HashMap<>();
    static List<Airport> all_airports = new ArrayList<>();


    /**
     * This function reads in the airport data from the csv file and creates an src.Airport object for each airport. It then
     * adds the src.Airport object to the all_airports ArrayList and adds it to the airports HashMap
     *
     * @param csv_file the file path of the csv file
     */
    public static void airport_reader(String csv_file) throws IOException {
        String ap_name = "";
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader(csv_file));

        }catch (IOException e){
            System.out.println("error reading file");
            System.exit(0);
        }
        String line = inputStream.readLine();

        while (line != null) {
            if (line.contains("\"")) {
                ap_name = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
                line = line.substring(0, line.indexOf("\"")) + line.substring(line.lastIndexOf("\"") + 1);

            }

            String[] data = line.split(",");
            String airport_ID = data[0];
            String name = ap_name.isEmpty() ? data[1] : ap_name;
            String city = data[2];
            String country = data[3];
            String IATA = data[4];
            String ICAO = data[5];
            String latitude = (data[6]);
            String longitude = (data[7]);
            String altitude = (data[8]);
            String timezone = (data[9]);
            String DST = data[10];
            String tz = data[11];
            String type = data[12];
            String source = data[13];

            Airport new_airport = new Airport(airport_ID, name, city,
                    country, IATA, ICAO, latitude, longitude, altitude,
                    timezone, DST, tz, type, source);

            all_airports.add(new_airport);

            if (airports.containsKey(new_airport.City)) {
                airports.get(new_airport.City).add(new_airport);
            } else {
                ArrayList<Airport> new_airport_list = new ArrayList<>();
                new_airport_list.add(new_airport);
                airports.put(new_airport.City, new_airport_list);
            }
            line = inputStream.readLine();
        }
        inputStream.close();

    }


    /**
     * This function reads in the airline data from the csv file and creates a new airline object for each line in the
     * file. It then adds the airline object to the airlines hashmap
     *
     * @param csv_file the file path of the csv file
     */
    public static void airline_reader(String csv_file) throws IOException {

        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader(csv_file));
        }catch (IOException e){
            System.out.println("error reading file");
            System.exit(0);
        }

        String line = inputStream.readLine();

        while (line != null) {

            String[] data = line.split(",");

            String airline_ID = data[0];
            String name = data[1];
            String alias = data[2];
            String IATA = data[3];
            String ICAO = data[4];
            String callsign = data[5];
            String country = data[6];
            String active = data[7];

            Airline new_airline = new Airline(airline_ID, name, alias, IATA,
                    ICAO, callsign, country, active);

            if (airlines.containsKey(new_airline.Airline_ID)) {
                airlines.get(new_airline.Airline_ID).add(new_airline);
            } else {
                ArrayList<Airline> new_airline_list = new ArrayList<>();
                new_airline_list.add(new_airline);
                airlines.put(new_airline.Airline_ID, new_airline_list);
            }

            if (new_airline.getActive().equals("Y")){
                active_flights.put(new_airline.getAirline_ID(), new_airline.getIATA());
            }

                line = inputStream.readLine();
            }

        inputStream.close();
        }


    /**
     * This function reads the routes.csv file and creates a HashMap of all the routes that are possible from a given
     * airport. It also creates a HashMap of all the possible destinations from a given airport
     *
     * @param csv_file the file path of the csv file containing the routes
     */
    public static void route_reader(String csv_file) throws IOException {
        String Eq = "";
        BufferedReader inputStream =null;
       try{

        inputStream = new BufferedReader(new FileReader(csv_file));

       }catch (IOException e){

           System.out.println("error reading file");
           System.exit(0);
       }

        String line = inputStream.readLine();

        while (line != null) {

            String[] data = line.split(",");
            if (line.split(",").length < 9)
                Eq = "/N";

            String airline_code = data[0];
            String airline_ID = data[1];
            String source_airport_code = data[2];
            String source_airport_ID = data[3];
            String destination_airport_code = data[4];
            String destination_airport_ID = data[5];
            String codeshare = data[6];
            String stops = data[7];
            String equipment = Eq.isEmpty() ? data[8] : Eq;

            Route new_route = new Route(airline_code, airline_ID,
                    source_airport_code, source_airport_ID,
                    destination_airport_code, destination_airport_ID,
                    codeshare, stops, equipment);

            if (routes.containsKey(new_route.Source_airport_code)) {
                routes.get(new_route.Source_airport_code).add(new_route);
            } else {
                ArrayList<Route> new_route_list = new ArrayList<>();
                new_route_list.add(new_route);
                routes.put(new_route.Source_airport_code, new_route_list);
            }

            if (airport_adjacency_list.containsKey(new_route.Source_airport_ID)){
                airport_adjacency_list.get(new_route.Source_airport_ID).add(new_route.Destination_airport_ID);

            }else{
                ArrayList<String> possible_destinations = new ArrayList<>();
                possible_destinations.add(new_route.Destination_airport_ID);
                airport_adjacency_list.put(new_route.Source_airport_ID, possible_destinations);
            }




            line = inputStream.readLine();
        }
        inputStream.close();


    }


    /**
     * This function reads the text file and returns the start city, start country, destination city, and destination
     * country
     *
     * @param txt_file The name of the text file that contains the start and destination cities.
     * @return an array of strings.
     */
    public static String[] read_from_file(String txt_file) throws IOException {
        BufferedReader inputStream = null;

        try{
            inputStream = new BufferedReader(new FileReader(txt_file));
        }catch (IOException e){

            System.out.println("error reading file");
            System.exit(0);
        }

        String line = inputStream.readLine();
        ArrayList<String> city = new ArrayList<>();
        ArrayList<String> country = new ArrayList<>();

        while (line != null) {

            city.add(line.split(",")[0]);
            country.add(line.split(",")[1]);

            line = inputStream.readLine();
        }
        String start_city = city.get(0);
        String dest_city = city.get(1);
        String start_country = country.get(0);
        String dest_country = country.get(1);

        return new String[]{start_city, start_country, dest_city, dest_country};


    }


    /**
     * This function takes in the list of all possible flight paths, the start city and the destination city and writes the
     * output to a file
     *
     * @param trips This is the list of all the possible trips from the start city to the destination city.
     * @param start_city The city from which the user wants to start the trip.
     * @param dest_city The destination city
     */
    public static void write_to_file(ArrayList<List<String>>trips, String start_city, String dest_city ){
        int stops;
        int total_stops_count = 0;
        int start_index = 0;
        int dest_index = 1;

        PrintWriter outputStream = null;
        String airline_code ;


        try{
            outputStream =
                    new PrintWriter( new FileOutputStream(start_city + "-" + dest_city + "_output.txt"));
        }
        catch (FileNotFoundException e){
            System.out.println("Error opening the file");
            System.exit(0);
        }

        for(List<String> flight_path : trips){
            int count = 1;
            while(dest_index != flight_path.size()) {
                String start = flight_path.get(start_index);
                String dest = flight_path.get(dest_index);


                airline_code = get_route_code(start,dest).get(0);
                stops = Integer.parseInt(get_route_code(start,dest).get(1));


                outputStream.println("      "+count + " " + airline_code + " from " + start + " to " + dest + " " + stops + " stops.");
                total_stops_count += stops;
                count += 1;

                start_index += 1;
                dest_index += 1;
            }
            outputStream.println("Total flights: "+ start_index) ;
            outputStream.println("Total additional stops: "+ total_stops_count) ;
            outputStream.println("Total distance: "+ Math.round(get_route_distance(flight_path)) + " Km");
            outputStream.println("Optimality criteria : Total Distance") ;
            outputStream.close();
        }
    }


    /**
     * Given a city, return a list of airports in that city.
     *
     * @param city The city name
     * @return A list of airports in the city.
     */
    public static List<Airport> get_airport(String city) {return airports.get(city);}


    /**
     * This function checks if the flight is active or not
     *
     * @param airline_id The unique identifier for the airline.
     * @return The method is_active_flight returns a boolean value.
     */
    public  static boolean is_active_flight(String airline_id){
        return active_flights.containsKey(airline_id);

    }


    /**
     * The distance between two points on the surface of a sphere is the arc length of the great circle path between them
     *
     * @param lat1 Latitude of point 1 (in decimal degrees)
     * @param lon1 longitude of the first point
     * @param lat2 Latitude of point 2 (in decimal degrees)
     * @param lon2 longitude of the second point
     * @return The distance between two points in kilometers.
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }


    /**
     * Convert degrees to radians.
     *
     * @param deg degree value of a coordinate
     * @return The distance between two points on the earth.
     */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);}


    /**
     * Converts radians to degrees.
     *
     * @param rad The radius of the Earth in kilometers.
     * @return The distance between two points on the earth.
     */
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    /**
     * This function takes in two strings, start_city and dest_city, and returns a list of strings containing the airline
     * code and number of stops for the route between the two cities
     *
     * @param start_city The city you want to start from
     * @param dest_city The destination city
     * @return The airline code and the number of stops.
     */
    public static List<String> get_route_code(String start_city, String dest_city) {
        List<String> route_info = new ArrayList<>();
        for (Map.Entry<String, List<Route>> entry : routes.entrySet()) {
            for(Route route: entry.getValue()){
                if (route.Source_airport_code.equals(start_city) && route.Destination_airport_code.equals(dest_city)){
                    route_info.add(route.getAirline_code());
                    route_info.add(route.getStops());

                    return route_info;
                }
            }
        }
        return null;
    }


    /**
     * This function takes in a list of IATA codes and returns the total distance of the route
     *
     * @param possible_path a list of IATA codes of airports that are in the order of the path
     * @return The total distance of the flight path.
     */
    public static double get_route_distance(List<String> possible_path){
        int start = 0;
        int dest = 1;
        double start_lat = 0;
        double start_long = 0;
        double dest_lat = 0;
        double dest_long = 0;

        double flight_distance = 0;

        while(dest < possible_path.size()){
            String start_IATA = possible_path.get(start);
            String dest_IATA = possible_path.get(dest);

            for (Airport airport : all_airports) {
                if (airport.getIATA().equals(start_IATA)) {
                    start_lat = Double.parseDouble(airport.Latitude);
                    start_long = Double.parseDouble(airport.Longitude);
                }
            }

            for (Airport airport : all_airports){
                if (airport.getIATA().equals(dest_IATA)){
                    dest_lat = Double.parseDouble(airport.Latitude);
                    dest_long = Double.parseDouble(airport.Longitude);
                }    }

            flight_distance += distance(start_lat,start_long, dest_lat,dest_long);
            start += 1;
            dest += 1;


        }

        return flight_distance;
    }


    /**
     * This function takes in a list of possible paths and returns the optimal path
     *
     * @param possible_paths_list a list of lists of strings, where each list of strings represents a possible path from
     * the starting point to the destination.
     * @return The optimal path is being returned.
     */
    public static List<List<String>> find_optimal_path(List<List<String>> possible_paths_list){

        List<List<String>>optimal_path = new ArrayList<>();
        optimal_path.add(possible_paths_list.get(0));

        double optimal_distance = get_route_distance(possible_paths_list.get(0));

        if (possible_paths_list.size() == 1){
            return optimal_path;
        }

        for(List<String> path : possible_paths_list ) {
            if (get_route_distance(path) < optimal_distance){
                optimal_distance = get_route_distance(path);
                optimal_path.remove(0);
                optimal_path.add(path);
            }
        }
        return optimal_path;
    }

    public static void main(String[] args) throws IOException {

        String start_city = read_from_file("Accra-Winnipeg.txt")[0];
        String dest_city = read_from_file("Accra-Winnipeg.txt")[2];


        // Step1: parsing the datafiles
        airport_reader("airports.csv");
        airline_reader("airlines.csv");
        route_reader("routes.csv");
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a file name: ");
        System.out.println("Format (StartCity-DestCity.txt)");
        System.out.flush();
        String filename = scanner.nextLine();
        read_from_file(filename);


        // Step 2: Query data for trip
        List<Airport> start_port_list = get_airport(start_city);
        List<Integer> airport_in_start_city = new ArrayList<>();
        for(Airport airport : start_port_list) {

            airport_in_start_city.add(Integer.parseInt(airport.Airport_ID));
        }

        List<Airport> dest_port_list = get_airport(dest_city);
        List<Integer> airport_in_dest_city = new ArrayList<>();
        for(Airport airport : dest_port_list) {
            airport_in_dest_city.add(Integer.parseInt(airport.Airport_ID));
        }


        // Step 3 src.Graph and searching algorithm
        Graph graph = new Graph();

        // add vetices
        for (Map.Entry<String, List<Airport>> ap : airports.entrySet()) {
            for (Airport airport : ap.getValue()) {
                if ( !airport.IATA.contains("\\")) {
                    int airport_id = Integer.parseInt(airport.Airport_ID);
                    String IATA = airport.IATA;
                    graph.addVertex(airport_id, IATA);
                }
            }
        }


        // add edges
        for (Map.Entry<String, List<Route>> rt : routes.entrySet()) {
            for (Route route : rt.getValue()) {

                if(is_active_flight(route.getAirline_ID()) &&
                        !(route.Source_airport_ID.contains("\\")) &&
                        !(route.Destination_airport_ID.contains("\\")))
                {
                    Integer start_id = Integer.parseInt(route.Source_airport_ID);
                    Integer dest_id = Integer.parseInt(route.Destination_airport_ID);

                    if (!start_id.equals(dest_id)) {
                        graph.addEdge(start_id, dest_id,1);
                    }
                }

            }

        }

        ArrayList<List<String>> trips  = new ArrayList<>();
        for(int start_airport : airport_in_start_city ){
            for (int dest_airport : airport_in_dest_city){
                if (!trips.contains(graph.searchAlgorithm(start_airport,dest_airport))){
                    trips.add(graph.searchAlgorithm(start_airport,dest_airport));
                    trips.remove(null);
                }
            }
        }

         //Step 4 write to file
        write_to_file((ArrayList<List<String>>) find_optimal_path(trips), start_city, dest_city);



    }
}
