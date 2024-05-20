import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class BoardView extends GridPane {
    private static final int SIZE = 8;
    public static final int TILE_SIZE = 100;
    private PieceView[][] pieces;

    public BoardView() {
        pieces = new PieceView[SIZE][SIZE];
        drawBoard();
        initializePieces();
    }

    private void drawBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.BEIGE);
                } else {
                    tile.setFill(Color.BROWN);
                }
                add(tile, col, row);
            }
        }
    }

    private void initializePieces() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if ((row + col) % 2 != 0 && (row < 3 || row > 4)) {
                    Color color = (row < 3) ? Color.BLACK : Color.WHITE;
                    PieceView piece = new PieceView(color, false);
                    pieces[row][col] = piece;
                    add(piece, col, row);
                    piece.setOnMouseClicked(this::handlePieceClick);
                }
            }
        }
    }

    private void handlePieceClick(MouseEvent event) {
        PieceView piece = (PieceView) event.getSource();

    }
}

