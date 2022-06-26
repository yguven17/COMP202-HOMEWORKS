import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing the game tree
 */
public class Tree {

	public Node root;
	private static double threshold = 0.4;
	public static Random rand = Main.rand;

	/**
	 * Default constructor for the tree. The root of the tree is set to the initial
	 * configuration of the tic-tac-toe game
	 */
	public Tree() {
		this.root = new Node();
	}

	/**
	 * Selects a leaf node or a node with non-empty list of possible moves.
	 *
	 * - Starting from the tree root, gets the list of non-null children of the
	 * current node by using getChildrenIdxs() method. - For each child: - If the
	 * child is an unfinished game i.e. has unplayed moves (use
	 * node.getUnplayedMoves() method): - Generate a random number between 0 and 1
	 * (1 excluded) - If the generated number is greater than the tree threshold
	 * return the current node - Otherwise select a child of the current node using
	 * node.selectChild() method - Repeat the above steps until you either arrive at
	 * a leaf node (number of children is 0)
	 * 
	 * @return the selected node
	 */
	public Node select() {
		/* TODO */
		Node current = root;
		ArrayList<Integer> indices = new ArrayList<>();
		while (!current.getChildrenIdxs().isEmpty()) {
			indices = current.getChildrenIdxs();
			if (!current.getUnplayedMoves().isEmpty()) {
				double randomvalue = rand.nextDouble();
				if (randomvalue > threshold) {
					return current;
				}
			}
			current = current.selectChild(indices);
		}

		return current;
	}

	/**
	 * Removes the worst performing node at the specified level in the tree if the
	 * level is not empty
	 * 
	 * @param level the level from which to remove the worst performing node
	 */
	public void pruneTree(int level) {
		ArrayList<Node> parents = this.getNodesAtLevel(level - 1);

		Node worstNodeParent = null;
		int worstScoreChildIndex = 0;
		double worstScore = Double.MAX_VALUE;
		double currentChildScore;

		for (Node parent : parents) {
			for (int i = 0; i < parent.getChildren().length; i++) {
				Node child = parent.getChildren()[i];
				if (child != null) {
					currentChildScore = child.getScore();
					if (currentChildScore < worstScore) {
						worstScore = currentChildScore;
						worstNodeParent = parent;
						worstScoreChildIndex = i;
					}
				}
			}
		}

		if (worstNodeParent == null)
			return;
		worstNodeParent.removeChild(worstScoreChildIndex);
	}

	/**
	 * Retrieves the nodes at the specified level in the tree. Root is at level 0
	 * 
	 * @param level the level required
	 * @return ArrayList of nodes in the specified level
	 */
	public ArrayList<Node> getNodesAtLevel(int level) {
		/* TODO */

		ArrayList<Node> nodeList = new ArrayList<>();

		if (level == 0) {
			nodeList.add(root);
			return nodeList;
		} else {

			Node searching = root;
			nodeFinder(searching, level, nodeList);
		}

		return nodeList;
	}

	public ArrayList<Node> nodeFinder(Node node, int level, ArrayList<Node> nodeList) {

		if (node == null) {
			return nodeList;
		}
		if (level == 0) {
			nodeList.add(node);

		} else {
			Node[] children = node.getChildren();
			for (int i = 0; i < children.length; i++) {
				node = children[i];
				nodeFinder(node, level - 1, nodeList);
			}
		}
		
		return nodeList;
	}

	public String toString() {
		return root.toString();
	}
}
