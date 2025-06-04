import java.util.Scanner;


class TicTacToe {
    private String [][] ticTacToe;

    public TicTacToe(String [][] ticTacToe) {
        this.ticTacToe = new String[3][3];

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                this.ticTacToe[i][j] = ticTacToe[i][j];
            }
        }
    }

    public void printTable () {

      for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
           if(j != 2) {
            System.out.printf("%c |" , ticTacToe[i][j]);
           } 
           else if(j == 2) {
             System.out.printf("%c \n", ticTacToe[i][j]);
           }
        }
        System.out.print("--------------------");
        System.out.print("\n");
      }
    }

}
