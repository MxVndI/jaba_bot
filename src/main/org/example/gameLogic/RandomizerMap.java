package main.org.example.gameLogic;

import java.io.FileNotFoundException;
import java.util.Random;

public class RandomizerMap {
    public static Object[][] generateMap() throws FileNotFoundException {
        MapsManager.loadMaps();
        int countMaps = MapsManager.getMapsCount();
        Random random = new Random();
        int mapId = random.nextInt(0, countMaps);
        return MapsManager.getMap(mapId);
    }
}