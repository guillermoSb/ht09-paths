package com.ht09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;

public class FileLoader {
    /**
     * Lee el nombre de un archivo
     * @param name Archivo
     * @return ArrayList<String> con ciudades
     */
    public static ArrayList<String> readFileCities(String name) {
        ArrayList<String> cities = new ArrayList<>(); // ArrayList que guarda las ciudades
        File file;          // archivo de texto
        FileReader fr;      // objeto que asegura la lectura del archivo
        BufferedReader bf;  // objeto similar al cursor

        // se coloca try-catch para que trate de realizar la lectura, si se levanta una excepción será tomada con el catch.
        try {
            file = new File(name);        // se especifica el archivo
            fr = new FileReader(file);      // se asigna a qué archivo se leerá
            bf = new BufferedReader(fr);    // se agrega un cursor de lectura
            String bfRead;                  // linea utilizada para el condicional
            int i=0;
            while ((bfRead = bf.readLine()) != null) {
                String[] dato = bfRead.split(" "); // la línea leída se convierte en un array string
                // Intentar encontrar la ciudad 1
                if (cities.indexOf(dato[0]) < 0) {
                    cities.add(dato[0]);
                }
                if (cities.indexOf(dato[1]) < 0) {
                    cities.add(dato[1]);
                }

            }
            bf.close(); // se cierra el cursor
            fr.close(); // se cierra el archivo
        } catch(Exception e){
            System.out.println("No se ha encontrado el texto " + name); // excepción
        }
        return cities; // retorno de la lista de pacientes con los elementos leídos.
    }

    /**
     * Lee el nombre de un archivo
     * @param name Archivo
     * @return ArrayList<ArrayList<Integer>> matriz
     */
    public static ArrayList<ArrayList<Integer>> readFileMatrix(String name) {
        ArrayList<String> cities = readFileCities(name); // ArrayList que guarda las ciudades
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();   // Matriz a regresar
        File file; // archivo de texto
        FileReader fr;      // objeto que asegura la lectura del archivo
        BufferedReader bf;  // objeto similar al cursor

        // Construir matriz
        for (int i = 0; i < cities.size(); i++) {
            matrix.add(new ArrayList<Integer>());
            for (int j = 0; j < cities.size(); j++) {
                matrix.get(i).add(null);
            }
        }

        // se coloca try-catch para que trate de realizar la lectura, si se levanta una excepción será tomada con el catch.
        try {
            file = new File(name);        // se especifica el archivo
            fr = new FileReader(file);      // se asigna a qué archivo se leerá
            bf = new BufferedReader(fr);    // se agrega un cursor de lectura
            String bfRead;                  // linea utilizada para el condicional
            int i=0;
            while ((bfRead = bf.readLine()) != null) {
                String[] dato = bfRead.split(" "); // la línea leída se convierte en un array string
                String origin = dato[0];
                String dest = dato[1];
                Integer weight = Integer.parseInt(dato[2]);
                Integer originIndex = cities.indexOf(origin);
                Integer destIndex = cities.indexOf(dest);
                matrix.get(originIndex).set(destIndex, weight);
            }
            bf.close(); // se cierra el cursor
            fr.close(); // se cierra el archivo
        } catch(Exception e){
            System.out.println(e);
            System.out.println("No se ha encontrado el texto " + name); // excepción
        }
        return matrix; // retorno de la lista de pacientes con los elementos leídos.
    }

    /**
     * Lee el nombre de un archivo
     * @param name Archivo
     * @return ArrayList<ArrayList<Integer>> matriz
     */
    public static ArrayList<ArrayList<Integer>> readFileAdyacentMatrix(String name) {
        ArrayList<String> cities = readFileCities(name); // ArrayList que guarda las ciudades
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();   // Matriz a regresar
        File file; // archivo de texto
        FileReader fr;      // objeto que asegura la lectura del archivo
        BufferedReader bf;  // objeto similar al cursor

        // Construir matriz
        for (int i = 0; i < cities.size(); i++) {
            matrix.add(new ArrayList<Integer>());
            for (int j = 0; j < cities.size(); j++) {
                matrix.get(i).add(-1);
            }
        }

        // se coloca try-catch para que trate de realizar la lectura, si se levanta una excepción será tomada con el catch.
        try {
            file = new File(name);        // se especifica el archivo
            fr = new FileReader(file);      // se asigna a qué archivo se leerá
            bf = new BufferedReader(fr);    // se agrega un cursor de lectura
            String bfRead;                  // linea utilizada para el condicional
            int i=0;
            while ((bfRead = bf.readLine()) != null) {
                String[] dato = bfRead.split(" "); // la línea leída se convierte en un array string
                String origin = dato[0];
                String dest = dato[1];
                Integer originIndex = cities.indexOf(origin);
                Integer destIndex = cities.indexOf(dest);
                matrix.get(originIndex).set(destIndex, destIndex);
            }
            bf.close(); // se cierra el cursor
            fr.close(); // se cierra el archivo
        } catch(Exception e){
            System.out.println(e);
            System.out.println("No se ha encontrado el texto " + name); // excepción
        }
        return matrix; // retorno de la lista de pacientes con los elementos leídos.
    }


}
