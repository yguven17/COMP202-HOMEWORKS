import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to represent a state in the tic-tac-toe game
 */
class Board {
    /**
     * Array to save the contents of the board.
     * There are 3 possible values for each element:
     *  ' ' (space character) - empty block in the board
     *  'x' - x (mark set by player 1)
     *  'o' - o (mark set by player 2)
     */
    private char[] board;
    static final int size = 3;

    /**
     * Boolean to specify which player's will play next.
     * Since it is a two player game boolean is used.
     */
    private boolean turn;

    /**
     * Creates a board for the start of the game, no move is made
     */
    public Board() {
        this.board = new char[size * size];
        this.turn = true;
        Arrays.fill(board, ' ');
    }

    /**
     * Copy constructor to create a board from another point in the game
     * where some moves may have been made
     * @param other The other board to copy its configuration from
     */
    public Board(Board other) {
        this.board = other.board.clone();
        this.turn = other.turn;
    }

    /**
     * Makes a random move for the player specified by 'turn'.
     * One of the empty squares will be filled by either 'x' or 'o'
     * @return The index of the square which was filled, -1 if the random chosen index is alredy filled
     */
    public int makeRandomMove() {
        ArrayList<Integer> emptyIdxs = this.getPossibleMoves();
        int emptyIdxsNo = emptyIdxs.size();

        if (emptyIdxsNo == 0) return -1;

        final int randIdx = emptyIdxs.get((int) Math.floor(Node.rand.nextDouble() * emptyIdxsNo));
        return makeMove(randIdx);
    }

    /**
     * Mark the square specified by 'idx' in the board depending on the players turn
     * @param idx The index of the square to be marked
     * @return idx, or -1 if the square is already marked
     */
    public int makeMove(int idx) {
        char mark = turn ? 'x' : 'o';
        board[idx] = mark;
        turn = !turn;
        return idx;
    }

    /**
     * Checks if the square at the specified index is already marked
     * @param idx the index in the board to check
     * @return true if the square can be marked, false otherwise
     */
    public boolean isPossibleMove(int idx) {
        return this.board[idx] == ' ';
    }

    /**
     * Get the list of possible moves for the current configuration of the game
     * @return array list containing the indices of empty squares in the board
     */
    public ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        for (int i = 0; i < this.board.length; i++) {
            if (this.isPossibleMove(i))
                moves.add(i);
        }
        return moves;
    }

    /**
     * Checks if the game specified by the state of the board is finished
     * @return  Returns the following integers depending on the state of the game:
     *          0 - unfinished game
     *          1 - draw
     *          2 - x won
     *          3 - o won
     */
    public int isFinished() {
        int winner = 0;

        ArrayList<int[]> wins = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int[] row = {i * size, i * size + 1, i * size + 2};
            int[] col = {i, i + 1 * size, i + 2 * size};
            wins.add(row);
            wins.add(col);
        }
        int[] diag1 = {0, 4, 8};
        int[] diag2 = {2, 4, 6};
        wins.add(diag1);
        wins.add(diag2);

        boolean won = false;
        for (int[] win : wins) {
            if (board[win[0]] != ' ' && board[win[0]] == board[win[1]] && board[win[0]] == board[win[2]]) {
                winner = board[win[0]] == 'x' ? 2 : 3;
                won = true;
                break;
            }
        }

        // check if there is a draw
        if (!won) {
            int i = 0;
            while (i < size * size && board[i] != ' ')
                i++;
            if (i >= size * size) winner = 1;
        }

        return winner;
    }

    /**
     * Makes random moves by both players until the game is finished or is drawn
     * @return Returns the winner of the game as follows:
     *          1 - draw
     *          2 - x won (player 1)
     *          3 - o won (player 2)
     */
    public int simulateGame() {
        Board temp = new Board(this);

        int result = temp.isFinished();
        while (result < 1) {

            if (temp.makeRandomMove() == -1) break;

            result = temp.isFinished();
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result += " " + board[i * size + j] + " ";
                if (j < size - 1) result += '|';
            }
            if (i < size -1) result += "\n---|---|---";
            result += "\n";
        }
        return result;
    }
}
