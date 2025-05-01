public class HuffmanNode  implements Comparable<HuffmanNode > {
    char character;
    int frequency;
    HuffmanNode  left, right;

    public HuffmanNode (char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = this.right = null;
    }

    public boolean isSheet() {
        return left == null && right == null;
    }

    public int compareTo(HuffmanNode  otro) {
        return this.frequency - otro.frequency;
    }
}