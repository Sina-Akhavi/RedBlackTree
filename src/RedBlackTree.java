public class RedBlackTree {
    private Node  root;

    private RotationType type;
    public RedBlackTree() {
        type = RotationType.NONE;
    }

    private void printPreorderUtil(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + "(" + root.color + ")" + " ");
        printPreorderUtil(root.left);
        printPreorderUtil(root.right);
    }

    public void printPreorder() {
        printPreorderUtil(this.root);
    }

    private Node rotateRight(Node myNode) {
        if (myNode == null) {
            System.out.println("myNode is null");
            return null;
        }
        Node A = myNode.left;
        Node T2 = A.right;

        myNode.left = T2;
        A.right = myNode;

        A.parent = myNode.parent;
        myNode.parent = A;

        if (T2 != null) {
            T2.parent = myNode;
        }
        return A;
    }

    private Node rotateLeft(Node myNode) {
        if (myNode == null) {
            System.out.println("myNode is null");
            return null;
        }
        Node C = myNode.right;
        Node T1 = C.left;

        myNode.right = T1;
        C.left = myNode;

        C.parent = myNode.parent;
        myNode.parent = C;

        if (T1 != null) {
            T1.parent = myNode;
        }
        return C;
    }

    public void insert(int data) {
        if (this.root == null) {
            this.root = new Node(data);
            root.color = Color.BLACK;
            return;
        }
        this.root = insertUtil(this.root, data);
    }

    private Node insertUtil(Node root, int data) {
        boolean isRedRedConflict = false;
        if (root == null) {
            return new Node(data);
        }

        if (data > root.data) {
            root.right = insertUtil(root.right, data);
            root.right.parent = root;

            if (root.color == Color.RED && root.right.color == Color.RED) {
                isRedRedConflict = true;
            }
        } else {
            root.left = insertUtil(root.left, data);
            root.left.parent = root;
            if (root.color == Color.RED && root.left.color == Color.RED) {
                isRedRedConflict = true;
            }
        }

        root = handleRotation(root, type);

        if (isRedRedConflict) {

            if (root.parent.right == root) {
                if (root.parent.left == null || root.parent.left.color == Color.BLACK) {
                    if (root.left != null && root.left.color == Color.RED) {
                        type = RotationType.RIGHT_LEFT;
                    } else {
                        type = RotationType.LEFT;
                    }
                } else {
                    root.color = Color.BLACK;
                    if (root.parent == this.root) {
                        root.parent.color = Color.BLACK;
                    } else {
                        root.parent.color = Color.RED;
                    }
                    root.parent.left.color = Color.BLACK;
                }
            } else {
                if (root.parent.right == null || root.parent.right.color == Color.BLACK) {
                    if (root.left != null && root.left.color == Color.RED) {
                        type = RotationType.RIGHT;
                    } else {
                        type = RotationType.LEFT_RIGHT;
                    }
                } else {
                    root.color = Color.BLACK;
                    root.parent.right.color = Color.BLACK;
                    if (root.parent == this.root) {
                        root.parent.color = Color.BLACK;
                    } else {
                        root.parent.color = Color.RED;
                    }
                }
            }
        }

        return root;
    }

    private Node handleRotation(Node root, RotationType type) {
        Node output = null;
        switch (type) {
            case LEFT:
                 output = handleRotateLeft(root);
                break;
            case RIGHT:
                 output = handleRotateRight(root);
                break;
            case LEFT_RIGHT:
                 output = handleLeftRightRotation(root);
                break;
            case RIGHT_LEFT:
                output = handleRightLeftRotation(root);
                break;
            default:
                return root;
        }
        this.type = RotationType.NONE;
        return output;
    }

    private Node handleRotateLeft(Node root) {
        root = rotateLeft(root);
        root.color = Color.BLACK;
        root.left.color = Color.RED;
        return root;
    }

    private Node handleRotateRight(Node root) {
        root = rotateRight(root);
        root.color = Color.BLACK;
        root.right.color = Color.RED;
        return root;
    }

    private Node handleRightLeftRotation(Node root) {
        root.right = rotateRight(root.right);
        root.right.parent = root;

        root = rotateLeft(root);
        root.color = Color.BLACK;
        root.left.color = Color.RED;

        return root;
    }

    private Node handleLeftRightRotation(Node root) {
        root.left = rotateLeft(root.left);
        root.left.parent = root;

        root = rotateRight(root);
        root.color = Color.BLACK;
        root.right.color = Color.RED;

        return root;
    }

    public Node find(int key) {
        Node curNode = this.root;

        while (curNode != null) {
            if (key == curNode.data){
                return curNode;
            } else if (key < curNode.data) {
                curNode = curNode.left;
            } else  {
                curNode = curNode.right;
            }
        }
        return null;
    }

}
