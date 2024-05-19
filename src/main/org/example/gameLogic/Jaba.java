package main.org.example.gameLogic;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Jaba {

    private Scanner scanner = new Scanner(System.in);
    private boolean haveAxe;
    private int countGems;
    private int moveCount;
    private GameMap gameMap;
    private Checker checker;
    private Render render;

    public Jaba() throws FileNotFoundException {
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
            switch (checkResult) {
                case 1:
                    break;
                case 2:
                    checker.setWin();
                    break;
                case 3:
                    gameMap.getGameMapArray()[newY][newX] = -1;
                    break;
                case 4:
                    gameMap.removeDoor();
                    gameMap.getGameMapArray()[newY][newX] = 0;
                    break;
                case 5:
                    gameMap.getGameMapArray()[newY][newX] = 0;
                    setHaveAxe(true);
                    break;
                case 6:
                    gameMap.getGameMapArray()[newY][newX] = 0;
                    break;
                case 7:
                    gameMap.getGameMapArray()[newY][newX] = 0;
                    gameMap.teleport();
                    break;
            }
            if (checkResult != 0 && checkResult != 7) {
                gameMap.moveJaba(newX, newY);
                gameMap.setJabXY(newX, newY);
            } else {
                checker.setLose();
            }
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

/*"🏁", -2, -4, -3, "💎"},
        {"🌳", -3, 1, 3, -1},
        {"🌳", 1, "🌳", "🗿", -2},
        {"🌳", 2, "🌳", "🌳", 2},
        {"💎", -1, -2, 3, "🐸"}};

        {
        {"🐸",0,"🥗","🌳", "💎"},
        {-1,"🌳","🌳","🌳","🌌"},
        {-1,"🌳","🏁","🌳","🪓"},
        {1, "🌳","🌳","🌳","🌳"},
        {-1,2,"💎","💎","🌌"}};

        */
//        let gameBoards = [
//        [
//        ['🏁',-2,-4,-3,'💎'],
//        ['🌳',-3,1,3,-1],
//        ['🌳',1,'🌳','🗿',-2],
//        ['🌳',2,'🌳','🌳',2],
//        ['💎',-1,-2,3,'🐸']],
//
//        [['💎',-2,-4,-3,'💎'],
//        ['🌳',-3,1,3,-1],
//        ['🌳',1,'🌳','🗿',-2],
//        ['🌳',2,'🌳','🌳',2],
//        ['🏁',-1,-2,'🐸',3]],
//
//        [['💎',-2,-4,-3,'🐸'],
//        ['🌳',-3,1,3,-1],
//        ['🌳',1,'🌳','🗿',-2],
//        ['🌳',2,'🌳','🌳',2],
//        ['🏁',-1,-2,3,'💎']],
//
//        [['🏁',-2,-4,-3,'💎'],
//        ['🌳',-3,1,3,-1],
//        ['🌳','🐸','🌳','🗿',-2],
//        ['🌳',1,'🌳','🌳',2],
//        ['💎',-1,-2,3,2]],
//
//        [['💎',-2,-4,-3,'💎'],
//        ['🌳',-3,1,'🪓','🐸'],
//        ['🌳',1, 1,'🗿',-2],
//        ['🌳',2,'🌳','🌳',2],
//        ['🏁',-1,-2,-1,3]],
//
//        [['💎',-2,-4,-3,'💎'],
//        ['🌳',-3,1,3,1],
//        ['🌳','🐸', 1,'🗿',-2],
//        ['🌳',2,'🌳','🌳',2],
//        ['🏁',-1,-2,-1,3]],
//
//        [['💎',-2,-4,-3,'🔑'],
//        ['🌳',-2,1,3,1],
//        ['🌳','🐸', 1,'🗿',-2],
//        ['🌳',2,'🌳','🌳',2],
//        ['🏁','🚪',-2,-1,3]],
//
//        [['💎',-2,-4,-3,'🐸'],
//        ['🌳',-2,1,3,1],
//        ['🌳','🌳', 1,'🗿',-2],
//        ['🌳',2,'🌳','🌳','🔑'],
//        ['🏁','🚪',-2,-1,3]],
//
//        [['🏁','🚪',-6,-3,'🐸'],
//        [1,'🌳',1,3,1],
//        [-4,'🌳', 1,'🗿',-1],
//        [-3,2,'🌳','🌳','🔑'],
//        [3,2,-2,-1,3]],
//
//        [['💎',-2,-1,-1,'🌌'],
//        ['🌳',-3,1,4,'🐸'],
//        ['🌳',1, 1,'🗿',-2],
//        ['🌳',2,'🌳','🌳',2],
//        ['🏁','🌌',-2,-1,3]],
//
//        [['🐸',0,'🔑',-2, '💎'],
//        [-2,1,'🌳','🌳','🌳'],
//        [-1,'🚪','🌌',1,'🏁'],
//        [1,'🌳','🌳','🌳','🌳'],
//        [1,-2,'💎','💎','🌌']],
//
//        {{"🐸",0,"🥗","🌳", "💎"},
//        {-1,"🌳","🌳","🌳","🌌"},
//        [-1,"🌳","🏁',"🌳',"🪓"},
//        [1, "🌳","🌳","🌳","🌳"},
//        [-1,2,"💎","💎","🌌"}};,
//
//        [['🐸','🥗','🥗','🥗', '🥗'],
//        ['🥗','🥗','🥗','🥗','🥗'],
//        ['🥗','🥗','🥗','🥗','🥗'],
//        ['🥗', '🥗','🥗','🗿','🗿'],
//        ['🥗','🥗','🥗',-42,'🏁']],
//
//        ]