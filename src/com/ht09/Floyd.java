package com.ht09;

import java.util.ArrayList;
import java.util.List;

public class Floyd {

    /**
     * Finds the shortest paths for a matrix
     * @param matrix
     * @param next
     * @return
     */
    public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> matrix, ArrayList<ArrayList<Integer>> next) {
        ArrayList<ArrayList<Integer>> solution = matrix;    // Solution to be returned
        for (int k = 0; k < matrix.size(); k++) {
            for (int i = 0; i < matrix.size(); i++) {
                for (int j = 0; j < matrix.size(); j++) {
                    ArrayList<Integer> newRow = matrix.get(i);
                    Integer current = matrix.get(i).get(j); // Current shortest path
                    Integer originToMedium = matrix.get(i).get(k); // Origin to medium
                    Integer mediumToDest = matrix.get(k).get(j);    // Origin to destination
                    if (originToMedium != null && mediumToDest != null) {
                        if (current != null) {
                            if (current > originToMedium + mediumToDest) {
                                newRow.set(j, originToMedium + mediumToDest);
                                next.get(i).set(j, next.get(i).get(k));
                            }
                        } else {
                            newRow.set(j, originToMedium + mediumToDest);
                            next.get(i).set(j, next.get(i).get(k));
                        }
                    }
                    solution.set(i, newRow);
                }
            }
        }
        return solution;
    }

    /**
     * Generates a matrix
     * @return
     */
    public static ArrayList<ArrayList<Integer>> generateTestMatrix(){
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(0);
        row1.add(3);
        row1.add(null);
        row1.add(7);
        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(8);
        row2.add(0);
        row2.add(2);
        row2.add(null);
        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(5);
        row3.add(null);
        row3.add(0);
        row3.add(1);
        ArrayList<Integer> row4 = new ArrayList<>();
        row4.add(2);
        row4.add(null);
        row4.add(null);
        row4.add(0);
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        matrix.add(row4);
        return matrix;
    }


    /**
     * Finds the center of a matrix
     * @param matrix
     * @return
     */
    public static Integer findCenter(ArrayList<ArrayList<Integer>> matrix) {
        Integer[] eccentricities = new Integer[matrix.size()];
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                Integer value = matrix.get(i).get(j);   // Current Value
                // Check if the value is null - nothing to do
                if (value != null) {
                    if (eccentricities[j] != null) {
                        eccentricities[j] = Math.max(eccentricities[j], value);
                    } else {
                        eccentricities[j] = value;
                    }
                }
            }
        }
        Integer currentMin = null;
        for (int i = 0; i < eccentricities.length; i++) {
            if (currentMin == null) {
                currentMin = i;
                continue;
            }
            if (eccentricities[i] != null){
                if (eccentricities[i] < currentMin) {
                    currentMin = i;
                }
            }
        }
        return currentMin;
    }
}
