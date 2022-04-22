import java.util.Scanner;
import java.util.ArrayList;
public class Player {

    private String name;
    private int wins;
    private int losses;
    private int numGuesses;
    private ArrayList<String> validGuesses;
    private ArrayList<String> validQuit;
    private ArrayList<ColoredPegs> codeBreakerCode;

    public Player(){
        this.name = "";
        this.wins = 0;
        this.losses = 0;
        this.numGuesses = 0;
        this.validGuesses = new ArrayList<String>();
        this.validQuit = new ArrayList<String>();
        this.codeBreakerCode = new ArrayList<ColoredPegs>();
        this.initValidGuesses();
        this.initValidQuit();
    }
    public Player(String name){
        this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.numGuesses = 0;
        this.validGuesses = new ArrayList<String>();
        this.validQuit = new ArrayList<String>();
        this.codeBreakerCode = new ArrayList<ColoredPegs>();
        this.initValidGuesses();
        this.initValidQuit();
    }
    /**
     * Initialize an ArrayList of valid guesses.
     */
    public void initValidGuesses(){
        this.validGuesses.add("RED");
        this.validGuesses.add("Red");
        this.validGuesses.add("red");
        this.validGuesses.add("YELLOW");
        this.validGuesses.add("Yellow");
        this.validGuesses.add("yellow");
        this.validGuesses.add("BLUE");
        this.validGuesses.add("Blue");
        this.validGuesses.add("blue");
        this.validGuesses.add("GREEN");
        this.validGuesses.add("Green");
        this.validGuesses.add("green");
        this.validGuesses.add("BLACK");
        this.validGuesses.add("Black");
        this.validGuesses.add("black");
        this.validGuesses.add("WHITE");
        this.validGuesses.add("White");
        this.validGuesses.add("white");
    }
    /**
     * Initialize an ArrayList of valid quit commands.
     */
    public void initValidQuit(){
        this.validQuit.add("QUIT");
        this.validQuit.add("Quit");
        this.validQuit.add("quit");
        this.validQuit.add("Q");
        this.validQuit.add("q");
        this.validQuit.add("EXIT");
        this.validQuit.add("Exit");
        this.validQuit.add("exit");
    }
    /**
     * Gets name of player.
     * @return name of player
     */
    public String getName(){
        return this.name;
    }
    /**
     * Sets name of player
     * @param playerName name of player
     */
    public void setName(String playerName){
        this.name = playerName;
    }
    /**
     * Increments number of wins by 1.
     */
    public void addWin(){
        this.wins += 1;
    }
    /**
     * Increments number of losses by 1.
     */
    public void addLoss(){
        this.losses += 1;
    }
    /**
     * Prints out the number of player wins and losses.
     */
    public void getWinLoss(){
        System.out.println("Player wins: "+ this.wins);
        System.out.println("Player losses: "+ this.losses);
    }
    /**
     * Gets number of guesses
     * @return int number of player's guesses
     */
    public int getNumGuesses(){
        return this.numGuesses;
    }
    /**
     * Increments number of guesses by 1.
     */
    public void addOneToNumGuesses(){
        this.numGuesses += 1;
    }
    /**
     * Reset attributes necessary to start a new game.
     */
    public void resetAttributes(){
        this.numGuesses = 0;
        this.codeBreakerCode.clear();
    }
    /**
     * resets player code to start a new game.
     */
    public void resetCBCode(){
        this.codeBreakerCode.clear();
    }
    /**
     * Converts user String input into an enum.
     * @param strGuess User String input
     * @return ColoredPeg enum
     */
    public ColoredPegs guessConverter(String strGuess){
        ColoredPegs convertedGuess;
        if(strGuess.equals(this.validGuesses.get(0))||
        strGuess.equals(this.validGuesses.get(1))||
        strGuess.equals(this.validGuesses.get(2))){
            convertedGuess = ColoredPegs.RED;
        }else if(strGuess.equals(this.validGuesses.get(3))||
        strGuess.equals(this.validGuesses.get(4))||
        strGuess.equals(this.validGuesses.get(5))){
            convertedGuess = ColoredPegs.YELLOW;
        }else if(strGuess.equals(this.validGuesses.get(6))||
        strGuess.equals(this.validGuesses.get(7))||
        strGuess.equals(this.validGuesses.get(8))){
            convertedGuess = ColoredPegs.BLUE;
        }else if(strGuess.equals(this.validGuesses.get(9))||
        strGuess.equals(this.validGuesses.get(10))||
        strGuess.equals(this.validGuesses.get(11))){
            convertedGuess = ColoredPegs.GREEN;
        }else if(strGuess.equals(this.validGuesses.get(12))||
        strGuess.equals(this.validGuesses.get(13))||
        strGuess.equals(this.validGuesses.get(14))){
            convertedGuess = ColoredPegs.BLACK;
        }else{
            convertedGuess = ColoredPegs.WHITE;
        }
        return convertedGuess;
    }
    /**
     * Shows player's code.
     */
    public void showCodeBreakerCode(){
        ArrayList<ColoredPegs> cbCode = this.getCodeBreakerCode();
        System.out.println("This is your code:"); 
        for(ColoredPegs Peg: cbCode){
            System.out.println(Peg);
        }
    }
    /**
     * Gets player's code
     * @return player's code
     */
    public ArrayList<ColoredPegs> getCodeBreakerCode(){
        return this.codeBreakerCode;
    }
    /**
     * Prompts for user input. Checks if the input is a valid entry.
     * Displays Help if Help is typed.
     * Quits game if Quit is typed.
     * If a valid entry is typed in the form of a guess, the corresponding
     * ColoredPeg enum will be added to player code.
     * If player has made an invalid entry, a helpful printout will alert the player
     * and direct them to invoke the Help command for a list of potentially valid input.
     * Invalid commands keep the player in this loop until a valid selection has been made.
     * @param com Computer class player
     * @return String of valid guess or valid quit command
     */
    public String makeSelection(Computer com){
        Scanner playerSelection = new Scanner(System.in);
        String selection;
        while(true){
            System.out.println("Please enter your guess: ");
            selection = playerSelection.next();
            if(this.validGuesses.contains(selection)){
                break;
            }else if(selection.equals("Help")){
                this.help();
            }else if(this.validQuit.contains(selection)){
                break;
            }else{
                System.out.println(selection + " is not a valid entry.");
                System.out.println("Enter 'Help' for a list of valid entries.");
            }
        }
        return selection;
    }
    /**
     * Player turn method. Prompts player for entries to their code.
     * Converts player entries into valid commands or ColoredPeg enum
     * @param com Computer class player
     * @return boolean that tells main program if the player has quit or
     * not.
     */
    public boolean turn(Computer com){
        this.resetCBCode();
        boolean playerStillPlaying = true;
        for(int i = 0; i <4; i++){
            String playerChoice = this.makeSelection(com);
            if(this.validQuit.contains(playerChoice)){
                playerStillPlaying = false;
                break;
            }
            ColoredPegs convertedPeg = this.guessConverter(playerChoice);
            this.codeBreakerCode.add(i, convertedPeg);
        }
        if(playerStillPlaying == true){
            this.addOneToNumGuesses();
        }
        return playerStillPlaying;
    }
    /**
     * Prints out a list of valid commands
     */
    public void help(){
        System.out.println("Guesses can be all UPPERCASE, Firstletter uppercase, or lowercase.");
        System.out.println("There are six valid colors to select from that are valid:");
        System.out.println("RED, YELLOW, BLUE, GREEN, BLACK, WHITE");
        System.out.println(" ");
        System.out.println("Some examples of valid guesses: ");
        System.out.println("'Red', 'RED', and 'red'");
        System.out.println(" ");
        System.out.println("To quit the program, the following are valid commands: ");
        System.out.println("'QUIT', 'Quit', 'quit', 'Q', 'q', 'EXIT', 'Exit', and 'exit'.");
    }
}