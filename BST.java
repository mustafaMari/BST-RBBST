
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;



public class BST<T> {
	class Node {
		T value;
		Node left;
		Node right;
		Node parent;
		boolean black = true; // color[x] := RED

		Node(T value) {
			this.value = value;
		}

		Node(T value, Node right, Node left, Node parent) {
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

		@Override
		public String toString() {

			String string = "";
			string += value.toString() + ",";
			// string += "value = " + value;
			// string += ", parent=" + (parent != null ? parent.value : "null");
			// string += ", right=" + (right != null ? right.value : "null");
			// string += ", left=" + (left != null ? left.value : "null");
			return string;

		}
	}

	private final Comparator<T> comparator;
	private Node root;

	BST(Comparator<T> comparator) {
		this.comparator = comparator;
		root = null;
	}

	private int noNodesLeft = 0;
	private int noNodesRight = 0;
	private int noLeafsLeft = 0;
	private int noLeafsRight = 0;

	// 1.a) find maximum
	public T max() {
		return find(max(root).value);
	}

	private Node max(Node node) {
//		while (node.right != null) {
//			node = node.right;
//		}
		if (node.right != null) {
			node = max(node.right);
		}
		return node;
	}

	private T find(T elem) {
		Node node = search(elem);
		return node == null ? null : node.value;
	}

	private Node search(T elem) {
		Node node = root;
		int cmp = 0;
		while (node != null && (cmp = comparator.compare(elem, node.value)) != 0) {
			node = cmp < 0 ? node.left : node.right;
		}
		return node;
	}

	// 1.b)find minimum
	public T min() {
		return min(root).value;
	}

	private Node min(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	// 2.a) find successor
	public T findSuccessor(T elem) {
		// case 1
		if (elem == max())
			return null;
		if (!exist(elem))
			return null;
		return findSuccessor(search(elem)).value;
	}

	public boolean exist(T elem) {
		if (search(elem) != null)
			return true;
		else
			return false;
	}

	private Node findSuccessor(Node node) {
		Node successor = null;
		// case 2:
		if (node.right != null)
			return min(node.right);
		// case 3:
		successor = node.parent;
		while (successor != null && node == successor.right) {
			node = successor;
			successor = successor.parent;
		}
		return successor;
	}

	// 2.b) find predecessor
	public T findPredecessor(T elem) {
		// case 1
		if (elem == min())
			return null;
		if (!exist(elem))
			return null;
		return findPredecessor(search(elem)).value;
	}

	private Node findPredecessor(Node node) {
		Node predecessor = null;
		// case 2:
		if (node.left != null)
			return max(node.left);
		// case 3:
		predecessor = node.parent;
		while (predecessor != null && node == predecessor.left) {
			node = predecessor;
			predecessor = predecessor.parent;
		}
		return predecessor;
	}

	// 3 insert
	public boolean add(T elem) {
		root = insert(root, elem);
		return true;
	}

	private Node insert(Node node, T elem) {
		if (node == null)
			return new Node(elem);
		else {
			int comp = comparator.compare(elem, node.value);
			if (comp > 0) {
				node.right = insert(node.right, elem);
				node.right.parent = node;
			} else if (comp < 0) {
				node.left = insert(node.left, elem);
				node.left.parent = node;
			}
		}
		return node;
	}

	// 4 remove
	public T remove(T elem) {
		return delete(elem, root).value;
	}

	protected Node delete(T elem, Node node) {
		if (node == null)
			throw new NoSuchElementException();
		else {
			int comp = comparator.compare(elem, node.value);
			if (comp < 0)
				node.left = delete(elem, node.left);
			else if (comp > 0)
				node.right = delete(elem, node.right);
			else if (node.left != null && node.right != null)
				node.right = detachMin(node, node.right);
			else
				node = (node.left != null) ? node.left : node.right;
		}
		return node;
	}

	private Node detachMin(Node del, Node node) {
		if (node.left != null)
			node.left = detachMin(del, node.left);
		else {
			del.value = node.value;
			node = node.right;
		}
		return node;
	}

	public String inOrderWalk() {
		return removeComma(inOrderWalk(root));
	}

	private String inOrderWalk(Node node) {
		String sr = "";
		if (node != null) {
			sr += inOrderWalk(node.left);
			sr += node.toString() + " ";
			sr += inOrderWalk(node.right);
		}
		return sr;
	}

	public String postOrderWalk() {
		return removeComma(postOrderWalk(root));
	}

	private String postOrderWalk(Node node) {
		String sr = "";
		if (node != null) {
			postOrderWalk(node.left);
			postOrderWalk(node.right);
			sr += node.value + " ";
		}
		return sr;
	}

	public String preOrderWalk() {
		return removeComma(preOrderWalk(root));
	}

	private String preOrderWalk(Node node) {
		String sr = "";
		if (node != null) {
			sr += node.value + " ";
			preOrderWalk(node.left);
			preOrderWalk(node.right);
		}
		return sr;
	}

	private String removeComma(String string) {

		if (string.length() < 2)
			return string;
		return string.substring(0, string.length() - 2);

	}

	public int height1(Node node) {
		return height(node);
	}

	private int height(Node node) {
		if (node != null) {
			int rightHeight = height(node.right);
			int leftHeight = height(node.left);
			if (rightHeight > leftHeight)
				return rightHeight + 1;
			else
				return leftHeight + 1;
		} else
			return 0;
	}

	private int noLeafsLeft(Node node) {
		if (node != null) {
			if (node.left == null && node.right == null) {
				noLeafsLeft++;
			}
			noLeafsLeft(node.left);
			noLeafsLeft(node.right);
		}
		return noLeafsLeft;
	}

	private int noLeafsRight(Node node) {
		if (node != null) {
			if (node.left == null && node.right == null) {
				noLeafsRight++;
			}
			noLeafsRight(node.left);
			noLeafsRight(node.right);
		}
		return noLeafsRight;
	}

	private int noNodesLeft(Node node) {
		if (node != null) {
			noNodesLeft(node.left);
			noNodesLeft(node.right);
			noNodesLeft++;
		}
		return noNodesLeft;
	}

	private int noNodesRight(Node node) {
		if (node != null) {
			noNodesRight(node.right);
			noNodesRight(node.left);
			noNodesRight++;
		}
		return noNodesRight;
	}

	public void characteristics() {
		System.out.println("The number of nodes in the left subtree: " + noNodesLeft(root.left));
		System.out.println("The number of nodes in the right subtree: " + noNodesRight(root.right));
		System.out.println("The number of the leafs in the left subtree: " + noLeafsLeft(root.left));
		System.out.println("The number of the leafs in the right subtree: " + noLeafsRight(root.right));
		System.out.println("The height of the tree is: " + (height(root)));
	}

	public void insertRB(T value) {
		insertRB(new Node(value));
	}

	public void insertRB(Node node) {
		add(node.value); // Tree_Insert(root,x)
		node = search(node.value);
		while (node != root && !node.parent.black) { // while x <> root and color[p[x]] = RED do
			if (node.parent == node.parent.parent.left) { // if p[x] = left[p[p[x]]] then
				Node y = node.parent.parent.right; // y := right[p[p[x]]]
				if (!y.black && y != null) { // if color[y] = RED then
					node.parent.black = true; // color[p[x]] := BLACK
					y.black = true; // color[y] := BLACK
					node.parent.parent.black = false; // color[p[p[x]]] := RED
					node = node.parent.parent; // x := p[p[x]]
				} else if (node == node.parent.right) { // if x = right[p[x]] then
					node = node.parent; // x := p[x]
					rotateLeft(node); // Left_Rotate(root,x)
				} else {
//				node.parent.color = "BLACK";
//				node.parent.parent.color = "RED";
					node.parent.black = true; // color[p[x]] := BLACK
					node.parent.parent.black = false; // color[p[p[x]]] := RED
					rotateRight(node.parent.parent); // Right_Rotate(root, p[p[x]])
				}
			} else // exchanging between "right" and "left"
			{
				Node anNode = node.parent.parent.left;
				if (!anNode.black && anNode != null) {
					node.parent.black = true;
					anNode.black = true;
					node.parent.parent.black = false;
					node = node.parent.parent;
				} else {
					if (node == node.parent.left) {
						node = node.parent;
						rotateRight(node);
					} else {
						node.parent.black = true;
						node.parent.parent.black = false;
						rotateLeft(node.parent.parent);
					}
				}
			}
		}
		root.black = true;
	}

	public void rotateLeft(Node node) {

		Node other = node.right;
		node.right = other.left;
		if (other.left != null)
			other.left.parent = node;
		other.parent = node.parent;
		if (node.parent == null)
			root = other;
		else if (node == node.parent.left)
			node.parent.left = other;
		else
			node.parent.right = other;
		other.left = node;
		node.parent = other;

	}

	public void rotateRight(Node node) {

		Node other = node.left;
		node.left = other.right;
		if (other.right != null)
			other.right.parent = node;
		other.parent = node.parent;
		if (node.parent == null)
			root = other;
		else if (node == node.parent.right)
			node.parent.right = other;
		else
			node.parent.left = other;
		other.right = node;
		node.parent = other;

	}
	public void printNaturalForm() {
        int width = 32;
        printLevels(root, width);
    }

    private void printLevels(Node node, int width) {
        if (node == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (true) {
            int levelNodeCount = queue.size();

            if (levelNodeCount == 0) {
                break;
            }

            StringBuilder levelAsString = new StringBuilder();
            while (levelNodeCount > 0) { // until we have read this entire level
                Node currentNode = queue.poll();

                String valToString = currentNode == null ? "X " : currentNode.value + " ";
                String valStr = " ";
                levelAsString.append(valStr);

                if (currentNode != null) {
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);
                }

                levelNodeCount--;
            }
            width /= 2;
            System.out.printf("%s%n", levelAsString.toString());
        }
    }

}


