package com;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Matrix {

    private final static Logger logger = Logger.getLogger(Matrix.class);

    public int[][] generateRandomAdjacencyMatrix(int x, int y) {

        int matrixSideLength = x * y;

        int[][] matrix = generateMatrixFiledWithZero(matrixSideLength);

        ArrayList<String> allElements = getAllElements(y, x);
        HashMap<String, ArrayList<String>> neighbours = getAllNeighbours(y, x);

        for (int i = 0; i < allElements.size(); i++) {
            ArrayList<String> neighbourForElement = neighbours.get(allElements.get(i));
            for (int j = i; j < allElements.size(); j++) {
                if (neighbourForElement.contains(allElements.get(j))) {
                    int connected = getRandomValue();
                    matrix[i][j] = connected;
                    matrix[j][i] = connected;
                } else {
                    matrix[i][j] = 0;
                    matrix[j][i] = 0;
                }
            }
        }

//        logMatrix(matrix);
//        printMatrix(matrix);
//        System.out.println(isMathixSymmetric(matrix));
        return matrix;
    }


    private int[][] generateMatrixFiledWithZero(int matrixSideLength) {
        int[][] matrix = new int[matrixSideLength][matrixSideLength];
        for (int i = 0; i < matrixSideLength; i++) {
            for (int j = 0; j < matrixSideLength; j++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }


    private ArrayList<String> getAllElements(int x, int y) {
        ArrayList<String> allElements = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                allElements.add(i + "" + j);
            }
        }
        return allElements;
    }


    private HashMap<String, ArrayList<String>> getAllNeighbours(int x, int y) {

        HashMap<String, ArrayList<String>> allNeighbours = new HashMap<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                ArrayList<String> neighboursForElement = new ArrayList<>();
                if (j - 1 >= 0) {
                    neighboursForElement.add(i + "" + (j - 1));
                }
                if (j + 1 < y) {
                    neighboursForElement.add(i + "" + (j + 1));
                }
                if (i - 1 >= 0) {
                    neighboursForElement.add((i - 1) + "" + j);
                }
                if (i + 1 < x) {
                    neighboursForElement.add((i + 1) + "" + j);
                }
                allNeighbours.put(i + "" + j, neighboursForElement);
            }
        }
        return allNeighbours;
    }


    private int getRandomValue() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return 1;
        } else {
            return 0;
        }
    }


    private boolean isMathixSymmetric(int[][] matrix) {
        boolean symmetric = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != matrix[j][i]) {
//                if (!matrix[i][j].equals(matrix[j][i])) {
                    symmetric = false;
                    break;
                }
            }
        }
        return symmetric;
    }


    private void printMatrix(int[][] matrix){
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(ints[j] + ", ");
            }
            System.out.println();
        }
    }
    private void logMatrix(int[][] matrix){
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] ints : matrix) {
            stringBuilder.setLength(0);
            for (int j = 0; j < matrix.length; j++) {
                stringBuilder
                        .append(ints[j])
                        .append(",");
            }
            logger.warn(stringBuilder);
        }
    }


}