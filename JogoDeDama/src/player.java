import java.util.Scanner;

public class player {
    private char color;

    public player(char color) {
        this.color = color;
    }

    public void makeMove(board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sua vez! Insira a coordenada de início (linha e coluna) e a coordenada de fim:");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        int endX = scanner.nextInt();
        int endY = scanner.nextInt();
        boolean validMove = board.movePiece(startX, startY, endX, endY);
        if (!validMove) {
            System.out.println("Movimento inválido! Tente novamente.");
            makeMove(board);
        }
    }

    public char getColor() {
        return color;
    }
}

