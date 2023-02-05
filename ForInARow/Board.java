package ForInARow;

abstract class Board {

    // Declare Properties.
	protected int rows;
	protected int cols;
    protected char[][] board;

    // Constructor.
	public Board(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.board = new char[this.rows][this.cols];
	}

	abstract public void initBoard();
}
