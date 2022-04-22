import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
public class Mastermind {
    public static void main(String[] args)throws IOException{
        Computer com = new Computer();
        Player player = new Player();
        boolean gameActive = true;
        boolean currentRoundActive = true;
        Scanner input = new Scanner(System.in);
        try {
            FileInputStream fileIn = new FileInputStream("README.TXT");
            Scanner Finput = new Scanner(fileIn);
            while(true){
                boolean answer = Finput.hasNextLine();
                if(answer == true){
                    String line = Finput.nextLine();
                    System.out.println(line);
                }else{
                    fileIn.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("'README.TXT' not found. Type 'Help' for some help.");
        }
        while(gameActive == true){
            com.generateCode();
            //line below is for testing purposes. Comment out once finalized.
            //com.printCode();
            while(currentRoundActive == true){
                boolean activeTurn = player.turn(com);
                if(activeTurn == false){
                    gameActive = false;
                    currentRoundActive = false;
                    break;
                }
                player.showCodeBreakerCode();
                currentRoundActive = com.evalCodes(player.getCodeBreakerCode(), player.getNumGuesses());
                if(currentRoundActive == false){
                    if(com.numCorrectColorAndPosition(player.getCodeBreakerCode()) == 4){
                        player.addWin();
                        break;
                    }else if((com.numCorrectColorAndPosition(player.getCodeBreakerCode()) != 4) && player.getNumGuesses() == 10){
                        player.addLoss();
                        break;
                    }
                }
            }
            if(gameActive == false){
                break;
            }
            com.printCode();
            player.getWinLoss();
            System.out.println("Total number of guesses: " + player.getNumGuesses());
            while(true){
                System.out.println("Would you like to play another game? [Y/N]: ");
                String playAgain = input.next();
                if(playAgain.equals("Y")){
                    player.resetAttributes();
                    com.resetEvals();
                    break;
                }else if(playAgain.equals("N")){
                    gameActive = false;
                    break;
                }else{
                    System.out.println("'" + playAgain + "' " + "is not a valid choice.");
                }
            }
        }
        com.printCode();
        player.getWinLoss();

        System.out.println("Thanks for playing!");
    } 
}
