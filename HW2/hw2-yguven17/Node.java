import java.util.ArrayList;
import java.util.Random;

/**
 * Class to represent a node in the tree used by the MCTS algorithm
 */
class Node {
    /**
     * The game board configuration for this tree node.
     */
    Board board;

    /**
     * Number of games that have passed this board configuration.
     */
    private int gamesPlayed;

    /**
     * Total score accumulated in all games that have passed this node.
     */
    private double totalScore;

    /**
     * Array storing the children of the current node.
     * Since there is a maximum number of 9 children in the tic-tac-toe game, an array of size 9 is used.
     * The initial values of the elements of the array are null, and are updated when children are added to this node.
     * The index of the child in this array corresponds to the index of the square where a move was made in the game board.
     */
    private Node[] children;
    
    /**
     * Reference to the parent of this node in the tree.
     */
    Node parent;

    private static double threshold = 0.4;
    public static Random rand = Main.rand;

    /**
     * Creates a node without setting its parent.
     * The board of this node is initialized as the initial state of the game.
     * Used to create the root node of the tree.
     */
    public Node() {
        this.parent = null;
        this.board = new Board();
        this.totalScore = 0.0;
        this.gamesPlayed = 0;
        this.setChildren(new Node[Board.size * Board.size]);
    }

    /**
     * Creates a node by specifying its parent and the board configuraion
     */
    public Node(Node parent, Board board) {
        this.parent = parent;
        this.board = board;
        this.totalScore = 0.0;
        this.gamesPlayed = 0;
        this.setChildren(new Node[Board.size * Board.size]);
    }

    public Node[] getChildren() {
        return children;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public void setChild(int idx, Node child) {
        this.children[idx] = child;
    }

    /**
     * Adds a child to the current node by choosing a random move from the list of possible moves.
     * The index of the child in the children array corresponds to the index of the square chosen by the random move.
     *
     * Note: This method is always called after the select method. This guarantees that there will always be a possible move
     * for the current node.
     *
     * @return reference to the newly created node
     */
    public Node expand() {
        ArrayList<Integer> unplayedMoves = this.getUnplayedMoves();

        // System.out.println(unplayedMoves.size());
        int idx = (int) Math.floor(rand.nextDouble() * unplayedMoves.size());

        Board newBoard = new Board(this.board);
        newBoard.makeMove(unplayedMoves.get(idx));
        Node newNode = new Node(this, newBoard);

        this.setChild(unplayedMoves.get(idx), newNode);
        return newNode;
    }

    public ArrayList<Integer> getUnplayedMoves() {
        Node[] allChildren = this.getChildren();
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < allChildren.length; i++) {
            if (allChildren[i] == null && this.board.isPossibleMove(i))
                indices.add(i);
        }
        return indices;
	}

	/**
     * Retrieves the indices in the children array of the current node that are not finished game configurations.
     * @return array list containing the indices in the children array of unfinished nodes
     */
    public ArrayList<Integer> getChildrenIdxs() {
        Node[] allChildren = this.getChildren();
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < allChildren.length; i++) {

            Node child = allChildren[i];

            if (child != null && child.board.isFinished() == 0)
                indices.add(i);
        }
        return indices;
    }

    /**
     * Selects a child from the list of children given as parameter.
     * First generate a random number, if the number is smaller than a threshold select a child at random.
     * Otherwise if the number is greater than or equal to the threshold select the child with the best score.
     * @param children ArrayList of indices in the children array of the non-null children of this node
     * @return reference to the selected child node
     */
    public Node selectChild(ArrayList<Integer> children) {
        double randomNo = rand.nextDouble();

        if (randomNo < threshold) {
            int idx = (int) Math.floor(rand.nextDouble() * children.size());
            return this.children[children.get(idx).intValue()];
        }

        double maxScore = -Double.MAX_VALUE;
        Node bestChild = null;
        for (int i : children) {
            Node child = this.children[i];
            double currScore = child.getScore();
            if (currScore > maxScore) {
                maxScore = currScore;
                bestChild = child;
            }
        }

        return bestChild;
    }

    /**
     * Simulate the game until it is finished
     * @return the score of the simulated game depending on its result for player 1 (x):
     *          0 - draw
     *          1 - win
     *          -1 - loss
     */
    public int simulateGame() {
        int result = this.board.simulateGame();
        return result == 1 ? 0 : (result == 2 ? 1 : -1);
    }

    /**
     * Traverse the nodes in the path from this node to the root of the tree and update
     * the scores of each of them (inclusive: also update this node and the root).
     * For each of the nodes in the path, its total score should be
     * increased by the 'score' parameter given to the method, and the number of games played should
     * be incremented by one.
     * @param score the score by which to update the above mentioned nodes
     */
    public void backPropagate(int score) {
        /* TODO */
    	
    	Node traver=this;
    	
        while(traver != null){
        	traver.gamesPlayed++;
        	traver.totalScore+=score;
        	traver=traver.parent;
        	
        }
    }

    /**
     * Gets the average score of this node by dividing its total score to the number of the games played
     * @return the average score of the node
     */
    public double getScore() {
        return this.totalScore / this.gamesPlayed;
    }

    public int getGamesPlayed() {
        return this.gamesPlayed;
    }

    /**
     * Removes the child node in the specified index from the list of children
     */
    public void removeChild(int index) {
        this.children[index] = null;
    }

    @Override
    public String toString() {
        return "{ totalScore: " + this.totalScore + ", gamesPlayed: " + this.gamesPlayed + " }";
    }
}
