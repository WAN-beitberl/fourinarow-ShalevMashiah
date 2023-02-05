package ForInARow;
import java.util.*;
import java.util.concurrent.TimeUnit;
import ForInARow.Board;

public class main {
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        startGame();
    }

    private static void startGame() throws InterruptedException
    {
        System.out.println("");
        System.out.println("========================================================");
        System.out.println("- Wellcome to Four In A Row game by Shalev Mashiah -");
        System.out.println("=========================================================");
        System.out.println("");

        System.out.println("Please Enter The Number Of Players: ");
        int numOfPlayers = reader.nextInt();

        System.out.println("Please Enter The Number Of Rows:  ");
        int numRows = reader.nextInt();

        System.out.println("Please Enter The Number Of Columns: ");
        int numCols = reader.nextInt();

        System.out.println("Please Enter The Number Of The Size Of The Streak To Win: ");
        int streakToWin = reader.nextInt();

        Player[] players = new Player[numOfPlayers];


        System.out.println("Let's Start By Getting To Know Each Other ");

        for (int i = 0; i < numOfPlayers; i++)
        {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Player " + (i + 1) + "Please Enter Your Name : ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            String playerName = reader.next();

            players[i] = new Player(playerName);

            System.out.println("Nice To See You Here " + playerName);
        }

        System.out.println("After Getting To Know Each Other Let's Start Playing");

        for(int i = 3; i > 0 ; i--)
        {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("The Game Starts In " + i + " ...");
        }

        // Init game.
        ConnectFour game = new ConnectFour(numRows, numCols, streakToWin, players);

        Scanner sc = new Scanner(System.in);
    
        int currPlayer = 0;
        while (!game.gameOver) {
        System.out.println("It's " + players[currPlayer].getName() + "'s turn.");
        System.out.println("Enter column number (0-" + (numCols - 1) + ") to drop your piece: ");
        int col = sc.nextInt();
        while (col < 0 || col >= numCols || game.board.getBoard()[0][col] != ' ') {
            System.out.print("Invalid column, choose again: ");
            col = sc.nextInt();
        }
        game.dropPiece(col, players[currPlayer]);
        game.printBoard();
        if (game.checkForWin(game.getLowestEmptyRow(col), col, players[currPlayer].getToken())) {
            System.out.println(players[currPlayer].getName() + " wins!");
            game.gameOver = true;
        } else if (!game.isFull()) {
            System.out.println("It's a tie!");
            game.gameOver = true;
        } else {
            currPlayer = (currPlayer + 1) % numOfPlayers;
            System.out.println();
        }
    }
    sc.close();
}
}

