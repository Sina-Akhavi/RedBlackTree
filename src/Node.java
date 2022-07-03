public class Node {
    public Node right, left;

    public Node parent;

    public int data;

    public Color color;

    public Node(int data) {
        this.data = data;
        color = Color.RED;
        this.right = null;
        this.left = null;
        this.parent = null;
    }

    public Node clone() {
        Node newNode = new Node(data);
        newNode.right = this.right;
        newNode.left = this.left;
        newNode.parent = this.parent;

        return newNode;
    }

    @Override
    public String toString() {
        String output = "";

        int parentValue;
        if (parent != null) {
           parentValue = parent.data;
        } else {
            parentValue = -1000000000;
        }
        output = "value = " + this.data + ",  parent= " + parentValue;
        return output;
    }
}
