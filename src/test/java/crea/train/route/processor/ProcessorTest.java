package crea.train.route.processor;

import org.junit.Test;

/**
 * @author pramesh-bhattarai
 */
public class ProcessorTest {

    @Test
    public void shouldLoadFileIntoTheSystemAndPrintTheResult() {
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("route-plan.csv").getFile();

        Executor executor = new Executor();
        executor.setRoutes(filePath);
        executor.setSource("A");
        executor.setDestination("B");
        executor.execute();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfUnableToFindFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = classLoader.getResource("").getFile();


        Executor executor = new Executor();
        executor.setRoutes(filePath);
        executor.setSource("A");
        executor.setDestination("B");
        executor.execute();
    }
}
