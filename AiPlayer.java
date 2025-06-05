import java.util.Random;

public class AiPlayer extends Player {

  protected String difficulty;

  public AiPlayer(String symbol, boolean isTurn, String difficulty) {
    super(symbol, isTurn);
    this.difficulty = difficulty;
  }

  public boolean makeMove(TicTacToe game) {
    String[][] board = game.getBoard();
    Random random = new Random();
    boolean isValid = false;
    String[] corners = { "1", "3", "7", "9" };

    switch (this.difficulty) {
      case "Easy":
        while (!isValid) {
          String chosenPosition = Integer.toString(random.nextInt(9) + 1);
          isValid = game.makeMove(chosenPosition, this.symbol);
        }
        return isValid;

      case "Hard":

        String enemySymbol = this.symbol.equals("X") ? "0" : "X";
        for (String pos : corners) {
          if (board[0][2].equals(enemySymbol) || board[0][0].equals(enemySymbol) || board[2][2].equals(enemySymbol)
              || board[2][0].equals(enemySymbol)) {
            break;
          } else if (game.makeMove(pos, this.symbol)) {
            isValid = true;
            break;
          }
        }

        if (!isValid) {
          isValid = game.makeMove("5", this.symbol);
        }

        if (!isValid) {
          String bestMove = findBestMove(board, this.symbol);
          if (bestMove != null) {
            isValid = game.makeMove(bestMove, this.symbol);
          }
        }

        while (!isValid) {
          String chosenPosition = Integer.toString(random.nextInt(9) + 1);
          isValid = game.makeMove(chosenPosition, this.symbol);
        }

        return isValid;
    }
    return isValid;
  }

  public String findBestMove(String[][] board, String symbol) {
    int numAllySymbol = 0;
    int numEnemySymbol = 0;
    int numEmptySpace = 0;
    int antiDiagonalNumAllySymbol = 0;
    int antiDiagonalNumEmptySymbol = 0;
    int antiDiagonalNumEnemySymbol = 0;
    int i, j;
    boolean wasWinningMoveFound = false;
    String enemySymbol = symbol.equals("X") ? "O" : "X";
    String bestMove = null;
    String emptySpacePosition = null;

    for (i = 0; i < 3; i++) {
      numEmptySpace = 0;
      numEnemySymbol = 0;
      numAllySymbol = 0;

      for (j = 0; j < 3; j++) {
        if (board[i][j].equals(symbol)) {
          numAllySymbol++;
        } else if (board[i][j].equals(enemySymbol)) {
          numEnemySymbol++;
        } else {
          emptySpacePosition = board[i][j];
          numEmptySpace++;
        }
      }

      if (numAllySymbol == 2 && numEmptySpace == 1) {
        bestMove = emptySpacePosition;
        wasWinningMoveFound = true;
      } else if (numEnemySymbol == 2 && numEmptySpace == 1 && !wasWinningMoveFound) {
        bestMove = emptySpacePosition;
      }
    }

    for (i = 0; i < 3; i++) {
      numEmptySpace = 0;
      numEnemySymbol = 0;
      numAllySymbol = 0;

      for (j = 0; j < 3; j++) {
        if (board[j][i].equals(symbol)) {
          numAllySymbol++;
        } else if (board[j][i].equals(enemySymbol)) {
          numEnemySymbol++;
        } else {
          emptySpacePosition = board[j][i];
          numEmptySpace++;
        }
      }

      if (numAllySymbol == 2 && numEmptySpace == 1) {
        bestMove = emptySpacePosition;
        wasWinningMoveFound = true;
      } else if (numEnemySymbol == 2 && numEmptySpace == 1 && !wasWinningMoveFound) {
        bestMove = emptySpacePosition;
      }
    }

    numEmptySpace = 0;
    numEnemySymbol = 0;
    numAllySymbol = 0;

    for (i = 0; i < 3; i++) {
      if (board[i][i].equals(symbol)) {
        numAllySymbol++;
      } else if (board[i][i].equals(enemySymbol)) {
        numEnemySymbol++;
      } else {
        emptySpacePosition = board[i][i];
        numEmptySpace++;
      }
    }

    if (numAllySymbol == 2 && numEmptySpace == 1) {
      wasWinningMoveFound = true;
      bestMove = emptySpacePosition;
    } else if (numEnemySymbol == 2 && numEmptySpace == 1 && !wasWinningMoveFound) {
      bestMove = emptySpacePosition;
    }

    antiDiagonalNumAllySymbol = 0;
    antiDiagonalNumEmptySymbol = 0;
    antiDiagonalNumEnemySymbol = 0;
    emptySpacePosition = null;

    for (i = 0; i < 3; i++) {
      int row = i;
      int col = 2 - i;

      if (board[row][col].equals(symbol)) {
        antiDiagonalNumAllySymbol++;
      } else if (board[row][col].equals(enemySymbol)) {
        antiDiagonalNumEnemySymbol++;
      } else {
        emptySpacePosition = board[row][col];
        antiDiagonalNumEmptySymbol++;
      }
    }

    if (antiDiagonalNumAllySymbol == 2 && antiDiagonalNumEmptySymbol == 1) {
      wasWinningMoveFound = true;
      bestMove = emptySpacePosition;
    } else if (antiDiagonalNumEnemySymbol == 2 && antiDiagonalNumEmptySymbol == 1 && !wasWinningMoveFound) {
      bestMove = emptySpacePosition;
    }

    return bestMove;
  }
}
