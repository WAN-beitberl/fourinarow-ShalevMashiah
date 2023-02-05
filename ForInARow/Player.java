package ForInARow;

public class Player {

    private static int count = 0;
	private int id;
	private int wins;
	private String name;
    private char token;

	// Constructor.
	public Player(String name){
		this.id = count++;
		this.name = name;
		this.wins = 0;
        this.token = name.charAt(0);
	}

	// Get player Token.
    public char getToken() {
        return token;
    }

    // Get player name.
    public String getName() {
        return name;
    }

    
}
