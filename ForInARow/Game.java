package ForInARow;

abstract class Game{

    protected int clock;
    protected ConnectBoard board;
    protected Player[] players;
    protected boolean gameOver = false;

    abstract public void printBoard();
    abstract public boolean checkForWin(int row, int col, char token);
    public int getClock() {
        return clock;
    }
    public void setClock(int clock) {
        this.clock = clock;
    }
    
    public boolean isFull() {
        for (int i = 0; i < this.board.getNumCols(); i++) {
            if (this.board.getToken(0, i) == ' ' ) {
                return true;
            }
        }
        return false;
    }

}