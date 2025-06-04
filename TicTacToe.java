import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

class TicTacToe {
    private String [][] ticTacToe;
    private HashMap<String, int[]> positionsMap;

    public TicTacToe() {
        ticTacToe = new String[3][3];
    
        initializeBoard();
    }

    private void initializeBoard() {
        int count = 1;

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                ticTacToe[i][j] = Integer.toString(count);
                count++;
            }
        }

        initializeHashMap();
    }

    private void initializeHashMap() {
        positionsMap = new HashMap<>();
        int count = 1;

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                positionsMap.put(Integer.toString(count), new int[] {i, j});
                count++;
            }
        }
    }

    public void testPrint() {
        System.out.println(Arrays.toString(positionsMap.get("2")));
    }

    public static void main(String[] args) {
        TicTacToe tic = new TicTacToe();

        tic.testPrint();
    }
}