public class Piece {
    public enum Color {
        BLACK, WHITE
    }

    public enum Type {
        REGULAR, KING
    }

    private Color color;
    private Type type;

    public Piece(Color color) {
        this.color = color;
        this.type = Type.REGULAR;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
