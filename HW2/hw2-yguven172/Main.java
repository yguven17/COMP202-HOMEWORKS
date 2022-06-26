import java.util.Random;

class Main {

    public static Random rand;
    public static int iterations = 10000;

    public static void main(String[] args) {
        System.out.println("=======================");
        for (int seed = 10; seed < 10000; seed *= 5) {
            rand = new Random(seed); 
            Tree gameTree = new Tree();
            for (int i = 0; i < iterations; i++) {
                Node move = gameTree.select();

                // If move is a node that does not have any possible moves, we should expand it
                if (move.getUnplayedMoves().size() > 0) {
                    move = move.expand();
                }
                int simulationResult = move.simulateGame();
                move.backPropagate(simulationResult);

                if (i % 10 == 0) {
                    gameTree.pruneTree(5);
                }
            }

            // Print the scores of the root and its children
            Node root = gameTree.root;
            System.out.println(root);

            for (int childIdx: root.getChildrenIdxs()) {
                Node child = root.getChildren()[childIdx];
                System.out.println(child);
            }
            System.out.println("=======================");
        }
    }
}
