public class piece {
    private boolean isKing;
    private char color; // 'W' para branco, 'B' para preto

    public piece(char color) {
        this.color = color;
        this.isKing = false;
    }

    public boolean isKing() {
        return isKing;
    }

    public void makeKing() {
        this.isKing = true;
    }

    public char getColor() {
        return color;
    }
}
