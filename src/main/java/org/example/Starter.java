package org.example;

public class Starter {
    public void createNewGame() {

    }
}
/*
{
                {"🏁", -2, -4, -3, "💎"},
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

//
//        function checkLeftItem() {
//        if (gameBoards[gameID-1][jabY][jabX-1] === '🌳' || gameBoards[gameID-1][jabY][jabX-1] === '🗿' ||
//        gameBoards[gameID-1][jabY][jabX-1] === '🚪')
//        return false;
//        else if (gameBoards[gameID-1][jabY][jabX-1] === '🏁')
//        winGame();
//        else if (gameBoards[gameID-1][jabY][jabX-1] === '💎') {
//        countGems++;
//        gemsText.innerHTML = "Gems : " + countGems;
//        gameBoards[gameID-1][jabY][jabX-1] = -1;
//        }
//        else if (gameBoards[gameID-1][jabY][jabX-1] === '🔑') {
//        gameBoards[gameID-1][jabY][jabX-1] = 0;
//        gameBoards[gameID-1][getDoorY()][getDoorX()] = 0;
//        }
//        else if (gameBoards[gameID-1][jabY][jabX-1] === '🪓') {
//        gameBoards[gameID-1][jabY][jabX-1] = 0;
//        haveAxe = true;
//        spellBtn.innerHTML += axeBtn;
//        }
//        else if (gameBoards[gameID-1][jabY][jabX-1] === '🥗') {
//        gameBoards[gameID-1][jabY][jabX-1] = -1;
//        startMoves+=3;
//        moveText.innerHTML = startMoves;
//        }
//        return true;
//        }
//        function checkRightItem() {
//        if (gameBoards[gameID-1][jabY][jabX+1] === '🌳' || gameBoards[gameID-1][jabY][jabX+1] === '🗿' ||
//        gameBoards[gameID-1][jabY][jabX+1] === '🚪')
//        return false;
//        else if (gameBoards[gameID-1][jabY][jabX+1] === '🏁')
//        winGame();
//        else if (gameBoards[gameID-1][jabY][jabX+1] === '💎') {
//        countGems++;
//        gemsText.innerHTML = "Gems : " + countGems;
//        gameBoards[gameID-1][jabY][jabX+1] = -1;
//        }
//        else if (gameBoards[gameID-1][jabY][jabX+1] === '🔑') {
//        gameBoards[gameID-1][jabY][jabX+1] = 0;
//        gameBoards[gameID-1][getDoorY()][getDoorX()] = 0;
//        }
//        else if (gameBoards[gameID-1][jabY][jabX+1] === '🪓') {
//        gameBoards[gameID-1][jabY][jabX+1] = 0;
//        haveAxe = true;
//        spellBtn.innerHTML += axeBtn;
//        }
//        else if (gameBoards[gameID-1][jabY][jabX+1] === '🥗') {
//        gameBoards[gameID-1][jabY][jabX+1] = -1;
//        startMoves+=3;
//        moveText.innerHTML = startMoves;
//        }
//        return true;
//        }
//        function checkUpItem() {
//        if (gameBoards[gameID-1][jabY-1][jabX] === '🌳' || gameBoards[gameID-1][jabY-1][jabX] === '🗿' ||
//        gameBoards[gameID-1][jabY-1][jabX] === '🚪')
//        return false;
//        else if (gameBoards[gameID-1][jabY-1][jabX] === '🏁')
//        winGame();
//        else if (gameBoards[gameID-1][jabY-1][jabX] === '💎') {
//        countGems++;
//        gemsText.innerHTML = "Gems : " + countGems;
//        gameBoards[gameID-1][jabY-1][jabX] = -1;
//        }
//        else if (gameBoards[gameID-1][jabY-1][jabX] === '🔑') {
//        gameBoards[gameID-1][jabY-1][jabX] = 0;
//        gameBoards[gameID-1][getDoorY()][getDoorX()] = 0;
//        }
//        else if (gameBoards[gameID-1][jabY-1][jabX] === '🪓') {
//        gameBoards[gameID-1][jaby-1][jabX] = 0;
//        haveAxe = true;
//        spellBtn.innerHTML += axeBtn;
//        }
//        else if (gameBoards[gameID-1][jabY-1][jabX] === '🥗') {
//        gameBoards[gameID-1][jabY-1][jabX] = -1;
//        startMoves+=3;
//        moveText.innerHTML = startMoves;
//        }
//        return true;
//        }
//        function checkDownItem() {
//        if (gameBoards[gameID-1][jabY+1][jabX] === '🌳' || gameBoards[gameID-1][jabY+1][jabX] === '🗿'||
//        gameBoards[gameID-1][jabY+1][jabX] === '🚪')
//        return false;
//        else if (gameBoards[gameID-1][jabY+1][jabX] === '🏁')
//        winGame();
//        else if (gameBoards[gameID-1][jabY+1][jabX] === '💎') {
//        countGems++;
//        gemsText.innerHTML = "Gems : " + countGems;
//        gameBoards[gameID-1][jabY+1][jabX] = -1;
//        }
//        else if (gameBoards[gameID-1][jabY+1][jabX] === '🔑') {
//        gameBoards[gameID-1][jabY+1][jabX] = 0;
//        gameBoards[gameID-1][getDoorY()][getDoorX()] = 0;
//        }
//        else if (gameBoards[gameID-1][jabY+1][jabX] === '🪓') {
//        gameBoards[gameID-1][jabY+1][jabX] = 0;
//        haveAxe = true;
//        spellBtn.innerHTML += axeBtn;
//        }
//        else if (gameBoards[gameID-1][jabY+1][jabX] === '🥗') {
//        gameBoards[gameID-1][jabY+1][jabX] = -1;
//        startMoves+=3;
//        moveText.innerHTML = startMoves;
//        }
//        return true;
//        }
//
//        function checkRightPortal() {
//        if (gameBoards[gameID-1][jabY][jabX+1] === '🌌') {
//        gameBoards[gameID-1][jabY][jabX+1] = 0;
//        return true;
//        }
//        }
//
//        function checkLeftPortal() {
//        if (gameBoards[gameID-1][jabY][jabX-1] === '🌌') {
//        gameBoards[gameID-1][jabY][jabX-1] = 0;
//        return true;
//        }
//        }
//
//        function checkUpPortal() {
//        if (gameBoards[gameID-1][jabY-1][jabX] === '🌌') {
//        gameBoards[gameID-1][jabY-1][jabX] = 0;
//        return true;
//        }
//        }
//
//        function checkDownPortal() {
//        if (gameBoards[gameID-1][jabY+1][jabX] === '🌌') {
//        gameBoards[gameID-1][jabY+1][jabX] = 0;
//        return true;
//        }
//        }
//
//        function winGame() {
//        alert("УРА ПОБЕДА!!!");
//        location.reload(true);
//        }
//
//        function startGame() {
//        if (isRunning == false) {
//        isRunning = true;
//        moveText.innerHTML = "Moves : " + startMoves;
//        makeTurn();
//        }
//        }
//
//        function loseGame() {
//        if (startMoves <= 0) {
//        alert("Вы проиграли");
//        location.reload(true);
//        isRunning = false;
//        }
//        }
//
//        function reGenerateLevel() {
//        startMoves = 3;
//        moveText.innerHTML = "Moves : " + startMoves;
//        gems = 0;
//        gemsText.innerHTML = "Gems : " + gems;
//        gameID = Math.floor(Math.random() * (gameBoards.length)+1);
//        render();
//        }
//
//        function getDoorX() {
//        for (let i = 0; i < gameBoards[gameID-1].length; i++)
//        for (let j = 0; j < gameBoards[gameID-1][i].length; j++)
//        if (gameBoards[gameID-1][i][j] === '🚪')
//        return j;
//        }
//
//        function getDoorY() {
//        for (let i = 0; i < gameBoards[gameID-1].length; i++)
//        for (let j = 0; j < gameBoards[gameID-1][i].length; j++)
//        if (gameBoards[gameID-1][i][j] === '🚪')
//        return i;
//        }
//
//        function getPortalY() {
//        for (let i = 0; i < gameBoards[gameID-1].length; i++)
//        for (let j = 0; j < gameBoards[gameID-1][i].length; j++)
//        if (gameBoards[gameID-1][i][j] === '🌌')
//        return i;
//        }
//
//        function getPortalX() {
//        for (let i = 0; i < gameBoards[gameID-1].length; i++)
//        for (let j = 0; j < gameBoards[gameID-1][i].length; j++)
//        if (gameBoards[gameID-1][i][j] === '🌌')
//        return j;
//        }
//
//        let breakLeft = "<button onClick = 'slayLeftTree()'>СЛЕВА</button>"
//        let breakRight = "<button onClick = 'slayRightTree()'>СПРАВА</button><br></br>"
//        let breakUp = "<button onClick = 'slayUpTree()'>СВЕРХУ</button>"
//        let breakDown = "<button onClick = 'slayDownTree()'>СНИЗУ</button>"
//
//        function createButtons() {
//        spellBtn.innerHTML = '';
//        spellBtn.innerHTML += breakLeft;
//        spellBtn.innerHTML += breakRight;
//        spellBtn.innerHTML += breakUp;
//        spellBtn.innerHTML += breakDown;
//        }
//
//        function slayUpTree() {
//        if (gameBoards[gameID-1][jabY-1][jabX] === '🌳') {
//        gameBoards[gameID-1][jabY-1][jabX] = 0;
//        haveAxe = false;
//        spellBtn.innerHTML = '';
//        render();
//        }
//        else alert("Это не дерево");
//        }
//        function slayDownTree() {
//        if (gameBoards[gameID-1][jabY+1][jabX] === '🌳') {
//        gameBoards[gameID-1][jabY+1][jabX] = 0;
//        haveAxe = false;
//        spellBtn.innerHTML = '';
//        render();
//        }
//        else alert("Это не дерево");
//        }
//        function slayLeftTree() {
//        if (gameBoards[gameID-1][jabY][jabX-1] === '🌳') {
//        gameBoards[gameID-1][jabY][jabX-1] = 0;
//        haveAxe = false;
//        spellBtn.innerHTML = '';
//        render();
//        }
//        else alert("Это не дерево");
//        }
//        function slayRightTree() {
//        if (gameBoards[gameID-1][jabY][jabX+1] === '🌳') {
//        gameBoards[gameID-1][jabY][jabX+1] = 0;
//        haveAxe = false;
//        spellBtn.innerHTML = '';
//        render();
//        }
//        else alert("Это не дерево");
//        }
