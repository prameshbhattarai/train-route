package crea.train.route.utils;

import crea.train.route.contract.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pramesh-bhattarai
 */
public final class RoutePlanLoader {

    private RoutePlanLoader() {}

    public static List<Route> getRoutePlans(String text) {
        try {
            List<Route> routes = new ArrayList<>();
            String[] eachRoutePlan = text.split("\n");

            for(String routePlan : eachRoutePlan) {
                String[] route = routePlan.split(",");
                String source = route[0];
                String destination = route[1];
                int timeTaken = Integer.parseInt(route[2]);

                routes.add(new Route(source, destination, timeTaken));
            }

            return routes;
        } catch (Exception e) {
            String errorMessage = String.format("Unable to load route plan from csv file, error: %s", e.getMessage());
            throw new IllegalStateException(errorMessage);
        }
    }
}
