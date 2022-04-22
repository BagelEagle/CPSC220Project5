import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class Computer {
    private ArrayList<ArrayList<String>> pastCodeEvals;
    private ArrayList<ColoredPegs> code; 
    private ArrayList<ColoredPegs> validPegs;
    public Computer(){
        this.code = new ArrayList<ColoredPegs>();
        this.pastCodeEvals = new ArrayList<ArrayList<String>>();
        this.validPegs = new ArrayList<ColoredPegs>();
        this.validPegs.add(ColoredPegs.RED);
        this.validPegs.add(ColoredPegs.YELLOW);
        this.validPegs.add(ColoredPegs.BLUE);
        this.validPegs.add(ColoredPegs.GREEN);
        this.validPegs.add(ColoredPegs.BLACK);
        this.validPegs.add(ColoredPegs.WHITE);
        }
    /**Generates a code for the computer player. This code is kept private from the user.
     * The objective is to correctly guess this code each round.
     */
    public void generateCode(){
        Random random = new Random();
        for(int i = 0; i < 4; i++){
            int randomNum = random.nextInt(6);
            this.code.add(i, this.validPegs.get(randomNum));
        }
    }
    /**Reveals the computer generated code via print statements.
     * 
    */
    public void printCode(){
        System.out.println("This is the computer's code: ");
        for(ColoredPegs peg: code){
            System.out.println(peg);
        }
    }
    /**
     * Checks player code if it matches the computer code's position and color.
     * @param codeBreakerCode this is the code that the player made after inputting 4 valid peg colors.
     * @return the number of correct pegs in the correct position.
     */
    public int numCorrectColorAndPosition(ArrayList<ColoredPegs> codeBreakerCode){
        int correctPegs = 0;
        for(int i = 0; i < 4; i++){
            if(this.code.get(i).equals(codeBreakerCode.get(i))){
                correctPegs += 1;
            }
        }
        return correctPegs;
    }
    /**
     * Checks player code if it has matching pegs but in the wrong position.
     * @param codeBreakerCode this is the code that the player made after inputting 4 valid peg colors.
     * @param correctPegs the number of correct pegs in the correct position.
     * @return the number of pegs that are of the correct color but are not in the correct position.
     * NOTE: Suppose the computer has 1 red peg and 3 blue pegs, and the player guesses 2 red pegs
     * and 2 yellow pegs, and all of thos pegs are in the incorrect position, this method will return
     * 1. It does not count the 2nd red peg.
     */
    public int numCorrectColorWrongPosition(ArrayList<ColoredPegs> codeBreakerCode, int correctPegs){
        int playerPegOccurances = 0;
        int comPegOccurances = 0;
        int partialMatch = 0;
        int correctColor = 0;
        for(int i = 0; i < 6; i++){
            playerPegOccurances = Collections.frequency(codeBreakerCode, this.validPegs.get(i));
            comPegOccurances = Collections.frequency(this.code, this.validPegs.get(i));
            if((playerPegOccurances > 0) && (comPegOccurances > 0)){
                if(playerPegOccurances == comPegOccurances){
                    correctColor += playerPegOccurances;
                }else if(playerPegOccurances > comPegOccurances){
                    correctColor += comPegOccurances;
                }else if(playerPegOccurances < comPegOccurances){
                    correctColor += playerPegOccurances;
                }
            }
        }
        partialMatch = correctColor - correctPegs;

        return partialMatch;
    }
    /**
     * Evalutes player code. Terminates the game if the player has 10 wrong guesses or
     * successfully guessed the computer's code.
     * @param codeBreakerCode this is the code that the player made after inputting 4 valid peg colors.
     * @param numGuesses the number of guesses that the player has made so far
     * @return boolean that will flag in the main method whether to continue the round or not.
     */
    public boolean evalCodes(ArrayList<ColoredPegs> codeBreakerCode, int numGuesses){
        boolean guessAgain = true;
        ArrayList<String> evalResult = new ArrayList<String>();
        int evalCorrectPegs = numCorrectColorAndPosition(codeBreakerCode);
        String correctCandP = "Number of pegs of correct color and in the correct position: " + evalCorrectPegs;
        String correctCwrongP = "Number of pegs of correct color in the wrong position: " + numCorrectColorWrongPosition(codeBreakerCode, evalCorrectPegs);
        System.out.println(correctCandP);
        if(evalCorrectPegs != 4){
            System.out.println(correctCwrongP);
        }
        evalResult.add(correctCandP);
        evalResult.add(correctCwrongP);
        this.pastCodeEvals.add(evalResult);
        if((numGuesses == 10) || (evalCorrectPegs == 4)){
            guessAgain = false;
        }
        return guessAgain;
    }
    /**
     * Prints out the result of the evalCodes method
     * @param codeBreakerCode this is the code that the player made after inputting 4 valid peg colors.
     */
    public void showEvalResult(ArrayList<ColoredPegs> codeBreakerCode){
        int evalCorrectPegs = numCorrectColorAndPosition(codeBreakerCode);
        int partCorrect = numCorrectColorWrongPosition(codeBreakerCode, evalCorrectPegs);
        System.out.println("Number of pegs of correct color and in the correct position: " + evalCorrectPegs);
        System.out.println("Number of pegs of correct color in the wrong position: " + partCorrect);
    }
    /**
     * Resets the result of code evaluations. Best used at the end of a round.
     */
    public void resetEvals(){
        this.pastCodeEvals.clear();
    }
}