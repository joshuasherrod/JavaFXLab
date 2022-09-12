package edu.sdccd.cisc191;

// Adapted from GoneFishing by Tasha Frankie and Allan Schougaard
import java.util.Random;

/**
 * Contains all the data required for the game play
 */
public class ModelGameBoard
{
    public static int DIMENSION = 6;
    public static int GUESSES = 30;
    public static int TOTAL_FISH = 10;

    private boolean[][] gameBoard;
    private int guessesRemaining;
    private int fishRemaining;

    /**
     * Initializes the game board with fish
     */
    public ModelGameBoard()
    {
        gameBoard = new boolean[DIMENSION][DIMENSION];  // defaults to false
        guessesRemaining = GUESSES;
        fishRemaining = TOTAL_FISH;
        Random randomNumberGenerator = new Random();
        for (int fishCounter = 0; fishCounter < TOTAL_FISH; fishCounter++)
        {
            int x, y;
            // finds an empty gameBoard slot
            do
            {
                x = randomNumberGenerator.nextInt(DIMENSION);
                y = randomNumberGenerator.nextInt(DIMENSION);
            } while (gameBoard[x][y]);
            gameBoard[x][y] = true;
        }
    }

    /**
     * @param row
     * @param col
     * @return Returns true if fish is found at row,col
     */
    public boolean fishAt(int row, int col)
    {
        return gameBoard[row][col];
    }

    /**
     * @param row
     * @param col
     * @return Returns true if fish is found at row,col and updates counters
     */
    public boolean makeGuess(int row, int col)
    {
        boolean foundFish = fishAt(row, col);
        guessesRemaining--;
        if (foundFish)
        {
            fishRemaining--;
        }

        return foundFish;
    }

    public int getGuessesRemaining()
    {
        return guessesRemaining;
    }

    public int getFishRemaining()
    {
        return fishRemaining;
    }
}