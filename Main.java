/**
 * Project 3.6.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 * After wathcing the memory strings appear in the buttons one at a time, the
 * player recreates the sequence from memory.
 */
import java.util.*;

public class Main
{
  // Variables
  private String[] memoryStr = {"y", "x", "b"};
  private String[] tempMemoryStr = {};
  private int score = 0;
  private int games = 0;
  private double speedLevel = 0;
  private double speedOfGame = 0;
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

  // Combines all of the strings in a list of strings
  public String addStringInLists(String[] strList) {
    String newString = "";
    for (String str : strList) {
      newString += str;
    }
    return newString;
  }
  
  // Main Method
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
    world.speedLevel++;
    world.speedOfGame = world.speedUpGame(world.speedLevel);
    world.guess = game.playSequence(world.tempMemoryStr, world.speedOfGame);
    // Debug: System.out.println(world.guess);

    while (world.gameOn == true) {
      // If the player guesses the sequence correct, add score & game
      if (world.guess != null && world.guess.equals(world.sequence)) {
        game.matched();
        world.score++;
        world.games++;
        // Speeds up game gradually
        world.speedLevel++;
        world.speedOfGame = world.speedUpGame(world.speedLevel);
      }
      // Else ask the player to try again  
      else {
        world.games++;
        game.tryAgain();
      }

      // Creates a try again screen; if yes, replay game
      if (game.playAgain() == true) {
        // Randomizes the new string
        world.tempMemoryStr = RandomPermutation.next(RandomPermutation.RandomLetter(3));
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