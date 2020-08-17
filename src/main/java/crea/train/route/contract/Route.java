package crea.train.route.contract;

/**
 * @author pramesh-bhattarai
 */
public final class Route {

    private final String source;
    private final String destination;
    private final int timeTaken;

    public Route(String source, String destination, int timeTaken) {
        this.source = source;
        this.destination = destination;
        this.timeTaken = timeTaken;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

}
