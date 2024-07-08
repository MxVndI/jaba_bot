package main.org.example.gameLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class MapsManager {
    private static LinkedHashMap<Integer, Object[][]> MAPS = new LinkedHashMap<>();
    private final static int MAP_SIZE = 5;

    public static void loadMaps() throws FileNotFoundException{
            Scanner scanner = new Scanner(new File("src/main/maps.txt"));
            int id = 0;
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                Scanner scanner1 = new Scanner(str);
                while (scanner1.hasNext()) {
                    Object[][] x = new Object[MAP_SIZE][MAP_SIZE];
                    for (int i = 0; i < MAP_SIZE; i++) {
                        for (int j = 0; j < MAP_SIZE; j++) {
                            x[i][j] = scanner1.next();
                        }
                    }
                    x = replaceAll(x);
                    MAPS.put(id, x);
                }
                id++;
            }

    }

    private static Object[][] replaceAll(Object[][] matrix) {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (matrix[i][j].equals("flag"))
                    matrix[i][j] = "🏁";
                else if (matrix[i][j].equals("tree"))
                    matrix[i][j] = "🌳";
                else if (matrix[i][j].equals("gem")) {
                    matrix[i][j] = "💎";
                } else if (matrix[i][j].equals("key")) {
                    matrix[i][j] = "🔑";
                } else if (matrix[i][j].equals("door")) {
                    matrix[i][j] = "🚪";
                } else if (matrix[i][j].equals("axe")) {
                    matrix[i][j] = "🪓";
                } else if (matrix[i][j].equals("salat")) {
                    matrix[i][j] = "🥗";
                } else if (matrix[i][j].equals("portal")) {
                    matrix[i][j] = "🌌";
                } else if (matrix[i][j].equals("stone")) {
                    matrix[i][j] = "🗿";
                } else if (matrix[i][j].equals("frog")) {
                    matrix[i][j] = "🐸";
                }
            }
        }
        return matrix;
    }

    private void addMap() {

    }

    private void removeMap() {

    }

    private void printMatrix(Object[][] matr) {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.println(matr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int getMapsCount() {
        return MAPS.size();
    }

    public static Object[][] getMap(Integer id) {
        return MAPS.get(id);
    }
}
