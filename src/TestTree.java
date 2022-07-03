public class TestTree {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.insert(79);
        tree.insert(15);
        tree.insert(10);
        tree.insert(5);
        tree.insert(7);
        tree.insert(2);
        tree.insert(0);
        tree.insert(1);

        tree.printPreorder();
        System.out.println("---------------\n");

        System.out.println(tree.find(2));
    }
}
