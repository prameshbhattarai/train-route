package crea.train.route.analyzer;

import crea.train.route.contract.Route;

import java.util.*;

/**
 * @author pramesh-bhattarai
 */
public final class Analyzer {

    private final List<Route> routes;

    private final Set<String> settledStations;
    private final Set<String> unSettledStations;

    private final Map<String, String> predecessors;
    private final Map<String, Integer> timeTaken;

    private final String source;
    private final String destination;

    public Analyzer(List<Route> routes, String source, String destination) {
        this.routes = new ArrayList<>(routes);
        this.source = source;
        this.destination = destination;

        this.settledStations = new HashSet<>();
        this.unSettledStations = new HashSet<>();

        this.timeTaken = new HashMap<>();
        this.predecessors = new HashMap<>();
    }

    public void analyze() {
        checkIfSourceExist();

        while (this.unSettledStations.size() > 0) {

            String station = getNearestUnsettledStationByTime();

            this.settledStations.add(station);
            this.unSettledStations.remove(station);

            findUnsettledAdjacentStations(station);
        }
    }

    public int getTimeTaken() {
        Integer timeTaken = this.timeTaken.get(this.destination);
        return timeTaken == null ? -1 : timeTaken;
    }

    public int getNumberOfStops() {
        String predecessors = this.predecessors.get(this.destination);
        if (predecessors == null) {
            return 0;
        }

        int stop = 0;
        while (this.predecessors.get(predecessors) != null) {
            ++stop;
            predecessors = this.predecessors.get(predecessors);
        }
        return stop;
    }

    public List<String> getRoutePath(String destination) {

        List<String> routes = new ArrayList<>();
        String current = destination;
        // check if a current exists
        if (this.predecessors.get(current) == null) {
            return null;
        }
        routes.add(current);
        while (this.predecessors.get(current) != null) {
            current = this.predecessors.get(current);
            routes.add(current);
        }
        // Put it into the correct order
        Collections.reverse(routes);

        return routes;
    }

    private void checkIfSourceExist() {
        for (Route route : this.routes) {
            if (route.getSource().equals(this.source)) {
                this.timeTaken.put(this.source, 0);
                this.unSettledStations.add(this.source);
                break;
            }
        }
    }

    // this is our priority queue
    private String getNearestUnsettledStationByTime() {
        String temp = null;
        for (String unsettledStation : this.unSettledStations) {
            if (temp == null) {
                temp = unsettledStation;
            } else if (getShortestTime(unsettledStation) < getShortestTime(temp)) {
                temp = unsettledStation;
            }
        }
        return temp;
    }

    private int getShortestTime(String destination) {
        Integer d = this.timeTaken.get(destination);
        return (d == null) ? Integer.MAX_VALUE : d;
    }

    private void findUnsettledAdjacentStations(String station) {
        List<String> unsettledAdjacentStations = getUnsettledAdjacentStations(station);

        for (String target : unsettledAdjacentStations) {

            int previousDistanceToTarget = getShortestTime(target);
            int newDistanceToTarget = getShortestTime(station) + getTime(station, target);

            if (newDistanceToTarget < previousDistanceToTarget) {
                // updated timeTaken
                this.timeTaken.put(target, newDistanceToTarget);
                this.predecessors.put(target, station);

                this.unSettledStations.add(target);
            }
        }

    }

    private int getTime(String source, String destination) {
        for (Route route : this.routes) {
            if (route.getSource().equals(source) && route.getDestination().equals(destination)) {
                return route.getTimeTaken();
            }
        }
        throw new IllegalStateException(String.format("Source: %s and Destination: %s not found in routes", source, destination));
    }

    private List<String> getUnsettledAdjacentStations(String station) {
        List<String> unsettledAdjacentStations = new ArrayList<>();
        for (Route route : this.routes) {

            String source = route.getSource();
            String destination = route.getDestination();

            if (source.equals(station) && !this.settledStations.contains(destination)) {
                unsettledAdjacentStations.add(destination);
            }
        }
        return unsettledAdjacentStations;
    }

    private void print() {
        System.out.println("---------------");
        System.out.println("settled stations" + this.settledStations.toString());
        System.out.println("un settled stations" + this.unSettledStations.toString());
        System.out.println("time taken " + this.timeTaken.toString());
        System.out.println("predecessors" + this.predecessors.toString());
        System.out.println("---------------");
    }

}
