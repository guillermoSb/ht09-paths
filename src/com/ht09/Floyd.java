package com.ht09;

import java.util.ArrayList;
import java.util.List;

public class Floyd {
    public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> matrix) {
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
                            newRow.set(j, Math.min(originToMedium + mediumToDest, current));
                        } else {
                            newRow.set(j, originToMedium + mediumToDest);
                        }
                    }
                    solution.set(i, newRow);
                }
            }
        }
        return solution;
    }

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
}
