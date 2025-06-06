//Eduardo Curcino Monteiro Filho
//Felipe Carvalho Leal 

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
      System.out.print("--------");
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

  public void loopGame() {
    Scanner scanner = new Scanner(System.in);
    String chosenPosition;
    boolean isValid = false;

    while (gameSituation() == null) {
      printTable();
      if (this.playerCount == 2) {
        if (player1.isTurn) {
          isValid = false;
          while (!isValid) {
            System.out.println("Player1: Pick a valid position (1-9)");
            chosenPosition = Integer.toString(scanner.nextInt());
            isValid = makeMove(chosenPosition, player1.symbol);
          }
          player1.isTurn = false;
          player2.isTurn = true;
        } else {
          isValid = false;
          while (!isValid) {
            System.out.println("Player2: Pick a valid position (1-9)");
            chosenPosition = Integer.toString(scanner.nextInt());
            isValid = makeMove(chosenPosition, player2.symbol);
          }
          player1.isTurn = true;
          player2.isTurn = false;
        }
      } else if (this.playerCount == 1) {
        if (player1.isTurn) {
          isValid = false;
          while (!isValid) {
            System.out.println("Pick a valid positon (1-9)");
            chosenPosition = Integer.toString(scanner.nextInt());
            isValid = makeMove(chosenPosition, player1.symbol);
          }
          player1.isTurn = false;
          player2.isTurn = true;
        } else {
          isValid = false;
          while (!isValid) {
            System.out.println("AI is choosing...");
            isValid = ((AiPlayer) player2).makeMove(this);
          }
          player1.isTurn = true;
          player2.isTurn = false;
        }
      }
    }
  }

  public String gameSituation() {

    String result = null;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 1; j++) {
        if (ticTacToe[i][j].equals(ticTacToe[i][j + 1]) && ticTacToe[i][j].equals(ticTacToe[i][j + 2])) {
          switch (ticTacToe[i][j]) {
            case "X":
              System.out.println("X has won");
              break;
            case "O":
              System.out.println("O has won");
              break;
          }
          result = "someoneWon";
          break;
        }
      }
    }

    if (result == null) {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 1; j++) {
          if (ticTacToe[j][i].equals(ticTacToe[j + 1][i]) && ticTacToe[j][i].equals(ticTacToe[j + 2][i])) {
            switch (ticTacToe[j][i]) {
              case "X":
                System.out.println("X has won");
                break;
              case "O":
                System.out.println("O has won");
                break;
            }
            result = "SomeoneWon";
            break;
          }
        }
      }
    }

    if (result == null) {
      for (int i = 0; i < 1; i++) {
        if (ticTacToe[i][i].equals(ticTacToe[i + 1][i + 1]) && ticTacToe[i][i].equals(ticTacToe[i + 2][i + 2])) {
          switch (ticTacToe[i][i]) {
            case "X":
              System.out.println("X has won");
              break;
            case "O":
              System.out.println("O has won");
              break;
          }
          result = "SomeoneWon";
          break;
        }
      }
    }

    if (result == null) {
      if (ticTacToe[0][2].equals(ticTacToe[1][1]) && ticTacToe[2][0].equals(ticTacToe[0][2])) {
        switch (ticTacToe[1][1]) {
          case "X":
            System.out.println("X has won");
            break;
          case "O":
            System.out.println("O has won");
            break;
        }
        result = "someoneWon";
      }
    }

    if (result == null) {
      int countEmpty = 0;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (!ticTacToe[i][j].equals("X") && !ticTacToe[i][j].equals("O")) {
            countEmpty++;
            break;
          }
        }
      }
      if (countEmpty == 0) {
        result = "Draw";
        System.out.println("It's a Draw");
      }
    }

    return result;
  }

}
