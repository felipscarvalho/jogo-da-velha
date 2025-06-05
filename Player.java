import java.util.Scanner;

class Player {
  protected String symbol;

  private static final Scanner scanner = new Scanner(System.in);

  public Player(String symbol) {
    this.symbol = symbol;
  }

  public static String chooseSymbol() {

    String symbol = null;

    while (symbol == null) {
      System.out.println("Choose your symbol:");
      System.out.println("1) X \n 2) O");
      System.out.println("Enter 1 or 2:");
      int choice = scanner.nextInt();

      if (choice == 1) {
        symbol = "X";
      } else if (choice == 2) {
        symbol = "O";
      } else {
        System.out.println("Please pick a valid option");
      }
    }

    return symbol;
  }

  public static int chooseNumberOfPlayers() {
    int playersCount = 0;

    while (playersCount == 0) {
      System.out.println("Choose the number of players:");
      System.out.println("Type 1 to play against the AI");
      System.out.println("Type 2 to play against another player");
      playersCount = scanner.nextInt();

      if (playersCount != 1 && playersCount != 2) {
        System.out.println("Please choose a valid number of players");
        playersCount = 0;
      }
    }

    return playersCount;
  }

  public static String chooseDifficulty() {

    String difficulty = null;

    while (difficulty == null) {
      System.out.println("Choose the difficulty of the AI");
      System.out.println("Easy) 1");
      System.out.println("Hard) 2");
      int choice = scanner.nextInt();
      if (choice == 1) {
        difficulty = "Easy";
      } else if (choice == 2) {
        difficulty = "Hard";
      } else {
        System.out.println("Please pick a valid option");
      }
    }

    return difficulty;
  }

}
