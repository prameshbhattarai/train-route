package crea.train.route.analyzer;

import crea.train.route.contract.Route;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pramesh-bhattarai
 */
public class AnalyzerTest {

    private List<Route> routes = new ArrayList<>();
    private Analyzer analyzer;

    @Before
    public void initializeRoutes() {
        routes.add(new Route("A", "B", 5));
        routes.add(new Route("B", "C", 5));
        routes.add(new Route("C", "D", 7));
        routes.add(new Route("A", "D", 15));

        routes.add(new Route("E", "F", 5));
        routes.add(new Route("F", "G", 5));
        routes.add(new Route("G", "H", 10));
        routes.add(new Route("H", "I", 10));
        routes.add(new Route("I", "J", 5));
        routes.add(new Route("G", "J", 20));

    }

    @Test
    public void scenario_1() {
        analyzer = new Analyzer(routes, "A", "B");
        analyzer.analyze();
        int numberOfStops = analyzer.getNumberOfStops();
        int timeTaken = analyzer.getTimeTaken();

        Assert.assertEquals(0, numberOfStops);
        Assert.assertEquals(5, timeTaken);
    }

    @Test
    public void scenario_2() {
        analyzer = new Analyzer(routes, "A", "C");
        analyzer.analyze();
        int numberOfStops = analyzer.getNumberOfStops();
        int timeTaken = analyzer.getTimeTaken();

        Assert.assertEquals(1, numberOfStops);
        Assert.assertEquals(10, timeTaken);
    }

    @Test
    public void scenario_3() {
        analyzer = new Analyzer(routes, "E", "J");
        analyzer.analyze();
        int numberOfStops = analyzer.getNumberOfStops();
        int timeTaken = analyzer.getTimeTaken();

        Assert.assertEquals(2, numberOfStops);
        Assert.assertEquals(30, timeTaken);
    }

    @Test
    public void scenario_4() {
        analyzer = new Analyzer(routes, "A", "D");
        analyzer.analyze();
        int numberOfStops = analyzer.getNumberOfStops();
        int timeTaken = analyzer.getTimeTaken();

        Assert.assertEquals(0, numberOfStops);
        Assert.assertEquals(15, timeTaken);
    }

    @Test
    public void scenario_5() {
        analyzer = new Analyzer(routes, "A", "J");
        analyzer.analyze();
        int numberOfStops = analyzer.getNumberOfStops();
        int timeTaken = analyzer.getTimeTaken();

        Assert.assertEquals(0, numberOfStops);
        Assert.assertEquals(-1, timeTaken);
    }

    @Test
    public void scenario_6() {
        analyzer = new Analyzer(routes, "Z", "Z");
        analyzer.analyze();
        int numberOfStops = analyzer.getNumberOfStops();
        int timeTaken = analyzer.getTimeTaken();

        Assert.assertEquals(0, numberOfStops);
        Assert.assertEquals(-1, timeTaken);
    }

}
