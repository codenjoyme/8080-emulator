package spec;

public class Pair<V, W> {

    private V left;
    private W right;

    public Pair(V left, W right) {
        this.left = left;
        this.right = right;
    }

    public static <V, W> Pair<V, W> of(V left, W right) {
        return new Pair<>(left, right);
    }

    public V getLeft() {
        return left;
    }

    public W getRight() {
        return right;
    }

    public void setLeft(V left) {
        this.left = left;
    }

    public void setRight(W right) {
        this.right = right;
    }
}
