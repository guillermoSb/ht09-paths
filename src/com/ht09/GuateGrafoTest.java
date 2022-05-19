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
            assertEquals(guateGrafo.findRoute("Mixco", "SantaLucia").size(), 4);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldAddConneciton() {
        ArrayList<String> cities = FileLoader.readFileCities("guategrafotest.txt");
        ArrayList<ArrayList<Integer>> matrix = FileLoader.readFileMatrix("guategrafotest.txt");
        ArrayList<ArrayList<Integer>> adyacentMatrix = FileLoader.readFileAdyacentMatrix("guategrafotest.txt");
        GuateGrafo guateGrafo = new GuateGrafo(cities, adyacentMatrix, matrix);
        try {
            guateGrafo.addConnection("Mixco", "Peten", 100);
            guateGrafo.addConnection("Mixco", "Huehue", 200);
            guateGrafo.findRoute("Mixco", "Huehue");
            guateGrafo.addConnection("Escuintla", "Huehue", 2);
            guateGrafo.interruptConnection("Escuintla", "Huehue");
           assertEquals(guateGrafo.findRoute("Mixco", "Huehue").size(), 2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
