package org.example;

public class GameMap {

    private int jabX;
    private int jabY;

    private static Object[][] gameMap = new Object[5][5];

    public GameMap(Object[][] newMap) {
        for (int i = 0; i < gameMap.length; i++)
            for (int j = 0; j < gameMap[i].length; j++)
                gameMap[i][j] = newMap[i][j];
        getJabaPosition();
    }

    public Object[][] getGameMapArray() {
        return gameMap;
    }

    private void getJabaPosition() {
        for (int i = 0; i < gameMap.length; i++)
            for (int j = 0; j < gameMap[i].length; j++)
                if (gameMap[i][j].toString() == "🐸") {
                    jabX = j;
                    jabY = i;
                }
    }

    public void setJabX(int jabX) {
        this.jabX = jabX;
    }

    public void setJabY(int jabY) {
        this.jabY = jabY;
    }

    public int getJabX() {
        return jabX;
    }

    public int getJabY() {
        return jabY;
    }

    public void setJabXY(int jabX, int jabY) {
        this.jabX = jabX;
        this.jabY = jabY;
    }

    public void moveJaba(int newX, int newY) {
        getJabaPosition();
        if (gameMap[newY][newX] != null) {
            Object temp = gameMap[jabY][jabX];
            if (isDigit(gameMap[newY][newX].toString()) == true) {
                if (Integer.parseInt(gameMap[newY][newX].toString()) > 0)
                    gameMap[jabY][jabX] = Integer.parseInt(gameMap[newY][newX].toString()) * (-1);
                else
                    gameMap[jabY][jabX] = gameMap[newY][newX];
                gameMap[newY][newX] = temp;
            } else {
                if (gameMap[newY][newX].toString().equals("🏁")) {
                    gameMap[jabY][jabX] = 0;
                    gameMap[newY][newX] = temp;
                }
                else {
                    gameMap[jabY][jabX] = gameMap[newY][newX];
                    gameMap[newY][newX] = temp;
                }
            }

            //loseGame();
        }
    }

    public boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void teleport() {
        Object temp = gameMap[jabY][jabX];
        gameMap[jabY][jabX] = -1;
        gameMap[getPortalY()][getPortalX()] = temp;
        jabX = getPortalX();
        jabY = getPortalY();
        getJabaPosition();
    }

    private int getPortalX() {
        for (int i = 0; i < gameMap.length; i++)
            for (int j = 0; j < gameMap[i].length; j++)
                if (gameMap[i][j].toString() == "🌌") {
                    return j;
                }
        return 0;
    }

    private int getPortalY() {
        for (int i = 0; i < gameMap.length; i++)
            for (int j = 0; j < gameMap[i].length; j++)
                if (gameMap[i][j].toString() == "🌌") {
                    return i;
                }
        return 0;
    }

    protected void removeDoor() {
        for (int i = 0; i < gameMap.length; i++)
            for (int j = 0; j < gameMap[i].length; j++)
                if (gameMap[i][j].toString() == "🚪") {
                    gameMap[i][j] = 0;
                }
    }

}
