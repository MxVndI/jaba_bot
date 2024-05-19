package main.org.example.gameLogic;

public class Checker {

    private Jaba jab;
    private boolean isWin;
    private boolean isLose;

    public void setJab(Jaba jab) {
        this.jab = jab;
    }

    public Checker(Jaba jab) {
        this.jab = jab;
        isWin = false;
        isLose = false;
    }

    public int checkNextItem(String item) {
        switch (item) {
            case "🌳":
            case "🗿":
            case "🚪":
            case "🐸":
                return 0;
            case "🏁":
                return 2;
            case "💎":
                jab.addCountGems();
                return 3;
            case "🔑":
                return 4;
            case "🪓":
                jab.setHaveAxe(true);
                return 5;
            case "🥗":
                jab.setMoveCount(jab.getMoveCount() + 3);
                return 6;
            case "🌌":
                return 7;
            default:
                return 1;
        }
    }

    protected void setWin() {
        isWin = true;
    }

    public boolean isWin() {
        return isWin;
    }

    protected void setLose() {
        isLose = true;
    }

    public boolean isLose() {
        return isLose;
    }
}

