package com.ht09;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FloydTest {

    @Test
    public void shouldReturnAValue() {
        ArrayList<ArrayList<Integer>> matrix = Floyd.generateTestMatrix();
        ArrayList<ArrayList<Integer>> result = Floyd.solve(matrix);
        assertNotNull(result);
    }

    @Test
    public void shouldReturnAnArray() {
        ArrayList<ArrayList<Integer>> matrix = Floyd.generateTestMatrix();
        ArrayList<ArrayList<Integer>> result = Floyd.solve(matrix);
        assertInstanceOf(ArrayList.class, result);
    }

    @Test
    public void shouldSolveCorrectly() {
        ArrayList<ArrayList<Integer>> matrix = Floyd.generateTestMatrix();
        ArrayList<ArrayList<Integer>> result = Floyd.solve(matrix);
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(0);
        row1.add(3);
        row1.add(5);
        row1.add(6);
        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(5);
        row2.add(0);
        row2.add(2);
        row2.add(3);
        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(3);
        row3.add(6);
        row3.add(0);
        row3.add(1);
        ArrayList<Integer> row4 = new ArrayList<>();
        row4.add(2);
        row4.add(5);
        row4.add(7);
        row4.add(0);
        expected.add(row1);
        expected.add(row2);
        expected.add(row3);
        expected.add(row4);
        assertEquals(result, expected);
    }

    @Test
    public void shouldReturnCenter() {
        ArrayList<ArrayList<Integer>> matrix = Floyd.generateTestMatrix();
        ArrayList<ArrayList<Integer>> result = Floyd.solve(matrix);
        Integer centerColumn = Floyd.findCenter(result);
        assertNotNull(centerColumn);
        assertEquals(centerColumn, 0);
    }



}
