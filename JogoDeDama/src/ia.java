public class ia {
    private char color;

    public ia(char color) {
        this.color = color;
    }

    public static void makeMove(board board) {
    }

    public void ia(board board) {

        boolean moveMade = false;
        while (!moveMade) {
            int startX = (int) (Math.random() * 8);
            int startY = (int) (Math.random() * 8);
            int endX = (int) (Math.random() * 8);
            int endY = (int) (Math.random() * 8);
            moveMade = board.movePiece(startX, startY, endX, endY);
        }
    }

    public char getColor() {
        return color;
    }
}
