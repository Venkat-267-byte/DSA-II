class ProductNode {

    int productId;
    String productName;
    int stock;
    int height;

    ProductNode left, right;

    ProductNode(int id, String name, int stockQty) {

        productId = id;
        productName = name;
        stock = stockQty;
        height = 1;
    }
}

class AVLTree {

    int height(ProductNode node) {

        if (node == null)
            return 0;

        return node.height;
    }

    int getBalance(ProductNode node) {

        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    ProductNode rightRotate(ProductNode y) {

        ProductNode x = y.left;
        ProductNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;

        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    ProductNode leftRotate(ProductNode x) {

        ProductNode y = x.right;
        ProductNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;

        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    ProductNode insert(ProductNode node,
                       int id,
                       String name,
                       int stock) {

        if (node == null)
            return new ProductNode(id, name, stock);

        if (id < node.productId)
            node.left = insert(node.left, id, name, stock);

        else if (id > node.productId)
            node.right = insert(node.right, id, name, stock);

        else
            return node;

        node.height = 1 +
                Math.max(height(node.left),
                        height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && id < node.left.productId)
            return rightRotate(node);

        if (balance < -1 && id > node.right.productId)
            return leftRotate(node);

        if (balance > 1 && id > node.left.productId) {

            node.left = leftRotate(node.left);

            return rightRotate(node);
        }

        if (balance < -1 && id < node.right.productId) {

            node.right = rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }

    void inorder(ProductNode root) {

        if (root != null) {

            inorder(root.left);

            System.out.println(
                    "Product ID: " + root.productId +
                    " | Product Name: " + root.productName +
                    " | Stock: " + root.stock);

            inorder(root.right);
        }
    }
}

public class casestudy1 {

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        ProductNode root = null;

        root = tree.insert(root, 101, "T-Shirt", 50);
        root = tree.insert(root, 205, "Shoes", 30);
        root = tree.insert(root, 150, "Watch", 20);
        root = tree.insert(root, 120, "Bag", 40);
        root = tree.insert(root, 300, "Headphones", 15);

        System.out.println(
                "===== Meesho Product Inventory =====");

        tree.inorder(root);
    }
}