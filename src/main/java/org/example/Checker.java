package org.example;

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
        if (item.equals("🌳") || item.equals("🗿") || item.equals("🚪") || item.equals("🐸"))
            return 0;
        else if (item.equals("🏁")) {
            return 2;
        } else if (item.equals("💎")) {
            jab.addCountGems();
            return 3;
        } else if (item.equals("🔑")) {
            return 4;
        } else if (item.equals("🪓")) {
            jab.setHaveAxe(true);
            return 5;
        } else if (item.equals("🥗")) {
            jab.setMoveCount(jab.getMoveCount() + 3);
            return 6;
        } else if (item.equals("🌌")) {
            return 7;
        } else {
            return 1;
        }
    }

    protected void setWin() {
        isWin = true;
    }

    protected boolean isWin() {
        return isWin;
    }

    protected void setLose() {
        isLose = true;
    }

    protected boolean isLose() {
        return isLose;
    }
}

