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
  private String[] memoryStr = {"y", "x", "b"};
  private String[] tempMemoryStr = {};
  private int score = 0;
  private int games = 0;
  private String guess = "";
  private String sequence = "";

  // End game method
  public void endGame(int score, int games){
    game.showScore(score, games);
    game.quit();
  }

  // Main Method
  public static void main(String[] args) {
    MemoryGameGUI game = new MemoryGameGUI();
    Main world = new Main();
    
    // Randomizes the string list into a new list
    world.tempMemoryStr = RandomPermutation.next(world.memoryStr);

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

    // Creates a try again
    if (game.playAgain() == true) {
      world.guess = game.playSequence(world.tempMemoryStr, 0.5);
    }
    else {
      world.quit(world.score, world.games);
    }
  }
}