public class CheckersGame {
    private Board board;
    private Piece.Color currentPlayer;

    public CheckersGame() {
        board = new Board();
        currentPlayer = Piece.Color.WHITE;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        Piece piece = board.getPiece(startX, startY);
        if (piece == null || !board.isWithinBounds(endX, endY) || piece.getColor() != currentPlayer) {
            return false;
        }

        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        if (deltaX == 1 && deltaY == 1 && board.getPiece(endX, endY) == null) {
            return true;
        } else if (board.isCaptureMove(startX, startY, endX, endY)) {
            return true;
        }

        return false;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY) {
        System.out.println("Trying to move piece from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
        if (isValidMove(startX, startY, endX, endY)) {
            System.out.println("Move is valid");
            Piece piece = board.getPiece(startX, startY);
            board.setPiece(startX, startY, null);
            board.setPiece(endX, endY, piece);

            if (board.isCaptureMove(startX, startY, endX, endY)) {
                board.capturePiece(startX, startY, endX, endY);
            }

            checkForPromotion(endX, endY);
            switchPlayer();
            return true;
        } else {
            System.out.println("Move is not valid");
        }
        return false;
    }

    private void checkForPromotion(int row, int col) {
        Piece piece = board.getPiece(row, col);
        if (piece != null) {
            if ((piece.getColor() == Piece.Color.WHITE && row == 0) ||
                    (piece.getColor() == Piece.Color.BLACK && row == 7)) {
                piece.setType(Piece.Type.KING);
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    }

    public boolean isGameOver() {
        return !hasValidMoves(Piece.Color.WHITE) || !hasValidMoves(Piece.Color.BLACK);
    }

    private boolean hasValidMoves(Piece.Color color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getColor() == color) {
                    for (int endX = 0; endX < 8; endX++) {
                        for (int endY = 0; endY < 8; endY++) {
                            if (isValidMove(row, col, endX, endY)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void undoMove(int startX, int startY, int endX, int endY, Piece capturedPiece) {
        Piece piece = board.getPiece(endX, endY);
        board.setPiece(startX, startY, piece);
        board.setPiece(endX, endY, null);
        if (capturedPiece != null) {
            int middleX = (startX + endX) / 2;
            int middleY = (startY + endY) / 2;
            board.setPiece(middleX, middleY, capturedPiece);
        }
        switchPlayer();
    }
}

