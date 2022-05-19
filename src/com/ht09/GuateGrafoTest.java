package com.ht09;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class GuateGrafoTest {

    @Test
    public void shouldReadCities() {
        ArrayList<String> cities = FileLoader.readFileCities("guategrafotest.txt");
        assertInstanceOf(ArrayList.class, cities);
        assertEquals(cities.size(), 4);
        assertEquals(cities.get(0), "Mixco");
        assertEquals(cities.get(3), "SantaLucia");
    }

    @Test
    public void shouldCreateDistanceMatrix() {
        ArrayList<ArrayList<Integer>> matrix = FileLoader.readFileMatrix("guategrafotest.txt");
        assertInstanceOf(ArrayList.class, matrix);
        assertEquals(matrix.size(), 4);
        for (int i = 0; i < matrix.size(); i++) {
            assertEquals(matrix.size(), matrix.get(i).size());
        }
    }

    @Test
    public void shouldCreateAdyacencyMatrix() {
        ArrayList<ArrayList<Integer>> matrix = FileLoader.readFileAdyacentMatrix("guategrafotest.txt");
        System.out.println(matrix);
    }

    @Test
    public void shouldFindShortestRoute() {
        ArrayList<String> cities = FileLoader.readFileCities("guategrafotest.txt");
        ArrayList<ArrayList<Integer>> matrix = FileLoader.readFileMatrix("guategrafotest.txt");
        ArrayList<ArrayList<Integer>> adyacentMatrix = FileLoader.readFileAdyacentMatrix("guategrafotest.txt");
        GuateGrafo guateGrafo = new GuateGrafo(cities, adyacentMatrix, matrix);
        try {
            guateGrafo.findRoute("Mixco", "SantaLucia");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
