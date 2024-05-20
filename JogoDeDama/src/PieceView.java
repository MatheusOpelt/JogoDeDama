import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PieceView extends Circle {
    private boolean isKing;

    public PieceView(Color color, boolean isKing) {
        super(BoardView.TILE_SIZE / 2 * 0.8);
        setFill(color);
        this.isKing = isKing;
    }

    public void makeKing() {
        isKing = true;
        setStroke(Color.GOLD);
        setStrokeWidth(4);
    }

    public boolean isKing() {
        return isKing;
    }
}
