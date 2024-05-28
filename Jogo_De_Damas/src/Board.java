public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        setupBoard();
    }

    private void setupBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 != 0) {
                    if (row < 3) {
                        board[row][col] = new Piece(Piece.Color.BLACK);
                    } else if (row > 4) {
                        board[row][col] = new Piece(Piece.Color.WHITE);
                    }
                }
            }
        }
    }

    public Piece getPiece(int row, int col) {
        if (isWithinBounds(row, col)) {
            return board[row][col];
        }
        return null;
    }

    public void setPiece(int row, int col, Piece piece) {
        if (isWithinBounds(row, col)) {
            board[row][col] = piece;
        }
    }

    public boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public boolean isCaptureMove(int startX, int startY, int endX, int endY) {
        int middleX = (startX + endX) / 2;
        int middleY = (startY + endY) / 2;
        return Math.abs(endX - startX) == 2 && Math.abs(endY - startY) == 2 &&
                board[middleX][middleY] != null && board[middleX][middleY].getColor() != board[startX][startY].getColor();
    }

    public void capturePiece(int startX, int startY, int endX, int endY) {
        int middleX = (startX + endX) / 2;
        int middleY = (startY + endY) / 2;
        board[middleX][middleY] = null;
    }
}
