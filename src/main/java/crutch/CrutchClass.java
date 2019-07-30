package crutch;

import algorithm.ShortPath;
import com.Matrix;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class CrutchClass {

    private final static Logger logger = Logger.getLogger(CrutchClass.class);

    public static Double probabilityOfSuccess(Integer x, Integer y, int attemptNumbers) {

        Instant start = Instant.now();
        Matrix matrix = new Matrix();

        x = x + 2;

        int sucessNumber = 0;
        for (int i = 1; i <= attemptNumbers; i++) {

            logger.warn("iteration " + i);
            int[][] adjacencyMatrix = matrix.generateRandomAdjacencyMatrix(x, y);

            ShortPath shortestPath = new ShortPath();
            shortestPath.setV(x * y);

            boolean b = checkAllPath(x, y, adjacencyMatrix);
            if (b){
                sucessNumber++;
            }
//            System.out.println("Current iteration " + i);
//            System.out.println("Current success % " + String.format("%.2f", (double)sucessNumber/i*100));
            logger.warn("Current success % " + String.format("%.2f", (double)sucessNumber/i*100));
            printProgress(attemptNumbers, i);
        }
        System.out.println();

        System.out.println(String.format("%-19s%-6s%1s", "Success rate",
                String.format("%.2f", (double) sucessNumber / attemptNumbers * 100),
                "%"));

        Instant end = Instant.now();
        System.out.println(String.format("%-19s%-11s", "Elapsed time", Duration.between(start, end)));

        return (double) sucessNumber / attemptNumbers * 100;
    }

    private static boolean checkAllPath(int x, int y, int[][] adjacencyMatrix) {
        ArrayList<Integer> listOfStartNodes = new ArrayList<>();
        ArrayList<Integer> listOfEndNodes = new ArrayList<>();

        ShortPath shortestPath = new ShortPath();
        shortestPath.setV(x * y);

        for (int j = 0; j < x * y; j = j + x) {
            listOfStartNodes.add(j);
        }

        for (int j = x - 1; j < x * y; j = j + x) {
            listOfEndNodes.add(j);
        }
        boolean ifPathExists = false;

        out: for (Integer listOfStartNode : listOfStartNodes) {
            for (Integer listOfEndNode : listOfEndNodes) {
                int a = shortestPath.dijkstraAlgorithm(adjacencyMatrix, listOfStartNode, listOfEndNode);
//                System.out.println("From " + listOfStartNode + " to " + listOfEndNode);
//                System.out.println("a -> " + a);

                if (a > 0 && a < Integer.MAX_VALUE) {
                    logger.warn("From " + listOfStartNode + " to " + listOfEndNode + " -> " + a);
                    ifPathExists = true;
                    break out;
                }
            }
        }
        return ifPathExists;
    }



    private static void printProgress(long total, long current) {
        int percent = (int) (current * 100 / total);
        String string = '\r' +
                "Progress.........." +
                String.format(" %d%% ", percent);
        System.out.print(string);
    }

}
