public class game {
    private board board;
    private player player;
    private ia ai;
    private boolean playerTurn;

    public game() {
        board = new board();
        player = new player('W');
        ai = new ia('B');
        playerTurn = true;
    }

    public void start() {
        while (true) {
            board.printBoard();
            if (playerTurn) {
                player.makeMove(board);
            } else {
                ia.makeMove(board);
            }
            playerTurn = !playerTurn;
        }
    }
}

