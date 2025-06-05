import java.util.Random;

public class AiPlayer extends Player {

  protected String difficulty;

  public AiPlayer(String symbol, boolean isTurn, String difficulty) {
    super(symbol, isTurn);
    this.difficulty = difficulty;
  }

  public void makeMove(TicTacToe game) {

    String[][] board = game.getBoard();
    Random random = new Random();
    boolean isValid = false;

    switch (this.difficulty) {
      case "Easy":
        while (!isValid) {
          String chosenPosition = Integer.toString(random.nextInt(9) + 1);
          isValid = game.makeMove(chosenPosition, this.symbol);
        }
        break;

      case "Hard":

        String[] corners = { "1", "3", "7", "9" };
        boolean almostWinning = false;
        boolean almostLosing = false;
        String situation = null;
        int countConsecutives = 0;

        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
          }
        }

    }

  }
}
