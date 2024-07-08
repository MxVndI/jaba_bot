package main.org.example.gameLogic;


public class Render {

    private Jaba jab;
    private static String gameField = "";

    public Render(Jaba jab) {
        this.jab = jab;
    }

    public String render(Object[][] gameMap) {
        gameField = "";
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                gameField += gameMap[i][j] + " ";
            }
            gameField += "\n";
        }
        return gameField;
    }

}
