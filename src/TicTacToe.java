import java.util.Scanner;

public class TicTacToe
{
    final static int ROWS = 3;
    final static int COLS = 3;

    static String board[][];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;
        String player = "X";
        board = new String[ROWS][COLS];
        int row = 0;
        int col = 0;

        while (playAgain)
        {
            clearBoard();
            boolean gameWon = false;
            int moveCount = 0;

            while (!gameWon && moveCount < ROWS * COLS)
            {
                showBoard();
                row = SafeInput.getRangedInt(in, "Enter your row", 1, 3);
                col = SafeInput.getRangedInt(in, "Enter your column", 1, 3);
                row--;
                col--;

                while (!isValidMove(row, col))
                {
                    System.out.println("Invalid move. Try again.");
                    row = SafeInput.getRangedInt(in, "Enter your row", 1, 3);
                    col = SafeInput.getRangedInt(in, "Enter your column", 1, 3);
                    row--;
                    col--;
                }

                board[row][col] = player;
                moveCount++;

                if (isWin(player))
                {
                    showBoard();
                    System.out.println("Player " + player + " wins!");
                    gameWon = true;
                }
                else if (isTie())
                {
                    showBoard();
                    System.out.println("It's a tie!");
                    gameWon = true;
                }

                player = (player.equals("X")) ? "O" : "X";
            }

            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again?");
        }
    }

    private static void clearBoard()
    {
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                board[r][c] = " ";
    }

    private static void showBoard()
    {
        for (int r = 0; r < ROWS; r++)
        {
            System.out.print("|");
            for (int c = 0; c < COLS; c++)
                System.out.print(board[r][c] + "|");
            System.out.println();
        }
    }

    private static boolean isValidMove(int r, int c)
    {
        boolean retVal = false;

        if (board[r][c].equals(" "))
            retVal = true;

        return retVal;
    }

    private static boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player)
    {
        for (int r = 0; r < ROWS; r++)
        {
            if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player))
                return true;
        }
        return false;
    }

    private static boolean isColWin(String player)
    {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie()
    {
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
            {
                if (board[r][c].equals(" "))
                    return false;
            }
        return true;
    }
}