package ForInARow;

class ConnectBoard {

    private int numRows;
    private int numCols;
    private int streakToWin;
    private char[][] board;

    public ConnectBoard(int numRows, int numCols, int streakToWin) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.streakToWin = streakToWin;
        board = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public char getToken(int row, int col) {
        return board[row][col];
      }
    
      public void setToken(int row, int col, char c) {
        board[row][col] = c;
      }
    
    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getStreakToWin() {
        return streakToWin;
    }

    public char[][] getBoard() {
        return board;
    }
}
