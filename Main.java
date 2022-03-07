/**
 * Project 3.6.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 * After wathcing the memory strings appear in the buttons one at a time, the
 * player recreates the sequence from memory.
 */
public class Main
{
  // Variables
  private String[] tempMemoryStr = {};
  private int score = 0;
  private int games = 0;
  private double speedOfGame = 0;
  private int lettersToGuess = 3;
  private static int lettersLimit = 5;
  private String guess = "";
  private String sequence = "";
  private Boolean gameOn = true;

  // Speeds up the time the letters shows up
  public double speedUpGame(double level) {
    double speed = 1.0/3.0/(level)+0.15;
    // Debug
    // System.out.println("Level:" + level);
    // System.out.println("Speed:" + speed);
    return speed;
  }

  // Control the amount of letters that the player has to guess
  public void extendLetters() {
    if (lettersToGuess < lettersLimit && score % 5 == 0) {
      lettersToGuess++;
    }
  }

  // Combines all of the strings in a list of strings
  public String addStringInLists(String[] strList) {
    String newString = "";
    for (String str : strList) {
      newString += str;
    }
    return newString;
  }

  public static void main(String[] args) {
    // Objects 
    MemoryGameGUI game = new MemoryGameGUI();
    Main world = new Main();

    // Randomizes the string list into a new list
    world.tempMemoryStr = RandomPermutation.next(RandomPermutation.RandomLetter(3));
    // Adds all of the items in the list into a string
    world.sequence = world.addStringInLists(world.tempMemoryStr);  
    // Creates a board
    game.createBoard(3, true);
    // Creates a new game instance
    world.speedOfGame = world.speedUpGame(world.score + 1);
    world.guess = game.playSequence(world.tempMemoryStr, world.speedOfGame);
    // Debug: System.out.println(world.guess);

    while (world.gameOn == true) {
      // If the player guesses the sequence correct, add score & game
      if (world.guess != null && world.guess.equals(world.sequence)) {
        game.matched();
        world.score++;
        world.games++;
        // Speeds up game gradually
        world.speedOfGame = world.speedUpGame(world.score + 1);
        // Adds letters gradually
        world.extendLetters();
      }
      // Else ask the player to try again  
      else {
        world.games++;
        game.tryAgain();
      }

      // Creates a try again screen; if yes, replay game
      if (game.playAgain() == true) {
        // Randomizes the new string
        world.tempMemoryStr = RandomPermutation.next(RandomPermutation.RandomLetter(world.lettersToGuess));
        world.sequence = world.addStringInLists(world.tempMemoryStr);
        world.guess = game.playSequence(world.tempMemoryStr, world.speedOfGame);
      }
      // else, display score and quit the game 
      else {
        game.showScore(world.score, world.games);
        game.quit();
        world.gameOn = false;
      }
    }
  }
}