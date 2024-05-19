package main.org.example.gameLogic;


public class Render {

    private Jaba jab;
    private static String gameField = "";

    public Render(Jaba jab) {
        this.jab = jab;
    }

    public String render(Object[][] gameMap) {
        gameField = "";
        //System.out.println("Кол-во кристаллов " + jab.getCountGems());
        //System.out.println("Кол-во запасов " + jab.getMoveCount());
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                //System.out.print(gameMap[i][j] + " ");
                gameField += gameMap[i][j] + " ";
            }
            gameField += "\n";
            //System.out.println("\n");
        }
        return gameField;
    }

}
