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
}