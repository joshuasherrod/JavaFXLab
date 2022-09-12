package edu.sdccd.cisc191;

/**
 * Manages the game rules and logic
 */
public class ControllerGameBoard
{
    protected ModelGameBoard modelGameBoard;
    private boolean gameOver;

    public ControllerGameBoard()
    {
        this.modelGameBoard = new ModelGameBoard();
    }

    public boolean fishWin()
    {
        boolean fishWin = modelGameBoard.getGuessesRemaining() == 0 && modelGameBoard.getFishRemaining() > 0;
        if(fishWin) {
            gameOver = true;
        }
        return fishWin;
    }

    public boolean playerWins()
    {
        boolean playerWins = modelGameBoard.getFishRemaining() == 0;
        if(playerWins) {
            gameOver = true;
        }
        return playerWins;
    }

    public boolean makeGuess(int row, int col) {
        if(!gameOver) {
            return modelGameBoard.makeGuess(row, col);
        }
        return false;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}