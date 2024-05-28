public class AIPlayer {
    private CheckersGame game;

    public AIPlayer(CheckersGame game) {
        this.game = game;
    }

    public void makeMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;

        for (int startX = 0; startX < 8; startX++) {
            for (int startY = 0; startY < 8; startY++) {
                Piece piece = game.getBoard().getPiece(startX, startY);
                if (piece != null && piece.getColor() == Piece.Color.BLACK) {
                    for (int endX = 0; endX < 8; endX++) {
                        for (int endY = 0; endY < 8; endY++) {
                            if (game.isValidMove(startX, startY, endX, endY)) {
                                Piece capturedPiece = executeMove(startX, startY, endX, endY);
                                int score = minimax(game, 3, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                                game.undoMove(startX, startY, endX, endY, capturedPiece);
                                if (score > bestScore) {
                                    bestScore = score;
                                    bestMove = new int[]{startX, startY, endX, endY};
                                }
                            }
                        }
                    }
                }
            }
        }

        if (bestMove != null) {
            game.movePiece(bestMove[0], bestMove[1], bestMove[2], bestMove[3]);
        }
    }

    private Piece executeMove(int startX, int startY, int endX, int endY) {
        Piece capturedPiece = null;
        if (game.getBoard().isCaptureMove(startX, startY, endX, endY)) {
            int middleX = (startX + endX) / 2;
            int middleY = (startY + endY) / 2;
            capturedPiece = game.getBoard().getPiece(middleX, middleY);
        }
        game.movePiece(startX, startY, endX, endY);
        return capturedPiece;
    }

    private int minimax(CheckersGame game, int depth, int alpha, int beta, boolean isMaximizing) {
        if (depth == 0 || game.isGameOver()) {
            return evaluateBoard(game);
        }

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int startX = 0; startX < 8; startX++) {
                for (int startY = 0; startY < 8; startY++) {
                    Piece piece = game.getBoard().getPiece(startX, startY);
                    if (piece != null && piece.getColor() == Piece.Color.BLACK) {
                        for (int endX = 0; endX < 8; endX++) {
                            for (int endY = 0; endY < 8; endY++) {
                                if (game.isValidMove(startX, startY, endX, endY)) {
                                    Piece capturedPiece = executeMove(startX, startY, endX, endY);
                                    int eval = minimax(game, depth - 1, alpha, beta, false);
                                    game.undoMove(startX, startY, endX, endY, capturedPiece);
                                    maxEval = Math.max(maxEval, eval);
                                    alpha = Math.max(alpha, eval);
                                    if (beta <= alpha) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int startX = 0; startX < 8; startX++) {
                for (int startY = 0; startY < 8; startY++) {
                    Piece piece = game.getBoard().getPiece(startX, startY);
                    if (piece != null && piece.getColor() == Piece.Color.WHITE) {
                        for (int endX = 0; endX < 8; endX++) {
                            for (int endY = 0; endY < 8; endY++) {
                                if (game.isValidMove(startX, startY, endX, endY)) {
                                    Piece capturedPiece = executeMove(startX, startY, endX, endY);
                                    int eval = minimax(game, depth - 1, alpha, beta, true);
                                    game.undoMove(startX, startY, endX, endY, capturedPiece);
                                    minEval = Math.min(minEval, eval);
                                    beta = Math.min(beta, eval);
                                    if (beta <= alpha) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return minEval;
        }
    }

    private int evaluateBoard(CheckersGame game) {
        int score = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = game.getBoard().getPiece(row, col);
                if (piece != null) {
                    if (piece.getColor() == Piece.Color.BLACK) {
                        score += (piece.getType() == Piece.Type.KING) ? 5 : 1;
                    } else {
                        score -= (piece.getType() == Piece.Type.KING) ? 5 : 1;
                    }
                }
            }
        }
        return score;
    }
}


