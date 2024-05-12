package org.example;

import java.util.Scanner;

public class Jaba {

    private Scanner scanner = new Scanner(System.in);
    private boolean haveAxe;
    private int countGems;
    private int moveCount;
    private GameMap gameMap;
    private Checker checker;
    private Render render;

    public Jaba() {
        render = new Render(this);
        gameMap = new GameMap(RandomizerMap.generateMap());
        moveCount = 3;
        countGems = 0;
        haveAxe = false;
        checker = new Checker(this);
    }

    public Render getRender() {
        return render;
    }

    public void addCountGems() {
        countGems++;
    }

    public int getCountGems() {
        return countGems;
    }

    public void moving(String direction) {
        int newY = gameMap.getJabY();
        int newX = gameMap.getJabX();
        if (direction.equals("w")) {
            if (newY > 0)
                newY = gameMap.getJabY() - 1;
        } else if (direction.equals("s")) {
            if (newY < 4)
                newY = gameMap.getJabY() + 1;
        } else if (direction.equals("a")) {
            if (newX > 0)
                newX = gameMap.getJabX() - 1;
        } else if (direction.equals("d")) {
            if (newX < 4)
                newX = gameMap.getJabX() + 1;
        } else if (direction.equals("cut")) {
            if (haveAxe == true) {
                haveAxe = false;
                System.out.println("Где рубить?");
                System.out.println("1 - слева, 2 - справа" +
                        "\n 3 - сверху, 4 - снизу");
                int temp = scanner.nextInt();
                switch (temp) {
                    case (1):
                        if (gameMap.getGameMapArray()[gameMap.getJabY()][gameMap.getJabX() - 1].toString().equals("🌳"))
                            gameMap.getGameMapArray()[gameMap.getJabY()][gameMap.getJabX() - 1] = 0;
                        break;
                    case (2):
                        if (gameMap.getGameMapArray()[gameMap.getJabY()][gameMap.getJabX() + 1].toString().equals("🌳"))
                            gameMap.getGameMapArray()[gameMap.getJabY()][gameMap.getJabX() + 1] = 0;
                        break;
                    case (3):
                        if (gameMap.getGameMapArray()[gameMap.getJabY() - 1][gameMap.getJabX()].toString().equals("🌳"))
                            gameMap.getGameMapArray()[gameMap.getJabY() - 1][gameMap.getJabX()] = 0;
                        break;
                    case (4):
                        if (gameMap.getGameMapArray()[gameMap.getJabY() + 1][gameMap.getJabX()].toString().equals("🌳"))
                            gameMap.getGameMapArray()[gameMap.getJabY() + 1][gameMap.getJabX()] = 0;
                        break;
                }
            }
        }
        if (gameMap.isDigit(gameMap.getGameMapArray()[newY][newX].toString()))
            moveCount += Integer.parseInt(gameMap.getGameMapArray()[newY][newX].toString());
        if (moveCount > 0) {
            int checkResult = checker.checkNextItem(gameMap.getGameMapArray()[newY][newX].toString());
            if (checkResult == 0) {

            } else if (checkResult == 1) {

            } else if (checkResult == 2) {
                checker.setWin();
            } else if (checkResult == 3) {
                gameMap.getGameMapArray()[newY][newX] = -1;
            } else if (checkResult == 4) {
                gameMap.removeDoor();
                gameMap.getGameMapArray()[newY][newX] = 0;
            } else if (checkResult == 5) {
                gameMap.getGameMapArray()[newY][newX] = 0;
                setHaveAxe(true);
            } else if (checkResult == 6) {
                gameMap.getGameMapArray()[newY][newX] = 0;
            } else if (checkResult == 7) {
                gameMap.getGameMapArray()[newY][newX] = 0;
                gameMap.teleport();
            }
            if (checkResult != 0 && checkResult != 7) {
                gameMap.moveJaba(newX, newY);
                gameMap.setJabXY(newX, newY);
            }
        }
        else {
            checker.setLose();
        }
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setHaveAxe(boolean haveAxe) {
        this.haveAxe = haveAxe;
    }

    public boolean getHaveAxe() {
        return haveAxe;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Checker getChecker() {
        return checker;
    }
}
