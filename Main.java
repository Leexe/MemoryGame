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
  private String guess = "";
  private String sequence = "";

  // Main Method
  public static void main(String[] args) {
    // Objects 
    MemoryGameGUI game = new MemoryGameGUI();
    Main world = new Main();

    // Randomizes the string list into a new list
    world.tempMemoryStr = RandomPermutation.next(world.memoryStr);
    // Debug: 
    System.out.println(Arrays.toString(world.tempMemoryStr));

    // Adds all of the items in the list into a string
    for (String str : world.tempMemoryStr) {
      world.sequence += str;
    }

    // Creates a new game instance
    world.guess = game.playSequence(world.tempMemoryStr, 0.5);

    // If the player guesses the sequence correct, add score & game
    if (world.guess == world.sequence) {
      world.score++;
      world.games++;
    }
    // Else ask the player to try again  
    else {
      game.tryAgain();
    }

    // Creates a try again screen; if yes, replay game
    if (game.playAgain() == true) {
      world.guess = game.playSequence(world.tempMemoryStr, 0.5);
    }
    // else, display score and quit the game 
    else {
      game.showScore(world.score, world.games);
      game.quit();
    }
  }
}