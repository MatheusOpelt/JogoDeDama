import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckersUI {
    private JFrame frame;
    private CheckerPanel[][] panels;
    private CheckersGame game;
    private AIPlayer aiPlayer;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public CheckersUI() {
        game = new CheckersGame();
        aiPlayer = new AIPlayer(game);

        frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(8, 8));

        panels = new CheckerPanel[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                panels[row][col] = new CheckerPanel(null, row, col);
                panels[row][col].setPreferredSize(new Dimension(80, 80));
                panels[row][col].addMouseListener(new PanelClickListener());
                frame.add(panels[row][col]);
            }
        }

        updateBoard();
        frame.pack();
        frame.setVisible(true);
    }

    private void updateBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                panels[row][col].setPiece(game.getBoard().getPiece(row, col));
            }
        }
    }

    private class PanelClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            CheckerPanel panel = (CheckerPanel) e.getSource();
            int row = panel.getRow();
            int col = panel.getCol();

            if (selectedRow == -1 && selectedCol == -1) {
                if (game.getBoard().getPiece(row, col) != null &&
                        game.getBoard().getPiece(row, col).getColor() == Piece.Color.WHITE) {
                    selectedRow = row;
                    selectedCol = col;
                    panel.setBackground(Color.YELLOW); // Indica que a peça foi selecionada
                }
            } else {
                if (game.movePiece(selectedRow, selectedCol, row, col)) {
                    updateBoard();
                    if (!game.isGameOver()) {
                        aiPlayer.makeMove();
                        updateBoard();
                    }
                }
                selectedRow = -1;
                selectedCol = -1;
                resetPanelColors(); // Reseta as cores dos painéis
            }
        }
    }

    private void resetPanelColors() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    panels[row][col].setBackground(Color.LIGHT_GRAY);
                } else {
                    panels[row][col].setBackground(Color.DARK_GRAY);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CheckersUI::new);
    }
}

