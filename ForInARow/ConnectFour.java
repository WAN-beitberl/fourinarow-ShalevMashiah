package ForInARow;
import java.util.*;
class ConnectFour extends Game {

    public ConnectFour(int numRows, int numCols, int streakToWin, Player[] players) {
        clock = 0;
        board = new ConnectBoard(numRows, numCols, streakToWin);
        this.players = players;
    }

    
    public void dropPiece(int col, Player player) {
        int row = getLowestEmptyRow(col);
        board.getBoard()[row][col] = player.getToken();
    }

    int getLowestEmptyRow(int col) {
        for (int i = board.getNumRows() - 1; i >= 0; i--) {
            if (board.getBoard()[i][col] == ' ') {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void printBoard() 
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
        for (int i = 0; i < board.getNumRows(); i++) {
            for (int j = 0; j < board.getNumCols(); j++) {

                System.out.print(" | " + this.board.getBoard()[i][j]);
            }
            System.out.println(" | ");
            
        }
    }

    private boolean checkRowWin(int row, char token) {
        int streak = 0;
        for (int col = 0; col < board.getNumCols(); col++) {
            if (board.getToken(row, col) == token) {
                streak++;
                if (streak == board.getStreakToWin()) {
                    return true;
                }
            } else {
                streak = 0;
            }
        }
        return false;
    }
    
    private boolean checkColWin(int col, char token) {
        int streak = 0;
        for (int row = 0; row < board.getNumRows(); row++) {
            if (board.getToken(row, col) == token) {
                streak++;
                if (streak == board.getStreakToWin()) {
                    return true;
                }
            } else {
                streak = 0;
            }
        }
        return false;
    }
    
    private boolean checkDiagWin1(int row, int col, char token) {
        int streak = 0;
        int r, c;
        for (r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if (board.getToken(r, c) == token) {
                streak++;
                if (streak == board.getStreakToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }
        for (r = row + 1, c = col + 1; r < board.getNumRows() && c < board.getNumCols(); r++, c++) {
            if (board.getToken(r, c) == token) {
                streak++;
                if (streak == board.getStreakToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private boolean checkDiagWin2(int row, int col, char token) {
        int streak = 0;
        int r, c;
        for (r = row, c = col; r >= 0 && c < board.getNumCols(); r--, c++) {
            if (board.getToken(r, c) == token) {
                streak++;
                if (streak == board.getStreakToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }
        for (r = row + 1, c = col - 1; r < board.getNumRows() && c >= 0; r++, c--) {
            if (board.getToken(r, c) == token) {
                streak++;
                if (streak == board.getStreakToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }
    
    public boolean checkForWin(int row, int col, char token) {
        return checkRowWin(row, token) || checkColWin(col, token) || checkDiagWin1(row, col, token) || checkDiagWin2(row, col, token);
    }    
}
