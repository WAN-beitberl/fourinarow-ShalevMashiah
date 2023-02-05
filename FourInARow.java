import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class FourInARow {
    private static char[][] board = new char[6][7];
    private static char PLAYER_ONE ;
    private static char PLAYER_TWO ;
    private static char currentPlayer;
    private static Scanner scanner = new Scanner(System.in);
    private static final int TURN_TIME = 30; // 30 seconds per turn
    private static Timer timer = new Timer();
    private static int timeLeft = TURN_TIME;

    private static int playerOneScore = 0;
    private static int playerTwoScore = 0;
    private static String playerOneName;
    private static String playerTwoName;

    public static void main(String[] args) throws InterruptedException{
        int flag = 0;
        Wellcome();
        initBoard();

        while (true) 
        {   
            printBoard();
            int col = getColumn();
            dropPiece(col);
            timeLeft = TURN_TIME;
            if (checkForWin()) {
                System.out.println("Player " + currentPlayer + " Wins!");
                if (currentPlayer == PLAYER_ONE) {
                    playerOneScore++;
                } else {
                    playerTwoScore++;
                }
                System.out.println( playerOneName + " " + playerOneScore + " " + playerTwoName + " " + playerTwoScore);
                flag = 1;
            }
            
            if (checkForTie()) {
                System.out.println("Tie game!");
                flag = 1;
            }
            
            if(flag != 1)
            {
            switchPlayer();
            }
            
            if(flag == 1)
            {
                int start = endGame();
                if(start == 1) System.exit(0);
                if(start == 0)
                {
                    initBoard();
                    flag = 0;
                }
                if(start == 2) 
                 System.out.println("Invalid value Please try again");
            }
        }
    }

    private static int endGame ()
    {
        System.out.println("");
        System.out.println("========================================================");
        System.out.println("- Wellcome to Four In A Row game by Shalev Mashiah -");
        System.out.println("=========================================================");
        System.out.println("");
        int input;
        System.out.println("Would you like another game?");
        System.out.println("Yes - 0 : No 1");
        input = scanner.nextInt();
        if(input == 1) 
        {
            System.out.println("Ok Bye Bye");
            return 1;
        }
        else if(input == 0) return 0;
        return 2;
    }

    private static void Wellcome() throws InterruptedException
    {
        System.out.println("");
        System.out.println("========================================================");
        System.out.println("- Wellcome to Four In A Row game by Shalev Mashiah -");
        System.out.println("=========================================================");
        System.out.println("");
        System.out.println("Let's Start By Getting To Know Each Other ");
        System.out.println("Player One Please Enter Your Name : ");
        playerOneName = scanner.nextLine();
        System.out.println("Nice To See You Here " + playerOneName);
        System.out.println("Player Two Please Enter Your Name : ");
        playerTwoName = scanner.nextLine();
        System.out.println("Nice To See You Here " + playerTwoName);
        System.out.println("After Getting To Know Each Other Let's Start Playing");

        for(int i = 3; i > 0 ; i--)
        {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("The Game Starts In " + i + " ...");
        }

        PLAYER_ONE = playerOneName.charAt(0);
        PLAYER_TWO = playerTwoName.charAt(0);
        currentPlayer = PLAYER_ONE;
    }

    private static void initBoard()
    {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() 
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("");
        System.out.println("========================================================");
        System.out.println("- Wellcome to Four In A Row game by Shalev Mashiah -");
        System.out.println("=========================================================");
        System.out.println("");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {

                System.out.print(" | " + board[i][j]);
            }
            System.out.println(" | ");
            
        }
    }

    private static int getColumn() {
        int col;
        while (true) {
            if (currentPlayer == PLAYER_ONE) {
            System.out.println("");
            System.out.println(playerOneName + " Its your turn know drop a piece in column (0-6): ");
            }

            if (currentPlayer == PLAYER_TWO) {
                System.out.println("");
                System.out.println(playerTwoName + " Its your turn know drop a piece in column (0-6): ");
                }
            col = scanner.nextInt();
            if (col < 0 || col > 6) {
                System.out.println("Invalid column, please try again.");
            } else if (board[0][col] != ' ') {
                System.out.println("Column full, please try again.");
            } else {
                break;
            }
        }
        return col;
    }
    

    private static void dropPiece(int col) {
        for (int i = 5; i >= 0; i--) {
            if (board[i][col] == ' ') {
                board[i][col] = currentPlayer;
                break;
            }
        }
    }

    private static boolean checkForWin() {
        return checkRows() || checkCols() || checkDiags();
    }
    private static boolean checkRows() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i][j+1] == currentPlayer &&
                    board[i][j+2] == currentPlayer &&
                    board[i][j+3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkCols() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++)
            {
                if (board[i][j] == currentPlayer &&
                    board[i+1][j] == currentPlayer &&
                    board[i+2][j] == currentPlayer &&
                    board[i+3][j] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiags() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer &&
                    board[i+1][j+1] == currentPlayer &&
                    board[i+2][j+2] == currentPlayer &&
                    board[i+3][j+3] == currentPlayer) {
                    return true;
                }
                if (board[i][j+3] == currentPlayer &&
                    board[i+1][j+2] == currentPlayer &&
                    board[i+2][j+1] == currentPlayer &&
                    board[i+3][j] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkForTie() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer() {
        if (currentPlayer == PLAYER_ONE) {
            currentPlayer = PLAYER_TWO;
        } else {
            currentPlayer = PLAYER_ONE;
        }
    }

}


