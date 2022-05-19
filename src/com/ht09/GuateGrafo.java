package com.ht09;

import java.util.ArrayList;

public class GuateGrafo {
    ArrayList<String> cities = new ArrayList<>();
    ArrayList<ArrayList<Integer>> adyacencyMatrix = null;
    ArrayList<ArrayList<Integer>> pathMatrix = null;

    public GuateGrafo(ArrayList<String> cities,ArrayList<ArrayList<Integer>> adyacencyMatrix, ArrayList<ArrayList<Integer>> pathMatrix) {
        this.cities = cities;
        this.adyacencyMatrix = adyacencyMatrix;
        this.pathMatrix= pathMatrix;
    }


    /**
     * Calculates the shortest path on the matrix
     */
    private void calculateShortestPath() {
        this.pathMatrix = Floyd.solve(pathMatrix, adyacencyMatrix);
    }

    /**
     * Finds a route between two cities
     * @param from  Origin city name
     * @param to    Dest city name
     */
    public void findRoute(String from, String to) throws Exception {
        // Find the indices of the origin and destination
        Integer originIndex = cities.indexOf(from);
        Integer destIndex = cities.indexOf(to);
        // Validate that the cities exists
        if (originIndex < 0 || destIndex < 0) throw new Exception("Una de las ciudades ingresadas no existe.");
        this.calculateShortestPath();   // Recalculate the shortest path
        System.out.println(adyacencyMatrix);
        // Validar que si exista un camino
        if (pathMatrix.get(originIndex).get(destIndex) == null) throw new Exception("No hay forma de llegar entre esas dos ciudades.");
        // Obtener la distancia mas corta
        Integer shortestDistance = pathMatrix.get(originIndex).get(destIndex);
        System.out.printf("Distancia más corta (km) = %d\n", shortestDistance);
        Integer u = originIndex;
        if (adyacencyMatrix.get(originIndex).get(destIndex) == -1) throw new Exception("No hay forma de llegar entre esas dos ciudades.");
        System.out.println("Camino a tomar: ");
        while (u != destIndex) {
            System.out.printf("%s -> ",cities.get(u));
            u = adyacencyMatrix.get(u).get(destIndex);
        }
        System.out.println(cities.get(destIndex));
    }

}
