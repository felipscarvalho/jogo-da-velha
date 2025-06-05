import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

public class TicTacToe {

  protected int playerCount;
  protected Player player1;
  protected Player player2;
  private String[][] ticTacToe;
  private HashMap<String, int[]> positionsMap;

  public TicTacToe() {
    ticTacToe = new String[3][3];

    initializeBoard();
    setupPlayers();
  }

  private void initializeBoard() {
    int count = 1;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        ticTacToe[i][j] = Integer.toString(count);
        count++;
      }
    }

    initializeHashMap();
  }

  private void initializeHashMap() {
    positionsMap = new HashMap<>();
    int count = 1;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        positionsMap.put(Integer.toString(count), new int[] { i, j });
        count++;
      }
    }
  }

  public void printTable() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (j != 2) {
          System.out.printf("%s |", ticTacToe[i][j]);
        } else if (j == 2) {
          System.out.printf("%s \n", ticTacToe[i][j]);
        }
      }
      System.out.print("--------------------");
      System.out.print("\n");
    }
  }

  public String[][] getBoard() {
    return ticTacToe;
  }

  public void setupPlayers() {

    Random random = new Random();
    this.playerCount = Player.chooseNumberOfPlayers();
    String difficulty = null;

    this.player1 = new Player(Player.chooseSymbol(), random.nextBoolean());

    String symbol2 = player1.symbol.equals("X") ? "O" : "X";

    if (playerCount == 1) {
      difficulty = Player.chooseDifficulty();
      this.player2 = new AiPlayer(symbol2, !(player1.isTurn), difficulty);
    } else if (playerCount == 2) {
      this.player2 = new Player(symbol2, !(player1.isTurn));
    }

    System.out.println("Player 1 is the symbol " + player1.symbol);
    System.out.println("Player 2 is the symbol " + player2.symbol);
  }

  public boolean makeMove(String position, String playerSymbol) {

    if (!positionsMap.containsKey(position))
      return false;

    int coords[] = positionsMap.get(position);
    int row = coords[0];
    int column = coords[1];

    if (!ticTacToe[row][column].equals("X") && !ticTacToe[row][column].equals("O")) {
      ticTacToe[row][column] = playerSymbol;
      return true;
    }

    return false;

  }

  public static void main(String[] args) {
    TicTacToe tic = new TicTacToe();

  }
}
