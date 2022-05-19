package com.ht09;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int option = 0;
        Scanner scan = new Scanner(System.in);
        ArrayList<String> cities = FileLoader.readFileCities("guategrafo.txt");
        ArrayList<ArrayList<Integer>> matrix = FileLoader.readFileMatrix("guategrafo.txt");
        ArrayList<ArrayList<Integer>> adyacentMatrix = FileLoader.readFileAdyacentMatrix("guategrafo.txt");
        GuateGrafo grafo = new GuateGrafo(cities, adyacentMatrix, matrix);  // Create the graph
        while (option != 4) {
            View.showMenu();
            try {
                option = scan.nextInt();    // Ask for the option that the user wants
                scan.nextLine();
                if (option == 1) {
                    System.out.print("Ciudad de origen: ");
                    String originCity = scan.nextLine();
                    System.out.print("Ciudad de destino: ");
                    String destCity = scan.nextLine();
                    ArrayList<String> path = grafo.findRoute(originCity, destCity);
                    for (int i = 0; i < path.size(); i++) {
                        System.out.print(path.get(i));
                        if (i < path.size() - 1) System.out.print(" -> ");
                        if (i == path.size() - 1) System.out.println();

                    }
                } else if(option == 2) {
                    System.out.print("Ciudad que queda en el centro: ");
                    System.out.println(grafo.center());
                } else if(option == 3) {
                    System.out.println("Modificar el grafo:");
                    System.out.println("1 - Agregar conexión");
                    System.out.println("2 - Bloquear conexión");
                    Integer opt2 = scan.nextInt();
                    scan.nextLine();
                    if (opt2 == 1) {
                        System.out.print("Ciudad de origen: ");
                        String originCity = scan.nextLine();
                        System.out.print("Ciudad de destino: ");
                        String destCity = scan.nextLine();
                        System.out.print("Distancia en km: ");
                        Integer distance = scan.nextInt();
                        scan.nextLine();
                        grafo.addConnection(originCity, destCity, distance);
                        System.out.printf("Se ha conectado a %s con %s y la distancia es %d(km)", originCity, destCity, distance);
                    } else if (opt2 == 2) {
                        System.out.print("Ciudad de origen: ");
                        String originCity = scan.nextLine();
                        System.out.print("Ciudad de destino: ");
                        String destCity = scan.nextLine();
                        grafo.interruptConnection(originCity, destCity);
                        System.out.printf("Se ha interrumpido a %s con %s.", originCity, destCity);
                    } else {
                        System.out.println("No es una opción válida");
                    }
                    System.out.println(grafo.center());
                }

            } catch (InputMismatchException e) {
                System.out.println("Se espera que ingrese un numero");
                scan.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
