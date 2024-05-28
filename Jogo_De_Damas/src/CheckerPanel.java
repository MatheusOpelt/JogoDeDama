import javax.swing.*;
import java.awt.*;

public class CheckerPanel extends JPanel {
    private Piece piece;
    private int row;
    private int col;

    public CheckerPanel(Piece piece, int row, int col) {
        this.piece = piece;
        this.row = row;
        this.col = col;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        repaint();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if ((getRow() + getCol()) % 2 == 0) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(Color.DARK_GRAY);
        }
        g.fillRect(0, 0, getWidth(), getHeight());

        if (piece != null) {
            if (piece.getColor() == Piece.Color.WHITE) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillOval(10, 10, getWidth() - 20, getHeight() - 20);

            if (piece.getType() == Piece.Type.KING) {
                g.setColor(Color.RED);
                g.drawString("K", getWidth() / 2 - 5, getHeight() / 2 + 5);
            }
        }
    }
}

