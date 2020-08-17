package crea.train.route;

import crea.train.route.processor.Executor;

import java.util.Scanner;

/**
 * @author pramesh-bhattarai
 */
public class CREATrainRoute {

    public static void main(String... args) {
        Executor executor = new Executor();

        executor.setRoutes(getFilePath(args));

        Scanner in = new Scanner(System.in);

        System.out.println("What station are you getting on the train ?");
        executor.setSource(in.nextLine());

        System.out.println("What station are you getting off the train ?");
        executor.setDestination(in.nextLine());

        executor.execute();
    }

    private static String getFilePath(String... args) {
        String error = "Please provide file path in --file=FILE_PATH format";
        if (args.length == 0) throw new IllegalStateException(error);

        String fileKey = "--file=";
        if(!args[0].contains(fileKey)) throw new IllegalStateException(error);

        return args[0].substring(fileKey.length());
    }
}
