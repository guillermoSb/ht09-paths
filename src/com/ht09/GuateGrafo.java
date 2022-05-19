package com.ht09;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GuateGrafo {
    ArrayList<String> cities = new ArrayList<>();
    ArrayList<ArrayList<Integer>> adyacencyMatrix = null;
    ArrayList<ArrayList<Integer>> pathMatrix = null;
    ArrayList<ArrayList<Integer>> shortestMatrix = null;

    public GuateGrafo(ArrayList<String> cities,ArrayList<ArrayList<Integer>> adyacencyMatrix, ArrayList<ArrayList<Integer>> pathMatrix) {
        this.cities = cities;
        this.adyacencyMatrix = adyacencyMatrix;
        this.pathMatrix= pathMatrix;
    }

    /**
     * Clones a matrix
     * @param matrix
     * @return
     */
    public static ArrayList<ArrayList<Integer>> cloneMatrix(ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<ArrayList<Integer>> clone = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            clone.add(matrix.get(i));
        }
        return clone;
    }


    /**
     * Add a connection between two cities.
     * @param origin
     * @param dest
     * @param distance
     */
    public void addConnection(String origin, String dest, Integer distance) {
        // Find if the origin city and the destCity are already on the array
        Integer originIndex = cities.indexOf(origin);
        Integer destIndex = cities.indexOf(dest);
        if (originIndex < 0) {
            cities.add(origin);
            originIndex = cities.size() - 1;
        };
        if (destIndex < 0) {
            cities.add(dest);
            destIndex = cities.size() - 1;
        }
        // Check if matrix is needed to recalculate
        if (pathMatrix.size() < cities.size()) {
            // We need to add a new position for the matrix
            while (pathMatrix.size() < cities.size()) {
                ArrayList<Integer> newRow = new ArrayList<>();
                ArrayList<Integer> newRowAdyacent = new ArrayList<>();
                for (int i = 0; i < cities.size(); i++) {
                    newRow.add(null);
                    newRowAdyacent.add(-1);
                }
                pathMatrix.add(newRow);
                adyacencyMatrix.add(newRowAdyacent);

                for (int i = 0; i < pathMatrix.size(); i++) {
                    if (pathMatrix.get(i).size() < pathMatrix.size()) {
                        for (int j = 0; j < pathMatrix.size() - pathMatrix.get(i).size(); j++) {
                            pathMatrix.get(i).add(null);
                            adyacencyMatrix.get(i).add(-1);
                        }
                    }
                }
            }
        }
        pathMatrix.get(originIndex).set(destIndex, distance);
        adyacencyMatrix.get(originIndex).set(destIndex, destIndex);
    }

    /**
     * Interrupts the connection between two cities
     * @param origin
     * @param dest
     * @throws Exception
     */
    public void interruptConnection(String origin, String dest) throws Exception {
        Integer originIndex = cities.indexOf(origin);
        Integer destIndex = cities.indexOf(dest);
        if (originIndex < 0 || destIndex < 0) throw new Exception("No existe conexión entre esas ciudades.");
        pathMatrix.get(originIndex).set(destIndex, null);
        adyacencyMatrix.get(originIndex).set(destIndex, -1);
    }


    /**
     * Calculates the shortest path on the matrix
     */
    private void calculateShortestPath() {
        ArrayList<ArrayList<Integer>> matrixToSolve = GuateGrafo.cloneMatrix(pathMatrix);
        this.shortestMatrix = Floyd.solve(matrixToSolve, adyacencyMatrix);

    }

    /**
     * Finds a route between two cities
     * @param from  Origin city name
     * @param to    Dest city name
     */
    public ArrayList<String> findRoute(String from, String to) throws Exception {
        // Find the indices of the origin and destination
        Integer originIndex = cities.indexOf(from);
        Integer destIndex = cities.indexOf(to);
        // Validate that the cities exists
        if (originIndex < 0 || destIndex < 0) throw new Exception("Una de las ciudades ingresadas no existe.");
        this.calculateShortestPath();   // Recalculate the shortest path
        // Validar que si exista un camino
        if (shortestMatrix.get(originIndex).get(destIndex) == null) throw new Exception("No hay forma de llegar entre esas dos ciudades.");
        // Obtener la distancia mas corta
        Integer shortestDistance = shortestMatrix.get(originIndex).get(destIndex);
        System.out.printf("Distancia más corta (km) = %d\n", shortestDistance);
        Integer u = originIndex;
        if (adyacencyMatrix.get(originIndex).get(destIndex) == -1) throw new Exception("No hay forma de llegar entre esas dos ciudades.");
//        System.out.println("Camino a tomar: ");
        ArrayList<String> path = new ArrayList<>();
        while (u != destIndex) {
//            System.out.printf("%s -> ",cities.get(u));
            path.add(cities.get(u));
            u = adyacencyMatrix.get(u).get(destIndex);
        }
        path.add(cities.get(destIndex));
        return path;
    }

}
