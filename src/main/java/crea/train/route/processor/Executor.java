package crea.train.route.processor;

import crea.train.route.analyzer.Analyzer;
import crea.train.route.contract.Route;
import crea.train.route.utils.FileReaderUtils;
import crea.train.route.utils.RoutePlanLoader;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @author pramesh-bhattarai
 */
public final class Executor {

    private List<Route> routes;
    private String source;
    private String destination;

    public Executor() {}

    public void setRoutes(String filePath) {
        this.routes = getRoutePlans(filePath);

        if (routes.size() == 0) {
            throw new IllegalStateException("No RoutePlans found.");
        }
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void execute() {

        Analyzer analyzer = new Analyzer(this.routes, this.source, this.destination);
        analyzer.analyze();

        int stop = analyzer.getNumberOfStops();
        int timeTaken = analyzer.getTimeTaken();

        String result;
        if (timeTaken < 0) {
            result = String.format("No routes from %s to %s", this.source, this.destination);
        } else {
            result = String.format("Your trip from %s to %s includes %d stops and will take %d minutes.",
                    this.source, this.destination, stop, timeTaken);
        }
        System.out.println(result);
    }

    private List<Route> getRoutePlans(String filePath) {
        InputStream stream = getInputStream(filePath);
        String rawText = FileReaderUtils.inputStreamToString(stream);
        return RoutePlanLoader.getRoutePlans(rawText);
    }

    private InputStream getInputStream(String filePath) {
        try {
            return FileReaderUtils.getFileInputStream(filePath);
        } catch (FileNotFoundException e) {
            String error = String.format("Unable to get file in provided path: %s, please provide full path eg: %s",
                    filePath, "/home/pramesh-bhattarai/Desktop/route-plan.csv");
            throw new IllegalStateException(error);
        }
    }
}
