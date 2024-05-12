package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomizerMap {
    private final static int MAP_SIZE = 5;
    private static List<Object[][]> mapPool = new ArrayList<>();

    public RandomizerMap() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/maps.txt"));
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            Scanner scanner1 = new Scanner(str);
            //System.out.println(str);
            while (scanner1.hasNext()) {
                Object[][] x = new Object[MAP_SIZE][MAP_SIZE]; // коэффициенты перед x
                for (int i = 0; i < MAP_SIZE; i++) {
                    for (int j = 0; j < MAP_SIZE; j++) {
                        x[i][j] = scanner1.next();
                    }
                }
                x = replaceAll(x);
                //printMatrix(x);
                mapPool.add(x);
            }
        }
    }

    public static Object[][] generateMap() {
        int countMaps = mapPool.size();
        Random random = new Random();
        int mapId = random.nextInt(0, countMaps);
        return mapPool.get(mapId);
    }

    private void printMatrix(Object[][] matr) {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.println(matr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private Object[][] replaceAll(Object[][] matrix) {
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
}


/* ТЕКСТ ДЛЯ ЭМОДЗИ


 */